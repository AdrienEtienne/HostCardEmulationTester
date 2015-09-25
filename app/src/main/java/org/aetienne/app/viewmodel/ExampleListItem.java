package org.aetienne.app.viewmodel;

/**
 * Created by Adrien on 25/09/2015.
 */
public class ExampleListItem {
    private String itemTitle;

    public String getItemTitle() {
        return itemTitle;
    }

    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
    }

    public ExampleListItem(String title){
        this.itemTitle = title;
    }
}
