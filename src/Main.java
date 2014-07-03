import java.io.*;
import java.util.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import model.Field;
import model.Table;

public class Main {

	final static String directoryPath = "inputs";

	final static String PARSER_STYLE_START = Utils.tabGen(1)
			+ "public static ArrayList<xaxContentValues> parseJsonxax(JSONArray joArray, Context ctxt) { \n"
			+ Utils.tabGen(2) + "ArrayList<xaxContentValues> list = new ArrayList<xaxContentValues>(); \n"
			+ Utils.tabGen(2) + "try { \n" + Utils.tabGen(3) + "xaxContentValues val; \n" + Utils.tabGen(3)
			+ "for (int i = 0; i < joArray.length(); i++) { \n" + Utils.tabGen(4)
			+ "JSONObject joInside = joArray.getJSONObject(i);\n" + Utils.tabGen(4) + "val = new xaxContentValues();";
	final static String PARSER_STYLE_START_ID = Utils.tabGen(1)
			+ "public static ArrayList<xaxContentValues> parseJsonxax(JSONArray joArray, Context ctxt, int extId) { \n"
			+ Utils.tabGen(2) + "ArrayList<xaxContentValues> list = new ArrayList<xaxContentValues>(); \n"
			+ Utils.tabGen(2) + "try { \n" + Utils.tabGen(3) + "xaxContentValues val; \n" + Utils.tabGen(3)
			+ "for (int i = 0; i < joArray.length(); i++) { \n" + Utils.tabGen(4)
			+ "JSONObject joInside = joArray.getJSONObject(i);\n" + Utils.tabGen(4) + "val = new xaxContentValues();";
	final static String PARSER_STYLE_END = Utils.tabGen(4) + "list.add(val);\n" + Utils.tabGen(3) + "} \n"
			+ Utils.tabGen(2) + "} catch (JSONException e) {\n" + Utils.tabGen(3) + "e.printStackTrace();\n"
			+ Utils.tabGen(2) + "} \n" + Utils.tabGen(2) + "return list;\n}";

	final static String PARSER_STYLE_START_BASIC = Utils.tabGen(1)
			+ "public static ArrayList<xax> parseJson(JSONArray joArray, Context ctxt) { \n" + Utils.tabGen(2)
			+ "ArrayList<xax> list = new ArrayList<xax>(); \n" + Utils.tabGen(2) + "try { \n" + Utils.tabGen(3)
			+ "xax val; \n" + Utils.tabGen(3) + "for (int i = 0; i < joArray.length(); i++) {\n" + Utils.tabGen(4)
			+ "JSONObject joInside = joArray.getJSONObject(i);\n" + Utils.tabGen(4) + "val = new xax();";
	final static String PARSER_STYLE_END_BASIC = Utils.tabGen(3)
			+ "list.add(val);\n	} \n	} catch (JSONException e) {\n		e.printStackTrace();\n	} \n	return list;\n}";

	final static String EXTRACT_URI_METHOD = Utils.tabGen(1)
			+ "public static Pattern pattern = Pattern.compile(\"\\d+\");\n" + Utils.tabGen(1)
			+ "public static String extractFromQuote(String str) {\n" + Utils.tabGen(2)
			+ "String s = str.substring(1, str.length() - 1);\n" + Utils.tabGen(2) + "return s;\n	}\n\n"
			+ Utils.tabGen(1) + "public static int extractIdFromUri(String uri) {\n" + Utils.tabGen(2)
			+ "uri = extractFromQuote(uri);\n" + Utils.tabGen(2) + "Matcher m = pattern.matcher(uri);\n"
			+ Utils.tabGen(2) + "if (m.find()) {\n" + Utils.tabGen(3) + "System.out.println(m.group(0));\n"
			+ Utils.tabGen(2) + "}\n" + Utils.tabGen(2) + "int id = Integer.valueOf(m.group(0));\n" + Utils.tabGen(2)
			+ "return id;\n" + Utils.tabGen(1) + "}";

	final static String JUNCTION_TABLE = "junction table";

	public static ArrayList<Table> junctionTables;
	public static ArrayList<Table> tables;
	public static ArrayList<Field> currentTableFields;
	public static ArrayList<Field> primaryKeys;

