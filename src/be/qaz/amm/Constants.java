package be.qaz.amm;

import java.util.Arrays;
import java.util.List;

public class Constants {

    public static final String DEFAULT_PACKAGE = "be.qaz.jacksonorm.pojo";

    public static final String STRING = "String";
    public static final String INT = "int";
    public static final String DOUBLE = "double";
    public static final String LONG = "long";
    public static final String LONG_ARRAY = "Long";
    public static final String NUMBER = "Number";
    public static final String DB_INT = "Integer";
    public static final String URI = "Uri";
    public static final String BOOL = "boolean";
    public static final String DB_BOOL = "Boolean";
    public static final String DATE = "Date";
    public static final String ARRAY = "Array";
    public static final String OBJECT = "Object";
    public static final String FOREIGN_KEY = "Foreign Key";
    public static final String JUNCTION = "#Junction#"; //used to create field linking tables implicitly
    public static final String CALLER = "#Caller#"; //used to create a method call to the next parser

    public static final List<String> PRIMITIVE_TYPES = Arrays.asList(new String[]{STRING, INT, DOUBLE, LONG, LONG_ARRAY, NUMBER, URI, BOOL, DATE});

    public static final String PARSER_STYLE_START_ARRAY =
            Utils.tabGen(1) + "public static ArrayList<xaxContentValues> parseJsonxax(JSONObject jo, Context ctxt) { \n"
                    + Utils.tabGen(2) + "ArrayList<xaxContentValues> list = new ArrayList<xaxContentValues>(); \n"
                    + Utils.tabGen(2) + "try { \n"
                    + Utils.tabGen(3) + "if (jo.isNull(\"deleted\") == false) {\n"
                    + Utils.tabGen(4) + "JSONArray jObjects = jo.getJSONArray(\"deleted\");\n"
                    + Utils.tabGen(4) + "for (int h = 0; h < jObjects.length(); h++) {\n"
                    + Utils.tabGen(5) + "xaxSelection where = new xaxSelection();\n"
                    + Utils.tabGen(5) + "where.idDb(jObjects.getInt(h));\n"
                    + Utils.tabGen(5) + "where.delete(ctxt.getContentResolver());\n"
                    + Utils.tabGen(4) + "}\n"
                    + Utils.tabGen(3) + "}\n"
                    + Utils.tabGen(3) + "xaxContentValues val; \n"
                    + Utils.tabGen(3) + "JSONArray jaObjects = jo.getJSONArray(\"objects\"); \n"
                    + Utils.tabGen(3) + "for (int i = 0; i < jaObjects.length(); i++) { \n"
                    + Utils.tabGen(4) + "JSONObject joInside = jaObjects.getJSONObject(i);\n"
                    + Utils.tabGen(4) + "val = new xaxContentValues();";

    public static final String PARSER_STYLE_START_OBJECT = Utils.tabGen(1)
            + "public static ArrayList<xaxContentValues> parseJsonxax(JSONObject joInside, Context ctxt) { \n"
            + Utils.tabGen(2) + "ArrayList<xaxContentValues> list = new ArrayList<xaxContentValues>(); \n"
            + Utils.tabGen(2) + "try { \n"
            + Utils.tabGen(3) + "xaxContentValues val = new xaxContentValues();";

    public static final String PARSER_STYLE_START_ID_ARRAY = Utils.tabGen(1)
            + "public static ArrayList<xaxContentValues> parseJsonxax(JSONArray joInput, Context ctxt, int extId) { \n"
            + Utils.tabGen(2) + "ArrayList<xaxContentValues> list = new ArrayList<xaxContentValues>(); \n"
            + Utils.tabGen(2) + "try { \n"
            + Utils.tabGen(3) + "xaxContentValues val; \n"
            + Utils.tabGen(3) + "for (int i = 0; i < joInput.length(); i++) { \n"
            + Utils.tabGen(4) + "JSONObject joInside = joInput.getJSONObject(i);\n"
            + Utils.tabGen(4) + "val = new xaxContentValues();";

    public static final String PARSER_STYLE_START_ID_OBJECT = Utils.tabGen(1)
            + "public static ArrayList<xaxContentValues> parseJsonxax(JSONObject joInside, Context ctxt, int extId) { \n"
            + Utils.tabGen(2) + "ArrayList<xaxContentValues> list = new ArrayList<xaxContentValues>(); \n"
            + Utils.tabGen(2) + "try { \n"
            + Utils.tabGen(3) + "xaxContentValues val = new xaxContentValues();";

    public static final String PARSER_STYLE_END_ARRAY = Utils.tabGen(4) + "list.add(val);\n"
            + Utils.tabGen(3) + "} \n"
            + Utils.tabGen(2) + "} catch (JSONException e) {\n"
            + Utils.tabGen(3) + "e.printStackTrace();\n"
            + Utils.tabGen(2) + "} \n"
            + Utils.tabGen(2) + "return list;\n"
            + Utils.tabGen(1) + "}";

    public static final String PARSER_STYLE_END_OBJECT = Utils.tabGen(3) + "list.add(val);\n"
            + Utils.tabGen(2)
            + "} catch (JSONException e) {\n"
            + Utils.tabGen(3) + "e.printStackTrace();\n"
            + Utils.tabGen(2) + "} \n"
            + Utils.tabGen(2) + "return list;\n"
            + Utils.tabGen(1) + "}";

    public static final String EXTRACT_URI_METHOD = Utils.tabGen(1)
            + "public static Pattern pattern = Pattern.compile(\"\\d+\");\n"
            + Utils.tabGen(1) + "public static String extractFromQuote(String str) {\n"
            + Utils.tabGen(2) + "String s = str.substring(1, str.length() - 1);\n"
            + Utils.tabGen(2) + "return s;\n	}\n\n"
            + Utils.tabGen(1) + "public static int extractIdFromUri(String uri) {\n"
            + Utils.tabGen(2) + "uri = extractFromQuote(uri);\n"
            + Utils.tabGen(2) + "Matcher m = pattern.matcher(uri);\n"
            + Utils.tabGen(2) + "if (m.find()) {\n"
            + Utils.tabGen(3) + "System.out.println(m.group(0));\n"
            + Utils.tabGen(2) + "}\n"
            + Utils.tabGen(2) + "int id = Integer.valueOf(m.group(0));\n"
            + Utils.tabGen(2) + "return id;\n"
            + Utils.tabGen(1) + "}";

    public static final String JUNCTION_TABLE = "junction table";

}
