package be.qaz.amm.generator.beans;

import java.util.ArrayList;

import be.qaz.amm.Constants;
import be.qaz.amm.Utils;
import be.qaz.amm.model.Field;
import be.qaz.amm.model.Table;

public class AndroidActiveBeanGenerator extends JavaBeanGenerator {

    private static final String DEFAULT_SUFFIX = "";
    private static final String EXTRA_IMPORT_AA = "import com.activeandroid.Model;\nimport com.activeandroid.annotation.Column;\nimport com.activeandroid.annotation.Table;";

    public static String getClassName(Table table) {
        return table.getName() + DEFAULT_SUFFIX;
    }

    /**
     * Generate Java beans with getters & setters
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

        attributStringsOutput.add("package " + Constants.DEFAULT_PACKAGE + ".model;");
        attributStringsOutput.add(DEFAULT_IMPORT);
        attributStringsOutput.add(EXTRA_IMPORT_AA);

        attributStringsOutput.add("@Table(name = \"" + Utils.checkSqlForbiddenName(className) + "\")");
        attributStringsOutput.add("public class " + className + " extends Model {");

        String constructorParamLine = "\n" + Utils.tabGen(1) + "public " + className + "(";
        String constructorBodyLine = Utils.tabGen(2) + "super();";

        ArrayList<Field> fields = table.getFields();

        for (Field f : fields) {
            if (f != null) {
                // attributes
                fieldName = Utils.checkJavaForbiddenName(f.getName() + DEFAULT_SUFFIX);
                fieldType = giveRightType(f);
                if (!f.getType().equalsIgnoreCase(Constants.OBJECT)) {

                    f.addAnnotation("@Column(name = \"" + Utils.checkJavaForbiddenName(f.getOrignalName()) + "\")");
                    line = createAttribute(fieldName, fieldType, f.getAnnotations());
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
                    f.setAnnotations(new ArrayList<String>());
                }
            }
        }

        // full param constructor
        constructorParamLine += ") {";
        constructorStringsOutput.add(constructorParamLine);
        constructorBodyLine += "\n" + Utils.tabGen(1) + "}";
        constructorStringsOutput.add(constructorBodyLine);

        // parameterless constructor
        constructorBodyLine = "\n" + Utils.tabGen(1) + "public " + className + "() {";
        constructorBodyLine += "\n" + Utils.tabGen(2) + "super();\n" + Utils.tabGen(1) + "}";
        constructorStringsOutput.add(constructorBodyLine);

        methodStringsOutput.add(createToStringMethod(table));

        // reconstructing the class
        attributStringsOutput.addAll(constructorStringsOutput);
        attributStringsOutput.addAll(methodStringsOutput);
        attributStringsOutput.add("}");
        return attributStringsOutput;
    }

    private static ArrayList<String> createJacksonToAAConstructor(Table table) {
        final String className = getClassName(table);
        ArrayList<String> output = new ArrayList<String>();
        String fieldName;
        output.add(Utils.tabGen(1) + "public " + className + "(" + className + "Json obj) {");
        output.add(Utils.tabGen(2) + "super();");
        if (table.getFields() != null) {
            for (Field f : table.getFields()) {
                fieldName = Utils.checkJavaForbiddenName(f.getName() + DEFAULT_SUFFIX);
                output.add("this." + fieldName + " = " + fieldName + ";");
            }
        }
        output.add("}");
        return output;
    }

    protected static String giveRightType(Field f) {
        String type = f.getType().split(";")[0];
        if (type.equalsIgnoreCase(Constants.URI)) {
            type = Constants.INT;
            f.addAnnotation("//URI to " + f.getConstraint());
        }

        if (!Constants.PRIMITIVE_TYPES.contains(type) && !type.equals(Constants.ARRAY)) {
            type += DEFAULT_SUFFIX;
        }

        if (type.equalsIgnoreCase(Constants.ARRAY)) {
            if (f.getType().split(";").length > 1) {
                String arrayType = f.getType().split(";")[1];
                if (arrayType.equalsIgnoreCase(Constants.URI)) {
                    arrayType = Constants.DB_INT;
                    f.addAnnotation("//URIs to " + f.getConstraint());
                } else {
                    if (!Constants.PRIMITIVE_TYPES.contains(arrayType)) {
                        arrayType += DEFAULT_SUFFIX;
                    }
                }
                type = "ArrayList<" + arrayType + ">";
            } else {
                type = "ArrayList";
            }
        }
        return type;
    }
}
