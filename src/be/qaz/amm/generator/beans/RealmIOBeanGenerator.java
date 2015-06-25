package be.qaz.amm.generator.beans;

import be.qaz.amm.Constants;
import be.qaz.amm.Utils;
import be.qaz.amm.model.Field;
import be.qaz.amm.model.Table;

import java.util.ArrayList;

public class RealmIOBeanGenerator {

    protected static final String DEFAULT_IMPORT = "import java.sql.Date;\nimport java.util.ArrayList;\nimport java.util.Collection;\nimport io.realm.RealmObject;";
    private static final String DEFAULT_SUFFIX = "";

    public static String getClassName(Table table) {
        return table.getName() + DEFAULT_SUFFIX;
    }

    /**
     * Generate Realm IO beans with getters & setters
     *
     * @param table
     * @return
     */
    public static ArrayList<String> generateJavaBean(Table table) {
        if (table == null || table.getFields() == null || table.getFields().size() == 0)
            return null;

        final String className = getClassName(table);

        ArrayList<String> attributStringsOutput = new ArrayList<String>();
        ArrayList<String> constructorStringsOutput = new ArrayList<String>();
        ArrayList<String> methodStringsOutput = new ArrayList<String>();
        String fieldName;
        String fieldType;
        String line;

        attributStringsOutput.add("package " + Constants.DEFAULT_PACKAGE + ";");
        attributStringsOutput.add(DEFAULT_IMPORT);

        if (table.getAnnotations() != null && table.getAnnotations().size() > 0) {
            for (String anotation : table.getAnnotations()) {
                attributStringsOutput.add("\n" + anotation);
            }
        }
        attributStringsOutput.add("public class " + className + " extends RealmObject {");

        String constructorParamLine = "\n" + Utils.tabGen(1) + "public " + className + "(";
        String constructorBodyLine = Utils.tabGen(2) + "super();";

        ArrayList<Field> fields = table.getFields();

        for (Field f : fields) {
            if (f != null) {
                // attributes
                fieldName = Utils.checkJavaForbiddenName(f.getName() + DEFAULT_SUFFIX);
                fieldType = findType(f);

                line = createAttributes(fieldName, fieldType, f.getAnnotations());
                attributStringsOutput.add(line);

                // Aggregating constructor parameters
                constructorParamLine += fieldType + " " + fieldName;
                if (fields.indexOf(f) < fields.size() - 1) {
                    constructorParamLine += ", ";
                }

                // Aggregating constructor body
                constructorBodyLine += "\n" + Utils.tabGen(2) + "this." + fieldName + " = " + fieldName + ";";

                // Getter & setter
                line = createGetter(fieldType, fieldName);
                line += createSetter(fieldType, fieldName);
                methodStringsOutput.add(line);
            }
        }
        // parameterless constructor
        constructorBodyLine = "\n" + Utils.tabGen(1) + "public " + className + "() {";
        constructorBodyLine += "\n" + Utils.tabGen(2) + "super();\n" + Utils.tabGen(1) + "}";
        constructorStringsOutput.add(constructorBodyLine);

        // full param constructor
        /*constructorParamLine += ") {";
        constructorStringsOutput.add(constructorParamLine);
        constructorBodyLine += "\n" + Utils.tabGen(1) + "}";
        constructorStringsOutput.add(constructorBodyLine);*/

        //methodStringsOutput.add(createToStringMethod(table));

        // reconstructing the class
        attributStringsOutput.addAll(constructorStringsOutput);
        attributStringsOutput.addAll(methodStringsOutput);
        attributStringsOutput.add("}");
        return attributStringsOutput;
    }

    protected static String createAttributes(String name, String type, ArrayList<String> annotations) {
        String line = "";

        if (annotations != null && annotations.size() > 0) {
            for (String anotation : annotations) {
                line += "\n" + Utils.tabGen(1) + anotation;
            }
        }
        line += "\n" + Utils.tabGen(1) + "private " + type + " " + name + ";";
        return line;
    }

    protected static String createGetter(String type, String name) {
        String line = Utils.tabGen(1) + "public " + type + " get" + Utils.getNameCamelCase(name) + "() {\n";
        line += Utils.tabGen(2) + "return this." + name + ";\n	}\n";
        return line;
    }

    protected static String createSetter(String type, String name) {
        String line = "\n" + Utils.tabGen(1) + "public void set" + Utils.getNameCamelCase(name) + "(" + type + " "
                + name + ") {\n";
        line += Utils.tabGen(2) + "this." + name + " = " + name + ";\n" + Utils.tabGen(1) + "}\n";
        return line;
    }

    protected static String createToStringMethod(Table table) {
        final String className = getClassName(table);
        String line = Utils.tabGen(1) + "@Override\n";
        line += Utils.tabGen(1) + "public String toString() {\n";
        line += Utils.tabGen(2) + "final String s = \"Object : " + className + " : \"";
        if (table.getFields() != null) {
            for (Field f : table.getFields()) {
                line += "\n" + Utils.tabGen(2) + "+ \" " + Utils.checkJavaForbiddenName(f.getName()) + " = \" + "
                        + Utils.checkJavaForbiddenName(f.getName());
            }
        }
        line += ";";
        line += "\n" + Utils.tabGen(2) + "return s;";
        line += "\n" + Utils.tabGen(1) + "}";
        return line;
    }

    protected static String findType(Field f) {
        String type = f.getType().split(";")[0];
        if (type.equalsIgnoreCase(Constants.URI)) {
            type = Constants.STRING;
            f.addAnnotation("//URI to " + f.getConstraint());
        }

        if (type.equalsIgnoreCase(Constants.ARRAY)) {
            if (f.getType().split(";").length > 1) {
                String arrayType = f.getType().split(";")[1];
                if (arrayType.equalsIgnoreCase(Constants.URI)) {
                    arrayType = Constants.STRING;
                    f.addAnnotation("//URIs to " + f.getConstraint());
                }
                type = "ArrayList<" + arrayType + ">";
            } else {
                type = "ArrayList";
            }
        }
        return type;
    }
}
