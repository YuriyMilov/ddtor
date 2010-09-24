package gu.client.view.treeitems;

import gu.client.dao.CollectionListener;
import gu.client.model.Worder;
import gu.client.view.DatabaseEditorView;
import gu.client.view.workspace.StoryListView;
import gu.client.view.workspace.WorderListView;

import java.util.Iterator;
import java.util.List;



public class WordersTreeItem extends DynamicTreeItem implements CollectionListener{

	private List stories;
	public WordersTreeItem(DatabaseEditorView view) {
		super("<img src='item-stories.png' hspace='3'>Work Orders", view);
	}

	
	public void onTreeItemSelected() { onFocused(); }	
	public void onTreeItemStateChanged() { onFocused(); }
	
	public void onRefresh() {
		setEmpty();
		onFocused();
	}
	
	private void onFocused() {
		getView().getObjectFactory().getWorderDAO().getAll(this);
	}
	
	public void onCollection( List list ){
		if( isEmpty() ){
			removeItems();
			for( Iterator it = list.iterator(); it.hasNext(); ){
				addItem( new WorderTreeItem( (Worder)it.next(), getView() ) );
			}
		}
		if( isSelected() ){
			getView().setMainPane( new WorderListView( list, getView() ) );
		}
	}

}
