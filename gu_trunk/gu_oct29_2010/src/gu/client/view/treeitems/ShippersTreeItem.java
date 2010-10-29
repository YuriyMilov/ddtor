package gu.client.view.treeitems;

import gu.client.dao.CollectionListener;
import gu.client.model.Shipper;
//import gu.client.model.Story;
import gu.client.view.DatabaseEditorView;
import gu.client.view.workspace.ShipperListView;

import java.util.Iterator;
import java.util.List;

//import gu.client.view.workspace.StoryListView;


public class ShippersTreeItem extends DynamicTreeItem implements CollectionListener{

	private List stories;
	public ShippersTreeItem(DatabaseEditorView view) {
		super("<img src='item-stories.png' hspace='3'>Shippers", view);
	}

	
	public void onTreeItemSelected() { onFocused(); }	
	public void onTreeItemStateChanged() { onFocused(); }
	
	public void onRefresh() {
		setEmpty();
		onFocused();
	}
	
	private void onFocused() {
		getView().getObjectFactory().getShipperDAO().getAll(this);
	}
	
	public void onCollection( List list ){
		if( isEmpty() ){
			removeItems();
			for( Iterator it = list.iterator(); it.hasNext(); ){
				addItem( new ShipperTreeItem( (Shipper)it.next(), getView() ) );
			}
		}
		if( isSelected() ){
			getView().setMainPane( new ShipperListView( list, getView() ) );
		}
	}

}
