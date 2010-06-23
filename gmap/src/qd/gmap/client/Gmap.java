package qd.gmap.client;

import java.util.ArrayList;
import java.util.List;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.maps.client.InfoWindowContent;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.control.LargeMapControl;
import com.google.gwt.maps.client.control.MapTypeControl;
import com.google.gwt.maps.client.geocode.Geocoder;
import com.google.gwt.maps.client.geocode.LatLngCallback;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.geom.Point;
import com.google.gwt.maps.client.overlay.Icon;
import com.google.gwt.maps.client.overlay.Marker;
import com.google.gwt.maps.client.overlay.MarkerOptions;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;

public class Gmap implements EntryPoint {
	int i=0;
	public static String ch="";
	boolean red = true;
	private final GreetingServiceAsync greetingService = GWT
			.create(GreetingService.class);
	//Geocoder geocoder = new Geocoder();
	MapWidget map;
	HTML info;
	//static final int TIMEOUT = 30;
	//List<LatLng> tmpPointList = new ArrayList<LatLng>();
/*
	class LatitudeLongitude {
		private double latitude;
		private double longitude;

		public LatitudeLongitude(double latitude, double longitude) {
			this.latitude = latitude;
			this.longitude = longitude;
		}

		public double getLatitude() {
			return latitude;
		}

		public double getLongitude() {
			return longitude;
		}
	}
*/
	
	public void onModuleLoad() {
		LatLng place = LatLng.newInstance(40.745575,-73.990855);
		map = new MapWidget(place, 3);
		map.setSize("600px", "400px");
		map.setScrollWheelZoomEnabled(true);
		//map.addControl(new MapTypeControl());
		map.addControl(new LargeMapControl());
		//RootPanel.get().add(new HTML("<a href=.>Reload</a><br/><br/>"));
		//add();
		//preload();
		RootPanel.get().add(map);
		//add();
	}


	private void preload() {

		greetingService.greetServer("preload",
				new AsyncCallback<String[]>() {
					public void onFailure(Throwable caught) {
					}
					public void onSuccess(final String r[]) {

						/////////////////////////////////////////////////////////////
						
						map.addOverlay(new Marker(LatLng.newInstance(40.745575,-73.990855)));
					
						
						
						//for(i=0;i<r.length; i++)
						//	{ 
						//	i++;i++;
						//	double dlat=Double.parseDouble(r[i++]);
						//	double dlng=Double.parseDouble(r[i]);
						//	map.addOverlay(new Marker(LatLng.newInstance(dlat,dlng)));

						//}
	
						//RootPanel.get().add(map);
					}
					
				});
						
			//add();
	}
	
	/*
	private void add() {

		greetingService.greetServer("add",
				new AsyncCallback<String[]>() {
					public void onFailure(Throwable caught) {
					}

					public void onSuccess(final String r[]) {
						//if (!r[3].equals(ch))
						{
							ch=r[3];
							
						Geocoder geocoder = new Geocoder();
						geocoder.getLatLng(r[0],
								new LatLngCallback() {
									public void onFailure() {
									}
									public void onSuccess(LatLng point) {
										LatLng addressLatlng = LatLng
												.newInstance(point
														.getLatitude(), point
														.getLongitude());
										map.setCenter(addressLatlng);
										//if (tmpPointList.size() == 2) {
										//	tmpPointList.remove(0);
										//}
										//tmpPointList.add(point);
										Icon icon = Icon
												.newInstance(Icon.DEFAULT_ICON);
										if (r[1].equals("r")) {
											icon
													.setImageURL("http://www.google.com/mapfiles/markerA.png");
											red = true;
										}
										else {
											icon.setImageURL("markerA.png");
											red = false;
										}
										MarkerOptions ops = MarkerOptions
												.newInstance(icon);
										ops.setIcon(icon);
										final Marker marker = new Marker(point,
												ops);
										map.addOverlay(marker);
										map.getInfoWindow().open(marker,
												new InfoWindowContent(r[0]+" - "+r[3]));
										String status="red";
										if (red)
											status="RED";
										else
											status="GREEN";
										info= new HTML(r[0]+": delivered on " + r[2] + " (status: \""+status+"\"); SHIP: " + r[3]);
										RootPanel.get().add(info);
									}
								});
					}
					}
				});
		
		Timer timer = new Timer() {
				public void run() {
					add();
				}
			};
			timer.schedule(3000);
		
		
	}
*/
}