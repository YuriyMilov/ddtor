package gu.client.ui;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.HTML;

public class Link extends HTML 
{
	static public final String TARGET_NEW_WINDOW = "_blank";
	public Link()
	{
		setElement( DOM.createAnchor() );
		sinkEvents( Event.ONCLICK | Event.MOUSEEVENTS );
		setStyleName("gwtapps-Link");
	}

	public Link(String text, String href) 
	{
		this();
		setHTML(text);
		setLink(href);
	}

	public Link(String text, String href, String target ) 
	{
		this();
		setHTML(text);
		setLink(href);
		setTarget(href);
	}

	public void setLink( String link )
	{
		DOM.setAttribute(getElement(), "href", link );
	}	  

	public void setTarget( String target )
	{
		DOM.setAttribute(getElement(), "target", target );
	}
}
