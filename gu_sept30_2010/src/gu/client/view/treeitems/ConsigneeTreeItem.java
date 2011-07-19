package gu.client.view.treeitems;

import gu.client.model.Consignee;
import gu.client.view.DatabaseEditorView;
import gu.client.view.workspace.ConsigneeView;

public class ConsigneeTreeItem extends BaseTreeItem{
	private Consignee story;
	public ConsigneeTreeItem( Consignee story, DatabaseEditorView view ){
		super("<img src='item-story.png' hspace='3'>"+story.getName(), view );
		this.story = story;	
	}
	
	public void onTreeItemSelected() { onRefresh(); }	
	public void onRefresh(){
		setHTML("<img src='item-story.png' hspace='3'>"+story.getName() );
		getView().setMainPane( new ConsigneeView( story, getView() ) );
	}
}	