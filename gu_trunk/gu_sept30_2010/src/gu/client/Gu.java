package gu.client;


import gu.client.dao.RPCObjectFactory;
import gu.client.view.DatabaseEditorView;
import gu.client.view.UfosView;
import gu.client.view.ZgalaView;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;


public class Gu implements EntryPoint {


	public void onModuleLoad() {
				
		//create view
		UfosView view = new UfosView();
		
		//DatabaseEditorView view = new DatabaseEditorView();
		
		
		RootPanel.get().add(view );
	
			 
		//RPCObjectFactory objectFactory = new RPCObjectFactory( 
		//		GWT.getModuleBaseURL()+"objectFactory" );
		
		
		
		//give the view the object factory
		
		//view.setObjectFactory( objectFactory );

	}
}
