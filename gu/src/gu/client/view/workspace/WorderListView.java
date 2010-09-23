package gu.client.view.workspace;

import gu.client.dao.ObjectListener;
import gu.client.model.BaseObject;
import gu.client.model.Worder;
import gu.client.model.User;
import gu.client.view.DatabaseEditorView;
import gu.client.view.dialogs.WorderDialog;
//import gu.client.view.dialogs.StoryDialog;

import java.util.Iterator;
import java.util.List;

public class WorderListView extends ListView {
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
	public WorderListView( List stories, DatabaseEditorView view) {
		super("<img src='item-stories.png' hspace='3'>Work Orders", view );
		addColumn( "WO#" );
		addColumn( "Name" );
		addColumn( "ShipId" );
		addColumn( "ConsId");
		addColumn( "Equipm" );
		addColumn( "Pieces" );
		addColumn( "Type" );
		addColumn( "Descr" );
		addColumn( "Lbs" );
		addColumn( "Kgs" );
		addColumn( "Pickup" );
		addColumn( "Delivr" );
		addColumn( "BOL" );
		
		int row = 0;
		for( Iterator it = stories.iterator(); it.hasNext();){
			Worder story = (Worder)it.next();

			addField(row,story.get_wo_number());
			addField(row,story.get_customer_name());
			addField(row,story.get_ship_id());
			addField(row,story.get_cons_id()); 
			addField(row,story.get_equipment_description());

			addField(row,story.get_pieces());
			
			addField(row,story.get_type());
			addField(row,story.getDescription());
			
			addField(row,story.get_weight_lbs());
			addField(row,story.get_weight_kgs()); 
			addField(row,story.getpickup_dt());

			
			addField(row,story.get_delivery_dt());
			addField(row,story.get_bol()); 


			//if( story.getUser_id().length()>0)
			//	getView().getObjectFactory().getUserDAO().getById( story.getUser_id(), new GetAuthorListener(row) );
			row++;
		}
	}
	protected void onCreate(){
		new WorderDialog( getView() );
	}

}
