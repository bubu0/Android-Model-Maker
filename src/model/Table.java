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
	
	public Table() {
		super();
	}
	
	public Table(String name) {
		super();
		this.name = name;
	}
	
	public Table(String originalName, String name, String constrain, ArrayList<Field> fields) {
		super();
		this.originalName = originalName;
		this.name = name;
		this.constraint = constrain;
		if(fields != null)
			this.fields = fields;
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

	@Override
	public String toString() {
		String s = "\nTable @" + name + "@ containing";
		for (Field f : fields) {
			s += "\n #" + f.getName() + "# of type ?" + f.getType() + "? with constraint !" + f.getConstraint() + "!";
		}
		return s;
	}

}
