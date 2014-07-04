package model;

import java.util.ArrayList;

/**
 * @author quentin
 * 
 */
public class Table {

	private String originalName;
	private String name;
	private String constraint;
	private ArrayList<Field> fields = new ArrayList<Field>();
	private boolean isInArray = false; // used to create parser accordingly
										// instead of having a JSONObject for
										// parameter it will be a JSONArray

	public Table() {
		super();
	}

	public Table(String name) {
		super();
		this.name = name;
	}

	public Table(String originalName, String name, String constraint, ArrayList<Field> fields, boolean isInArray) {
		super();
		this.originalName = originalName;
		this.name = name;
		this.constraint = constraint;
		this.fields = fields;
		this.isInArray = isInArray;
	}

	public String getOriginalName() {
		return originalName;
	}

	public void setOriginalName(String originalName) {
		this.originalName = originalName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getConstraint() {
		return constraint;
	}

	public void setConstraint(String constrain) {
		this.constraint = constrain;
	}

	public ArrayList<Field> getFields() {
		return fields;
	}

	public void setFields(ArrayList<Field> fields) {
		this.fields = fields;
	}

	public boolean isInArray() {
		return isInArray;
	}

	public void setInArray(boolean isInArray) {
		this.isInArray = isInArray;
	}

	@Override
	public String toString() {
		String s = "\nTable @" + name + "@ is in array !" + isInArray + "! containing";
		if (fields != null) {
			for (Field f : fields) {
				s += "\n #" + f.getName() + "# of type ?" + f.getType() + "? with constraint !" + f.getConstraint()
						+ "!";
			}
		} else {
			s += "fields are null";
		}
		return s;
	}

}
