package be.qaz.amm.generator;

import be.qaz.amm.Constants;
import be.qaz.amm.Utils;
import be.qaz.amm.model.Table;

import java.util.ArrayList;

public class DbHelperGenerator {

    private static final String EXTRA_IMPORT_ORMLITE = "import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;\nimport com.j256.ormlite.dao.Dao;\nimport com.j256.ormlite.dao.RuntimeExceptionDao;\nimport com.j256.ormlite.support.ConnectionSource;\nimport com.j256.ormlite.table.TableUtils;";

    /**
     * Generate ORMLite related class
     *
     * @param table
     * @return array of string to be written in a file
     */
    public static ArrayList<String> generateJavaBean(ArrayList<Table> tables) {
        if (tables == null || tables.size() == 0) {
            return null;
        }

        ArrayList<String> classOutput = new ArrayList<String>();
        ArrayList<String> createOutput = new ArrayList<String>();
        ArrayList<String> deleteOutput = new ArrayList<String>();
        ArrayList<String> daoOutput = new ArrayList<String>();

        classOutput.add("package " + Constants.DEFAULT_PACKAGE + ";");
        classOutput.add(EXTRA_IMPORT_ORMLITE);
        classOutput.add("public class DatabaseHelper extends OrmLiteSqliteOpenHelper {");

        for (Table table : tables) {
            if (table != null) {
                final String tableName = table.getName();
                final String pascalTableName = Utils.getNamePascalCase(tableName);

                classOutput.add(Utils.tabGen(1) + "private Dao<" + tableName + ", Integer> " + pascalTableName + "Dao;");

                createOutput.add(Utils.tabGen(2) + "TableUtils.createTable(connectionSource, " + tableName + ".class);");
                deleteOutput.add(Utils.tabGen(2) + "TableUtils.dropTable(connectionSource, " + tableName + ".class, true);");

//				public Dao<CreatedBy, Integer> getCreateByDao() {
//					if (null == CreateByDao) {
//						try {
//							CreateByDao = getDao(CreatedBy.class);
//						}catch (java.sql.SQLException e) {
//							e.printStackTrace();
//						}
//					}
//					return CreateByDao;
//				}

                daoOutput.add("");
                daoOutput.add(Utils.tabGen(1) + "public Dao<" + tableName + ", Integer> get" + tableName + "Dao() {");
                daoOutput.add(Utils.tabGen(2) + "if (this." + pascalTableName + "Dao == null) {");
                daoOutput.add(Utils.tabGen(3) + "try {");
                daoOutput.add(Utils.tabGen(4) + pascalTableName + "Dao = getDao(" + tableName + ".class);");
                daoOutput.add(Utils.tabGen(3) + "}catch (java.sql.SQLException e) {");
                daoOutput.add(Utils.tabGen(4) + "e.printStackTrace();");
                daoOutput.add(Utils.tabGen(3) + "}");
                daoOutput.add(Utils.tabGen(2) + "}");
                daoOutput.add(Utils.tabGen(2) + "return " + pascalTableName + "Dao;");
                daoOutput.add(Utils.tabGen(1) + "}");
            }
        }

        classOutput.add("//Create calls");
        classOutput.addAll(createOutput);
        classOutput.add("//Upgrade calls");
        classOutput.addAll(deleteOutput);
        classOutput.add("//Dao");
        classOutput.addAll(daoOutput);

        classOutput.add("}");
        return classOutput;
    }

}
