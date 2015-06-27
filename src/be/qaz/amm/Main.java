package be.qaz.amm;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

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

import be.qaz.amm.generator.JsonGenerator;
import be.qaz.amm.generator.ParserGenerator;
import be.qaz.amm.generator.beans.JacksonBeanGenerator;
import be.qaz.amm.generator.beans.JavaBeanGenerator;
import be.qaz.amm.model.Field;
import be.qaz.amm.model.Table;

/**
 * @author Quentin AMOUDRUZ 2014-2015
 *         <p/>
 *         Convert json files to JAVA beans or other, can take multiple files as inputs, will link similar objects together
 *         It doesn't care how many object there is in the input files, it'll find them all.
 *         <p/>
 *         1 - Put json files in /etc/input
 *         2 - Run to generate
 *         3 - Let the magic (& horrible CPU usage) do the trick
 *         4 - Retrieve results in /etc/output
 */
public class Main {

    final static String INPUT_DIRECTORY_PATH = "etc/input";
    final static String OUTPUT_DIRECTORY_PATH = "etc/output/";

    final static boolean ENABLE_FOREIGN_KEYS = false;

    public static ArrayList<Table> mJunctionTables;
    public static ArrayList<Table> mTables;
    public static ArrayList<Field> mPrimaryKeys;

    public static ArrayList<Field> mCurrentTableFields;

    public static void main(String[] args) {
        System.out.println("START");
        mPrimaryKeys = new ArrayList<Field>();
        mTables = new ArrayList<Table>();
        mJunctionTables = new ArrayList<Table>();

        mTables = new ArrayList<Table>();
        mCurrentTableFields = new ArrayList<Field>();
        File file = new File(INPUT_DIRECTORY_PATH);
        for (File f : loadFiles(file)) {
            System.out.println("######## " + f.getName() + " FOUND ########");
            System.out.println("NEW PARSING");
            parseFile(f);
        }

        // Sorting fields alphabetically && adding foreign key field.
        for (Table table : mTables) {
            if (ENABLE_FOREIGN_KEYS) {
                table = addForeignKeyField(table);
            }
            Collections.sort(table.getFields(), new Comparator<Field>() {
                public int compare(Field f1, Field f2) {
                    return f1.getType().compareTo(f2.getType());
                }
            });
        }

        // generateParsers(mTables, false);
        generateJavaBeans(mTables, true);
        // generateJsonScheme(mTables, false);
        // generateDbHelper(mTables, false);

        System.out.println("######## END ######## ");
        System.out.println("######## TABLES DETAILS ########");
        System.out.println("TOTAL tables = " + mTables.size());
        for (Table t : mTables) {
            System.out.println(t.toString());
        }
    }

    public static void generateParsers(ArrayList<Table> tables, boolean consoleOutput) {
        System.out.println("######## GENERATED PARSER ########");
        ArrayList<String> generated = new ArrayList<String>();
        for (Table t : tables) {
            if (t != null && t.getFields() != null && t.getFields().size() > 0) {
                generated.addAll(ParserGenerator.generateJavaParser(t, tables));
            }
        }
        if (generated != null && generated.size() > 0) {
            if (consoleOutput) {
                for (String string : generated) {
                    System.out.println(string);
                }
            }
            String[] data = generated.toArray(new String[generated.size()]);
            writeFile(OUTPUT_DIRECTORY_PATH, "JsonParsers.java", data);
            generated.clear();
        }
    }

    public static void generateJsonScheme(ArrayList<Table> tables, boolean consoleOutput) {
        System.out.println("######## GENERATED JSON SCHEME ########");
        ArrayList<String> generated = new ArrayList<String>();
        for (Table t : tables) {
            if (t != null) {
                generated = JsonGenerator.generateJsonSchema(t, tables);
                if (generated != null && generated.size() > 0) {
                    if (consoleOutput) {
                        for (String string : generated) {
                            System.out.println(string);
                        }
                    }
                    String[] data = generated.toArray(new String[generated.size()]);
                    writeFile(OUTPUT_DIRECTORY_PATH, t.getOriginalName() + ".json", data);
                    generated.clear();
                }
            }
        }
    }

    public static void generateJavaBeans(ArrayList<Table> tables, boolean consoleOutput) {
        System.out.println("######## GENERATED JAVA BEANS ########");
        ArrayList<String> generated;
        for (Table t : tables) {
            if (t != null) {
                //Here, switch or add between the desired generator
                generated = JacksonBeanGenerator.generateJavaBean(t);
                if (generated != null && generated.size() > 0) {
                    if (consoleOutput) {
                        for (String string : generated) {
                            System.out.println(string);
                        }
                    }
                    String[] data = generated.toArray(new String[generated.size()]);
                    writeFile(OUTPUT_DIRECTORY_PATH + "/json/", t.getName() + "Json.java", data);
                    generated.clear();
                }
                //Here, switch or add between the desired generator
                generated = JavaBeanGenerator.generateJavaBean(t);
                if (generated != null && generated.size() > 0) {
                    if (consoleOutput) {
                        for (String string : generated) {
                            System.out.println(string);
                        }
                    }
                    String[] data = generated.toArray(new String[generated.size()]);
                    writeFile(OUTPUT_DIRECTORY_PATH + "/model/", t.getName() + ".java", data);
                    generated.clear();
                }
            }
        }
    }

