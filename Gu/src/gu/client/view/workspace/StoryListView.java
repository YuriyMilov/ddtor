package gu.client.view.workspace;

import gu.client.dao.ObjectListener;
import gu.client.model.BaseObject;
import gu.client.model.Story;
import gu.client.model.User;
import gu.client.view.DatabaseEditorView;
import gu.client.view.dialogs.StoryDialog;

import java.util.Iterator;
import java.util.List;


public class StoryListView extends ListView {
	private class GetAuthorListener implements ObjectListener{
		private final int row;
		public GetAuthorListener( int row ){
			this.row = row;
		}
		public void onObject(BaseObject object) {
			User user = (User)object;
			if( user != null )
				addField(row,user.getName());
		}
	}
	public StoryListView( List stories, DatabaseEditorView view) {
		super("<img src='item-stories.png' hspace='3'>Orders", view );
		
		addColumn( "3id" );
		addColumn( "3title" );
		addColumn( "3url" );
		addColumn( "3description" );
		addColumn( "3posted by" );
		
		int row = 0;
		for( Iterator it = stories.iterator(); it.hasNext();){
			Story story = (Story)it.next();
			addField(row,story.getId());
			addField(row,story.getTitle());
			addField(row,story.getUrl());
			addField(row,story.getDescription());
			if( story.getUser_id().length()>0)
				getView().getObjectFactory().getUserDAO().getById( story.getUser_id(), new GetAuthorListener(row) );
			row++;
		}
	}
	protected void onCreate(){
		new StoryDialog( getView() );
	}

}
