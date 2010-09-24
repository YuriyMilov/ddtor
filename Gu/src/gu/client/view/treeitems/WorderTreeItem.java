package gu.client.view.treeitems;


import gu.client.model.Worder;
import gu.client.view.DatabaseEditorView;
import gu.client.view.workspace.WorderView;


public class WorderTreeItem extends BaseTreeItem{
	private Worder story;
	public WorderTreeItem( Worder story, DatabaseEditorView view ){
		super("<img src='item-story.png' hspace='3'>"+story.get_wo_number(), view );
		this.story = story;	
		//addItem( new WigsTreeItem(story, view));
	}
	
	public void onTreeItemSelected() { onRefresh(); }	
	public void onRefresh(){
		setHTML("<img src='item-story.png' hspace='3'>"+story.get_wo_number());
		getView().setMainPane( new WorderView( story, getView() ) );
	}
}	