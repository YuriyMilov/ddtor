package gu.client.ui;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class IconPanel extends Composite
{
	HorizontalPanel mainPanel = new HorizontalPanel();
	Image image;
	Widget content;
	public IconPanel( String img, String text )
	{
		this( img, new Label( text ) );
		Label label = (Label)content;
		label.setWordWrap(false);
	}
	public IconPanel( String img, Widget widget )
	{
		image = new Image( img );
		content = widget;
		initWidget( mainPanel );
		mainPanel.add( image );
		setStyleName("gwtapps-IconPanel");
		mainPanel.add( new Label(" ") );
		mainPanel.add( content );
	}
}
