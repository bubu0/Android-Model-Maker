package be.qaz.amm.generator;

import be.qaz.amm.Constants;
import be.qaz.amm.Utils;
import be.qaz.amm.model.Field;
import be.qaz.amm.model.Table;

import java.util.ArrayList;

public class JsonGenerator {

    /**
     * Create json files which could be used with
     * android-contentprovider-generator
     *
     * @param table
     * @return list of String ready to be written in a file
     */
    public static ArrayList<String> generateJsonSchema(Table table, ArrayList<Table> tables) {
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
        //this field will not null
        boolean hasIdConstraint = false;
        for (Field f : fields) {
            type = Utils.convertToDBType(f.getType());
            if (type.equals(Constants.URI) || (f.getConstraint() != null && f.getConstraint().equals(Constants.FOREIGN_KEY))) {
                type = Constants.DB_INT;
            }
            // TODO create an array of unused type for this case
            if (f.getName() != null && !f.getType().equals(Constants.OBJECT) && !f.getType().equals(Constants.JUNCTION)
                    && !f.getType().equals(Constants.CALLER) && !f.getType().contains(Constants.ARRAY)) {
                javaOutput.add(Utils.tabGen(2) + "{");
                usableFieldName = Utils.checkSqlForbiddenName(f.getOrignalName());
                line = Utils.tabGen(3) + "\"name\": \"" + usableFieldName + "\",";
                javaOutput.add(line);
                line = Utils.tabGen(3) + "\"type\": \"" + type + "\",";
                javaOutput.add(line);
                if (f.getType().equalsIgnoreCase(Constants.URI)
                        || (table.getConstraint() != null && (table.getConstraint().equals(Constants.JUNCTION_TABLE) || table.getConstraint().equals(Constants.FOREIGN_KEY)))) {
                    Table t = Utils.findTableWithName(f.getConstraint(), tables);
                    if (t != null) {
                        line = Utils.tabGen(3) + "\"nullable\": \"false\",\n";
                        line += Utils.tabGen(3) + "\"foreignKey\": {\n";
                        line += Utils.tabGen(4) + "\"table\": \"" + t.getOriginalName() + "\",\n";
                        line += Utils.tabGen(4) + "\"onDelete\": \"CASCADE\",\n";
                        line += Utils.tabGen(3) + "},";
                        javaOutput.add(line);
                    }
                }

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
                || (table.getConstraint() != null && table.getConstraint().equalsIgnoreCase(Constants.JUNCTION_TABLE))) {
            javaOutput.add(Utils.tabGen(1) + "\"constraints\": [");
            javaOutput.add(Utils.tabGen(2) + "{");
            javaOutput.add(Utils.tabGen(3) + "\"name\": \"unique_name\",");
            if (hasIdConstraint) {
                line = "\"definition\": \"unique (" + "id_db" + ") on conflict replace\"";
                javaOutput.add(Utils.tabGen(3) + line);
            } else {
                line = "\"definition\": \"unique ("
                        + Utils.checkSqlForbiddenName(table.getFields().get(0).getOrignalName()) + ", "
                        + Utils.checkSqlForbiddenName(table.getFields().get(1).getOrignalName())
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

}
