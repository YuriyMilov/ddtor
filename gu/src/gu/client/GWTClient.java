package gu.client;

import gu.client.dao.RPCObjectFactory;
import gu.client.view.DatabaseEditorView;
import java.util.ArrayList;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.maps.client.InfoWindowContent;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.control.LargeMapControl;
import com.google.gwt.maps.client.event.MarkerClickHandler;
import com.google.gwt.maps.client.event.MarkerClickHandler.MarkerClickEvent;
import com.google.gwt.maps.client.geocode.Geocoder;
import com.google.gwt.maps.client.geocode.LatLngCallback;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.overlay.Icon;
import com.google.gwt.maps.client.overlay.Marker;
import com.google.gwt.maps.client.overlay.MarkerOptions;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DecoratedTabPanel;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class GWTClient implements EntryPoint {
	private static final int HeaderRowIndex = 0;
	private final dbServiceAsync srv = GWT.create(dbService.class);
	final ArrayList<Marker> ar = new ArrayList<Marker>();
	final ArrayList<Integer> aritab = new ArrayList<Integer>();
	LatLng place = LatLng.newInstance(44, -77);	
	private static String[][] tt_srv = null;
	private static String[][] tt_clt = null;
	private DatabaseEditorView view = new DatabaseEditorView();
	DecoratorPanel dp = new DecoratorPanel();
	int rowIndex = 1;
	private MapWidget map = new MapWidget();
	final Geocoder geo = new Geocoder();
	LatLng gde = LatLng.newInstance(44, -77);
	private FlexTable flexTable = new FlexTable();
	//private Button bb1 = new Button("Map View");
	private Button bb2 = new Button("Load Board");
	private Button bb3 = new Button("Database");
	private Button bb4 = new Button("Search");
	private Button bb5 = new Button("Submit");
	private Button bb6 = new Button("Reload Map");
	final ListBox km = new ListBox(false);
	final ListBox dropBox_shipper = new ListBox(false);
	final ListBox drop_box_equip = new ListBox(false);	
	final FlexTable layout = new FlexTable();
	final TextBox tbox1 = new TextBox();
	final TextBox tbox2 = new TextBox();

	public void onModuleLoad() {
		map = new MapWidget(gde, 3);
		map.setSize("1200px", "800px");
		map.setScrollWheelZoomEnabled(true);
		map.addControl(new LargeMapControl());

		srv.getData("login", new AsyncCallback<String[][]>() {
			public void onFailure(Throwable caught) {
				RootPanel.get().add(new HTML(caught.toString()));
			}

			public void onSuccess(final String r[][]) {
				if (r[0][0].indexOf("Logout") > -1) {
					onSigned(r[0][0]);

				} else
					RootPanel.get().add(new HTML(r[0][0]));
			}
		});
	}

	public void onSigned(String logout) {
		RootPanel.get().add(new HTML(logout));
		preps();
		srv.getData("all", new AsyncCallback<String[][]>() {
			public void onFailure(Throwable caught) {
				RootPanel.get().add(new HTML(caught.toString()));
			}

			public void onSuccess(String[][] r) {
				tt_srv = r;
				RootPanel.get().add(
						new HTML("<br>"));
				//ar.clear();
				getMap(r);
				RootPanel.get().add(map);
			}
		});

		//bb1.addClickHandler(new ClickHandler() {
		//	@Override
			//public void onClick(ClickEvent event) {

				dp.removeFromParent();
				view.removeFromParent();
				flexTable.removeFromParent();
				map.removeFromParent();
				map = new MapWidget(gde, 3);
				map.setSize("1200px", "800px");
				map.setScrollWheelZoomEnabled(true);
				map.addControl(new LargeMapControl());

				//for (int i = 0; i < ar.size(); i++)
				//	map.addOverlay(ar.get(i));
				//RootPanel.get().add(map);
			//}
		//});
				
	
				
		bb2.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {

				dp.removeFromParent();
				view.removeFromParent();
				map.removeFromParent();
				flexTable.removeAllRows();

				prepTable();
				getData();
				RootPanel.get().add(flexTable);
			}
		});
		bb3.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {

				dp.removeFromParent();
				view.removeFromParent();
				flexTable.removeFromParent();
				map.removeFromParent();

				RPCObjectFactory objectFactory = new RPCObjectFactory(GWT
						.getModuleBaseURL() + "objectFactory");
				view.setObjectFactory(objectFactory);

				RootPanel.get().add(view);
			}
		});
		bb4.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {

				// dp.removeFromParent();
				flexTable.removeFromParent();
				map.removeFromParent();
				view.removeFromParent();
				
				//map = new MapWidget(gde, 3);
				//map.setSize("1200px", "800px");
				//map.setScrollWheelZoomEnabled(true);
				//map.addControl(new LargeMapControl());
				
				RootPanel.get().add(dp);
			}
		});

		bb5.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {

				dp.removeFromParent();
				
				map = new MapWidget(gde, 3);
				map.setSize("1200px", "800px");
				map.setScrollWheelZoomEnabled(true);
				map.addControl(new LargeMapControl());
				//for (int i = 0; i < ar.size(); i++)
				//	map.addOverlay(ar.get(i));
				
				map.clearOverlays();
				layout.setHTML(0, 5, "Wait...");
				geo.getLatLng(tbox1.getText(), geoint);				
				
				RootPanel.get().add(map);
			}
		});

		bb6.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				dp.removeFromParent();
				view.removeFromParent();
				flexTable.removeFromParent();
				map.removeFromParent();

				ar.clear();
				map.clearOverlays();
				srv.getData("all", new AsyncCallback<String[][]>() {

					public void onFailure(Throwable caught) {
						RootPanel.get().add(new HTML(caught.toString()));
					}

					public void onSuccess(String[][] r) {
						tt_srv = r;
						//ar.clear();
						getMap(r);
					}
				});
				map.removeFromParent();
				map = new MapWidget(gde, 3);
				map.setSize("1200px", "800px");
				map.setScrollWheelZoomEnabled(true);
				map.addControl(new LargeMapControl());

				for (int i = 0; i < ar.size(); i++)
					map.addOverlay(ar.get(i));
				RootPanel.get().add(map);
			}
		});

	
		RootPanel.get().add(bb4);
		RootPanel.get().add(bb2);
		RootPanel.get().add(bb3);
		RootPanel.get().add(bb6);
	
	}

	private void addColumn(Object columnHeading) {
		Widget widget = createCellWidget(columnHeading);
		int cell = flexTable.getCellCount(HeaderRowIndex);

		widget.setWidth("100%");
		widget.addStyleName("FlexTable-ColumnLabel");

		flexTable.setWidget(HeaderRowIndex, cell, widget);

		flexTable.getCellFormatter().addStyleName(HeaderRowIndex, cell,
				"FlexTable-ColumnLabelCell");
	}

	private Widget createCellWidget(Object cellObject) {
		Widget widget = null;

		if (cellObject instanceof Widget)
			widget = (Widget) cellObject;
		else
			widget = new Label(cellObject.toString());

		return widget;
	}

	private void addRow(Object[] cellObjects) {

		for (int cell = 0; cell < cellObjects.length; cell++) {
			Widget widget = createCellWidget(cellObjects[cell]);
			flexTable.setWidget(rowIndex, cell, widget);
			flexTable.getCellFormatter().addStyleName(rowIndex, cell,
					"FlexTable-Cell");
		}
		rowIndex++;
	}

	private void applyDataRowStyles() {
		HTMLTable.RowFormatter rf = flexTable.getRowFormatter();

		for (int row = 1; row < flexTable.getRowCount(); ++row) {
			if ((row % 2) != 0) {
				rf.addStyleName(row, "FlexTable-OddRow");
			} else {
				rf.addStyleName(row, "FlexTable-EvenRow");
			}
		}
	}

	private void prepTable() {
		flexTable.insertRow(HeaderRowIndex);
		flexTable.getRowFormatter().addStyleName(HeaderRowIndex,
				"FlexTable-Header");
		addColumn("WO#(Name)");
		//mk.getName(),
		//mk.getCity(), mk.getProv(), mk.getPostalCode(),
		//mk.getLatitude(),mk.getLongtitude(), mk.getId()
		addColumn("City(From:)");
		addColumn("Prov(Origin)");
		addColumn("PostalCode(To:)");
		addColumn("Latitude(Destination)");
		addColumn("Longtitude(Equipmqnt)");
		addColumn("Id(Pieces)");
		addColumn("Type");
		addColumn("Description");
		addColumn("Weight:");
		addColumn("lbs");
		addColumn("kgs");
		addColumn("Pickup");
		addColumn("Delivery");
	}

	public void getData() {
		try {
			int k=0;
			
				tt_clt = new String[tt_srv.length][tt_srv[0].length - 8];
				for (int row = 0; row < tt_srv.length; row++) {
					for (int col = 0; col<tt_srv[row].length-8; col++) {
					 tt_clt[row][col]=tt_srv[row][col+8];
				 }
						if(aritab.contains(row))						
							addRow(tt_clt[row]);
				}
				
			

			applyDataRowStyles();

			flexTable.setCellSpacing(0);
			flexTable.addStyleName("FlexTable");
		} catch (Exception aa) {
			RootPanel.get().add(new HTML("getData *** "+ aa.toString()));
		}

	}

	public void preps() {
		
		srv.getData("shippers", new AsyncCallback<String[][]>() {
			public void onFailure(Throwable caught) {
				RootPanel.get().add(new HTML(caught.toString()));
			}

			public void onSuccess(String r[][]) {
				dropBox_shipper.addItem("", "");
				for (int i = 0; i < r.length; i++) {
					dropBox_shipper.addItem(r[i][0], r[i][3]);
				}
				//dropBox_shipper.setItemSelected(0, true);
			}
		});
		
		
		tbox1.setText("Toronto");
		tbox2.setText("Atlanta");
		km.addItem("10 km");
		km.addItem("20 km");
		km.addItem("50 km");
		km.addItem("100 km");
		km.addItem("200 km");
		km.addItem("all");
		layout.setWidget(0, 0, new HTML(" "));
		layout.setCellSpacing(7);
		

		layout.setWidget(1, 1, new Label("Shipper "));
		layout.setWidget(2, 1, dropBox_shipper);
		drop_box_equip.addItem("Skids", "Skids");
		drop_box_equip.addItem("Boxes", "Boxes");
		
		layout.setWidget(3, 1, new Label("Equipment Type "));
		layout.setWidget(4, 1, drop_box_equip);

		layout.setWidget(1, 2, new Label("Origin "));
		layout.setWidget(2, 2, tbox1);
		
		layout.setWidget(3, 2, new Label("Radius"));
		layout.setWidget(4, 2, km);

		layout.setWidget(1, 3, new Label("Destination "));
		layout.setWidget(2, 3, tbox2);

		layout.setWidget(4, 3, bb5);
		
		layout.setWidget(5, 0, new HTML(" "));
		
		dp.add(layout);
	}

	public void getMap(String[][] r) {
		try {
			for (int i = 0; i < r.length; i++) {
				final String color = r[i][0];
				final double dlat 
				
				= Double.parseDouble(r[i][1]);
				final double dlng = Double.parseDouble(r[i][2]);
				final String s1 = "Company name: "+r[i][8]+"<br>City: "+r[i][9]+"<br>Province: "+r[i][10]+"<br>Postal Code: "+r[i][11]+"<br>Latitude: "+r[i][12]+"<br>Longtitude: "+r[i][13];
				// RootPanel.get().add(new HTML(s1));
				final Icon icon = Icon.newInstance(Icon.DEFAULT_ICON);
				if (color.equals("b"))
					icon.setImageURL("blue.png");
				else if (color.equals("g"))
					icon.setImageURL("markerGreen.png");
				else
					icon.setImageURL("marker.png");
				MarkerOptions ops = MarkerOptions.newInstance(icon);
				ops.setIcon(icon);
				final Marker mm = new Marker(LatLng.newInstance(dlat, dlng),
						ops);
				mm.addMarkerClickHandler(new MarkerClickHandler() {

					public void onClick(MarkerClickEvent event) {
						map.getInfoWindow().open(mm, new InfoWindowContent(s1));
					}
				});
				ar.add(mm);
				aritab.add(i);
				map.addOverlay(mm);
			}
		} catch (Exception eee) {
			RootPanel.get().add(new HTML(eee.toString()));
		}

		// /////////////////////////////////////////
/*
		try {
			for (int i = 0; i < r.length; i++) {
				final String color = r[i][4];
				final double dlat = Double.parseDouble(r[i][5]);
				final double dlng = Double.parseDouble(r[i][6]);
				final String s1 = r[i][7];
				// RootPanel.get().add(new HTML(s1));
				final Icon icon = Icon.newInstance(Icon.DEFAULT_ICON);
				if (color.equals("b"))
					icon.setImageURL("blue.png");
				else if (color.equals("g"))
					icon.setImageURL("markerGreen.png");
				else
					icon.setImageURL("marker.png");
				MarkerOptions ops = MarkerOptions.newInstance(icon);
				ops.setIcon(icon);
				final Marker mm = new Marker(LatLng.newInstance(dlat, dlng),
						ops);
				mm.addMarkerClickHandler(new MarkerClickHandler() {

					public void onClick(MarkerClickEvent event) {
						map.getInfoWindow().open(mm, new InfoWindowContent(s1));
					}
				});
				ar.add(mm);
				map.addOverlay(mm);
			}

		} catch (Exception eee) {
			RootPanel.get().add(new HTML(eee.toString()));
		}
		*/
	}
	
