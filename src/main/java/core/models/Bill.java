package core.models;

import server.models.split.Item;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Bill implements Serializable {

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
        for (int i = 0; i < parse1.length; i++) {
            String[] parsedEntry = parse1[i].split(":");
            Item item = new Item(parsedEntry[0],Double.parseDouble(parsedEntry[1]));
            items.add(item);
        }

    }

    public String getContent() {
        return content;
    }

    public String getLabel() {
        return label;
    }

    public ArrayList<Item> getItems(){
        return this.items;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "label='" + label + '\'' +
                ", content='" + content + '\'' +
                ", items='" + items + '\'' +
                '}';
    }
}
