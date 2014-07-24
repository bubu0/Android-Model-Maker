import java.io.*;
import java.util.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import model.Field;
import model.Table;

public class Main {

	final static String inputDirectoryPath = "inputs";
	final static String outputDirectoryPath = "export/";

	final static String PARSER_STYLE_START_ARRAY = Utils.tabGen(1)
			+ "public static ArrayList<xaxContentValues> parseJsonxax(JSONArray joInput, Context ctxt) { \n"
			+ Utils.tabGen(2) + "ArrayList<xaxContentValues> list = new ArrayList<xaxContentValues>(); \n"
			+ Utils.tabGen(2) + "try { \n" + Utils.tabGen(3) + "xaxContentValues val; \n" + Utils.tabGen(3)
			+ "for (int i = 0; i < joInput.length(); i++) { \n" + Utils.tabGen(4)
			+ "JSONObject joInside = joInput.getJSONObject(i);\n" + Utils.tabGen(4) + "val = new xaxContentValues();";

	final static String PARSER_STYLE_START_OBJECT = Utils.tabGen(1)
			+ "public static ArrayList<xaxContentValues> parseJsonxax(JSONObject joInside, Context ctxt) { \n"
			+ Utils.tabGen(2) + "ArrayList<xaxContentValues> list = new ArrayList<xaxContentValues>(); \n"
			+ Utils.tabGen(2) + "try { \n" + Utils.tabGen(3) + "xaxContentValues val = new xaxContentValues();";

	final static String PARSER_STYLE_START_ID_ARRAY = Utils.tabGen(1)
			+ "public static ArrayList<xaxContentValues> parseJsonxax(JSONArray joInput, Context ctxt, int extId) { \n"
			+ Utils.tabGen(2) + "ArrayList<xaxContentValues> list = new ArrayList<xaxContentValues>(); \n"
			+ Utils.tabGen(2) + "try { \n" + Utils.tabGen(3) + "xaxContentValues val; \n" + Utils.tabGen(3)
			+ "for (int i = 0; i < joInput.length(); i++) { \n" + Utils.tabGen(4)
			+ "JSONObject joInside = joInput.getJSONObject(i);\n" + Utils.tabGen(4) + "val = new xaxContentValues();";

	final static String PARSER_STYLE_START_ID_OBJECT = Utils.tabGen(1)
			+ "public static ArrayList<xaxContentValues> parseJsonxax(JSONObject joInside, Context ctxt, int extId) { \n"
			+ Utils.tabGen(2) + "ArrayList<xaxContentValues> list = new ArrayList<xaxContentValues>(); \n"
			+ Utils.tabGen(2) + "try { \n" + Utils.tabGen(3) + "xaxContentValues val = new xaxContentValues();";

	final static String PARSER_STYLE_END_ARRAY = Utils.tabGen(4) + "list.add(val);\n" + Utils.tabGen(3) + "} \n"
			+ Utils.tabGen(2) + "} catch (JSONException e) {\n" + Utils.tabGen(3) + "e.printStackTrace();\n"
			+ Utils.tabGen(2) + "} \n" + Utils.tabGen(2) + "return list;\n" + Utils.tabGen(1) + "}";

	final static String PARSER_STYLE_END_OBJECT = Utils.tabGen(3) + "list.add(val);\n" + Utils.tabGen(2)
			+ "} catch (JSONException e) {\n" + Utils.tabGen(3) + "e.printStackTrace();\n" + Utils.tabGen(2) + "} \n"
			+ Utils.tabGen(2) + "return list;\n" + Utils.tabGen(1) + "}";

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

	// used to create the method that will call simple parsers one by one to
	// sync the entire file
	public static ArrayList<String> syncMethod = new ArrayList<String>();

