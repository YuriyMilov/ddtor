package gu.client.view.workspace;

import gu.client.dao.ObjectListener;
import gu.client.model.BaseObject;
import gu.client.model.Story;
import gu.client.model.User;
import gu.client.view.DatabaseEditorView;
import gu.client.view.dialogs.StoryDialog;


public class StoryView extends ObjectView {
	private class GetAuthorListener implements ObjectListener{
		public void onObject(BaseObject object) {
			User user = (User)object;
			if( user != null )
				addField("posted by",user.getName());
		}
	}
	private final Story story;
	public StoryView( Story story1, DatabaseEditorView view ){	
		super( "<img src='item-story.png' hspace='3'>"+story1.getTitle(), view );
		this.story = story1;

		addField( "id", story.getId() );
		addField( "title", story.getTitle() );
		addField( "url", story.getUrl() );
		addField( "description", story.getDescription() );
		if( story.getUser_id() != null && story.getUser_id().length()>0)
			getView().getObjectFactory().getUserDAO().getById( story.getUser_id(), new GetAuthorListener() );

	}
	
	protected void onDelete() {
		getView().getObjectFactory().getStoryDAO().delete(story);
	}
	protected void onEdit() {
		new StoryDialog( story, getView() );
	}
}