    /**
     * Designed for ORMLite not really used anymore
     *
     * @param tables
     * @param consoleOutput
     */
    public static void generateDbHelper(ArrayList<Table> tables, boolean consoleOutput) {
        System.out.println("######## GENERATED DBHELPER ########");
        ArrayList<String> generated = new ArrayList<String>();
        if (generated != null && generated.size() > 0) {
            if (consoleOutput) {
                for (String string : generated) {
                    System.out.println(string);
                }
            }
            String[] data = generated.toArray(new String[generated.size()]);
            writeFile(OUTPUT_DIRECTORY_PATH, "DatabaseHelper.java", data);
            generated.clear();
        }
    }

    public static void writeFile(String path, String name, String[] lines) {
        BufferedWriter writer = null;
        try {
            new File(path).mkdirs();
            final File outFile = new File(path + name);

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

    /**
     * Load files into software
     */
    public static File[] loadFiles(File directoryPath) {
        final File[] entityFiles = directoryPath.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return !pathname.getName().startsWith("_") && pathname.getName().endsWith(".json");
            }
        });
        return entityFiles;
    }

    /**
     * Loaded file is converted to a JSONObject here
     *
     * @param file
     */
    public static void parseFile(File file) {
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
     * Parse your JSONObject and fill the mTables array.
     *  @param jsonObj        JSONObject : you're about to parse
     * @param currentObjName String : the name of obj
     * @param parentObjName  String : the name of the object containing obj, ex : "recipes"
     */
    public static void parseJsonObject(JSONObject jsonObj, String currentObjName, String parentObjName,
                                       boolean isInArray) {

        String key;
        Object aObj;

        if (currentObjName != null && !currentObjName.isEmpty()) {
            // My jsons use a generic "objects" name regardless of the entity
            // you're querying
            if (currentObjName.equalsIgnoreCase("objects")) {
                currentObjName = parentObjName.substring(5);
            }

            Table currentTable = createTable(currentObjName, isInArray, parentObjName);
            if (currentTable != null) {
                mTables.add(currentTable);
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
                System.out.println("IN " + currentObjName + " # ARRAY : " + key);
                if (array.size() > 0) {
                    for (Object bObj : array) {
                        if (bObj instanceof JSONObject) {
                            parseJsonObject((JSONObject) bObj, key, currentObjName, true);
                            createField(key, currentObjName, Constants.ARRAY, key, bObj);
                        } else {
                            //In this case instead of creating an array of primitive, we create a new object containing the primitive value
                            //TODO NOT working but only useful for Realm.io lib
                            if(true) {
                                String objt = aObj.toString();
                                if (objt.length() > 30) {
                                    objt = objt.substring(0, 30);
                                    objt += "...";
                                }

                                System.out.println("++++ parseJsonObject/ARRAY key = " + key + " aObj = " + objt
                                        + " currentObjName " + currentObjName);

                                createField(key, currentObjName, Constants.ARRAY, key, bObj);
                            } else {
                                createField(key, currentObjName, Constants.ARRAY, key, bObj);
                                Table currentTable2 = createTable(key, true, currentObjName);
                                if (currentTable2 != null) {
                                    mTables.add(currentTable2);
                                }

                                final String type = Utils.javaTypeResolver(bObj);
                                currentTable2.addFields(new Field(key, key, type, currentObjName, currentTable2.getName()));
                                System.out.println("IN " + currentObjName + " # " + type + " : " + key);
                            }
                        }
                    }
                }

            } else {
                final String type = Utils.javaTypeResolver(aObj);
                System.out.println("IN " + currentObjName + " # " + type + " : " + key);
                createField(key, currentObjName, type, null, aObj);
            }
        }
    }

    public static Table createTable(String tableName, boolean isInArray, String parent) {
        Table table = null;
        if (tableName != null && !tableName.isEmpty()) {
            if (!Utils.tableAlreadyExists(tableName, mTables)) {
                table = new Table(tableName, Utils.getNameCamelCase(tableName), null, null, isInArray, parent);
            } else {
                table = Utils.findTableWithName(tableName, mTables);
                for (String existingParent : table.getParent()) {
                    if (parent.equalsIgnoreCase(existingParent)) {
                        return null;
                    }
                }
                table.addParent(parent);
                return null;
            }
        }
        return table;
    }

    /**
     * createField
     *
     * @param fieldName  String : field name
     * @param value      Object : content value used to determined its type
     * @param tableName  String : field's table
     * @param constraint String : foreign table name
     * @return created field
     */
    public static Field createField(String fieldName, String tableName, String type, String constraint, Object value) {
        // TODO bad design this shouldn't add the field to the table
        Field field = null;
        Table constraintTable = Utils.findTableWithName(constraint, mTables);

        if ((fieldName == null) || (tableName == null)) {
            return null;
        }
        String javaFieldName = Utils.getNamePascalCase(fieldName);

        if (type.equalsIgnoreCase(Constants.ARRAY)) {
            // TODO create specific type for array
            String arrayType = Utils.getNameCamelCase(Utils.javaTypeResolver(value));
            // One URI = two fieds, one for Jackson in String and one foreign
            // object collection
            if (arrayType.equalsIgnoreCase(Constants.URI)) {
                if (constraint == null) {
                    constraint = Utils.extractTableFromUri((String) value);
                }
            } else if (constraintTable != null && !arrayType.equalsIgnoreCase(Constants.URI)) {
                arrayType = constraintTable.getName();
            } else if (!arrayType.equalsIgnoreCase(Constants.URI)) {
                mTables.add(createArrayTable(fieldName, fieldName, arrayType, tableName));
                arrayType = fieldName;
            }

            type += ";" + Utils.getNameCamelCase(arrayType);
            // Uri are nothing but foreign key declaration
        } else if (type.equalsIgnoreCase(Constants.URI)) {
            constraint = Utils.extractTableFromUri((String) value);
        } else if (type.equalsIgnoreCase(Constants.OBJECT)) {
            type = constraintTable.getName();
        }

        field = new Field(fieldName, javaFieldName, type, constraint, tableName);

        // if the same field is found with another type it's overridden with
        // the default & more "do it all" String or ArrayList type also means
        // your json sucks
        Table table = Utils.findTableWithName(tableName, mTables);
        if (table != null) {
            ArrayList<Field> fs = table.getFields();
            if (fs == null) {
                fs = new ArrayList<Field>();
            }
            final int existingFieldIndex = Utils.findIndexOfFieldInTable(field, table);
            if (existingFieldIndex >= 0) {
                Field existingField = table.getFields().get(existingFieldIndex);
                if (!existingField.getType().equalsIgnoreCase(field.getType())) {

                    if (existingField.getType().contains(Constants.ARRAY)) {
                        field.setType(Constants.ARRAY);
                    } else {
                        field.setType(Constants.STRING);
                    }
                    table.getFields().set(existingFieldIndex, field);

                    System.out.println("FIELD TYPE CHANGED " + fieldName + " TO STRING FROM TABLE " + tableName);
                }
            } else {
                fs.add(field);
                table.setFields(fs);
            }

        } else {
            System.out.println("TABLE NOT FOUND " + tableName + " FOR FIELD " + fieldName);
        }
        return field;
    }

    /**
     * Create table containing array of primitive value.
     *
     * @param foreignTableName String : linked table name
     * @param tableName        String : table name
     * @param type             String : type of the array
     * @return created table
     */
    public static Table createArrayTable(String foreignTableName, String tableName, String type, String parent) {
        if (!Utils.tableAlreadyExists(tableName, mTables)) {
            Field f1 = new Field(foreignTableName + "_id", Utils.getNamePascalCase(foreignTableName) + "Id",
                    Constants.INT, foreignTableName, tableName);
            Field f2 = new Field(tableName, Utils.getNamePascalCase(tableName), type, null, tableName);
            Table arrayTable = createTable(tableName, false, null);
//			arrayTable.addFields(f1); TODO replace with related class
            arrayTable.addFields(f2);
            return arrayTable;
        }
        return null;
    }

    public static Table createJunctionTable(String refTable, String extTable) {
        final String tName = Utils.createJunctionTableName(refTable, extTable);
        if (!Utils.tableAlreadyExists(tName, mTables)) {
            final ArrayList<Field> fs = new ArrayList<Field>();
            final Field f1 = new Field(refTable + "_id", Utils.getNamePascalCase(refTable) + "Id", Constants.INT,
                    refTable, refTable);
            final Field f2 = new Field(extTable + "_id", Utils.getNamePascalCase(extTable) + "Id", Constants.INT,
                    extTable, refTable);
            fs.add(f1);
            fs.add(f2);
            final Table juncTable = new Table(tName, Utils.getNameCamelCase(tName), Constants.JUNCTION_TABLE, fs,
                    false, refTable);
            return juncTable;
        }
        return null;
    }

    public static Table addForeignKeyField(Table table) {
        if (table.getParent() != null && table.getParent().size() > 0) {
            for (String parent : table.getParent()) {
                if (!parent.contains("root")) {
                    createField(parent, table.getName(), Utils.getNameCamelCase(parent), Constants.FOREIGN_KEY, null);
                }
            }
        } else {
            // if more than one parent then ???
            // create junction table
        }
        return table;
    }
}
