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
import com.google.gwt.user.client.ui.RootPanel;

public class Gmap implements EntryPoint {
	int i = 0;
	public static String ch = "";
	boolean red = true;
	private final GreetingServiceAsync greetingService = GWT
			.create(GreetingService.class);
	MapWidget map;
	HTML info;

	public void onModuleLoad() {
		LatLng place = LatLng.newInstance(40.745575, -73.990855);
		map = new MapWidget(place, 3);
		map.setSize("600px", "400px");
		map.setScrollWheelZoomEnabled(true);
		map.addControl(new LargeMapControl());
		aa();
		a2();
		RootPanel.get().add(map);
	}

	private void aa() {

		greetingService.greetServer("aa", new AsyncCallback<String[]>() {
			public void onFailure(Throwable caught) {
			}

			public void onSuccess(final String r[]) {
				if(r!=null)
				for (i = 0; i < r.length; i++) {
					i++;
					final String s1=r[i++];
					final double dlat = Double.parseDouble(r[i++]);
					final double dlng = Double.parseDouble(r[i]);
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
		});
	}
		 

	private void a2() {

		greetingService.greetServer("a2", new AsyncCallback<String[]>() {
			public void onFailure(Throwable caught) {
			}

			public void onSuccess(final String r[]) {

				for (i = 0; i < r.length; i++) {
					i++;
					final String s1=r[i++];
					double dlat = Double.parseDouble(r[i++]);
					double dlng = Double.parseDouble(r[i]);
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
		});
	}
}