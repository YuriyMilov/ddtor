package gu.client.view.dialogs;

import gu.client.model.Consignee;
import gu.client.view.DatabaseEditorView;
import com.google.gwt.user.client.ui.ListBox;

public class ConsigneeDialog extends ObjectDialogBox {

	Consignee story = new Consignee();
	ListBox userList = new ListBox();
	public ConsigneeDialog( DatabaseEditorView view ) {
		super("<img src='item-story.png' hspace='3'>Create Consignee", view );
		story = new Consignee();
		init();
	}

	public ConsigneeDialog(Consignee story, DatabaseEditorView view) {
		super("<img src='item-story.png' hspace='3'>Edit Consignee", view );
		this.story = story;
		init();
	}

	private void init() {
		addField( "Company", story.getName() );
		addField( "City", story.getCity());
		addField( "Province", story.getProv());
		addField( "PosalCode", story.getPostalCode());
		addField( "Latitude", story.getLatitude());
		addField( "Longtitude", story.getLongtitude());
		addButtons();
	}

	public void onSubmit(){
		story.setName( getField(0) );
		story.setCity( getField(1) );
		story.setProv( getField(2) );
		story.setPostalCode( getField(3) );
		story.setLatitude( getField(4) );
		story.setLongtitude( getField(5) );
		getView().getObjectFactory().getConsigneeDAO().save( story );
	}

}
