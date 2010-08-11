package qd.gmap.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.maps.client.InfoWindowContent;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.control.LargeMapControl;
import com.google.gwt.maps.client.event.MarkerClickHandler;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.overlay.Icon;
import com.google.gwt.maps.client.overlay.Marker;
import com.google.gwt.maps.client.overlay.MarkerOptions;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class Gmap implements EntryPoint {
	int i = 0;
	public static String ch = "";
	boolean red = true;
	private final GreetingServiceAsync greetingService = GWT
			.create(GreetingService.class);
	MapWidget map;
	HTML info;

	final VerticalPanel vp = new VerticalPanel();
	final VerticalPanel vpm = new VerticalPanel();
	
	public void onModuleLoad() {
		
		LatLng place = LatLng.newInstance(40.745575, -73.990855);
		map = new MapWidget(place, 3);
		map.setSize("600px", "400px");
		//map.setSize("1200px", "800px");
		map.setScrollWheelZoomEnabled(true);
		map.addControl(new LargeMapControl());
		//aa();
		//a2();

	

		//RootPanel.get().add(new HTML("<p>wewrt <a href=http://google.com>rrrrrrr</a>"));
		login();
			
	
			//vpm.add(map);
			//RootPanel.get().add(vpm);
			
	}

	private void login() {

		greetingService.greetServer("login", new AsyncCallback<String[]>() {
			public void onFailure(Throwable caught) {
				RootPanel.get().add(new HTML("error :( pls, contact administrator"));
			}

			public void onSuccess(final String r[]) {
				if (r[0].indexOf("Logout")>-1)
					{
					a4();
					RootPanel.get().add(map);
					RootPanel.get().add(new HTML(r[0]));
					}
				else
					RootPanel.get().add(new HTML(r[0]));
			}
		});
	}
	
	
	private void a4() {

		greetingService.greetServer("a4", new AsyncCallback<String[]>() {
			public void onFailure(Throwable caught) {
			}

			public void onSuccess(final String r[]) {
				try{
				for (i = 0; i < r.length; i++) {
					final String color=r[i++];
					final double dlat = Double.parseDouble(r[i++]);
					final double dlng = Double.parseDouble(r[i++]);
					final String s1=r[i];
					
					
					Icon icon = Icon.newInstance(Icon.DEFAULT_ICON);
					if(color.equals("b"))
						icon.setImageURL("blue.png");
					else					
						if(color.equals("g"))
						icon.setImageURL("markerGreen.png");
					else
						icon.setImageURL("marker.png");
					
					MarkerOptions ops = MarkerOptions.newInstance(icon);
					ops.setIcon(icon);
					final Marker mm = new Marker(LatLng.newInstance(dlat, dlng), ops);
					mm.addMarkerClickHandler(new MarkerClickHandler(){

					public void onClick(MarkerClickEvent event) {
						map.getInfoWindow().open(mm,
							        new InfoWindowContent(s1));
						}});
					map.addOverlay(mm);
					}
				
			}
				catch(Exception eee){}
		}
		
		});
	}
	
	private void aa() {

		greetingService.greetServer("aa", new AsyncCallback<String[]>() {
			public void onFailure(Throwable caught) {
			}

			public void onSuccess(final String r[]) {
				try{
				for (i = 0; i < r.length; i++) {
					i++;
					i++;
					final double dlat = Double.parseDouble(r[i++]);
					final double dlng = Double.parseDouble(r[i++]);
					final String s1=r[i];
					
					Icon icon = Icon.newInstance(Icon.DEFAULT_ICON);
					icon.setImageURL("markerGreen.png");
					MarkerOptions ops = MarkerOptions.newInstance(icon);
					ops.setIcon(icon);
					final Marker mm = new Marker(LatLng.newInstance(dlat, dlng), ops);
					mm.addMarkerClickHandler(new MarkerClickHandler(){

					public void onClick(MarkerClickEvent event) {
						map.getInfoWindow().open(mm,
							        new InfoWindowContent(s1));
						}});
					map.addOverlay(mm);
					}
			}
				catch(Exception eee){}
		}
		
		});
	}
		 

	private void a2() {

		greetingService.greetServer("a2", new AsyncCallback<String[]>() {
			public void onFailure(Throwable caught) {
			}

			public void onSuccess(final String r[]) {
				try{
				for (i = 0; i < r.length; i++) {
					
					i++;
					i++;
					final double dlat = Double.parseDouble(r[i++]);
					final double dlng = Double.parseDouble(r[i++]);
					final String s1=r[i];
					
					Icon icon = Icon.newInstance(Icon.DEFAULT_ICON);
					icon.setImageURL("marker.png");
					MarkerOptions ops = MarkerOptions.newInstance(icon);
					ops.setIcon(icon);
					final Marker mm = new Marker(LatLng.newInstance(dlat, dlng), ops);
					mm.addMarkerClickHandler(new MarkerClickHandler(){
						public void onClick(MarkerClickEvent event) {
						map.getInfoWindow().open(mm,
							        new InfoWindowContent(s1));
						}});
					map.addOverlay(mm);
					
				}
				}
				catch(Exception eee){}
			}
		});
	}
}