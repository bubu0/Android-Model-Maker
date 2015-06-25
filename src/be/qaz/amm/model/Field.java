package be.qaz.amm.model;

import java.util.ArrayList;

/**
 *
 */

/**
 * @author quentin
 */
public class Field {
    private String name;
    private String orignalName;
    //see Constant
    private String type;
    //like primary key, NOT NULL see Constant
    private String constraint;
    //Field's table name
    private String parent;
    private ArrayList<String> annotations = new ArrayList<String>();
    private boolean isArray = false;

    public Field() {
        super();
    }

    public Field(String orignalName, String name, String type, String constraint, String parent) {
        super();
        this.name = name;
        this.orignalName = orignalName;
        this.type = type;
        this.constraint = constraint;
        this.parent = parent;
    }

    public Field(String orignalName, String name, String type,
                 String constraint, String parent, ArrayList<String> annotations,
                 boolean isArray) {
        super();
        this.name = name;
        this.orignalName = orignalName;
        this.type = type;
        this.constraint = constraint;
        this.parent = parent;
        this.annotations = annotations;
        this.isArray = isArray;
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

    public boolean isArray() {
        return isArray;
    }

    public void setIsArray(boolean isArray) {
        this.isArray = isArray;
    }

    public ArrayList<String> getAnnotations() {
        return annotations;
    }

    public void setAnnotations(ArrayList<String> annotations) {
        this.annotations = annotations;
    }

    public void addAnnotation(String annotation) {
        this.annotations.add(annotation);
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    @Override
    public String toString() {
        return "Field orginal name = " + orignalName + " & name = " + name + " & type = " + type + " & constrain = "
                + constraint + " & isArray = " + isArray + " & parent = " + parent;
    }

}
