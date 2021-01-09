package core.models;

public class Group {
    private String label;

    private String id;




    public Group(String id, String label){
        this.id = id;
        this.label = label;


    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }


    public String toString() {
        return this.getLabel();
    }
}
