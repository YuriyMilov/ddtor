package qd.gmap.client;

import java.util.ArrayList;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.maps.client.InfoWindowContent;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.control.LargeMapControl;
import com.google.gwt.maps.client.event.MarkerClickHandler;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.overlay.Icon;
import com.google.gwt.maps.client.overlay.Marker;
import com.google.gwt.maps.client.overlay.MarkerOptions;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class Gmap implements EntryPoint {
	int i = 0;
	double d1 = 0.0, d2 = 0.0;
	public static String ch = "";
	boolean red = true;
	private final GreetingServiceAsync greetingService = GWT
			.create(GreetingService.class);
	MapWidget map;
	// LatLng place = LatLng.newInstance(40.745575, -73.990855);
	LatLng place = LatLng.newInstance(40.745575, -73.990855);
	HTML info;
	//
	final VerticalPanel vp = new VerticalPanel();
	final VerticalPanel vpm = new VerticalPanel();

	final ListBox km = new ListBox(false);
	final ArrayList<Marker> ar = new ArrayList();;
	final FlexTable layout = new FlexTable();

	public void onModuleLoad() {
		map = new MapWidget(place, 3);
		//map.setSize("600px", "400px");
		 map.setSize("1200px", "800px");
		map.setScrollWheelZoomEnabled(true);
		map.addControl(new LargeMapControl());
		km.addItem("10 km");
		km.addItem("20 km");
		km.addItem("50 km");
		km.addItem("100 km");
		km.addItem("200 km");
		km.addItem("all");
		layout.setHTML(0, 5, "Wait...");
		login();
	}

	private void login() {

		greetingService.greetServer("login", new AsyncCallback<String[]>() {
			public void onFailure(Throwable caught) {
				RootPanel.get().add(
						new HTML("error :( pls, contact administrator"));
			}

			public void onSuccess(final String r[]) {
				if (r[0].indexOf("Logout") > -1) {
					a4();
					layout.setHTML(0, 5, "");
					layout.setCellSpacing(3);
					final TextBox tbox1 = new TextBox();
					tbox1.setText("Toronto");
					layout.setWidget(0, 0, new Label("Destination: "));
					layout.setWidget(0, 1, tbox1);
					Button but1 = new Button("Submit", new ClickHandler() {
						public void onClick(ClickEvent event) {
							map.clearOverlays();
							layout.setHTML(0, 5, "Wait...");

							greetingService.get_r4(
									km.getItemText(km.getSelectedIndex()),
									tbox1.getText(),
									new AsyncCallback<String[]>() {

										public void onFailure(Throwable caught) {
											layout.setHTML(0, 5, "onFailure");
										}

										public void onSuccess(String tt[]) {
											try {

												place = LatLng.newInstance(
														d1 = Double
																.parseDouble(tt[0]),
														d2 = Double
																.parseDouble(tt[1]));
												layout.setHTML(
														0,
														5,
														String.valueOf(d1)
																+ " "
																+ String.valueOf(d2));

												
												Icon icon = Icon.newInstance(Icon.DEFAULT_ICON);
												icon.setImageURL("blue.png");
												MarkerOptions ops = MarkerOptions.newInstance(icon);
												ops.setIcon(icon);
												final Marker md = new Marker(LatLng.newInstance(d1,
														d2), ops);
												md.addMarkerClickHandler(new MarkerClickHandler() {

													public void onClick(MarkerClickEvent event) {
														map.getInfoWindow().open(md,
																new InfoWindowContent("Destination point"));
													}
												});
												map.addOverlay(md);
												
												
												String skm = km.getItemText(km
														.getSelectedIndex());
												
												
												if (skm.equals("10 km")) {
													map.setCenter(place, 11);

													for (int i = 0; i < ar
													.size(); i++)
													{
														double dis=place.distanceFrom(ar.get(i).getLatLng());
														if (dis<10000)
															map.addOverlay(ar.get(i));
													}

												}
												
												if (skm.equals("20 km")) {
													map.setCenter(place, 10);

													for (int i = 0; i < ar
													.size(); i++)
													{
														double dis=place.distanceFrom(ar.get(i).getLatLng());
														if (dis<20000)
															map.addOverlay(ar.get(i));
													}

												}
												
												if (skm.equals("50 km")) {
													map.setCenter(place, 9);

													for (int i = 0; i < ar
													.size(); i++)
													{
														double dis=place.distanceFrom(ar.get(i).getLatLng());
														if (dis<100000)
															map.addOverlay(ar.get(i));
													}

												}
												
												if (skm.equals("100 km")) {
													map.setCenter(place, 8);

													for (int i = 0; i < ar
													.size(); i++)
													{
														double dis=place.distanceFrom(ar.get(i).getLatLng());
														if (dis<100000)
															map.addOverlay(ar.get(i));
													}

												}
												
												if (skm.equals("200 km")) {
													map.setCenter(place, 7);

													for (int i = 0; i < ar
													.size(); i++)
													{
														double dis=place.distanceFrom(ar.get(i).getLatLng());
														if (dis<100000)
															map.addOverlay(ar.get(i));
													}

												}

												if (skm.equals("all")) {
													map.setCenter(place, 3);

													for (int i = 0; i < ar
															.size(); i++)
														map.addOverlay(ar
																.get(i));

												}

											} catch (Exception eee) {
												layout.setHTML(0, 5,
														eee.toString());
											}
										}
									});
							/*
							 * greetingService.greetServer("a4", new
							 * AsyncCallback<String[]>() { public void
							 * onFailure(Throwable caught) { }
							 * 
							 * public void onSuccess(final String r[]) { try {
							 * map.clearOverlays(); //layout.setHTML(0, 5,
							 * "Wait...");
							 * 
							 * for (i = 0; i < r.length; i++) { final String
							 * color = r[i++]; final double dlat =
							 * Double.parseDouble(r[i++]); final double dlng =
							 * Double.parseDouble(r[i++]); final String s1 =
							 * r[i];
							 * 
							 * Icon icon = Icon.newInstance(Icon.DEFAULT_ICON);
							 * if (color.equals("b"))
							 * icon.setImageURL("blue.png"); else if
							 * (color.equals("g"))
							 * icon.setImageURL("markerGreen.png"); else
							 * icon.setImageURL("marker.png");
							 * 
							 * MarkerOptions ops =
							 * MarkerOptions.newInstance(icon);
							 * ops.setIcon(icon); final Marker mm = new
							 * Marker(LatLng.newInstance(dlat, dlng), ops);
							 * mm.addMarkerClickHandler(new MarkerClickHandler()
							 * {
							 * 
							 * public void onClick(MarkerClickEvent event) {
							 * map.getInfoWindow().open(mm, new
							 * InfoWindowContent(s1)); } }); String skm =
							 * km.getItemText(km .getSelectedIndex());
							 * 
							 * if (skm.equals("10 km")) { map.setCenter(place,
							 * 11); map.addOverlay(mm); }
							 * 
							 * map.addOverlay(new Marker(place));
							 * 
							 * }
							 * 
							 * } catch (Exception eee) { } }
							 * 
							 * });
							 */
						}

					});

					layout.setWidget(0, 4, but1);
					layout.setWidget(0, 2, new HTML("Radius: "));
					layout.setWidget(0, 3, km);

					RootPanel.get().add(layout);
					RootPanel.get().add(map);
					RootPanel.get().add(new HTML(r[0]));
				} else
					RootPanel.get().add(new HTML(r[0]));
			}
		});
	}

	private void a4() {

		greetingService.greetServer("a4", new AsyncCallback<String[]>() {
			public void onFailure(Throwable caught) {
			}

			public void onSuccess(final String r[]) {
				try {
					for (i = 0; i < r.length; i++) {
						final String color = r[i++];
						final double dlat = Double.parseDouble(r[i++]);
						final double dlng = Double.parseDouble(r[i++]);
						final String s1 = r[i];

						Icon icon = Icon.newInstance(Icon.DEFAULT_ICON);
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

}