	public static String currentObj = "obj";
	public static int deepness = 0;
	public static ArrayList<String> tempTree = new ArrayList<String>();

	public static void main(String[] args) {
		System.out.println("START");
		primaryKeys = new ArrayList<Field>();
		tables = new ArrayList<Table>();
		junctionTables = new ArrayList<Table>();

		File file = new File(directoryPath);
		for (File f : loadFiles(file)) {
			// try {
			System.out.println("######## " + f.getName() + " FOUND ########");
			System.out.println("NEW PARSING");

			currentObj = f.getName().replace(".json", "");
			if (!tableAlreadyExists((String) f.getName())) {
				Table t = new Table(currentObj, Utils.getNameCamelCase(currentObj), "origin", null);
				tables.add(t);
			}
			expParserFile(f);
		}

		ArrayList<String> generated = new ArrayList<String>();
		System.out.println("######## GENERATED PARSERS ########");
		for (Table t : tables) {
			if (t != null && t.getFields().size() > 0) {
				generated.addAll(generateJavaParser(t));
			}
		}

		if (generated.size() > 0) {
			String[] data = generated.toArray(new String[generated.size()]);
			writeFile("JsonParsers.java", data);
			generated.clear();
		}

		System.out.println("######## GENERATED JSON ########");
		for (Table t : tables) {
			if (t != null) {
				generated = generateTableJson(t);
				if (generated != null) {
					// for (String string : generated) {
					// System.out.println(string);
					// }
					if (generated.size() > 0) {
						String[] data = generated.toArray(new String[generated.size()]);
						writeFile(t.getOriginalName() + ".json", data);
						generated.clear();
					}
				}
			}
		}

		System.out.println("######## GENERATED JAVA BEANS ########");
		for (Table t : tables) {
			if (t != null) {
				generated = generateJavaBean(t);
				if (generated != null) {
					for (String string : generated) {
						System.out.println(string);
					}
				}
				if (generated != null && generated.size() > 0) {
					String[] data = generated.toArray(new String[generated.size()]);
					writeFile(t.getName() + ".class", data);
					generated.clear();
				}
			}
		}

		System.out.println("END");
		System.out.println("TOTAL tables = " + tables.size());
		for (Table t : tables) {
			System.out.println(t.getName() + " contains " + t.getFields().size());
		}
		System.out.println("TABLES DETAILS");
		for (Table t : tables) {
			System.out.println(t.toString());
		}
	}

	public static String convertSqlTypeToJava(String type) {
		if (type.equalsIgnoreCase("TEXT")) {
			type = "String";
		} else if (type.startsWith("VARCHAR")) {
			type = "String";
		} else if (type.startsWith("INTEGER")) {
			type = "Integer";
			// } else if (type.startsWith("INTEGER")) {
			// type = "Long";
		} else if (type.startsWith("FLOAT")) {
			type = "Float";
		} else if (type.startsWith("REAL")) {
			type = "Float";
			// } else if (type.startsWith("REAL")) {
			// type = "Double";
		} else if (type.startsWith("BOOL")) {
			type = "Boolean";
		} else if (type.startsWith("DATE")) {
			type = "Date";
		} else if (type.startsWith("BLOB")) {
			type = "byte[]";
			// } else if (type.startsWith("INTEGER")) {
			// type = "enum";
		} else if (type.startsWith("TIMESTAMP")) {
			type = "Date";
		}

		return type;
	}

	public static void writeFile(String name, String[] lines) {
		BufferedWriter writer = null;
		try {
			// create a temporary file
			// String timeLog = new
			// SimpleDateFormat("ddMMyyyy_HHmmss").format(Calendar.getInstance().getTime());
			// File logFile = new File("export/" + name + "_" + timeLog);
			File logFile = new File("export/" + name);

			// This will output the full path where the file will be written
			// to...
			System.out.println(logFile.getCanonicalPath());
			writer = new BufferedWriter(new FileWriter(logFile));

			String line;
			for (int i = 0; i < lines.length; i++) {
				line = String.format("%s \n", lines[i]);
				writer.write(line);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// Close the writer regardless of what happens...
				writer.flush();
				writer.close();
			} catch (Exception e) {
			}
		}
	}

