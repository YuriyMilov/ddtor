package gu.client.view.dialogs;

import gu.client.dao.CollectionListener;
import gu.client.model.Worder;
import gu.client.model.User;
import gu.client.view.DatabaseEditorView;

import java.util.Iterator;
import java.util.List;

import com.google.gwt.user.client.ui.ListBox;

public class WigDialog extends ObjectDialogBox {

	private final Worder story;
	private ListBox userList = new ListBox();

	public WigDialog(Worder story, DatabaseEditorView view) {
		super("<img src='item-dig.png' hspace='3'>Wig", view );
		this.story = story;
		
		//fill user list box
		final String id = story.getId();
		getView().getObjectFactory().getUserDAO().getAll(new CollectionListener(){
			public void onCollection(List list) {
				for( Iterator it = list.iterator(); it.hasNext(); ){
					User user = (User)it.next();
					userList.addItem( user.getName(), user.getId() );
				}
			}
		});
		addField( "user", userList );
		addButtons();		
	}

	public void onSubmit(){
		User user = new User();
		user.setId(userList.getValue( userList.getSelectedIndex() ));
		getView().getObjectFactory().getStoryDAO().addTo( story, "digs", user );
	}
}