package gu.client.ui;

import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.MouseListener;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.FocusPanel;

public class ImageButton extends FocusPanel {
	Image image = new Image();
	public ImageButton( String url ){
		 image.setStyleName("gwtapps-Image");
		 image.setUrl(url);
		 setStyleName("gwtapps-ImageButton");
		 add(image);
		 addMouseListener(new MouseListener(){			 
	            public void onMouseEnter( Widget sender ){
	                addStyleName("gwtapps-ImageButton-hover");
	            }
	            public void onMouseMove( Widget sender, int x, int y ){
	                addStyleName("gwtapps-ImageButton-hover");
	            }	            
	            public void onMouseLeave (Widget sender){
	                removeStyleName("gwtapps-ImageButton-hover");
	                removeStyleName("gwtapps-ImageButton-selected");
	            }	            
	            public void onMouseDown(Widget sender, int x, int y){
	            	addStyleName("gwtapps-ImageButton-selected");
	            }
	            public void onMouseUp(Widget sender, int x, int y) {
	            	addStyleName("gwtapps-ImageButton-hover");
	            }
		 });
	}
	public void setUrl( String url )
	{
		image.setUrl( url );
	}
	public void setState( boolean state ){
		if( state ){
			addStyleName( "gwtapps-ImageButton-on" );
		}
		else {
			removeStyleName( "gwtapps-ImageButton-on" );
		}
	}
}
