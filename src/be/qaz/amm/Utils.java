package be.qaz.amm;

import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.text.WordUtils;

import be.qaz.amm.model.Field;
import be.qaz.amm.model.Table;

public class Utils {

	public final static Pattern patternUri = Pattern.compile("^/.*");
	public final static Pattern patternDate = Pattern
			.compile("[0-9]{4}-[0-9]{2}-[0-9]{2}T[0-9]{2}:[0-9]{2}:[0-9]{2}.*");

	public final static Pattern patternDigits = Pattern.compile("\\d+");
	public final static Pattern patternQuoteTagValue = Pattern.compile("\"(.+?)\":.*");
	public final static Pattern patternQuoteTagValue2 = Pattern.compile("([a-zA-Z0-9_]+?):.*");
	public final static Pattern patternQuoteFieldName = Pattern.compile("\"[a-zA-Z0-9_]+?\"");
	public final static Pattern patternQuoteInsideQuote = Pattern.compile("\"(.+?)\"?");
	public final static Pattern patternQuoteNumber = Pattern.compile("[0-9]+.?[0-9]*");
	public final static Pattern patternQuoteUri = Pattern.compile("^/*");
	public final static Pattern patternQuoteDate = Pattern
			.compile("\"[0-9]{4}-[0-9]{2}-[0-9]{2}T[0-9]{2}:[0-9]{2}:[0-9]{2}.*");// Pattern.compile("\"[0-9]{4}-[0-9]{2}-[0-9]{2}T[0-9]{2}:[0-9]{2}:[0-9]{2}(\\.[0-9]*)?(\")?");
	public final static Pattern patternQuoteBool = Pattern.compile("[tT]rue|[fF]alse");

	public final static Pattern[] quotedPatterns = { patternQuoteInsideQuote, patternQuoteDate, patternQuoteNumber,
			patternQuoteUri, patternQuoteBool };
	public final static String[] patternTypes = { Constants.STRING, Constants.DATE, Constants.INT, Constants.URI,
			Constants.BOOL };

	public final static String[] forbiddenSqlNames = { "public", "private", "order", "id", "group", "count" };
	// will prevent those keys from identified as a foreign key
	public final static String[] exceptionKeys = { "resource_uri" }; // ,
																		// "created_by",
																		// "modified_by"

	public static String getNameCamelCase(String name) {
		if (name == null) {
			return null;
		}
		if (name.contains("_"))
			return WordUtils.capitalizeFully(name, new char[] { '_' }).replaceAll("_", "");
		else
			return name.substring(0, 1).toUpperCase() + name.substring(1);
	}

	public static String getNamePascalCase(String name) {
		if (name == null) {
			return null;
		}
		String s = WordUtils.capitalize(name, new char[] { '_' }).replaceAll("_", "");
		return s.substring(0, 1).toLowerCase() + s.substring(1);
	}

	public static boolean isKindOf(Pattern pat, String str) {
		return str.matches(pat.pattern());
	}

	public static String javaTypeResolver(Object o) {
		String result = null;
		if (o instanceof Integer || o instanceof Long || o instanceof Double) {
			result = patternTypes[2];
		} else if (o instanceof Date) {
			result = patternTypes[1];
		} else if (o instanceof String) {
			String s = (String) o;
			if(isKindOf(patternDigit, s)) {
				result = patternTypes[2];
				return result;
			}

			if (s.startsWith("[\"")) {
				s = s.substring(2, s.length() - 3);
			} else if (s.startsWith("[")) {
				s = s.substring(1, s.length() - 2);
			}

			result = patternTypes[0];
			if (isKindOf(patternDate, s)) {
				result = patternTypes[1];
			} else if (isKindOf(patternUri, s)) {
				result = patternTypes[3];
			} else if (s.equals(Constants.JUNCTION)) {
				result = Constants.JUNCTION;
			} else if (s.equals(Constants.CALLER)) {
				result = Constants.CALLER;
			}
		} else if (o instanceof Boolean) {
			result = patternTypes[4];
		} else if (o instanceof Object[]) {
			result = patternTypes[3];
		} else {
			// String by default?
			result = patternTypes[0];
		}
		return result;
	}