/*	  
	Button but1 = new Button("Submit", new ClickHandler() {
		public void onClick(ClickEvent event) {
			map.clearOverlays();
			layout.setHTML(0, 5, "Wait...");
			geo.getLatLng(tbox1.getText(), geoint);
		}
	});
*/
	   final LatLngCallback geoint = new LatLngCallback()
		  {
			@Override
			public void onFailure() {
				RootPanel.get().add(new HTML("???"));
			}
			@Override
			public void onSuccess(LatLng point) {
				try {
					aritab.clear();
					
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
													"Origin point:<br>"
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
								{
								map.addOverlay(ar.get(i));
								aritab.add(i);
								}
						}
					}

					if (skm.equals("20 km")) {
						map.setCenter(place, 10);
						for (int i = 0; i < ar.size(); i++) {
							double dis = place.distanceFrom(ar.get(
									i).getLatLng());
							if (dis < 20000)
							{
								map.addOverlay(ar.get(i));
								aritab.add(i);
								}
				
						}
					}
					if (skm.equals("50 km")) {
						map.setCenter(place, 9);
						for (int i = 0; i < ar.size(); i++) {
							double dis = place.distanceFrom(ar.get(
									i).getLatLng());
							if (dis < 100000)
							{
								map.addOverlay(ar.get(i));
								aritab.add(i);
								}
				
						}
					}

					if (skm.equals("100 km")) {
						map.setCenter(place, 8);

						for (int i = 0; i < ar.size(); i++) {
							double dis = place.distanceFrom(ar.get(
									i).getLatLng());
							if (dis < 100000)
							{
								map.addOverlay(ar.get(i));
								aritab.add(i);
								}
				
						}
					}
					if (skm.equals("200 km")) {
						map.setCenter(place, 7);

						for (int i = 0; i < ar.size(); i++) {
							double dis = place.distanceFrom(ar.get(
									i).getLatLng());
							if (dis < 100000)
							{
								map.addOverlay(ar.get(i));
								aritab.add(i);
								}
				
						}
					}
					if (skm.equals("all")) {
						map.setCenter(place, 3);
						for (int i = 0; i < ar.size(); i++)
						{
							map.addOverlay(ar.get(i));
							aritab.add(i);
							}
			
					}
					layout.setHTML(0, 5, "");
					
				} catch (Exception eee) {
					layout.setHTML(0, 5, eee.toString());
				}
				}
		  };


}
