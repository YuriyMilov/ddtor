package gu.client.view.treeitems;

import gu.client.dao.CollectionListener;
import gu.client.model.Worder;
import gu.client.view.DatabaseEditorView;
import gu.client.view.workspace.WigsListView;

import java.util.List;


public class WigsTreeItem extends BaseTreeItem implements CollectionListener{
	
	Worder worder;
	public WigsTreeItem( Worder story, DatabaseEditorView view) {
		super("<img src='item-dig.png' hspace='3'>Digs", view);
		this.worder = worder;
	}

	
	public void onTreeItemSelected() { onRefresh(); }	
	public void onTreeItemStateChanged() { onRefresh(); }
	public void onRefresh() { 
		getView().getObjectFactory().getWorderDAO().getAllFrom(worder, "digs", this);
	}
	public void onCollection( List list ){
		if( isSelected() ){
			getView().setMainPane( new WigsListView( worder, list, getView() ) );
		}
	}
}
