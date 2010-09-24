package gu.client.view.treeitems;

import gu.client.model.Shipper;
import gu.client.view.DatabaseEditorView;
import gu.client.view.workspace.ShipperView;

public class ShipperTreeItem extends BaseTreeItem{
	private Shipper story;
	public ShipperTreeItem( Shipper story, DatabaseEditorView view ){
		super("<img src='item-story.png' hspace='3'>"+story.getName(), view );
		this.story = story;	
	}
	
	public void onTreeItemSelected() { onRefresh(); }	
	public void onRefresh(){
		setHTML("<img src='item-story.png' hspace='3'>"+story.getName() );
		getView().setMainPane( new ShipperView( story, getView() ) );
	}
}	