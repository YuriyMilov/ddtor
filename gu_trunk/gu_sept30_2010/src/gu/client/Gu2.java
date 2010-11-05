package gu.client;

import gu.client.view.LoginView;
import gu.client.view.ZgalaView;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;

public class Gu2 implements EntryPoint {

	  public void onModuleLoad() {
	      //LoginView view=new LoginView();
			//RootPanel.get().add(view,5,30);
					
	      ZgalaView view = new ZgalaView();
			RootPanel.get().add(view);
			
		  }

}
