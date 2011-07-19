package gu.client.view.treeitems;

import gu.client.dao.CollectionListener;
import gu.client.model.Story;
import gu.client.model.Worder;
import gu.client.view.DatabaseEditorView;
import gu.client.view.workspace.DigsListView;

import java.util.List;


public class DigsTreeItem extends BaseTreeItem implements CollectionListener{
	Story story;
	public DigsTreeItem( Story story, DatabaseEditorView view) {
		super("<img src='item-dig.png' hspace='3'>Digs", view);
		this.story = story;
	}

	
	//Worder worder;
	//public DigsTreeItem( Worder story, DatabaseEditorView view) {
	//	super("<img src='item-dig.png' hspace='3'>Digs", view);
	//	this.worder = worder;
	//}

	
	public void onTreeItemSelected() { onRefresh(); }	
	public void onTreeItemStateChanged() { onRefresh(); }
	
	public void onRefresh() { 
		getView().getObjectFactory().getStoryDAO().getAllFrom(story, "digs", this);
	}
	//public void onRefresh() { 
	//	getView().getObjectFactory().getWorderDAO().getAllFrom(worder, "digs", this);
	//}
	public void onCollection( List list ){
		if( isSelected() ){
			getView().setMainPane( new DigsListView( story, list, getView() ) );
		}
	}
}
