package gu.client.view.workspace;

import gu.client.model.Consignee;
import gu.client.view.DatabaseEditorView;
import gu.client.view.dialogs.ConsigneeDialog;


public class ConsigneeView extends ObjectView {
	private final Consignee story;
	public ConsigneeView( Consignee story1, DatabaseEditorView view ){	
		super( "<img src='item-story.png' hspace='3'>"+story1.getName(), view );
		this.story = story1;

		addField( "Company", story.getName());
		addField( "City", story.getCity());
		addField( "Province", story.getProv());
		addField( "PostalCode", story.getPostalCode());
		addField( "Latitude", story.getLatitude());
		addField( "Longtitude", story.getLongtitude());
		addField( "ConsID", story.get_ConsId());
	}
	
	protected void onDelete() {
		getView().getObjectFactory().getConsigneeDAO().delete(story);
	}
	protected void onEdit() {
		new ConsigneeDialog( story, getView() ); 
	}
}
