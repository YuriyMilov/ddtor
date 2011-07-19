package gu.client.ui;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class LoadingPanel extends SimplePanel
{
	int loadCount = 0;
	public LoadingPanel()
	{
		//setStyleName( "gwtapps-LoadingPanel" );
		setVisible( false );
	}	
	public LoadingPanel( Widget child )
	{
		this();
		setWidget( child );
	}
	public LoadingPanel(Label child) {
		this();
		setWidget( child );
	}
	public void onLoad()
	{
		setPosition();
	}
	public void loadingBegin()
	{
		if( loadCount == 0 )
		{
			setVisible( true );
			setPosition();
			DOM.setStyleAttribute(RootPanel.getBodyElement(), "cursor", "progress");
		}
		loadCount++;
	}
	public void loadingEnd()
	{
		loadCount--;
		if( loadCount == 0 )
		{
			setVisible( false );
			DOM.setStyleAttribute(RootPanel.getBodyElement(), "cursor", "");
		}
	}
	public void setPosition()
	{
		Widget child = getWidget();
		int top = DOM.getIntAttribute(RootPanel.getBodyElement(), "scrollTop");		
		int left = 0;//Window.getClientWidth() - child.getOffsetWidth()  + 
		DOM.getIntAttribute(RootPanel.getBodyElement(), "scrollLeft");	
		DOM.setStyleAttribute(getElement(),"position","absolute" );
		DOM.setStyleAttribute(getElement(),"top", Integer.toString( top ) );
		DOM.setStyleAttribute(getElement(),"left",Integer.toString( left ) );
	}
}
