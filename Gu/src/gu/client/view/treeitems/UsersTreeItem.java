package gu.client.view.treeitems;

import gu.client.dao.CollectionListener;
import gu.client.model.User;
import gu.client.view.DatabaseEditorView;

import java.util.Iterator;
import java.util.List;

import gu.client.view.workspace.UserListView;


public class UsersTreeItem extends DynamicTreeItem implements CollectionListener{

	public UsersTreeItem(DatabaseEditorView view) {
		super("<img src='item-users.png' hspace='3'>Shippers", view);
	}

	public void onTreeItemSelected() { onFocused(); }	
	public void onTreeItemStateChanged() { onFocused(); }
	
	public void onRefresh() {
		setEmpty();
		onFocused();
	}
	
	private void onFocused() {
		getView().getObjectFactory().getUserDAO().getAll(this);
	}
	
	public void onCollection( List list ){
		if( isEmpty() ){
			removeItems();
			for( Iterator it = list.iterator(); it.hasNext(); ){
				addItem( new UserTreeItem( (User)it.next(), getView() ) );
			}
		}
		if( isSelected() ){
			getView().setMainPane( new UserListView( list, getView() ) );
		}
	}


}
