package gu.client.view.workspace;

import gu.client.model.Story;
import gu.client.model.User;
import gu.client.view.DatabaseEditorView;
import gu.client.view.dialogs.DigDialog;

import java.util.Iterator;
import java.util.List;


public class DigsListView extends ListView {

	private final Story story;
	public DigsListView( Story story, List users, DatabaseEditorView view) {
		super("<img src='item-dig.png' hspace='3'>7Digs", view, "add" );
		this.story = story;

		addColumn( "7id" );
		addColumn( "7name" );
		addColumn( "7email" );
		
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
		new DigDialog( story, getView() );
	}
}
