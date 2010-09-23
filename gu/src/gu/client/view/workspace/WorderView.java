package gu.client.view.workspace;

import gu.client.dao.ObjectListener;
import gu.client.model.BaseObject;
import gu.client.model.Worder;
import gu.client.model.User;
import gu.client.view.DatabaseEditorView;
import gu.client.view.dialogs.WorderDialog;


public class WorderView extends ObjectView {
	private class GetAuthorListener implements ObjectListener{
		public void onObject(BaseObject object) {
			//User user = (User)object;
			//if( user != null )
			//	addField("posted by",user.getName());
		}
	}
	private final Worder story;
	public WorderView( Worder story1, DatabaseEditorView view ){	
		super( "<img src='item-story.png' hspace='3'>"+story1.get_wo_number(), view );
		this.story = story1;

		addField( "WO#", story.get_wo_number() );
		addField( "Name", story.get_customer_name() );
		addField( "ShipId", story.get_ship_id() );
		addField( "ConsId", story.get_cons_id());
		addField( "Equipm", story.get_equipment_description() );
		addField( "Pieces", story.get_pieces() );
		addField( "Type", story.get_type() );
		addField( "Descr", story.getDescription() );
		addField( "Lbs", story.get_weight_lbs() );
		addField( "Kgs", story.get_weight_kgs() );
		addField( "Pickup", story.getpickup_dt() );
		addField( "Delivr", story.get_delivery_dt() );
		addField( "BOL", story.get_bol());
		
	
		
		//if( story.getUser_id() != null && story.getUser_id().length()>0)
		//	getView().getObjectFactory().getUserDAO().getById( story.getUser_id(), new GetAuthorListener() );

	}
	
	protected void onDelete() {
		getView().getObjectFactory().getWorderDAO().delete(story);
	}
	protected void onEdit() {
		new WorderDialog( story, getView() );
	}
}
