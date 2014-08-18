package be.qaz.amm;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import be.qaz.amm.generator.BeanGenerator;
import be.qaz.amm.generator.JsonGenerator;
import be.qaz.amm.generator.ParserGenerator;
import be.qaz.amm.model.Field;
import be.qaz.amm.model.Table;

public class Main {

	final static String inputDirectoryPath = "etc/input";
	final static String outputDirectoryPath = "etc/export/";

	public static ArrayList<Table> junctionTables;
	public static ArrayList<Table> tables;
	public static ArrayList<Field> primaryKeys;

	public static ArrayList<Field> currentTableFields;

	public static void main(String[] args) {
		System.out.println("START");
		primaryKeys = new ArrayList<Field>();
		tables = new ArrayList<Table>();
		junctionTables = new ArrayList<Table>();

		tables = new ArrayList<Table>();
		currentTableFields = new ArrayList<Field>();
		File file = new File(inputDirectoryPath);
		for (File f : loadFiles(file)) {
			System.out.println("######## " + f.getName() + " FOUND ########");
			System.out.println("NEW PARSING");
			fileParser(f);
		}

		// Sorting fields alphabetically && adding foreign key field.
		for (Table table : tables) {
			table = addForeignKeyField(table);
			Collections.sort(table.getFields(), new Comparator<Field>() {
				public int compare(Field f1, Field f2) {
					return f1.getName().compareTo(f2.getName());
				}
			});
		}

		// generateParsers(tables);
		generateJavaBeans(tables);
		// generateJsonScheme(tables);

		System.out.println("######## END ######## ");
		System.out.println("######## TABLES DETAILS ########");
		System.out.println("TOTAL tables = " + tables.size());
//		for (Table t : tables) {
//			System.out.println(t.toString());
//		}
	}

	public static void generateParsers(ArrayList<Table> tables) {
		System.out.println("######## GENERATED PARSER ########");
		ArrayList<String> generated = new ArrayList<String>();
		for (Table t : tables) {
			if (t != null && t.getFields() != null && t.getFields().size() > 0) {
				generated.addAll(ParserGenerator.generateJavaParser(t, tables));
			}
		}

		if (generated.size() > 0) {
			String[] data = generated.toArray(new String[generated.size()]);
			writeFile("JsonParsers.java", data);
			generated.clear();
		}
	}

	public static void generateJsonScheme(ArrayList<Table> tables) {
		System.out.println("######## GENERATED JSON SCHEME ########");
		ArrayList<String> generated = new ArrayList<String>();
		for (Table t : tables) {
			if (t != null) {
				generated = JsonGenerator.generateJsonSchema(t, tables);
				if (generated != null) {
					if (generated.size() > 0) {
						String[] data = generated.toArray(new String[generated
								.size()]);
						writeFile(t.getOriginalName() + ".json", data);
						generated.clear();
					}
				}
			}
		}
	}

	public static void generateJavaBeans(ArrayList<Table> tables) {
		System.out.println("######## GENERATED JAVA BEANS ########");
		ArrayList<String> generated = new ArrayList<String>();
		for (Table t : tables) {
			if (t != null) {
				generated = BeanGenerator.generateJavaBean(t);
				/*
				 * if (generated != null) { for (String string : generated) {
				 * System.out.println(string); } }
				 */
				if (generated != null && generated.size() > 0) {
					String[] data = generated.toArray(new String[generated
							.size()]);
					writeFile(t.getName() + ".java", data);
					generated.clear();
				}
			}
		}
	}

	public static void writeFile(String name, String[] lines) {
		BufferedWriter writer = null;
		try {
			new File(outputDirectoryPath).mkdirs();
			final File outFile = new File(outputDirectoryPath + name);

			System.out.println(outFile.getCanonicalPath());
			writer = new BufferedWriter(new FileWriter(outFile));

			String line;
			for (final String line2 : lines) {
				line = String.format("%s \n", line2);
				writer.write(line);
			}

		} catch (final Exception e) {
			e.printStackTrace();
		} finally {
			try {
				writer.flush();
				writer.close();
			} catch (final Exception e) {
			}
		}
	}

	// Load files into prog
	public static File[] loadFiles(File directoryPath) {
		final File[] entityFiles = directoryPath.listFiles(new FileFilter() {
			@Override
			public boolean accept(File pathname) {
				return !pathname.getName().startsWith("_")
						&& pathname.getName().endsWith(".json");
			}
		});
		return entityFiles;
	}