	public static void main(String[] args) {
		System.out.println("START");
		primaryKeys = new ArrayList<Field>();
		tables = new ArrayList<Table>();
		junctionTables = new ArrayList<Table>();

		tables = new ArrayList<Table>();
		currentTableFields = new ArrayList<Field>();
		ArrayList<String> generated = new ArrayList<String>();
		File file = new File(inputDirectoryPath);
		for (File f : loadFiles(file)) {
			System.out.println("######## " + f.getName() + " FOUND ########");
			System.out.println("NEW PARSING");
			syncMethod.clear();
			expParserFile(f);
		}

		System.out.println("######## GENERATED PARSER ########");
		for (Table t : tables) {
			if (t != null && t.getFields() != null && t.getFields().size() > 0) {
				generated.addAll(generateJavaParser(t));
			}
		}

		if (generated.size() > 0) {
			// generated.addAll(syncMethod);
			// generated.addAll(syncMethod);
			String[] data = generated.toArray(new String[generated.size()]);
			writeFile("JsonParsers.java", data);
			generated.clear();
		}

		System.out.println("######## GENERATED JSON SCHEMA ########");
		for (Table t : tables) {
			if (t != null) {
				generated = generateJsonSchema(t);
				if (generated != null) {
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

		System.out.println("######## END ######## ");
		System.out.println("######## TABLES DETAILS ########");
		System.out.println("TOTAL tables = " + tables.size());
		for (Table t : tables) {
			System.out.println(t.toString());
		}

	}

	public static void writeFile(String name, String[] lines) {
		BufferedWriter writer = null;
		try {
			// create a temporary file
			// String timeLog = new
			// SimpleDateFormat("ddMMyyyy_HHmmss").format(Calendar.getInstance().getTime());
			// File logFile = new File("export/" + name + "_" + timeLog);
			new File(outputDirectoryPath).mkdirs();
			File logFile = new File(outputDirectoryPath + name);

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
			boolean isInArray = false;
			if (obj instanceof JSONArray) {
				JSONArray jsonArray = (JSONArray) obj;
				jsonObject = (JSONObject) jsonArray.get(0);
				isInArray = true;
			} else if (obj instanceof JSONObject) {
				jsonObject = (JSONObject) obj;
			} else {
				return;
			}

			final String rootObject = file.getName().replace(".json", "");
			// parseJsonObject(jsonObject, rootObject, null, isInArray);
			parseJsonObject(jsonObject, rootObject, null, true);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * createField
	 * 
	 * @param fieldName
	 *            String : field name
	 * @param value
	 *            Object : content value used to determined its type
	 * @param table
	 *            String : field's table
	 * @return created field
	 */
	public static Field createField(String fieldName, String table, String type, String constraint, Object value) {
		Field field;

		if (fieldName == null || table == null) {
			return null;
		}

		if (type == null) {
			type = Utils.INT;
		}

		if ((type.equalsIgnoreCase(Utils.URI) || type.equalsIgnoreCase(Utils.JUNCTION))
				&& Utils.isTagAllowed(fieldName)) {
			if (constraint == null) {
				constraint = Utils.extractTableFromUri((String) value);
			}
			Table junc = createJunctionTable(table, constraint);
			if (junc != null) {
				tables.add(junc);
				junctionTables.add(junc);
			}

			if (type.equalsIgnoreCase(Utils.JUNCTION)) {
				Table t = findTableWithName(constraint);
				if (t != null) {
					if (t.getFields() != null) {
						System.out.println("CALLER added + " + table + "FkId");
						t.getFields().add(new Field(table + "FkId", table + "FkId", Utils.CALLER, table));
					} else {
						final Field f = new Field(table + "FkId", table + "FkId", Utils.CALLER, table);
						ArrayList<Field> af = new ArrayList<Field>();
						af.add(f);
						t.setFields(af);
					}
				}
			}
		}

		if (type.equalsIgnoreCase(Utils.ARRAY)) {
			String arrayType = Utils.javaTypeResolver(value);
			Table arrayTb = createArrayTable(table, fieldName, arrayType);
			if (arrayTb != null) {
				tables.add(arrayTb);
			}
		}

		field = new Field(fieldName, Utils.getNamePascalCase(fieldName), type, constraint);

		Table t = findTableWithName(table);
		if (t != null) {
			ArrayList<Field> fs = t.getFields();
			if (fs == null) {
				fs = new ArrayList<Field>();
			}
			fs.add(field);
			t.setFields(fs);
		}

		return field;
	}

	/**
	 * Create table containing array of primitive value.
	 * 
	 * @param foreign
	 *            String : linked table name
	 * @param table
	 *            String : table name
	 * @param type
	 *            String : type of the array
	 * @return created table
	 */
	public static Table createArrayTable(String foreign, String table, String type) {
		if (!tableAlreadyExists(table)) {
			ArrayList<Field> fs = new ArrayList<Field>();
			Field f1 = new Field(foreign + "_id", Utils.getNamePascalCase(foreign) + "Id", Utils.INT, foreign);
			Field f2 = new Field(table, Utils.getNamePascalCase(table), type, null);
			fs.add(f1);
			fs.add(f2);
			Table arrayTable = new Table(table, Utils.getNameCamelCase(table), JUNCTION_TABLE, fs, false);
			return arrayTable;
		}
		return null;
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
	public static void parseJsonObject(JSONObject obj, String currentObjName, String exObjName, boolean isInArray) {

		Table currentTable;
		String key;
		Object aObj;
		boolean exObjAlreadySet = false;

		if (currentObjName != null && !currentObjName.isEmpty() && !tableAlreadyExists(currentObjName)) {
			currentTable = new Table(currentObjName, Utils.getNameCamelCase(currentObjName), null, null, isInArray);
			tables.add(currentTable);
		}

		Iterator iter = obj.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry entry = (Map.Entry) iter.next();
			key = (String) entry.getKey();
			aObj = obj.get(key);

			if (exObjName != null && !exObjAlreadySet) {
				createField(exObjName + "FkId", currentObjName, Utils.JUNCTION, exObjName, aObj);
				exObjAlreadySet = true;
			}

			if (aObj instanceof JSONObject) {
				System.out.println("IN " + currentObjName + " # OBJ : " + key);
				parseJsonObject((JSONObject) aObj, key, currentObjName, false);

			} else if (aObj instanceof JSONArray) {

				JSONArray array = (JSONArray) aObj;
				System.out.println("IN " + currentObjName + " # ARRAY : " + key);
				if (array.size() > 0) {
					Object bObj = array.get(0);

					if (bObj instanceof JSONObject) {
						parseJsonObject((JSONObject) bObj, key, currentObjName, true);
					} else {
						System.out.println("++++ parseJsonObject/ARRAY key = " + key + " aObj = " + aObj
								+ " currentObjName " + currentObjName);
						createField(key, currentObjName, Utils.ARRAY, null, bObj);
					}
				}

			} else {
				String type = Utils.javaTypeResolver(aObj);
				createField(key, currentObjName, type, null, aObj);
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
			Table juncTable = new Table(tName, Utils.getNameCamelCase(tName), JUNCTION_TABLE, fs, false);
			return juncTable;
		}
		return null;
	}

	/**
	 * Create java parsing methods to be used with
	 * android-contentprovider-generator project
	 * 
	 * @param table
	 * @return
	 */
	public static ArrayList<String> generateJavaParser(Table table) {
		if (table == null || table.getFields().size() == 0) {
			return null;
		}
		// used to avoid duplicate var name when two different attributes
		// reference the same table, ex : "Actions" contains "CreatedBy" &
		// "ModifiedBy" which both ref to the table "Users"
		int externalRefCtr = 0;
		boolean arrayInitiated = false;
		boolean objectInitiated = false;
		boolean jsonArrayInitiated = false;

		boolean isInArray = table.isInArray();
		ArrayList<String> javaOutput = new ArrayList<String>();
		if (table.getConstraint() != null && table.getConstraint().equalsIgnoreCase(JUNCTION_TABLE)) {
			return javaOutput;
		}
		final int nbOfTab = 4;
		String line;
		if (isInArray) {
			line = PARSER_STYLE_START_ARRAY.replaceAll("xax", table.getName());
		} else {
			line = PARSER_STYLE_START_OBJECT.replaceAll("xax", table.getName());
		}
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
					final String jValName = Utils.getNamePascalCase(f.getConstraint()) + "Cv" + externalRefCtr;
					externalRefCtr++;
					Table t = findTableWithName(jTableName);
					if (t != null) {
						jTableName = Utils.getNameCamelCase(jTableName);
						line = "\n" + Utils.tabGen(nbOfTab) + "int " + f.getName() + "Id = "
								+ "Utils.extractIdFromUri(joInside.getString(\"" + f.getOrignalName() + "\"));";
						line += "\n" + Utils.tabGen(nbOfTab) + "val.put" + fieldName + "(" + f.getName() + "Id" + ");";
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

				} else if (type.equalsIgnoreCase(Utils.URI) && f.getConstraint() == null) {

					line = Utils.tabGen(nbOfTab) + "val.put" + fieldName
							+ "(Utils.extractIdFromUri(joInside.getString(\"" + f.getOrignalName() + "\")));";

				} else if (type.equalsIgnoreCase(Utils.ARRAY) && f.getConstraint() == null) {

					// Handling array of primitive types
					String jTableName = Utils.getNameCamelCase(f.getName());
					final String jValName = Utils.getNamePascalCase(jTableName) + "Cv" + externalRefCtr;
					externalRefCtr++;
					Table t = findTableWithName(jTableName);
					if (t != null) {
						line = Utils.tabGen(nbOfTab) + "val.put" + fieldName
								+ "(Utils.extractIdFromUri(joInside.getString(\"" + f.getOrignalName() + "\")));";

						if (!jsonArrayInitiated) {
							line = Utils.tabGen(nbOfTab) + "JSONArray jArray = joInside.getJSONArray(\""
									+ f.getOrignalName() + "\");";
							jsonArrayInitiated = true;
						} else {
							line = Utils.tabGen(nbOfTab) + "jArray = joInside.getJSONArray(\"" + f.getOrignalName()
									+ "\");";
						}

						line += "\n" + Utils.tabGen(nbOfTab)
								+ "for(int j = 0, count = jArray.length(); j< count; j++) {";

						// resolving the enum type
						type = t.getFields().get(1).getType();
						line += "\n" + Utils.tabGen(nbOfTab + 1);
						if (type.equalsIgnoreCase(Utils.INT)) {
							line += "int v = jArray.getInt(j);";
						} else if (type.equalsIgnoreCase(Utils.URI)) {
							line += "int v = Utils.extractIdFromUri(jArray.getString(j));";
						} else { // String by default
							line += "String v = jArray.getString(j);";
						}

						line += "\n" + Utils.tabGen(nbOfTab + 1) + jTableName + "ContentValues " + jValName + " = new "
								+ jTableName + "ContentValues();";
						line += "\n" + Utils.tabGen(nbOfTab + 1) + jValName + ".put"
								+ Utils.getNameCamelCase(t.getFields().get(0).getName()) + "(id);";
						line += "\n" + Utils.tabGen(nbOfTab + 1) + jValName + ".put"
								+ Utils.getNameCamelCase(t.getFields().get(1).getName()) + "(v);";
						line += "\n" + Utils.tabGen(nbOfTab + 1) + "ctxt.getContentResolver().insert(" + jTableName
								+ "Columns.CONTENT_URI, " + jValName + ".values());";
						line += "\n" + Utils.tabGen(nbOfTab) + "}\n";
					}

				} else if (type.equalsIgnoreCase(Utils.JUNCTION)) {

					// Filling junction tables with param from method and NOT
					// from json;
					String jTableName = Utils.createJunctionTableName(table.getOriginalName(), f.getConstraint());
					final String jValName = Utils.getNamePascalCase(f.getConstraint()) + "cv" + externalRefCtr;
					System.out.println("JsonParser JUNCTION field : " + f.getName() + " & jTableName = " + jTableName
							+ " & jValName = " + jValName);
					externalRefCtr++;
					Table t = findTableWithName(jTableName);
					if (t != null) {
						if (isInArray) {
							line = PARSER_STYLE_START_ID_ARRAY.replaceAll("xax", table.getName());
						} else {
							line = PARSER_STYLE_START_ID_OBJECT.replaceAll("xax", table.getName());
						}
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
				} else if (type.equalsIgnoreCase(Utils.OBJECT)) {
					if (!objectInitiated) {
						line = Utils.tabGen(nbOfTab) + "JSONObject insideObj = joInside.getJSONObject(\""
								+ f.getOrignalName() + "\");";
						objectInitiated = true;
					} else {
						line = Utils.tabGen(nbOfTab) + "insideObj = joInside.getJSONObject(\"" + f.getOrignalName()
								+ "\");";
					}
				} else if (type.equalsIgnoreCase(Utils.CALLER)) {
					String distTableName = f.getConstraint();
					Table distTable = findTableWithName(distTableName);
					if (distTable.isInArray()) {
						endLine += "\n" + Utils.tabGen(nbOfTab) + "parseJson" + Utils.getNameCamelCase(distTableName)
								+ "(joInside.getJSONArray(\"" + distTableName + "\"), ctxt, id);";
					} else {
						if (distTable != null) {
							endLine += "\n" + Utils.tabGen(nbOfTab) + "parseJson"
									+ Utils.getNameCamelCase(distTableName) + "(joInside.getJSONObject(\""
									+ distTableName + "\"), ctxt, id);";
						}
					}
				} else if (type.equalsIgnoreCase(Utils.ARRAY)) {
					if (!arrayInitiated) {
						line = Utils.tabGen(nbOfTab) + "JSONArray insideArray = joInside.getJSONArray(\""
								+ f.getOrignalName() + "\");";
						javaOutput.add(line);
						line = Utils.tabGen(nbOfTab) + "ArrayList<String> ls = new ArrayList<String>();\n"
								+ Utils.tabGen(nbOfTab) + "for (int j = 0; j < insideArray.length(); j++) {\n"
								+ Utils.tabGen(3) + "ls.add(insideArray.getString(j));\n" + Utils.tabGen(nbOfTab) + "}";
						arrayInitiated = true;
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
						type = Utils.DB_BOOL;
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
		if (isInArray) {
			javaOutput.add(PARSER_STYLE_END_ARRAY + "\n");
		} else {
			javaOutput.add(PARSER_STYLE_END_OBJECT + "\n");
		}
		return javaOutput;
	}

	/**
	 * Create json files which could be used with
	 * android-contentprovider-generator
	 * 
	 * @param table
	 * @return list of String ready to be written in a file
	 */
	public static ArrayList<String> generateJsonSchema(Table table) {
		if (table == null || table.getFields() == null || table.getFields().size() == 0) {
			System.out.println("generateJsonSchema : table not generated = " + table);
			return null;
		}
		// TODO handle constraints such as UNIQUE & delete cascade
		ArrayList<String> javaOutput = new ArrayList<String>();
		String line;
		// javaOutput.add(table.getName());
		javaOutput.add("{");
		javaOutput.add(Utils.tabGen(1) + "\"fields\": [");
		ArrayList<Field> fields = table.getFields();
		String type = null;
		String usableFieldName = null;
		boolean hasIdConstraint = false;
		for (Field f : fields) {
			type = Utils.convertToDBType(f.getType());
			if (type.equalsIgnoreCase(Utils.URI)) {
				type = Utils.DB_INT;
			}
			// TODO create an array of unused type for this case
			if (f.getName() != null && f.getType() != Utils.OBJECT && f.getType() != Utils.JUNCTION
					&& f.getType() != Utils.CALLER && f.getType() != Utils.ARRAY) {
				javaOutput.add(Utils.tabGen(2) + "{");
				usableFieldName = Utils.checkForbiddenName(f.getOrignalName());
				line = Utils.tabGen(3) + "\"name\": \"" + usableFieldName + "\",";
				javaOutput.add(line);
				line = Utils.tabGen(3) + "\"type\": \"" + type + "\"";
				javaOutput.add(line);
				if (fields.indexOf(f) != fields.size() - 1) {
					line += ",";
					javaOutput.add(Utils.tabGen(2) + "},");
				} else {
					javaOutput.add(Utils.tabGen(2) + "}");
				}
				if (usableFieldName.equalsIgnoreCase("id_db")) {
					hasIdConstraint = true;
				}
			}
		}

		javaOutput.add(Utils.tabGen(1) + "],");
		if (hasIdConstraint
				|| (table.getConstraint() != null && table.getConstraint().equalsIgnoreCase(JUNCTION_TABLE))) {
			javaOutput.add(Utils.tabGen(1) + "\"constraints\": [");
			javaOutput.add(Utils.tabGen(2) + "{");
			if (hasIdConstraint) {
				javaOutput.add(Utils.tabGen(3) + "\"name\": \"unique_name\",");
				line = "\"definition\": \"unique (" + "id_db" + ") on conflict replace\"";
				javaOutput.add(Utils.tabGen(3) + line);
			} else {
				javaOutput.add(Utils.tabGen(3) + "\"name\": \"unique_name\",");
				line = "\"definition\": \"unique ("
						+ Utils.checkForbiddenName(table.getFields().get(0).getOrignalName()) + ", "
						+ Utils.checkForbiddenName(table.getFields().get(1).getOrignalName())
						+ ") on conflict replace\"";
				javaOutput.add(Utils.tabGen(3) + line);
			}
			javaOutput.add(Utils.tabGen(2) + "},");
			// javaOutput.add(Utils.tabGen(2) + "{");
			// javaOutput.add(Utils.tabGen(3) + "\"name\": \"xxx\",");
			// javaOutput.add(Utils.tabGen(3) +
			// "\"definition\": \"foreign key (yyy) references company (zzz) on delete cascade\",");
			// javaOutput.add(Utils.tabGen(2) + "},");
			javaOutput.add(Utils.tabGen(1) + "],");
		}

		javaOutput.add("}");
		return javaOutput;
	}

	/**
	 * Generate Java beans with getters & setters as well as a json parser
	 * 
	 * @param table
	 * @return
	 */
	public static ArrayList<String> generateJavaBean(Table table) {
		if (table == null || table.getFields() == null || table.getFields().size() == 0)
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
							line = Utils.tabGen(1) + "private ArrayList<Integer> " + fieldName + ";";
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
		line2 += "\n" + Utils.tabGen(2) + "super();";

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

		line2 += "\n" + Utils.tabGen(1) + "}\n";
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

						line = Utils.tabGen(1) + "public " + fieldType + " get" + fieldNameMethod + "() {\n";
						line += Utils.tabGen(2) + "return this." + fieldName + ";\n	}\n";
						line += "\n" + Utils.tabGen(1) + "public void set" + fieldNameMethod + "(" + fieldType + " "
								+ fieldName + ") {\n";
						line += Utils.tabGen(2) + "this." + fieldName + " = " + fieldName + ";\n" + Utils.tabGen(1)
								+ "}\n";
						javaOutput.add(line);
					}
				}
			}
		}
		javaOutput.addAll(generateSimpleJavaParser(table));
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
		if (tables.size() > 0 && tableName != null) {
			for (Table t : tables) {
				if (t.getName().equalsIgnoreCase(tableName) || t.getOriginalName().equalsIgnoreCase(tableName)) {
					return true;
				}
			}
		}
		return false;
	}

	public static Table findTableWithName(String tableName) {
		if (tables.size() > 0 && tableName != null) {
			for (Table t : tables) {
				if (t.getName().equalsIgnoreCase(tableName) || t.getOriginalName().equalsIgnoreCase(tableName)) {
					return t;
				}
			}
		}
		return null;
	}

	// TODO 1 - refactoring : maybe add a boolean param to generateJavaParser to
	// handle this case
	// TODO 2 - generate a sync method that will call those in a specified way

	/**
	 * Create java parsing methods to be used with
	 * android-contentprovider-generator project Generated methods will only
	 * take a JSONObject as parameter (not a JSONArray) & they won't make use of
	 * the CALLER type
	 * 
	 * @param table
	 * @return
	 */
	public static ArrayList<String> generateSimpleJavaParser(Table table) {
		if (table == null || table.getFields().size() == 0) {
			return null;
		}
		// used to avoid duplicate var name when two different attributes
		// reference the same table, ex : "Actions" contains "CreatedBy" &
		// "ModifiedBy" which both ref to the table "Users"
		int externalRefCtr = 0;
		boolean arrayInitiated = false;
		boolean objectInitiated = false;
		boolean jsonArrayInitiated = false;

		boolean isInArray = table.isInArray();
		ArrayList<String> javaOutput = new ArrayList<String>();
		if (table.getConstraint() != null && table.getConstraint().equalsIgnoreCase(JUNCTION_TABLE)) {
			return javaOutput;
		}
		final int nbOfTab = 4;
		String line;
		line = PARSER_STYLE_START_OBJECT.replaceAll("xax", table.getName());

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
					final String jValName = Utils.getNamePascalCase(f.getConstraint()) + "Cv" + externalRefCtr;
					externalRefCtr++;
					Table t = findTableWithName(jTableName);
					if (t != null) {
						jTableName = Utils.getNameCamelCase(jTableName);
						line = "\n" + Utils.tabGen(nbOfTab) + "int " + f.getName() + "Id = "
								+ "Utils.extractIdFromUri(joInside.getString(\"" + f.getOrignalName() + "\"));";
						line += "\n" + Utils.tabGen(nbOfTab) + "val.put" + fieldName + "(" + f.getName() + "Id" + ");";
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

				} else if (type.equalsIgnoreCase(Utils.URI) && f.getConstraint() == null) {

					line = Utils.tabGen(nbOfTab) + "val.put" + fieldName
							+ "(Utils.extractIdFromUri(joInside.getString(\"" + f.getOrignalName() + "\")));";

				} else if (type.equalsIgnoreCase(Utils.ARRAY) && f.getConstraint() == null) {

					// Handling array of primitive types
					String jTableName = Utils.getNameCamelCase(f.getName());
					final String jValName = Utils.getNamePascalCase(jTableName) + "Cv" + externalRefCtr;
					externalRefCtr++;
					Table t = findTableWithName(jTableName);
					if (t != null) {
						line = Utils.tabGen(nbOfTab) + "val.put" + fieldName
								+ "(Utils.extractIdFromUri(joInside.getString(\"" + f.getOrignalName() + "\")));";

						if (!jsonArrayInitiated) {
							line = Utils.tabGen(nbOfTab) + "JSONArray jArray = joInside.getJSONArray(\""
									+ f.getOrignalName() + "\");";
							jsonArrayInitiated = true;
						} else {
							line = Utils.tabGen(nbOfTab) + "jArray = joInside.getJSONArray(\"" + f.getOrignalName()
									+ "\");";
						}

						line += "\n" + Utils.tabGen(nbOfTab)
								+ "for(int j = 0, count = jArray.length(); j< count; j++) {";

						// resolving the enum type
						type = t.getFields().get(1).getType();
						line += "\n" + Utils.tabGen(nbOfTab + 1);
						if (type.equalsIgnoreCase(Utils.INT)) {
							line += "int v = jArray.getInt(j);";
						} else if (type.equalsIgnoreCase(Utils.URI)) {
							line += "int v = Utils.extractIdFromUri(jArray.getString(j));";
						} else { // String by default
							line += "String v = jArray.getString(j);";
						}

						line += "\n" + Utils.tabGen(nbOfTab + 1) + jTableName + "ContentValues " + jValName + " = new "
								+ jTableName + "ContentValues();";
						line += "\n" + Utils.tabGen(nbOfTab + 1) + jValName + ".put"
								+ Utils.getNameCamelCase(t.getFields().get(0).getName()) + "(id);";
						line += "\n" + Utils.tabGen(nbOfTab + 1) + jValName + ".put"
								+ Utils.getNameCamelCase(t.getFields().get(1).getName()) + "(v);";
						line += "\n" + Utils.tabGen(nbOfTab + 1) + "ctxt.getContentResolver().insert(" + jTableName
								+ "Columns.CONTENT_URI, " + jValName + ".values());";
						line += "\n" + Utils.tabGen(nbOfTab) + "}\n";
					}

				} else if (type.equalsIgnoreCase(Utils.CALLER)) {
					String distTableName = f.getConstraint();
					Table distTable = findTableWithName(distTableName);
					if (distTable.isInArray()) {
						syncMethod.add("\n" + Utils.tabGen(nbOfTab) + "parseJson"
								+ Utils.getNameCamelCase(distTableName) + "(joInside.getJSONArray(\"" + distTableName
								+ "\"), ctxt, id);");
					} else {
						if (distTable != null) {
							syncMethod.add("\n" + Utils.tabGen(nbOfTab) + "parseJson"
									+ Utils.getNameCamelCase(distTableName) + "(joInside.getJSONObject(\""
									+ distTableName + "\"), ctxt, id);");
						}
					}
				} else if (type.equalsIgnoreCase(Utils.JUNCTION)) {

					// Filling junction tables with param from method and NOT
					// from json;
					String jTableName = Utils.createJunctionTableName(table.getOriginalName(), f.getConstraint());
					final String jValName = Utils.getNamePascalCase(f.getConstraint()) + "cv" + externalRefCtr;
					System.out.println("JsonParser JUNCTION field : " + f.getName() + " & jTableName = " + jTableName
							+ " & jValName = " + jValName);
					externalRefCtr++;
					Table t = findTableWithName(jTableName);
					if (t != null) {

						line = PARSER_STYLE_START_ID_OBJECT.replaceAll("xax", table.getName());

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
				} else if (type.equalsIgnoreCase(Utils.OBJECT)) {
					if (!objectInitiated) {
						line = Utils.tabGen(nbOfTab) + "JSONObject insideObj = joInside.getJSONObject(\""
								+ f.getOrignalName() + "\");";
						objectInitiated = true;
					} else {
						line = Utils.tabGen(nbOfTab) + "insideObj = joInside.getJSONObject(\"" + f.getOrignalName()
								+ "\");";
					}
				} else if (type.equalsIgnoreCase(Utils.ARRAY)) {
					if (!arrayInitiated) {
						line = Utils.tabGen(nbOfTab) + "JSONArray insideArray = joInside.getJSONArray(\""
								+ f.getOrignalName() + "\");";
						javaOutput.add(line);
						line = Utils.tabGen(nbOfTab) + "ArrayList<String> ls = new ArrayList<String>();\n"
								+ Utils.tabGen(nbOfTab) + "for (int j = 0; j < insideArray.length(); j++) {\n"
								+ Utils.tabGen(3) + "ls.add(insideArray.getString(j));\n" + Utils.tabGen(nbOfTab) + "}";
						arrayInitiated = true;
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
						type = Utils.DB_BOOL;
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
		javaOutput.add(PARSER_STYLE_END_OBJECT + "\n");

		return javaOutput;
	}
}