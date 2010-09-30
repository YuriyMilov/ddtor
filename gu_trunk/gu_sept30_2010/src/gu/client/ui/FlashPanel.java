package gu.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class FlashPanel extends Composite 
{
	private FlashPanelImpl impl = (FlashPanelImpl) GWT.create(FlashPanelImpl.class);
	public FlashPanel( String url, String width, String height, String flashVars ) 
	{
		this();
		setURL(url,width,height,flashVars);
	}	
	public FlashPanel( String url ) 
	{
		this();
		setURL( url );
	}	
	public FlashPanel() 
	{
		Widget implWidget = impl.getWidget();
		initWidget( implWidget );
	}
	
	public void setURL( String url )
	{
		impl.setURL(url,"100%","100%","");
	}	
	public void setURL( String url, String width, String height, String flashVars )
	{

		impl.setURL(url, width, height, flashVars );
	}
}
