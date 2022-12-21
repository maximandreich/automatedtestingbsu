package model;

public class Item {

    private String itemName;
    private String itemURL;

    public Item(String itemName, String itemURL) {
        this.itemName = itemName;
        this.itemURL = itemURL;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemURL() {
        return itemURL;
    }

    public void setItemURL(String itemURL) {
        this.itemURL = itemURL;
    }
}
