package gu.client.view.dialogs;

import gu.client.model.User;
import gu.client.view.DatabaseEditorView;



public class UserDialog extends ObjectDialogBox {

	User user = new User();
	public UserDialog( DatabaseEditorView view ) {
		super("<img src='item-user.png' hspace='3'>Create User", view );
		user = new User();
		init();
	}

	public UserDialog(User user, DatabaseEditorView view) {
		super("<img src='item-story.png' hspace='3'>Edit User", view );
		this.user = user;
		init();
	}

	private void init() {
		addField( "name", user.getName() );
		addField( "email", user.getEmail() );
		addButtons();		
	}

	public void onSubmit(){
		user.setName( getField(0) );
		user.setEmail( getField(1) );
		getView().getObjectFactory().getUserDAO().save( user );
	}
}
