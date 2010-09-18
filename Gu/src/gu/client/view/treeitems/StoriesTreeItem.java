package gu.client.view.treeitems;

import gu.client.dao.CollectionListener;
import gu.client.model.Story;
import gu.client.view.DatabaseEditorView;

import java.util.Iterator;
import java.util.List;

import gu.client.view.workspace.StoryListView;


public class StoriesTreeItem extends DynamicTreeItem implements CollectionListener{

	private List stories;
	public StoriesTreeItem(DatabaseEditorView view) {
		super("<img src='item-stories.png' hspace='3'>Orders", view);
	}

	
	public void onTreeItemSelected() { onFocused(); }	
	public void onTreeItemStateChanged() { onFocused(); }
	
	public void onRefresh() {
		setEmpty();
		onFocused();
	}
	
	private void onFocused() {
		getView().getObjectFactory().getStoryDAO().getAll(this);
	}
	
	public void onCollection( List list ){
		if( isEmpty() ){
			removeItems();
			for( Iterator it = list.iterator(); it.hasNext(); ){
				addItem( new StoryTreeItem( (Story)it.next(), getView() ) );
			}
		}
		if( isSelected() ){
			getView().setMainPane( new StoryListView( list, getView() ) );
		}
	}

}
