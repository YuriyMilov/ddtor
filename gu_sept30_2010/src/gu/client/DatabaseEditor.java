package gu.client;



import gu.client.dao.RPCObjectFactory;
import gu.client.view.DatabaseEditorView;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;


public class DatabaseEditor implements EntryPoint{
	
	public void onModuleLoad(){
		
		//create view
		DatabaseEditorView view = new DatabaseEditorView();
		RootPanel.get("databaseEditorView").add( view );
	
	 		 
		RPCObjectFactory objectFactory = new RPCObjectFactory( 
				GWT.getModuleBaseURL()+"objectFactory" );
		
		
		
		//give the view the object factory
		view.setObjectFactory( objectFactory );
	}
}
