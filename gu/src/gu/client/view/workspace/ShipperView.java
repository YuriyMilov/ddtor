package gu.client.view.workspace;


import gu.client.dao.ObjectListener;
import gu.client.model.BaseObject;
import gu.client.model.Shipper;
import gu.client.view.DatabaseEditorView;
import gu.client.view.dialogs.ShipperDialog;


public class ShipperView extends ObjectView {
	private final Shipper story;
	public ShipperView( Shipper story1, DatabaseEditorView view ){	
		super( "<img src='item-story.png' hspace='3'>"+story1.getName(), view );
		this.story = story1;

		addField( "Company", story.getName());
		addField( "City", story.getCity());
		addField( "Province", story.getProv());
		addField( "PostalCode", story.getPostalCode());
		addField( "Latitude", story.getLatitude());
		addField( "Longtitude", story.getLongtitude());
	}
	
	protected void onDelete() {
		getView().getObjectFactory().getShipperDAO().delete(story);
	}
	protected void onEdit() {
		new ShipperDialog( story, getView() ); 
	}
}
