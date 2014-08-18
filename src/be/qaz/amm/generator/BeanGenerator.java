package be.qaz.amm.generator;

import java.util.ArrayList;

import be.qaz.amm.Constants;
import be.qaz.amm.Utils;
import be.qaz.amm.model.Field;
import be.qaz.amm.model.Table;

public class BeanGenerator {

	private static final String DEFAULT_PACKAGE = "com.example.kylewbanksblog.json";
	private static final String DEFAULT_IMPORT = "import java.sql.Date;\nimport java.util.ArrayList;";
	private static final String EXTRA_IMPORT_JACKSON = "\nimport com.fasterxml.jackson.annotation.JsonProperty;";
	private static final String EXTRA_IMPORT_AA = "import com.activeandroid.Model;\nimport com.activeandroid.annotation.Column;\nimport com.activeandroid.annotation.Table;";

	/**
	 * Generate Java beans with getters & setters as well as a json parser
	 * 
	 * @param table
	 * @return
	 */
	public static ArrayList<String> generateJavaBean(Table table) {
		if (table == null || table.getFields() == null
				|| table.getFields().size() == 0)
			return null;

		ArrayList<String> attributStringsOutput = new ArrayList<String>();
		ArrayList<String> constructorStringsOutput = new ArrayList<String>();
		ArrayList<String> methodStringsOutput = new ArrayList<String>();
		String fieldName;
		String fieldType;
		String line;

		attributStringsOutput.add("package " + DEFAULT_PACKAGE + ";");
		attributStringsOutput.add(DEFAULT_IMPORT);
		attributStringsOutput.add(EXTRA_IMPORT_JACKSON);
		// attributStringsOutput.add(EXTRA_IMPORT_AA);

		if (table.getAnnotations() != null && table.getAnnotations().size() > 0) {
			for (String anotation : table.getAnnotations()) {
				attributStringsOutput.add("\n" + anotation);
			}
		}
		attributStringsOutput.add("public class " + table.getName() + " {");

		String constructorParamLine = "\n" + Utils.tabGen(1) + "public "
				+ table.getName() + "(";
		String constructorBodyLine = Utils.tabGen(2) + "super();";

		ArrayList<Field> fields = table.getFields();
		for (Field f : fields) {
			if (f != null) {
				// attributes
				fieldType = f.getType().split(";")[0];
				if (!fieldType.equalsIgnoreCase(Constants.JUNCTION)
						&& !fieldType.equalsIgnoreCase(Constants.CALLER)) {
					fieldName = Utils.checkJavaForbiddenName(f.getName());
					if (!f.getType().equalsIgnoreCase(Constants.OBJECT)) {
						if (fieldType.equalsIgnoreCase(Constants.URI)) {
							fieldType = Constants.STRING;
						}

						if (fieldType.equalsIgnoreCase(Constants.ARRAY)) {
							if (f.getType().split(";").length > 1) {
								fieldType = "ArrayList<"
										+ f.getType().split(";")[1] + ">";
							} else {
								fieldType = "ArrayList";
							}
						}
						line = createAttributes(fieldName, fieldType,
								f.getAnnotations());
						attributStringsOutput.add(line);

						// Aggregating constructor parameters
						constructorParamLine += fieldType + " " + fieldName;
						if (fields.indexOf(f) < fields.size() - 1) {
							constructorParamLine += ", ";
						}

						// Aggregating constructor body
						constructorBodyLine += "\n" + Utils.tabGen(2) + "this."
								+ fieldName + " = " + fieldName + ";";

						// Getter & setter
						line = createGetter(fieldType, fieldName);
						line += createSetter(fieldType, fieldName);
						methodStringsOutput.add(line);
					}
				}
			}
		}

		constructorParamLine += ") {";
		constructorStringsOutput.add(constructorParamLine);
		constructorBodyLine += "\n" + Utils.tabGen(1) + "}";
		constructorStringsOutput.add(constructorBodyLine);

		// parameter less constructor
		constructorBodyLine = "\n" + Utils.tabGen(1) + "public "
				+ table.getName() + "() {";
		constructorBodyLine += "\n" + Utils.tabGen(2) + "super();\n"
				+ Utils.tabGen(1) + "}";
		constructorStringsOutput.add(constructorBodyLine);

		methodStringsOutput.add(createToStringMethod(table));
		// javaOutput.addAll(ParserGenerator.generateJavaParser(table));

		// reconstructing the class
		attributStringsOutput.addAll(constructorStringsOutput);
		attributStringsOutput.addAll(methodStringsOutput);
		attributStringsOutput.add("}");
		return attributStringsOutput;
	}

	private static String createAttributes(String name, String type,
			ArrayList<String> annotations) {
		String line = "";

		if (annotations != null && annotations.size() > 0) {
			for (String anotation : annotations) {
				line += "\n" + Utils.tabGen(1) + anotation;
			}
		}

		line += "\n" + Utils.tabGen(1) + "public " + type + " " + name + ";";
		return line;
	}

	private static String createGetter(String type, String name) {
		String line = Utils.tabGen(1) + "public " + type + " get"
				+ Utils.getNameCamelCase(name) + "() {\n";
		line += Utils.tabGen(2) + "return this." + name + ";\n	}\n";
		return line;
	}

	private static String createSetter(String type, String name) {
		String line = "\n" + Utils.tabGen(1) + "public void set"
				+ Utils.getNameCamelCase(name) + "(" + type + " " + name
				+ ") {\n";
		line += Utils.tabGen(2) + "this." + name + " = " + name + ";\n"
				+ Utils.tabGen(1) + "}\n";
		return line;
	}

	private static String createToStringMethod(Table table) {
		String line = Utils.tabGen(1) + "@Override\n";
		line += Utils.tabGen(1) + "public String toString() {\n";
		line += Utils.tabGen(2) + "final String s = \"Object : "
				+ table.getName() + " : \"";
		if (table.getFields() != null) {
			for (Field f : table.getFields()) {
				line += "\n" + Utils.tabGen(2) + "+ \" "
						+ Utils.checkJavaForbiddenName(f.getName())
						+ " = \" + "
						+ Utils.checkJavaForbiddenName(f.getName());
			}
		}
		line += ";";
		line += "\n" + Utils.tabGen(2) + "return s;";
		line += "\n" + Utils.tabGen(1) + "}";
		return line;
	}
}
