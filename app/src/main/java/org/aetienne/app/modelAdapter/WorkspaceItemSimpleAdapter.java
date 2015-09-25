package org.aetienne.app.modelAdapter;

import org.aetienne.app.viewmodel.ListItemSimpleAbstract;

/**
 * Created by Adrien on 25/09/2015.
 */
public class WorkspaceItemSimpleAdapter extends ListItemSimpleAbstract{

    public WorkspaceItemSimpleAdapter(String id, String title, String subtitle) {
        super(id, title, subtitle);
    }

    @Override
    public String getItemTitle() {
        return "Workspace " + this.mTitle;
    }

    @Override
    public String getItemSubtitle() {
        return "Port: " + this.mSubtitle;
    }

}
