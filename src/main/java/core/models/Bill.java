package core.models;

import server.models.split.Item;

import java.util.ArrayList;
import java.util.List;

public class Bill {

    private String label;
    private String content;
    private ArrayList<Item> items = new ArrayList<>();

    /**
     * Instantiates a list of items by parsing the content parameter
     * @param content "format : item1:price1/item1:price1/item1:price1"
     */
    public Bill(String label,String content){
        this.label=label;
        this.content=content;
        String[] parse1 = content.split("/");
        String[][] parse2;
        // TODO : parse
    }

    public String getContent() {
        return content;
    }

    public String getLabel() {
        return label;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "label='" + label + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
