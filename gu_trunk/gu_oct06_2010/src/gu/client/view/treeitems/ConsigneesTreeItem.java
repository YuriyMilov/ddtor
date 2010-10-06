package gu.client.view.treeitems;

import gu.client.dao.CollectionListener;
import gu.client.model.Consignee;
import gu.client.view.DatabaseEditorView;
import gu.client.view.workspace.ConsigneeListView;
import java.util.Iterator;
import java.util.List;

//import gu.client.view.workspace.StoryListView;


public class ConsigneesTreeItem extends DynamicTreeItem implements CollectionListener{

	private List stories;
	public ConsigneesTreeItem(DatabaseEditorView view) {
		super("<img src='item-stories.png' hspace='3'>Consignees", view);
	}

	
	public void onTreeItemSelected() { onFocused(); }	
	public void onTreeItemStateChanged() { onFocused(); }
	
	public void onRefresh() {
		setEmpty();
		onFocused();
	}
	
	private void onFocused() {
		getView().getObjectFactory().getConsigneeDAO().getAll(this);
	}
	
	public void onCollection( List list ){
		if( isEmpty() ){
			removeItems();
			for( Iterator it = list.iterator(); it.hasNext(); ){
				addItem( new ConsigneeTreeItem( (Consignee)it.next(), getView() ) );
			}
		}
		if( isSelected() ){
			getView().setMainPane( new ConsigneeListView( list, getView() ) );
		}
	}

}
