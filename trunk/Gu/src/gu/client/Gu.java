package gu.client;

import gu.client.dao.RPCObjectFactory;
import gu.client.view.DatabaseEditorView;
import java.util.ArrayList;
import java.util.List;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.maps.client.InfoWindowContent;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.control.LargeMapControl;
import com.google.gwt.maps.client.event.MarkerClickHandler;
import com.google.gwt.maps.client.geocode.Geocoder;
import com.google.gwt.maps.client.geocode.LatLngCallback;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.overlay.Icon;
import com.google.gwt.maps.client.overlay.Marker;
import com.google.gwt.maps.client.overlay.MarkerOptions;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CellPanel;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class Gu implements EntryPoint {
	int i = 0;
	double d1 = 0, d2 = 0;
	public static String ch = "";
	boolean red = true;
	private final GuServiceAsync srv = GWT.create(GuService.class);
	MapWidget map;
	LatLng place = LatLng.newInstance(44, -77);
	HTML info;

	final HorizontalPanel hp = new HorizontalPanel();
	final DecoratorPanel dp = new DecoratorPanel();
	
	final VerticalPanel vp = new VerticalPanel();
	
	final VerticalPanel vpm = new VerticalPanel();
	final ListBox km = new ListBox(false);
	final ArrayList<Marker> ar = new ArrayList<Marker>();;
	final FlexTable layout = new FlexTable();
	final TextBox tbox1 = new TextBox();
	final Geocoder geo = new Geocoder();
	
	
 	public void onModuleLoad() {
		
		DatabaseEditorView view = new DatabaseEditorView();
		
		RPCObjectFactory objectFactory = new RPCObjectFactory( 
				GWT.getModuleBaseURL()+"objectFactory" );
		view.setObjectFactory( objectFactory );
		
		RootPanel.get().add( view );
		
		
		
	}
	/*
public void onModuleLoad() {
		map = new MapWidget(place, 3);
		map.setSize("600px", "400px");
		//map.setSize("1200px", "800px");
		map.setScrollWheelZoomEnabled(true);
		map.addControl(new LargeMapControl());
		km.addItem("10 km");
		km.addItem("20 km");
		km.addItem("50 km");
		km.addItem("100 km");
		km.addItem("200 km");
		km.addItem("all");

		login_plus();
	}*/

	private void login_plus() {

		srv.greetServer("login", new AsyncCallback<String[]>() {
			public void onFailure(Throwable caught) {
				RootPanel.get().add(new HTML("???"));
			}

			public void onSuccess(final String r[]) {
				if (r[0].indexOf("Logout") > -1) {

					load_markers();

					tbox1.setText("Toronto");
					
					layout.setCellSpacing(3);
					layout.setWidget(0, 0, new Label("Destination: "));
					layout.setWidget(0, 1, tbox1);
					layout.setWidget(1, 0, new HTML("Radius: "));
					layout.setWidget(1, 1, km);
					layout.setWidget(2, 1, but1);
					dp.add(layout);
					hp.add(map);
					hp.add(dp);
					RootPanel.get().add(new HTML(r[0]));
					RootPanel.get().add(hp);
					
					
					///////////////////////
					DatabaseEditorView view = new DatabaseEditorView();
					//RootPanel.get("databaseEditorView").add( view );
					RootPanel.get().add( view );
					//dp.add( view );
					RPCObjectFactory objectFactory = new RPCObjectFactory( 
							GWT.getModuleBaseURL()+"objectFactory" );
					view.setObjectFactory( objectFactory );
					///////////////////////			
					
				} else
					RootPanel.get().add(new HTML(r[0]));
			}
		});
	}

	private void load_markers() {

		srv.greetServer("a4", new AsyncCallback<String[]>() {
			public void onFailure(Throwable caught) {
			}

			public void onSuccess(final String r[]) {
				try {
					for (i = 0; i < r.length; i++) {
						final String color = r[i++];
						final double dlat = Double.parseDouble(r[i++]);
						final double dlng = Double.parseDouble(r[i++]);
						final String s1 = r[i]; 
						final Icon icon = Icon.newInstance(Icon.DEFAULT_ICON);
						if (color.equals("b"))
							icon.setImageURL("blue.png");
						else if (color.equals("g"))
							icon.setImageURL("markerGreen.png");
						else
							icon.setImageURL("marker.png");
						MarkerOptions ops = MarkerOptions.newInstance(icon);
						ops.setIcon(icon);
						final Marker mm = new Marker(LatLng.newInstance(dlat,
								dlng), ops);
						mm.addMarkerClickHandler(new MarkerClickHandler() {

							public void onClick(MarkerClickEvent event) {
								map.getInfoWindow().open(mm,
										new InfoWindowContent(s1));
							}
						});
						ar.add(mm);
						map.addOverlay(mm);
					}

				} catch (Exception eee) {
				}
			}
		});
	}
	  
	Button but1 = new Button("Submit", new ClickHandler() {
		public void onClick(ClickEvent event) {
			map.clearOverlays();
			//layout.setHTML(0, 5, "Wait...");
			geo.getLatLng(tbox1.getText(), geoint);
		}
	});

	Button but2 = new Button("Edit", new ClickHandler() {
		public void onClick(ClickEvent event) {
			//create view
			DatabaseEditorView view = new DatabaseEditorView();
			//RootPanel.get("databaseEditorView").add( view );
			RootPanel.get().add( view );
			RPCObjectFactory objectFactory = new RPCObjectFactory( 
					GWT.getModuleBaseURL()+"objectFactory" );
			//give the view the object factory
			view.setObjectFactory( objectFactory );
		}
	});

	
	   final LatLngCallback geoint = new LatLngCallback()
		  {
			@Override
			public void onFailure() {
				RootPanel.get().add(new HTML("???"));
			}
			@Override
			public void onSuccess(LatLng point) {
				try {
					place = point;
					Icon icon = Icon.newInstance(Icon.DEFAULT_ICON);
					icon.setImageURL("blue.png");
					MarkerOptions ops = MarkerOptions
							.newInstance(icon);
					ops.setIcon(icon);
					final Marker md = new Marker(place, ops);
					md.addMarkerClickHandler(new MarkerClickHandler() {
						public void onClick(MarkerClickEvent event) {
							map.getInfoWindow()
									.open(md,
											new InfoWindowContent(
													"Destination point:<br>"
															+ tbox1.getText()));
						}
					});
					map.addOverlay(md);
					String skm = km.getItemText(km
							.getSelectedIndex());
					if (skm.equals("10 km")) {
						map.setCenter(place, 11);
						for (int i = 0; i < ar.size(); i++) {
							double dis = place.distanceFrom(ar.get(
									i).getLatLng());
							if (dis < 10000)
								map.addOverlay(ar.get(i));
						}
					}

					if (skm.equals("20 km")) {
						map.setCenter(place, 10);
						for (int i = 0; i < ar.size(); i++) {
							double dis = place.distanceFrom(ar.get(
									i).getLatLng());
							if (dis < 20000)
								map.addOverlay(ar.get(i));
						}
					}
					if (skm.equals("50 km")) {
						map.setCenter(place, 9);
						for (int i = 0; i < ar.size(); i++) {
							double dis = place.distanceFrom(ar.get(
									i).getLatLng());
							if (dis < 100000)
								map.addOverlay(ar.get(i));
						}
					}

					if (skm.equals("100 km")) {
						map.setCenter(place, 8);

						for (int i = 0; i < ar.size(); i++) {
							double dis = place.distanceFrom(ar.get(
									i).getLatLng());
							if (dis < 100000)
								map.addOverlay(ar.get(i));
						}
					}
					if (skm.equals("200 km")) {
						map.setCenter(place, 7);

						for (int i = 0; i < ar.size(); i++) {
							double dis = place.distanceFrom(ar.get(
									i).getLatLng());
							if (dis < 100000)
								map.addOverlay(ar.get(i));
						}
					}
					if (skm.equals("all")) {
						map.setCenter(place, 3);
						for (int i = 0; i < ar.size(); i++)
							map.addOverlay(ar.get(i));
					}
					layout.setHTML(0, 5, "");
				} catch (Exception eee) {
					layout.setHTML(0, 5, eee.toString());
				}
				}
		  };
		 

}
