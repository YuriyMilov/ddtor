package gu.client.view.dialogs;

import gu.client.dao.CollectionListener;
import gu.client.model.Story;
import gu.client.model.User;
import gu.client.view.DatabaseEditorView;

import java.util.Iterator;
import java.util.List;

import com.google.gwt.user.client.ui.ListBox;


public class StoryDialog extends ObjectDialogBox {

	Story story = new Story();
	ListBox userList = new ListBox();
	public StoryDialog( DatabaseEditorView view ) {
		super("<img src='item-story.png' hspace='3'>Create Story", view );
		story = new Story();
		init();
	}

	public StoryDialog(Story story, DatabaseEditorView view) {
		super("<img src='item-story.png' hspace='3'>Edit Story", view );
		this.story = story;
		init();
		
	}

	private void init() {
		addField( "title", story.getTitle() );
		addField( "url", story.getUrl() );
		addField( "description", story.getDescription() );
		addField( "posted by", userList );
		
		addButtons();
		
		//fill author list box
		final String postedby_id = story.getUser_id();
		getView().getObjectFactory().getUserDAO().getAll(new CollectionListener(){
			public void onCollection(List list) {
				for( Iterator it = list.iterator(); it.hasNext(); ){
					User user = (User)it.next();
					userList.addItem( user.getName(), user.getId() );
					if( postedby_id != null && postedby_id.compareTo(user.getId())==0)
						userList.setSelectedIndex(userList.getItemCount()-1);
				}
			}
		});
	}

	public void onSubmit(){
		story.setTitle( getField(0) );
		story.setUrl( getField(1) );
		story.setDescription( getField(2) );
		story.setUser_id( userList.getValue( userList.getSelectedIndex() ) );
		getView().getObjectFactory().getStoryDAO().save( story );
	}

}
