package be.qaz.amm.generator;

import java.util.ArrayList;

import be.qaz.amm.Constants;
import be.qaz.amm.Utils;
import be.qaz.amm.model.Field;
import be.qaz.amm.model.Table;

public class BeanGenerator {

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
				if (!fieldType.equalsIgnoreCase(Constants.JUNCTION) && !fieldType.equalsIgnoreCase(Constants.CALLER)) {
					fieldName = Utils.getNamePascalCase(f.getName());
					if (f.getType() != Constants.OBJECT) {
						if (fieldType.equalsIgnoreCase(Constants.URI)) {
							fieldType = Constants.INT;
						}

						if (fieldType != Constants.ARRAY) {
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
				if (!fieldType.equalsIgnoreCase(Constants.JUNCTION) && !fieldType.equalsIgnoreCase(Constants.CALLER)) {
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
				if (!fieldType.equalsIgnoreCase(Constants.JUNCTION) && !fieldType.equalsIgnoreCase(Constants.CALLER)) {
					fieldName = Utils.getNamePascalCase(f.getName());
					fieldNameMethod = Utils.getNameCamelCase(f.getName());
					if (fieldType != Constants.OBJECT) {
						if (fieldType.equalsIgnoreCase(Constants.URI)) {
							fieldType = Constants.INT;
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
		javaOutput.addAll(ParserGenerator.generateJavaParser(table));
		javaOutput.add("}");
		return javaOutput;
	}

}
