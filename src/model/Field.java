package model;
/**
 * 
 */

/**
 * @author quentin
 *
 */
public class Field {
	private String name;
	private String orignalName;
	private String type;
	private String constraint;
	
	public Field() {
		super();
	}
	public Field(String orignalName, String name, String type, String constraint) {
		super();
		this.name = name;
		this.orignalName = orignalName;
		this.type = type;
		this.constraint = constraint;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getConstraint() {
		return constraint;
	}
	public void setConstraint(String constrain) {
		this.constraint = constrain;
	}
	public String getOrignalName() {
		return orignalName;
	}
	public void setOrignalName(String orignalName) {
		this.orignalName = orignalName;
	}
	
	@Override
	public String toString() {
		return "Field orginal name = " + orignalName + " & name = " + name + " & type = " + type + " & constrain = " + constraint;
	}
	
	
}