	public static void fileParser(File file) {
		if (file == null) {
			return;
		}
		final JSONParser parser = new JSONParser();
		try {
			final Object obj = parser.parse(new FileReader(file));
			JSONObject jsonObject = null;
			boolean isInArray = false;
			if (obj instanceof JSONArray) {
				final JSONArray jsonArray = (JSONArray) obj;
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

		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Parse your JSONObject and fill the tables array.
	 * 
	 * @param jsonObj
	 *            JSONObject : you're about to parse
	 * @param currentObjName
	 *            String : the name of obj
	 * @param parentObjName
	 *            String : the name of the object containing obj, ex : "recipes"
	 *            contains "variants"
	 */
	public static void parseJsonObject(JSONObject jsonObj,
			String currentObjName, String parentObjName, boolean isInArray) {

		String key;
		Object aObj;

		if (currentObjName != null && !currentObjName.isEmpty()) {
			// My jsons use a generic "objects" name regardless of the entity
			// you're
			// querying
			if (currentObjName.equalsIgnoreCase("objects")) {
				currentObjName = parentObjName.substring(5);
			}

			Table currentTable = createTable(currentObjName, isInArray, parentObjName);
			if(currentTable != null) {
				tables.add(currentTable);
			}

		}

		Iterator iter = jsonObj.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry entry = (Map.Entry) iter.next();
			key = (String) entry.getKey();
			aObj = jsonObj.get(key);

			if (aObj instanceof JSONObject) {
				System.out.println("IN " + currentObjName + " # OBJ : " + key);

				parseJsonObject((JSONObject) aObj, key, currentObjName, false);
				createField(key, currentObjName, Constants.OBJECT, key, aObj);

			} else if (aObj instanceof JSONArray) {

				JSONArray array = (JSONArray) aObj;
				System.out
						.println("IN " + currentObjName + " # ARRAY : " + key);
				if (array.size() > 0) {
					for (Object bObj : array) {
						if (bObj instanceof JSONObject) {
							parseJsonObject((JSONObject) bObj, key,
									currentObjName, true);
							createField(key, currentObjName, Constants.ARRAY,
									key, bObj);
						} else {
							String objt = aObj.toString();
							if (objt.length() > 30) {
								objt = objt.substring(0, 30);
								objt += "...";
							}

							System.out
									.println("++++ parseJsonObject/ARRAY key = "
											+ key
											+ " aObj = "
											+ objt
											+ " currentObjName "
											+ currentObjName);
							createField(key, currentObjName, Constants.ARRAY,
									null, bObj);
						}
					}
				}

			} else {
				final String type = Utils.javaTypeResolver(aObj);
				System.out.println("IN " + currentObjName + " # " + type
						+ " : " + key);
				createField(key, currentObjName, type, null, aObj);
			}
		}
	}

	public static Table createTable(String tableName, boolean isInArray,
			String parent) {
		Table table = null;
		if (tableName != null && !tableName.isEmpty()) {
			if (!Utils.tableAlreadyExists(tableName, tables)) {
				table = new Table(tableName, Utils.getNameCamelCase(tableName),
						null, null, isInArray, parent);
				// AndroidActive Annotation
//				table.addAnnotations("@Table(name = \"" + table.getName()
//						+ "\")");
			} else {
				boolean isNewParent = false;
				table = Utils.findTableWithName(tableName, tables);
				for (String existingParent : table.getParent()) {
					if(!parent.equalsIgnoreCase(existingParent)) {
						isNewParent = true;
					}
				}
				if(isNewParent) {
					table.addParent(parent);
				}
				return null;
			}
		}
		return table;
	}

	/**
	 * createField 
	 * 
	 * @param fieldName
	 *            String : field name
	 * @param value
	 *            Object : content value used to determined its type
	 * @param tableName
	 *            String : field's table
	 * @return created field
	 */
	public static Field createField(String fieldName, String tableName,
			String type, String constraint, Object value) {
		//TODO bad design this shouldn't add the field to the table
		Field field = null;
		Table constraintTable = Utils.findTableWithName(constraint, tables);

		if ((fieldName == null) || (tableName == null)) {
			return null;
		}
		String javaFieldName = Utils.getNamePascalCase(fieldName);

		if (type.equalsIgnoreCase(Constants.ARRAY)) {
			String arrayType = Utils.javaTypeResolver(value);
			if (arrayType.equalsIgnoreCase(Constants.URI)) {
				if (constraint == null) {
					constraint = Utils.extractTableFromUri((String) value);
					arrayType = Constants.STRING;
				}
			} else if (constraintTable != null) {
				arrayType = constraintTable.getName();
			}

			type += ";" + arrayType;
		} else if (type.equalsIgnoreCase(Constants.URI)) {
			// javaFieldName += "Fk";
			constraint = Utils.extractTableFromUri((String) value);
		} else if (type.equalsIgnoreCase(Constants.OBJECT)) {
			type = constraintTable.getName();
		}

		field = new Field(fieldName, javaFieldName, type, constraint, tableName);
		// GSON annotations
		// field.addAnnotation("@SerializedName(\"" + fieldName + "\")");
		// Jackson annotations
		field.addAnnotation("@JsonProperty(\"" + fieldName + "\")");
		// ActiveAndroid annotations
		// @Column(name = "Polygon", onDelete = ForeignKeyAction.CASCADE,
		// notNull = true)
		// field.addAnnotation("@Column(name = \"" +
		// Utils.checkSqlForbiddenName(fieldName) + "\")");

		// if the same field is found with another type it's overridden with
		// the default & more "do it all" String or ArrayList type also means
		// your json
		// sucks
		Table table = Utils.findTableWithName(tableName, tables);
		if (table != null) {
			ArrayList<Field> fs = table.getFields();
			if (fs == null) {
				fs = new ArrayList<Field>();
			}
			final int existingFieldIndex = Utils.findIndexOfFieldInTable(field,
					table);
			if (existingFieldIndex >= 0) {
				Field existingField = table.getFields().get(existingFieldIndex);
				if (!existingField.getType().equalsIgnoreCase(field.getType())) {

					if (existingField.getType().contains(Constants.ARRAY)) {
						field.setType(Constants.ARRAY);
					} else {
						field.setType(Constants.STRING);
					}
					table.getFields().set(existingFieldIndex, field);

					System.out.println("FIELD TYPE CHANGED " + fieldName
							+ " TO STRING FROM TABLE " + tableName);
				}
			} else {
				fs.add(field);
				table.setFields(fs);
			}

		} else {
			System.out.println("TABLE NOT FOUND " + tableName + " FOR FIELD "
					+ fieldName);
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
	public static Table createArrayTable(String foreign, String table,
			String type) {
		if (!Utils.tableAlreadyExists(table, tables)) {
			final ArrayList<Field> fs = new ArrayList<Field>();
			final Field f1 = new Field(foreign + "_id",
					Utils.getNamePascalCase(foreign) + "Id", Constants.INT,
					foreign, table);
			final Field f2 = new Field(table, Utils.getNamePascalCase(table),
					type, null, table);
			fs.add(f1);
			fs.add(f2);
			final Table arrayTable = new Table(table,
					Utils.getNameCamelCase(table), Constants.JUNCTION_TABLE,
					fs, false, table);
			return arrayTable;
		}
		return null;
	}

	public static Table createJunctionTable(String refTable, String extTable) {
		final String tName = Utils.createJunctionTableName(refTable, extTable);
		if (!Utils.tableAlreadyExists(tName, tables)) {
			final ArrayList<Field> fs = new ArrayList<Field>();
			final Field f1 = new Field(refTable + "_id",
					Utils.getNamePascalCase(refTable) + "Id", Constants.INT,
					refTable, refTable);
			final Field f2 = new Field(extTable + "_id",
					Utils.getNamePascalCase(extTable) + "Id", Constants.INT,
					extTable, refTable);
			fs.add(f1);
			fs.add(f2);
			final Table juncTable = new Table(tName,
					Utils.getNameCamelCase(tName), Constants.JUNCTION_TABLE,
					fs, false, refTable);
			return juncTable;
		}
		return null;
	}

	public static Table addForeignKeyField(Table table) {
		if (table.getParent() != null && table.getParent().size() == 1) {
			final String parent = table.getParent().get(0);
			createField(parent, table.getName(),
					Utils.getNameCamelCase(parent),
					Constants.FOREIGN_KEY, null);
		} else {
			// if more than one parent then its a many to many relationship
			// create junction table
		}
		return table;
	}
}
