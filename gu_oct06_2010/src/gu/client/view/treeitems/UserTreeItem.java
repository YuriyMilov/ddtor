package gu.client.view.treeitems;

import gu.client.model.User;
import gu.client.view.DatabaseEditorView;
import gu.client.view.workspace.UserView;


public class UserTreeItem extends BaseTreeItem{
	private User user;
	public UserTreeItem( User user, DatabaseEditorView view ){
		super("<img src='item-user.png' hspace='3'>"+user.getName(), view );
		this.user = user;
	}
	
	public void onTreeItemSelected() { onRefresh(); }	
	public void onRefresh(){
		setHTML("<img src='item-user.png' hspace='3'>"+user.getName() );
		getView().setMainPane( new UserView( user, getView() ) );
	}
	
}	