package gu.client.view.workspace;

import gu.client.model.Worder;
import gu.client.model.User;
import gu.client.view.DatabaseEditorView;
import gu.client.view.dialogs.DigDialog;
import gu.client.view.dialogs.WigDialog;

import java.util.Iterator;
import java.util.List;

public class WigsListView extends ListView {

	private final Worder story;
	public WigsListView( Worder story, List users, DatabaseEditorView view) {
		super("<img src='item-dig.png' hspace='3'>Wigs", view, "add" );
		this.story = story;

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
		new WigDialog( story, getView() );
	}
}