	public static String extractTag(String str) {
		final int pos = str.indexOf(":");
		if (pos > 0) {
			String s = str.substring(0, pos);
			if (s.startsWith("\""))
				s = s.substring(1);

			if (s.endsWith("\""))
				s = s.substring(0, pos - 2);

			return s;
		}
		return str;
	}

	public static String extractValue(String str) {
		final int pos = str.indexOf(":");
		String s = str.substring(pos + 1);
		return s;
	}

	public static String checkForbiddenName(String str) {
		for (int i = 0; i < forbiddenSqlNames.length; i++) {
			if (str.equalsIgnoreCase(forbiddenSqlNames[i])) {
				str += "_db";
			}
		}
		return str;
	}

	public static Pattern patternDigit = Pattern.compile("\\d+");

	public static String extractFromQuote(String str) {
		String s = str;
		if (str.startsWith("\"") && str.endsWith("\"")) {
			s = str.substring(1, str.length() - 1);
		} else if (str.startsWith("\"")) {
			s = str.substring(1, str.length());
		} else if (str.endsWith("\"")) {
			s = str.substring(0, str.length() - 1);
		}
		return s;
	}

	public static int extractIdFromUri(String uri) {
		uri = extractFromQuote(uri);
		Matcher m = patternDigits.matcher(uri);

		if (m.find()) {
			System.out.println(m.group(0));
		}

		int id = Integer.valueOf(m.group(0));
		return id;
	}

	public static String extractTableFromUri(String uri) {
		uri = extractFromQuote(uri);
		String[] tab = uri.split("/");
		int pos = tab.length - 2;
		String tableName = null;
		if (pos < tab.length && pos >= 0) {
			tableName = tab[pos];
		}
		return tableName;
	}

	public static boolean isTagAllowed(String tag) {
		if (tag == null)
			return false;

		for (int i = 0; i < exceptionKeys.length; i++) {
			if (tag.equalsIgnoreCase(exceptionKeys[i])) {
				return false;
			}
		}
		return true;
	}

	public static String convertToDBType(String type) {
		if (type.equalsIgnoreCase(Constants.INT)) {
			return Constants.DB_INT;
		} else if (type.equalsIgnoreCase(Constants.BOOL)) {
			return Constants.DB_BOOL;
		}
		return type;
	}

	public static String createJunctionTableName(String refName, String extName) {
		String s;
		if (refName.compareTo(extName) < 0)
			s = refName + "_" + extName;
		else {
			s = extName + "_" + refName;
		}
		return s;
	}

	/**
	 * Create tabulation to indent your code.
	 * 
	 * @param nbOfTab
	 *            int : number of tab needed
	 * @return String : tabulation(s)
	 */
	public static String tabGen(int nbOfTab) {
		String s = "";
		while (nbOfTab > 0) {
			s += "	";
			nbOfTab--;
		}
		return s;
	}

	public static boolean fieldAlreadyExists(Field field, ArrayList<Field> fields) {
		for (Field f : fields) {
			if (f.getOrignalName().equalsIgnoreCase(field.getOrignalName())) {
				return true;
			}
		}
		return false;
	}

	public static boolean fieldAlreadyExistsInTable(Field field, Table table) {
		if (table.getFields() != null) {
			for (Field f : table.getFields()) {
				if (f.getOrignalName().equalsIgnoreCase(field.getOrignalName())) {
					return true;
				}
			}
		}
		return false;
	}

	public static boolean tableAlreadyExists(String tableName) {
		if (Constants.tables.size() > 0 && tableName != null) {
			for (Table t : Constants.tables) {
				if (t.getName().equalsIgnoreCase(tableName) || t.getOriginalName().equalsIgnoreCase(tableName)) {
					return true;
				}
			}
		}
		return false;
	}

	public static Table findTableWithName(String tableName) {
		if (Constants.tables.size() > 0 && tableName != null) {
			for (Table t : Constants.tables) {
				if (t.getName().equalsIgnoreCase(tableName) || t.getOriginalName().equalsIgnoreCase(tableName)) {
					return t;
				}
			}
		}
		return null;
	}
}
