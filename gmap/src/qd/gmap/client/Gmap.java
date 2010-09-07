package qd.gmap.client;

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

	public void onModuleLoad() {

		map = new MapWidget(place, 3);
		map.setSize("600px", "400px");
		// map.setSize("1200px", "800px");
		map.setScrollWheelZoomEnabled(true);
		map.addControl(new LargeMapControl());
		// aa();
		// a2();
		km.addItem("");
		km.addItem("10 km");
		km.addItem("20 km");
		km.addItem("50 km");
		km.addItem("100 km");

		// RootPanel.get().add(new
		// HTML("<p>wewrt <a href=http://google.com>rrrrrrr</a>"));
		login();

		// vpm.add(map);
		// RootPanel.get().add(vpm);

	}

	private void login() {

		greetingService.greetServer("login", new AsyncCallback<String[]>() {
			public void onFailure(Throwable caught) {
				RootPanel.get().add(
						new HTML("error :( pls, contact administrator"));
			}

			public void onSuccess(final String r[]) {
				if (r[0].indexOf("Logout") > -1) {
					// a4();

					final FlexTable layout = new FlexTable();
					layout.setCellSpacing(3);
					final TextBox tbox1 = new TextBox();
					tbox1.setText("Toronto");
					layout.setWidget(0, 0, new Label("Destination: "));
					layout.setWidget(0, 1, tbox1);

					/*
					 * Button but1 = new Button("Submit", new ClickHandler() {
					 * public void onClick(ClickEvent event) {
					 * 
					 * layout.setHTML(0, 5, "Wait...");
					 * 
					 * greetingService.get(tbox1.getText(), new
					 * AsyncCallback<String>() { public void onFailure(Throwable
					 * caught) { }
					 * 
					 * public void onSuccess(String result) { layout.setHTML(0,
					 * 5, result); String
					 * x=result.substring(0,result.indexOf(";")); double ix =
					 * Double.parseDouble(x); String
					 * y=result.substring(result.indexOf(";")+1); double iy =
					 * Double.parseDouble(y); final Marker mm2 = new
					 * Marker(LatLng.newInstance(ix, iy));
					 * 
					 * 
					 * map.addOverlay(mm2); } });
					 * 
					 * } });
					 */

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

										public void onSuccess(String r[]) {

											try {
												String skm = km.getItemText(km
														.getSelectedIndex()), se12="";
												
												try{
												place = LatLng.newInstance(
														Double.parseDouble(r[1]),
														Double.parseDouble(r[2]));
												}catch(Exception e12){se12=e12.toString();}
												
												if (skm.equals("")) {
													map.setCenter(place, 3);
													layout.setHTML(
															0,
															5,
															se12+" "+r[1]+" " +r[2]+" "+skm 
																	+ " "
																	+ String.valueOf((int) (r.length / 4))
																	+ " markers, zoom 3");
												} 
												if (skm.equals("10 km")) {
													 map.setCenter(place, 11);
													layout.setHTML(
															0,
															5,
															skm
																	+ ", "
																	+ String.valueOf((int) (r.length / 4))
																	+ " markers, zoom 11");
												}
												if (skm.equals("20 km")) {
													map.setCenter(place, 10);
													layout.setHTML(
															0,
															5,
															skm
																	+ ", "
																	+ String.valueOf((int) (r.length / 4))
																	+ " markers, zoom 10");
												}
												
												if (skm.equals("50 km")) {
													map.setCenter(place, 9);
													layout.setHTML(
															0,
															5,
															skm
																	+ ", "
																	+ String.valueOf((int) (r.length / 4))
																	+ " markers, zoom 9");
												}
												
												if (skm.equals("100 km")) {
													map.setCenter(place, 8);
													layout.setHTML(
															0,
															5,
															skm
																	+ ", "
																	+ String.valueOf((int) (r.length / 4))
																	+ " markers, zoom 8");
												}
												

												
												// a4();
												for (i = 0; i < r.length; i++) {
													final String color = r[i++];
													final double dlat = Double
															.parseDouble(r[i++]);
													final double dlng = Double
															.parseDouble(r[i++]);
													final String s1 = r[i];

													Icon icon = Icon
															.newInstance(Icon.DEFAULT_ICON);
													if (color.equals("b"))
														icon.setImageURL("blue.png");
													else if (color.equals("g"))
														icon.setImageURL("markerGreen.png");
													else
														icon.setImageURL("marker.png");

													MarkerOptions ops = MarkerOptions
															.newInstance(icon);
													ops.setIcon(icon);
													final Marker mm = new Marker(
															LatLng.newInstance(
																	dlat, dlng),
															ops);
													mm.addMarkerClickHandler(new MarkerClickHandler() {

														public void onClick(
																MarkerClickEvent event) {
															map.getInfoWindow()
																	.open(mm,
																			new InfoWindowContent(
																					s1));
														}
													});
													map.addOverlay(mm);
												}

											} catch (Exception eee) {

												layout.setHTML(0, 5,
														eee.toString());

											}
										}
									});

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
						map.addOverlay(mm);
					}

				} catch (Exception eee) {
				}
			}

		});
	}

	private void aa() {

		greetingService.greetServer("aa", new AsyncCallback<String[]>() {
			public void onFailure(Throwable caught) {
			}

			public void onSuccess(final String r[]) {
				try {
					for (i = 0; i < r.length; i++) {
						i++;
						i++;
						final double dlat = Double.parseDouble(r[i++]);
						final double dlng = Double.parseDouble(r[i++]);
						final String s1 = r[i];

						Icon icon = Icon.newInstance(Icon.DEFAULT_ICON);
						icon.setImageURL("markerGreen.png");
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
						map.addOverlay(mm);
					}
				} catch (Exception eee) {
				}
			}

		});
	}

	private void a2() {

		greetingService.greetServer("a2", new AsyncCallback<String[]>() {
			public void onFailure(Throwable caught) {
			}

			public void onSuccess(final String r[]) {
				try {
					for (i = 0; i < r.length; i++) {

						i++;
						i++;
						final double dlat = Double.parseDouble(r[i++]);
						final double dlng = Double.parseDouble(r[i++]);
						final String s1 = r[i];

						Icon icon = Icon.newInstance(Icon.DEFAULT_ICON);
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
						map.addOverlay(mm);

					}
				} catch (Exception eee) {
				}
			}
		});
	}
}