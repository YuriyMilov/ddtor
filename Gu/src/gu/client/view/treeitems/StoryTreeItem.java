package gu.client.view.treeitems;

import gu.client.model.Story;
import gu.client.view.DatabaseEditorView;

import gu.client.view.workspace.StoryView;

public class StoryTreeItem extends BaseTreeItem{
	private Story story;
	public StoryTreeItem( Story story, DatabaseEditorView view ){
		super("<img src='item-story.png' hspace='3'>"+story.getTitle(), view );
		this.story = story;	
		addItem( new DigsTreeItem(story, view));
	}
	
	public void onTreeItemSelected() { onRefresh(); }	
	public void onRefresh(){
		setHTML("<img src='item-story.png' hspace='3'>"+story.getTitle() );
		getView().setMainPane( new StoryView( story, getView() ) );
	}
}	