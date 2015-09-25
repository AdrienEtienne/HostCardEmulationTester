package org.aetienne.app.viewmodel;

/**
 * Created by Adrien on 25/09/2015.
 */
public abstract class ListItemSimpleAbstract {
    protected String mId;
    protected String mTitle;
    protected String mSubtitle;

    public String getItemTitle() {
        return mTitle;
    }

    public String getItemSubtitle() {
        return mSubtitle;
    }

    public ListItemSimpleAbstract(String id, String title, String subtitle){
        mId = id;
        mTitle = title;
        mSubtitle = subtitle;
    }
}
