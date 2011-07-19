package gu.client.ui;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.RootPanel;

public class GoogleMap extends HTML
{
	private static int nextId = 0;
	private JavaScriptObject map;
	public GoogleMap()
	{
		String id = "map"+(++nextId);
		setStyleName("gwtapps-GoogleMap");
		setHTML("<div id='"+id+"' style='height:100%'></div>");
		RootPanel.get().add( this );
		map = initMap(id);
	}
	
	public void setCenter( final double lon, final double lat )
	{
		setCenterImpl( map, lon, lat );
	}
	
	private native static JavaScriptObject initMap( String id )
	/*-{
		var map = new $wnd.GMap2( $doc.getElementById(id) );
		return map;
	}-*/;
	
	private native static void setCenterImpl( JavaScriptObject map, double lon, double lat )
	/*-{
		map.setCenter(new $wnd.GLatLng(lon, lat), 13);
	}-*/;

}
