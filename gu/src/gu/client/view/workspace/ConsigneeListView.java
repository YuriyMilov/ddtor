package gu.client.view.workspace;

import gu.client.model.Consignee;
import gu.client.view.DatabaseEditorView;
import gu.client.view.dialogs.ConsigneeDialog;

import java.util.Iterator;
import java.util.List;


public class ConsigneeListView extends ListView {
	public ConsigneeListView( List stories, DatabaseEditorView view) {
		super("<img src='item-stories.png' hspace='3'>Consignee", view );
		addColumn( "Company" );
		addColumn( "City" );
		addColumn( "Province" );
		addColumn( "PostalCode" );
		addColumn( "Latitude" );
		addColumn( "Longtitude" );
		
		int row = 0;
		for( Iterator it = stories.iterator(); it.hasNext();){
			Consignee story = (Consignee)it.next();
			addField(row,story.getName());
			addField(row,story.getCity());
			addField(row,story.getProv());
			addField(row,story.getPostalCode());
			addField(row,story.getLatitude());
			addField(row,story.getLongtitude());
			row++;
		}
	}
	protected void onCreate(){
		new ConsigneeDialog( getView() );
	}

}
