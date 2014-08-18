package be.qaz.amm.generator;

import java.util.ArrayList;

import be.qaz.amm.Constants;
import be.qaz.amm.Utils;
import be.qaz.amm.model.Field;
import be.qaz.amm.model.Table;


public class ParserGenerator {
	
	/**
	 * Create java parsing methods to be used with
	 * android-contentprovider-generator project
	 * 
	 * @param table
	 * @return
	 */
	public static ArrayList<String> generateJavaParser(Table table, ArrayList<Table> tables) {
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
		if (table.getConstraint() != null && table.getConstraint().equalsIgnoreCase(Constants.JUNCTION_TABLE)) {
			return javaOutput;
		}
		int nbOfTab = 4;
		String line;
		if (isInArray) {
			line = Constants.PARSER_STYLE_START_ARRAY.replaceAll("xax", table.getName());
		} else {
			line = Constants.PARSER_STYLE_START_OBJECT.replaceAll("xax", table.getName());
		}
		String endLine = "";
		javaOutput.add(line);
		line = "";
		for (Field f : table.getFields()) {
			String fieldName = f.getName();
			if (fieldName != null) {
				String type = f.getType();
				fieldName = Utils.getNameCamelCase(Utils.checkSqlForbiddenName(fieldName));
				if (type == null || type.equalsIgnoreCase("null")) {
					line = Utils.tabGen(nbOfTab) + "val.put" + fieldName + "Null();";
				} else if (type.equalsIgnoreCase(Constants.INT)) {
					type = "Int";
					if (f.getName().equalsIgnoreCase("id")) {
						line = Utils.tabGen(nbOfTab) + "int id = 0;\n";
						line += Utils.tabGen(nbOfTab) + "if( joInside.isNull(\"" + f.getOrignalName()
								+ "\") == false) {\n";
						line += Utils.tabGen(nbOfTab + 1) + "id = " + "joInside.get" + type + "(\""
								+ f.getOrignalName() + "\");\n";
						line += Utils.tabGen(nbOfTab + 1) + "val.putIdDb(id);\n";
						line += Utils.tabGen(nbOfTab) + "}";

						if (javaOutput.size() > 1) {
							// javaOutput.remove(1);
							javaOutput.add(1, line);
							line = null;
						}
					} else {
						line = Utils.tabGen(nbOfTab) + "if( joInside.isNull(\"" + f.getOrignalName()
								+ "\") == false)\n";
						line += Utils.tabGen(nbOfTab + 1) + "val.put" + fieldName + "(joInside.get" + type + "(\""
								+ f.getOrignalName() + "\"));";

					}
				} else if (type.equalsIgnoreCase(Constants.DATE)) {
					//TODO include Date parser
					line = Utils.tabGen(nbOfTab) + "if( joInside.isNull(\"" + f.getOrignalName() + "\") == false) \n";
					line += Utils.tabGen(nbOfTab + 1) + "val.put" + fieldName
							+ "(Utils.convertToDate(joInside.getString(\"" + f.getOrignalName()
							+ "\"), Utils.formats[7]));";
					// In case of an URI, we are linked to another table

				} else if (type.equalsIgnoreCase(Constants.URI)) {

					line = Utils.tabGen(nbOfTab) + "if( joInside.isNull(\"" + f.getOrignalName() + "\") == false)\n";
					line += Utils.tabGen(nbOfTab + 1) + "val.put" + fieldName
							+ "(Utils.extractIdFromUri(joInside.getString(\"" + f.getOrignalName() + "\")));";

				} else if (type.equalsIgnoreCase(Constants.ARRAY)) {

					// Handling array of primitive types
					String jTableName = Utils.getNameCamelCase(f.getName());
					String jValName = Utils.getNamePascalCase(jTableName) + "Cv" + externalRefCtr;
					externalRefCtr++;
					Table t = Utils.findTableWithName(jTableName, tables);
					if (t != null) {
						line = Utils.tabGen(nbOfTab) + "if( joInside.isNull(\"" + f.getOrignalName()
								+ "\") == false) {\n";
						line += Utils.tabGen(nbOfTab + 1) + "val.put" + fieldName
								+ "(Utils.extractIdFromUri(joInside.getString(\"" + f.getOrignalName() + "\")));";

						if (!jsonArrayInitiated) {
							line = "\n" + Utils.tabGen(nbOfTab) + "JSONArray jArray;\n";
							line += Utils.tabGen(nbOfTab) + "if( joInside.isNull(\"" + f.getOrignalName()
									+ "\") == false) {\n";
							line += Utils.tabGen(nbOfTab + 1) + "jArray = joInside.getJSONArray(\""
									+ f.getOrignalName() + "\");";
							jsonArrayInitiated = true;
						} else {
							line = Utils.tabGen(nbOfTab) + "if( joInside.isNull(\"" + f.getOrignalName()
									+ "\") == false) {\n";
							line += Utils.tabGen(nbOfTab + 1) + "jArray = joInside.getJSONArray(\""
									+ f.getOrignalName() + "\");";
						}

						nbOfTab = nbOfTab + 1;
						line += "\n" + Utils.tabGen(nbOfTab)
								+ "for(int j = 0, count = jArray.length(); j< count; j++) {";

						// resolving the enum type
						type = t.getFields().get(1).getType();
						line += "\n";

						boolean isURI = false;
						if (f.getConstraint() != null) {
							line += Utils.tabGen(nbOfTab + 1) + "final int v = Utils.extractIdFromUri(jArray.getString(j));";
							isURI = true;
						} else {

							if (type.equalsIgnoreCase(Constants.INT)) {
								line += Utils.tabGen(nbOfTab + 1) + "int v = jArray.getInt(j);";
							} else { // String by default
								line += Utils.tabGen(nbOfTab + 1) + "String v = jArray.getString(j);";
							}
						}

						if (isURI) {
							jTableName = Utils.createJunctionTableName(table.getOriginalName(), f.getConstraint());
							Table juncTable = Utils.findTableWithName(jTableName, tables);
							jTableName = juncTable.getName();
							jValName = juncTable.getName() + "Cv" + externalRefCtr;

							line += "\n" + Utils.tabGen(nbOfTab + 1) + jTableName + "ContentValues " + jValName
									+ " = new " + jTableName + "ContentValues();";

							final String fName1 = juncTable.getFields().get(0).getName();
							final String fName2 = juncTable.getFields().get(1).getName();
							if (juncTable.getFields().get(0).getConstraint().equalsIgnoreCase(table.getOriginalName())) {
								line += "\n" + Utils.tabGen(nbOfTab + 1) + jValName + ".put"
										+ Utils.getNameCamelCase(fName1) + "(id);";
								line += "\n" + Utils.tabGen(nbOfTab + 1) + jValName + ".put"
										+ Utils.getNameCamelCase(fName2) + "(v);";
							} else {
								line += "\n" + Utils.tabGen(nbOfTab + 1) + jValName + ".put"
										+ Utils.getNameCamelCase(fName2) + "(id);";
								line += "\n" + Utils.tabGen(nbOfTab + 1) + jValName + ".put"
										+ Utils.getNameCamelCase(fName1) + "(v);";
							}

							line += "\n" + Utils.tabGen(nbOfTab + 1) + "ctxt.getContentResolver().insert("
									+ juncTable.getName() + "Columns.CONTENT_URI, " + jValName + ".values());";

						} else {
							line += "\n" + Utils.tabGen(nbOfTab + 1) + jTableName + "ContentValues " + jValName
									+ " = new " + jTableName + "ContentValues();";
							line += "\n" + Utils.tabGen(nbOfTab + 1) + jValName + ".put"
									+ Utils.getNameCamelCase(t.getFields().get(0).getName()) + "(id);";
							line += "\n" + Utils.tabGen(nbOfTab + 1) + jValName + ".put"
									+ Utils.getNameCamelCase(t.getFields().get(1).getName()) + "(v);";
							line += "\n" + Utils.tabGen(nbOfTab + 1) + "ctxt.getContentResolver().insert(" + jTableName
									+ "Columns.CONTENT_URI, " + jValName + ".values());";
						}
						line += "\n" + Utils.tabGen(nbOfTab) + "}\n";
						nbOfTab = nbOfTab - 1;
						line += "\n" + Utils.tabGen(nbOfTab) + "}\n";
					}

				} else if (type.equalsIgnoreCase(Constants.JUNCTION)) {

					// Filling junction tables with param from method and NOT
					// from json;
					String juncTableName = Utils.createJunctionTableName(table.getOriginalName(), f.getConstraint());
					final String juncValName = Utils.getNamePascalCase(f.getConstraint()) + "Cv" + externalRefCtr;
					System.out.println("JsonParser JUNCTION field : " + f.getName() + " & jTableName = "
							+ juncTableName + " & jValName = " + juncValName);
					externalRefCtr++;
					Table t = Utils.findTableWithName(juncTableName, tables);
					if (t != null) {
						if (isInArray) {
							line = Constants.PARSER_STYLE_START_ID_ARRAY.replaceAll("xax", table.getName());
						} else {
							line = Constants.PARSER_STYLE_START_ID_OBJECT.replaceAll("xax", table.getName());
						}
						javaOutput.set(0, line);
						juncTableName = Utils.getNameCamelCase(juncTableName);
						line = "\n" + Utils.tabGen(nbOfTab) + juncTableName + "ContentValues " + juncValName
								+ " = new " + juncTableName + "ContentValues();";
						// line += "\n		" + jValName + ".putIdDbNull();";
						final String fName1 = t.getFields().get(0).getName();
						final String fName2 = t.getFields().get(1).getName();
						if (t.getFields().get(0).getConstraint().equalsIgnoreCase(table.getOriginalName())) {
							line += "\n" + Utils.tabGen(nbOfTab) + juncValName + ".put"
									+ Utils.getNameCamelCase(fName1) + "(id);";
							line += "\n" + Utils.tabGen(nbOfTab) + juncValName + ".put"
									+ Utils.getNameCamelCase(fName2) + "(extId);";
						} else {
							line += "\n" + Utils.tabGen(nbOfTab) + juncValName + ".put"
									+ Utils.getNameCamelCase(fName2) + "(id);";
							line += "\n" + Utils.tabGen(nbOfTab) + juncValName + ".put"
									+ Utils.getNameCamelCase(fName1) + "(extId);";
						}
						line += "\n" + Utils.tabGen(nbOfTab) + "ctxt.getContentResolver().insert(" + juncTableName
								+ "Columns.CONTENT_URI, " + juncValName + ".values());\n";
					}
				} else if (type.equalsIgnoreCase(Constants.OBJECT)) {
					if (!objectInitiated) {
						line = Utils.tabGen(nbOfTab) + "if( joInside.isNull(\"" + f.getOrignalName()
								+ "\") == false)\n";
						line += Utils.tabGen(nbOfTab + 1) + "JSONObject insideObj = joInside.getJSONObject(\""
								+ f.getOrignalName() + "\");";
						objectInitiated = true;
					} else {
						line = Utils.tabGen(nbOfTab) + "insideObj = joInside.getJSONObject(\"" + f.getOrignalName()
								+ "\");";
					}
				} else if (type.equalsIgnoreCase(Constants.CALLER)) {
					String distTableName = f.getConstraint();
					Table distTable = Utils.findTableWithName(distTableName, tables);
					if (distTable.isInArray()) {
						endLine += "\n" + Utils.tabGen(nbOfTab) + "if(joInside.isNull(\"" + distTableName + "\") == false)";
						endLine += "\n" + Utils.tabGen(nbOfTab+1) + "parseJson" + Utils.getNameCamelCase(distTableName)
								+ "(joInside.getJSONArray(\"" + distTableName + "\"), ctxt, id);";
					} else {
						if (distTable != null) {
							endLine += "\n" + Utils.tabGen(nbOfTab) + "if(joInside.isNull(\"" + distTableName + "\") == false)";
							endLine += "\n" + Utils.tabGen(nbOfTab+1) + "parseJson"
									+ Utils.getNameCamelCase(distTableName) + "(joInside.getJSONObject(\""
									+ distTableName + "\"), ctxt, id);";
						}
					}
				} else if (f.getConstraint() != null) {
					line = Utils.tabGen(nbOfTab) + "if( joInside.isNull(\"" + f.getOrignalName() + "\") == false)\n";
					line += Utils.tabGen(nbOfTab + 1) + "val.put" + fieldName + "(joInside.get" + type + "(\""
							+ f.getOrignalName() + "\"));";
				} else {
					if (type.equalsIgnoreCase(Constants.BOOL)) {
						type = Constants.DB_BOOL;
					}

					line = Utils.tabGen(nbOfTab) + "if( joInside.isNull(\"" + f.getOrignalName() + "\") == false)\n";
					line += Utils.tabGen(nbOfTab + 1) + "val.put" + fieldName + "(joInside.get" + type + "(\""
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
			javaOutput.add(Constants.PARSER_STYLE_END_ARRAY + "\n");
		} else {
			javaOutput.add(Constants.PARSER_STYLE_END_OBJECT + "\n");
		}
		return javaOutput;
	}

}
