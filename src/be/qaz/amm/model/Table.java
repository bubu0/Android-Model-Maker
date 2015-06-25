package be.qaz.amm.model;

import java.util.ArrayList;

/**
 * @author quentin
 */
public class Table {

    private String originalName;
    private String name;
    private String constraint;
    //Table's parents name
    private ArrayList<String> parents = new ArrayList<String>();
    private ArrayList<Field> fields = new ArrayList<Field>();
    // used to create parser accordingly instead of having a JSONObject for parameter it will be a JSONArray
    private boolean isInArray = false;
    private ArrayList<String> annotations = new ArrayList<String>();

    public Table() {
        super();
    }

    public Table(String name) {
        super();
        this.name = name;
    }

    public Table(String originalName, String name, String constraint,
                 ArrayList<Field> fields, boolean isInArray, String parent) {
        super();
        this.originalName = originalName;
        this.name = name;
        this.constraint = constraint;
        if (fields != null) {
            this.fields = fields;
        }
        this.isInArray = isInArray;
        if (parent != null) {
            this.parents.add(parent);
        }
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

    public void addFields(Field field) {
        this.fields.add(field);
    }

    public boolean isInArray() {
        return isInArray;
    }

    public void setInArray(boolean isInArray) {
        this.isInArray = isInArray;
    }

    public ArrayList<String> getAnnotations() {
        return annotations;
    }

    public void setAnnotations(ArrayList<String> annotations) {
        this.annotations = annotations;
    }

    public void addAnnotations(String annotation) {
        this.annotations.add(annotation);
    }

    public ArrayList<String> getParent() {
        return parents;
    }

    public void setParent(ArrayList<String> parent) {
        this.parents = parent;
    }

    public void addParent(String parent) {
        this.parents.add(parent);
    }

    @Override
    public String toString() {
        String s = "\nTable @" + name + "@ is in array !" + isInArray + "! containing :";
        if (parents != null) {
            int i = 0;
            for (String parent : parents) {
                s += "\n parent " + i + " is %" + parent + "%";
                i++;
            }
        }
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
