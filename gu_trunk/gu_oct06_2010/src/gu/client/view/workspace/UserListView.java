package gu.client.view.workspace;

import gu.client.model.User;
import gu.client.view.DatabaseEditorView;
import gu.client.view.dialogs.UserDialog;

import java.util.Iterator;
import java.util.List;

public class UserListView extends ListView {

	public UserListView( List users, DatabaseEditorView view) {
		super("<img src='item-users.png' hspace='3'>Users", view );

		addColumn( "id" );
		addColumn( "name" );
		addColumn( "email" );
		
		int row = 0;
		for( Iterator it = users.iterator(); it.hasNext();){
			User user = (User)it.next();
			addField(row,user.getId());
			addField(row,user.getName());
			addField(row,user.getEmail());
			row++;
		}
	}
	protected void onCreate(){
		new UserDialog( getView() );
	}
}