	// Load files into prog
	public static File[] loadFiles(File directoryPath) {
		File[] entityFiles = directoryPath.listFiles(new FileFilter() {
			@Override
			public boolean accept(File pathname) {
				return !pathname.getName().startsWith("_") && pathname.getName().endsWith(".json");
			}
		});
		return entityFiles;
	}

	public static void expParserFile(File file) {
		if (file == null) {
			return;
		}
		JSONParser parser = new JSONParser();
		try {
			Object obj = parser.parse(new FileReader(file));
			JSONObject jsonObject = null;
			if (obj instanceof JSONArray) {
				JSONArray jsonArray = (JSONArray) obj;
				jsonObject = (JSONObject) jsonArray.get(0);

			} else if (obj instanceof JSONObject) {
				jsonObject = (JSONObject) obj;
			} else {
				return;
			}

			tables = new ArrayList<Table>();
			currentTableFields = new ArrayList<Field>();
			parseJsonObject(jsonObject, currentObj, null);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param key
	 *            String : fieldname
	 * @param obj
	 *            Object : content value used to determined its type
	 * @param inkey
	 *            String : field's table
	 * @return created field
	 */
	public static Field createField(String key, Object obj, String inkey, String constraint) {
		Field field;
		String type = Utils.javaTypeResolver(obj);

		if (type != null && (type.equalsIgnoreCase(Utils.URI) || type.equalsIgnoreCase(Utils.JUNCTION))
				&& Utils.isTagAllowed(key)) {
			if (constraint == null) {
				constraint = Utils.extractTableFromUri((String) obj);
			}
			// System.out.println("createField key = " + key + " & obj = " + obj
			// + " inkey = " + inkey + " constraint = "
			// + constraint);
			Table junc = createJunctionTable(inkey, constraint);
			if (junc != null) {
				tables.add(junc);
				junctionTables.add(junc);
			}

			if (type.equalsIgnoreCase(Utils.JUNCTION)) {
				Table t = getTableWithName(constraint);
				if (t != null) {
					System.out.println("CALLER added + " + inkey + "FkId");
					t.getFields().add(new Field(inkey + "FkId", inkey + "FkId", Utils.CALLER, inkey));
				}
			}
		}

		if (type == null) {
			type = Utils.INT;
		}

		field = new Field(key, Utils.getNamePascalCase(key), type, constraint);

		Table t = getTableWithName(inkey);
		ArrayList<Field> fs = t.getFields();
		if (fs == null) {
			fs = new ArrayList<Field>();
		}
		fs.add(field);
		t.setFields(fs);

		return field;
	}

	/**
	 * Parse your JSONObject and fill the tables array.
	 * 
	 * @param obj
	 *            JSONObject : you're about to parse
	 * @param currentObjName
	 *            String : the name of obj
	 * @param exObjName
	 *            String : the name of the object containing obj, ex : "recipes"
	 *            contains "variants"
	 */
	public static void parseJsonObject(JSONObject obj, String currentObjName, String exObjName) {

		Table currentTable;
		String key;
		Object aObj;
		boolean exObjAlreadySet = false;

		if (!tableAlreadyExists(currentObjName)) {
			currentTable = new Table(currentObjName, Utils.getNameCamelCase(currentObjName), null, null);
			tables.add(currentTable);
		}

		Iterator iter = obj.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry entry = (Map.Entry) iter.next();
			key = (String) entry.getKey();
			aObj = obj.get(key);

			if (exObjName != null && !exObjAlreadySet) {
				createField(exObjName + "FkId", Utils.JUNCTION, currentObjName, exObjName);
				exObjAlreadySet = true;
			}

			if (aObj instanceof JSONObject) {
				System.out.println("IN " + currentObjName + " # OBJ : " + key);
				parseJsonObject((JSONObject) aObj, key, currentObjName);
			} else if (aObj instanceof JSONArray) {

				JSONArray array = (JSONArray) aObj;
				System.out.println("IN " + currentObjName + " # ARRAY : " + key);
				if (array.size() > 0) {
					Object bObj = array.get(0);

					if (bObj instanceof JSONObject) {
						parseJsonObject((JSONObject) bObj, key, currentObjName);
					} else {
						createField(key, aObj, currentObjName, null);
					}
				}

			} else {
				createField(key, aObj, currentObjName, null);
			}
		}
	}

	public static Table createJunctionTable(String refTable, String extTable) {
		String tName = Utils.createJunctionTableName(refTable, extTable);
		if (!tableAlreadyExists(tName)) {
			ArrayList<Field> fs = new ArrayList<Field>();
			Field f1 = new Field(refTable + "_id", Utils.getNamePascalCase(refTable) + "Id", Utils.INT, tName);
			Field f2 = new Field(extTable + "_id", Utils.getNamePascalCase(extTable) + "Id", Utils.INT, tName);
			fs.add(f1);
			fs.add(f2);
			Table juncTable = new Table(tName, Utils.getNameCamelCase(tName), JUNCTION_TABLE, fs);
			return juncTable;
		}
		return null;
	}

	public static ArrayList<String> generateJavaParser(Table table) {
		if (table == null || table.getFields().size() == 0) {
			return null;
		}
		// used to avoid duplicate var name when two different attributes
		// reference the same table, ex : "Actions" contains "CreatedBy" &
		// "ModifiedBy" which both ref to the table "Users"
		int externalRefCtr = 0;
		boolean arrayInited = false;
		boolean objectInited = false;
		ArrayList<String> javaOutput = new ArrayList<String>();
		if (table.getConstraint() != null && table.getConstraint().equalsIgnoreCase(JUNCTION_TABLE)) {
			return javaOutput;
		}
		final int nbOfTab = 4;
		String line = PARSER_STYLE_START.replaceAll("xax", table.getName());
		String endLine = "";
		javaOutput.add(line);
		line = "";
		for (Field f : table.getFields()) {
			String fieldName = f.getName();
			if (fieldName != null) {
				String type = f.getType();
				fieldName = Utils.getNameCamelCase(Utils.checkForbiddenName(fieldName));
				if (type == null || type.equalsIgnoreCase("null")) {
					line = Utils.tabGen(nbOfTab) + "val.put" + fieldName + "Null();";
				} else if (type.equalsIgnoreCase(Utils.INT)) {
					type = "Int";
					if (f.getName().equalsIgnoreCase("id")) {
						line = Utils.tabGen(nbOfTab) + "int id = " + "joInside.get" + type + "(\"" + f.getOrignalName()
								+ "\");\n";
						line += Utils.tabGen(nbOfTab) + "val.putIdDb(id);";

						// BUG somehow add a String at a given index duplicates
						// the preceding String so I'm erasing it.
						if (javaOutput.size() > 1) {
							// javaOutput.remove(1);
							javaOutput.add(1, line);
							line = null;
						}
					} else {
						line = Utils.tabGen(nbOfTab) + "val.put" + fieldName + "(joInside.get" + type + "(\""
								+ f.getOrignalName() + "\"));";
					}
				} else if (type.equalsIgnoreCase(Utils.DATE)) {
					line = Utils.tabGen(nbOfTab) + "val.put" + fieldName + "(Utils.convertToDate(joInside.getString(\""
							+ f.getOrignalName() + "\"), Utils.formats[6]));";
					// In case of an URI, we are linked to another table
				} else if (type.equalsIgnoreCase(Utils.URI) && f.getConstraint() != null) {

					// Filling junction tables;
					String jTableName = Utils.createJunctionTableName(table.getName(), f.getConstraint());
					final String jValName = Utils.getNamePascalCase(f.getConstraint()) + "cv" + externalRefCtr;
					externalRefCtr++;
					for (Table t : tables) {
						if (t.getOriginalName().equalsIgnoreCase(jTableName)) {
							jTableName = Utils.getNameCamelCase(jTableName);
							line = "\n" + Utils.tabGen(nbOfTab) + "int " + f.getName() + "Id = "
									+ "Utils.extractIdFromUri(joInside.getString(\"" + f.getOrignalName() + "\"));";
							line += "\n" + Utils.tabGen(nbOfTab) + "val.put" + fieldName + "(" + f.getName() + "Id"
									+ ");";
							line += "\n" + Utils.tabGen(nbOfTab) + jTableName + "ContentValues " + jValName + " = new "
									+ jTableName + "ContentValues();";
							// line += "\n		" + jValName + ".putIdDbNull();";
							line += "\n" + Utils.tabGen(nbOfTab) + jValName + ".put"
									+ Utils.getNameCamelCase(t.getFields().get(0).getName()) + "(id);";
							line += "\n" + Utils.tabGen(nbOfTab) + jValName + ".put"
									+ Utils.getNameCamelCase(t.getFields().get(1).getName()) + "(" + f.getName() + "Id"
									+ ");";
							line += "\n" + Utils.tabGen(nbOfTab) + "ctxt.getContentResolver().insert(" + jTableName
									+ "Columns.CONTENT_URI, " + jValName + ".values());\n";
							javaOutput.add(line);
							line = null;
						}
					}

				} else if (type.equalsIgnoreCase(Utils.URI) && f.getConstraint() == null) {

					line = Utils.tabGen(nbOfTab) + "val.put" + fieldName
							+ "(Utils.extractIdFromUri(joInside.getString(\"" + f.getOrignalName() + "\")));";

				} else if (type.equalsIgnoreCase(Utils.JUNCTION)) {

					// Filling junction tables with param from method and NOT
					// from json;
					String jTableName = Utils.createJunctionTableName(table.getOriginalName(), f.getConstraint());
					final String jValName = Utils.getNamePascalCase(f.getConstraint()) + "cv" + externalRefCtr;
					System.out.println("JsonParser JUNCTION field : " + f.getName() + " & jTableName = " + jTableName
							+ " & jValName = " + jValName);
					externalRefCtr++;
					for (Table t : tables) {
						if (t.getOriginalName().equalsIgnoreCase(jTableName)) {
							line = PARSER_STYLE_START_ID.replaceAll("xax", table.getName());
							javaOutput.set(0, line);
							jTableName = Utils.getNameCamelCase(jTableName);
							line = "\n" + Utils.tabGen(nbOfTab) + jTableName + "ContentValues " + jValName + " = new "
									+ jTableName + "ContentValues();";
							// line += "\n		" + jValName + ".putIdDbNull();";
							line += "\n" + Utils.tabGen(nbOfTab) + jValName + ".put"
									+ Utils.getNameCamelCase(t.getFields().get(0).getName()) + "(id);";
							line += "\n" + Utils.tabGen(nbOfTab) + jValName + ".put"
									+ Utils.getNameCamelCase(t.getFields().get(1).getName()) + "(extId);";
							line += "\n" + Utils.tabGen(nbOfTab) + "ctxt.getContentResolver().insert(" + jTableName
									+ "Columns.CONTENT_URI, " + jValName + ".values());\n";
						}
					}
				} else if (type.equalsIgnoreCase(Utils.OBJECT)) {
					if (!arrayInited) {
						line = Utils.tabGen(nbOfTab) + "JSONObject insideObj = joInside.getJSONObject(\""
								+ f.getOrignalName() + "\");";
						arrayInited = true;
					} else {
						line = Utils.tabGen(nbOfTab) + "insideObj = joInside.getJSONObject(\"" + f.getOrignalName()
								+ "\");";
					}
				} else if (type.equalsIgnoreCase(Utils.CALLER)) {
					String distTableName = f.getConstraint();
					endLine += "\n" + Utils.tabGen(nbOfTab) + "parseJson" + Utils.getNameCamelCase(distTableName)
							+ "(joInside.getJSONArray(\"" + distTableName + "\"), ctxt, id);";
				} else if (type.equalsIgnoreCase(Utils.ARRAY)) {
					if (!objectInited) {
						line = Utils.tabGen(nbOfTab) + "JSONArray insideArray = joInside.getJSONArray(\""
								+ f.getOrignalName() + "\");";
						javaOutput.add(line);
						line = Utils.tabGen(nbOfTab) + "ArrayList<String> ls = new ArrayList<String>();\n"
								+ Utils.tabGen(nbOfTab) + "for (int j = 0; j < insideArray.length(); j++) {\n"
								+ Utils.tabGen(3) + "ls.add(insideArray.getString(j));\n" + Utils.tabGen(nbOfTab) + "}";
						objectInited = true;
					} else {
						line = Utils.tabGen(nbOfTab) + "insideArray = joInside.getJSONArray(\"" + f.getOrignalName()
								+ "\");";
						javaOutput.add(line);
						line = Utils.tabGen(nbOfTab) + "ls = new ArrayList<String>();\n" + Utils.tabGen(nbOfTab)
								+ "for (int j = 0; j < insideArray.length(); j++) {\n" + Utils.tabGen(nbOfTab)
								+ "ls.add(insideArray.getString(j));\n" + Utils.tabGen(nbOfTab) + "}";
					}
				} else if (f.getConstraint() != null) {
					line = Utils.tabGen(nbOfTab) + "val.put" + fieldName + "(insideObj.get" + type + "(\""
							+ f.getOrignalName() + "\"));";
				} else {
					if (type.equalsIgnoreCase(Utils.BOOL)) {
						type = "Boolean";
					}

					line = Utils.tabGen(nbOfTab) + "val.put" + fieldName + "(joInside.get" + type + "(\""
							+ f.getOrignalName() + "\"));";
				}
				if (line != null && !line.isEmpty()) {
					javaOutput.add(line);
					line = null;
				}
			}
		}
		line = Utils.tabGen(nbOfTab) + "ctxt.getContentResolver().insert(" + table.getName()
				+ "Columns.CONTENT_URI, val.values());";

		if (!endLine.isEmpty()) {
			javaOutput.add(endLine);
			endLine = "";
		}
		javaOutput.add(line);
		javaOutput.add(PARSER_STYLE_END + "\n");
		return javaOutput;
	}

	public static ArrayList<String> generateTableJson(Table table) {
		if (table == null || table.getFields().size() == 0)
			return null;

		ArrayList<String> javaOutput = new ArrayList<String>();
		String line;
		// javaOutput.add(table.getName());
		javaOutput.add("{");
		javaOutput.add(Utils.tabGen(1) + "\"fields\": [");
		ArrayList<Field> fields = table.getFields();
		for (Field f : fields) {
			String type = Utils.convertToDBType(f.getType());
			if (type.equalsIgnoreCase(Utils.URI)) {
				type = Utils.DB_INT;
			}
			if (f.getName() != null && f.getType() != Utils.OBJECT && f.getType() != Utils.JUNCTION
					&& f.getType() != Utils.CALLER) {
				javaOutput.add(Utils.tabGen(2) + "{");
				line = Utils.tabGen(3) + "\"name\": \"" + Utils.checkForbiddenName(f.getOrignalName()) + "\",";
				javaOutput.add(line);
				line = Utils.tabGen(3) + "\"type\": \"" + type + "\"";
				javaOutput.add(line);
				if (fields.indexOf(f) != fields.size() - 1) {
					line += ",";
					javaOutput.add(Utils.tabGen(2) + "},");
				} else {
					javaOutput.add(Utils.tabGen(2) + "}");
				}
			}
		}
		javaOutput.add(Utils.tabGen(1) + "]");
		javaOutput.add("}");
		return javaOutput;
	}

	public static ArrayList<String> generateJavaBean(Table table) {
		if (table == null || table.getFields().size() == 0)
			return null;

		ArrayList<String> javaOutput = new ArrayList<String>();
		String fieldName;
		String fieldNameMethod;
		String fieldType;
		String line;
		// used to reduce the amount of necessary loop
		String line2 = "\n" + Utils.tabGen(1) + "public " + table.getName() + "(";
		javaOutput.add("\npublic class " + table.getName() + " {");
		ArrayList<Field> fields = table.getFields();
		// Field id = new Field("_id", "id", "long", null);
		// fields.add(id);

		// attributes
		for (Field f : fields) {
			if (f != null) {
				fieldType = f.getType();
				if (!fieldType.equalsIgnoreCase(Utils.JUNCTION) && !fieldType.equalsIgnoreCase(Utils.CALLER)) {
					fieldName = Utils.getNamePascalCase(f.getName());
					if (f.getType() != Utils.OBJECT) {
						if (fieldType.equalsIgnoreCase(Utils.URI)) {
							fieldType = Utils.INT;
						}

						if (fieldType != Utils.ARRAY) {
							line = Utils.tabGen(1) + "private " + fieldType + " " + fieldName + ";";
						} else {
							line = Utils.tabGen(1) + "private ArrayList<Integer>" + fieldName + ";";
						}
						javaOutput.add(line);
						// if(!fieldName.equalsIgnoreCase("id")) {
						line2 += fieldType + " " + fieldName;
						if (fields.indexOf(f) < fields.size() - 1) {
							line2 += ", ";
						}
						// }
					}
				}
			}
		}

		line2 += ") {";
		line2 += "\n" + Utils.tabGen(1) + "super();";

		// constructors
		line = "\n" + Utils.tabGen(1) + "public " + table.getName() + "() {";
		line += "\n" + Utils.tabGen(2) + "super();\n" + Utils.tabGen(1) + "}";
		javaOutput.add(line);

		for (Field f : fields) {
			if (f != null) {
				fieldType = f.getType();
				if (!fieldType.equalsIgnoreCase(Utils.JUNCTION) && !fieldType.equalsIgnoreCase(Utils.CALLER)) {
					fieldName = Utils.getNamePascalCase(f.getName());
					if (fieldName != null) {
						line2 += "\n" + Utils.tabGen(2) + "this." + fieldName + " = " + fieldName + ";";
					}
				}
			}
		}

		line2 += "\n" + Utils.tabGen(1) + "}";
		javaOutput.add(line2);

		// getters & setters
		for (Field f : fields) {
			if (f != null) {
				fieldType = f.getType();
				if (!fieldType.equalsIgnoreCase(Utils.JUNCTION) && !fieldType.equalsIgnoreCase(Utils.CALLER)) {
					fieldName = Utils.getNamePascalCase(f.getName());
					fieldNameMethod = Utils.getNameCamelCase(f.getName());
					if (fieldType != Utils.OBJECT) {
						if (fieldType.equalsIgnoreCase(Utils.URI)) {
							fieldType = Utils.INT;
						}

						if (fieldType != Utils.ARRAY) {
							line = Utils.tabGen(1) + "public " + fieldType + " get" + fieldNameMethod + "() {\n";
						} else {
							line = Utils.tabGen(1) + "public ArrayList<Integer> get" + fieldNameMethod + "() {\n";
						}
						line += Utils.tabGen(2) + "return this." + fieldName + ";\n	}";

						if (fieldType != Utils.ARRAY) {
							line = "\n" + Utils.tabGen(1) + "public void set" + fieldNameMethod + "(" + fieldType + " "
									+ fieldName + ") {\n";
						} else {
							line = "\n" + Utils.tabGen(1) + "public void set" + fieldNameMethod
									+ "(ArrayList<Integer> " + fieldName + ") {\n";
						}
						line += Utils.tabGen(2) + "this." + fieldName + " = " + fieldName + ";\n" + Utils.tabGen(2)
								+ "}";
						javaOutput.add(line);
					}
				}
			}
		}
		javaOutput.addAll(generateJavaParser(table));
		javaOutput.add("}");
		return javaOutput;
	}

	public static boolean fieldAlreadyExists(Field field) {
		for (Field f : currentTableFields) {
			if (f.getOrignalName().equalsIgnoreCase(field.getOrignalName())) {
				return true;
			}
		}
		return false;
	}

	public static boolean tableAlreadyExists(String tableName) {
		for (Table t : tables) {
			if (t.getName().equalsIgnoreCase(tableName) || t.getOriginalName().equalsIgnoreCase(tableName)) {
				return true;
			}
		}
		return false;
	}

	public static Table getTableWithName(String tableName) {
		for (Table t : tables) {
			if (t.getName().equalsIgnoreCase(tableName) || t.getOriginalName().equalsIgnoreCase(tableName)) {
				return t;
			}
		}
		return null;
	}
}