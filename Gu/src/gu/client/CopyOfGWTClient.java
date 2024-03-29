package gu.client;

import gu.client.dao.RPCObjectFactory;
import gu.client.ui.LoadingPanel;
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
import com.google.gwt.maps.client.geocode.Geocoder;
import com.google.gwt.maps.client.geocode.LatLngCallback;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.overlay.Icon;
import com.google.gwt.maps.client.overlay.Marker;
import com.google.gwt.maps.client.overlay.MarkerOptions;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class CopyOfGWTClient implements EntryPoint {
	private static final int HeaderRowIndex = 0;
	private final dbServiceAsync srv = GWT.create(dbService.class);
	final ArrayList<Marker> ar = new ArrayList<Marker>();
	final ArrayList<Integer> aritab = new ArrayList<Integer>();
	LatLng place = LatLng.newInstance(44, -77);
	private static String[][] tt_srv = null;
	private static String[][] tt_clt = null;
	private DatabaseEditorView view = new DatabaseEditorView();
	int rowIndex = 1;
	MapWidget map = new MapWidget();
	VerticalPanel pvMap = new VerticalPanel();
	DecoratorPanel dp2 = new DecoratorPanel();
	HorizontalPanel phSearch = new HorizontalPanel();
	VerticalPanel pvL = new VerticalPanel();
	VerticalPanel pvR = new VerticalPanel();
	HorizontalPanel phTop = new HorizontalPanel();
	HorizontalPanel phMain = new HorizontalPanel();
	HorizontalPanel phLogin = new HorizontalPanel();

	private LoadingPanel loading = new LoadingPanel(new Label("loading..."));

	final Geocoder geo = new Geocoder();
	final LatLng gde = LatLng.newInstance(44, -77);

	final Button but_map = new Button("Map");
	final Button but_LoadBoard = new Button("Load Board");
	final Button but_database = new Button("Database");
	// final Button but_search = new Button("Search");
	final Button but_submit = new Button("Submit");
	final Button but_reload = new Button("Reload");
	final FlexTable flexTable = new FlexTable();
	final ListBox km = new ListBox(false);
	// final ListBox dropBox_shipper = new ListBox(false);
	final ListBox drop_box_equip = new ListBox(false);
	final FlexTable layout = new FlexTable();
	final TextBox tbox1 = new TextBox();
	final TextBox tbox2 = new TextBox();

	public void onModuleLoad() {

		map = new MapWidget(gde, 3);
		map.setSize("1200px", "800px");
		map.setScrollWheelZoomEnabled(true);
		map.addControl(new LargeMapControl());

		pvL.setWidth("20px");
		phMain.add(pvL);

		pvR.add(phLogin);
		pvR.add(phTop);

		phSearch.add(dp2);
		phMain.add(pvR);

		srv.getData("login", new AsyncCallback<String[][]>() {
			public void onFailure(Throwable caught) {
				phLogin.add(new HTML(caught.toString()));

			}

			public void onSuccess(final String r[][]) {
				
				if (r[0][0].indexOf("Logout") > -1) {
					onSigned(r[0][0]);
				} else
					phLogin.add(new HTML(r[0][0]));
			}
		});
		RootPanel.get().add(phMain);
	}

	public void onSigned(String logout) {
		phLogin.add(new HTML(logout));
		srv.getData("all", new AsyncCallback<String[][]>() {
			public void onFailure(Throwable caught) {
				phLogin.add(new HTML(caught.toString()));
			}

			public void onSuccess(String[][] r2) {
				
				
				ArrayList<String[]> ar2 = new ArrayList<String[]>();

				for (int j = 0; j < r2.length; j++) {
					String[] ccn = new String[] { r2[j][4], r2[j][5],r2[j][6], r2[j][7],
							"","","","",r2[j][8],r2[j][9],r2[j][10],r2[j][11],r2[j][12],
							r2[j][13],r2[j][14],r2[j][15],r2[j][16],r2[j][17],r2[j][18],
							r2[j][19],r2[j][20],r2[j][21] };
					if(!r2[j][5].equals("0"))
						ar2.add(ccn);
					String[] ccn2 = new String[] { r2[j][0], r2[j][1],
							r2[j][2], r2[j][3], "", "","", 
							"",r2[j][8],r2[j][9],r2[j][10],r2[j][11],r2[j][12],
							r2[j][13],r2[j][14],r2[j][15],r2[j][16],r2[j][17],r2[j][18],
							r2[j][19],r2[j][20],r2[j][21] };
					if(!r2[j][5].equals("0"))
						ar2.add(ccn2);
				}
				
				String[][] r = new String[2 * r2.length][22];
				for (int n = 0; n < ar2.size(); n++) {
					r[n] = ar2.get(n);
				}
				//phLogin.add(new HTML(String.valueOf(r2.length )+" "+String.valueOf(ar2.size())));
				
				tt_srv = r;
				prep_Search_Panel_dp();
				prepButtons();

				pvR.add(phSearch);
				pvR.add(pvMap);

				phTop.add(but_map);
				phTop.add(but_LoadBoard);
				phTop.add(but_database);
				phTop.add(but_reload);
				// phTop.add(but_search);

				set_Map_Markers(r);
				pvMap.add(map);

			}
		});
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

	private void prep_FlexTable() {
		flexTable.insertRow(HeaderRowIndex);
		flexTable.getRowFormatter().addStyleName(HeaderRowIndex,
				"FlexTable-Header");
		addColumn("WO#");
		addColumn("From:");
		addColumn("Origin");
		addColumn("To:");
		addColumn("Destination");
		addColumn("Equipmqnt");
		addColumn("Pieces");
		addColumn("Type");
		addColumn("Description");
		addColumn("Weight:");
		addColumn("lbs");
		addColumn("kgs");
		addColumn("Pickup");
		addColumn("Delivery");
	}

	public void set_data_for_flexTable() {
		try {
			tt_clt = new String[tt_srv.length][tt_srv[0].length - 8];
			for (int row = 0; row < tt_srv.length; row++) {
				for (int col = 0; col < tt_srv[row].length - 8; col++) {
					tt_clt[row][col] = tt_srv[row][col + 8];
				}
				if (aritab.contains(row))
					addRow(tt_clt[row++]);
			}

			applyDataRowStyles();

			flexTable.setCellSpacing(0);
			flexTable.addStyleName("FlexTable");
		} catch (Exception aa) {
			phLogin.add(new HTML("getData *** " + aa.toString()));
		}

	}

	void prepButtons() {

		but_map.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {

				map.removeFromParent();
				// dp.removeFromParent();
				view.removeFromParent();
				flexTable.removeAllRows();
				flexTable.removeFromParent();

				pvMap.add(map);
			}
		});
		but_LoadBoard.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {

				map.removeFromParent();
				// dp.removeFromParent();
				view.removeFromParent();
				flexTable.removeAllRows();
				flexTable.removeFromParent();

				prep_FlexTable();
				set_data_for_flexTable();
				pvMap.add(flexTable);
			}
		});
		but_database.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {

				map.removeFromParent();
				// dp.removeFromParent();
				view.removeFromParent();
				flexTable.removeAllRows();
				flexTable.removeFromParent();

				RPCObjectFactory objectFactory = new RPCObjectFactory(GWT
						.getModuleBaseURL() + "objectFactory");
				view.setObjectFactory(objectFactory);

				pvMap.add(view);
			}
		});
		/*
		 * but_search.addClickHandler(new ClickHandler() {
		 * 
		 * @Override public void onClick(ClickEvent event) {
		 * 
		 * // dp.removeFromParent(); flexTable.removeFromParent();
		 * map.removeFromParent(); view.removeFromParent();
		 * 
		 * pvMap.add(dp); } });
		 */

		but_submit.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {

				map.removeFromParent();
				// dp.removeFromParent();
				view.removeFromParent();
				flexTable.removeAllRows();
				flexTable.removeFromParent();

				map.clearOverlays();
				layout.setHTML(0, 5, "Wait...");
				geo.getLatLng(tbox1.getText(), geoint);

				pvMap.add(map);
			}
		});
		but_reload.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				map.removeFromParent();
				// dp.removeFromParent();
				view.removeFromParent();
				flexTable.removeAllRows();
				flexTable.removeFromParent();

				ar.clear();
				map.clearOverlays();
				srv.getData("all", new AsyncCallback<String[][]>() {

					public void onFailure(Throwable caught) {
						pvR.add(new HTML(caught.toString()));
					}

					public void onSuccess(String[][] r2) {

						
						
						ArrayList<String[]> ar2 = new ArrayList<String[]>();

						for (int j = 0; j < r2.length; j++) {
							String[] ccn = new String[] { r2[j][4], r2[j][5],r2[j][6], r2[j][7],
									"","","","",r2[j][8],r2[j][9],r2[j][10],r2[j][11],r2[j][12],
									r2[j][13],r2[j][14],r2[j][15],r2[j][16],r2[j][17],r2[j][18],
									r2[j][19],r2[j][20],r2[j][21] };
							if(!r2[j][5].equals("0"))
								ar2.add(ccn);
							String[] ccn2 = new String[] { r2[j][0], r2[j][1],
									r2[j][2], r2[j][3], "", "","", 
									"",r2[j][8],r2[j][9],r2[j][10],r2[j][11],r2[j][12],
									r2[j][13],r2[j][14],r2[j][15],r2[j][16],r2[j][17],r2[j][18],
									r2[j][19],r2[j][20],r2[j][21] };
							if(!r2[j][5].equals("0"))
								ar2.add(ccn2);
						}
						
						String[][] r = new String[2 * r2.length][22];
						for (int n = 0; n < ar2.size(); n++) {
							r[n] = ar2.get(n);
						}

							tt_srv = r;

						// ar.clear();
						set_Map_Markers(r);
					}
				});

				for (int i = 0; i < ar.size(); i++)
					map.addOverlay(ar.get(i));

				map = new MapWidget(gde, 3);
				map.setSize("1200px", "800px");
				map.setScrollWheelZoomEnabled(true);
				map.addControl(new LargeMapControl());
				pvMap.add(map);
			}
		});
	}

	/*
	 * void load_shippers() {
	 * 
	 * srv.getData("shippers", new AsyncCallback<String[][]>() { public void
	 * onFailure(Throwable caught) { pvR.add(new HTML(caught.toString())); }
	 * 
	 * public void onSuccess(String r[][]) { dropBox_shipper.addItem("", "");
	 * for (int i = 0; i < r.length; i++) { dropBox_shipper.addItem(r[i][0],
	 * r[i][3]); } } });
	 * 
	 * }
	 */
	void prep_Search_Panel_dp() {
		tbox1.setText("Toronto");
		//tbox2.setText("Atlanta");
		km.addItem("all");
		km.addItem("10 km");
		km.addItem("20 km");
		km.addItem("50 km");
		km.addItem("100 km");
		km.addItem("200 km");
		
		layout.setWidget(0, 0, new HTML(" "));
		layout.setCellSpacing(7);
		// layout.setWidget(1, 1, new Label("Shipper "));
		// layout.setWidget(2, 1, dropBox_shipper);
		// drop_box_equip.addItem("Skids", "Skids");
		// drop_box_equip.addItem("Boxes", "Boxes");
		// layout.setWidget(3, 1, new Label("Equipment Type "));
		// layout.setWidget(4, 1, drop_box_equip);

		layout.setWidget(0, 0, new Label("Origin "));
		layout.setWidget(0, 1, tbox1);
		layout.setWidget(0, 2, new Label("Radius"));
		layout.setWidget(0, 3, km);
		//layout.setWidget(0, 4, new Label("Destination "));
		//layout.setWidget(0, 5, tbox2);
		layout.setWidget(0, 4, but_submit);

		dp2.add(layout);
	}

	public void set_Map_Markers(String[][] r) {
		try {

			for (int i = 0; i < r.length; i++) {
				final String color = r[i][0];
				double dlat = -1;
				try {
					dlat = Double.parseDouble(r[i][1]);
				} catch (Exception e) {
				}
				double dlng = -1;
				try {
					dlng = Double.parseDouble(r[i][2]);
				} catch (Exception e) {
				}

				final String s1 = "WO#: " + r[i][8]+" <br> more info may be added here...";
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
			phLogin.add(new HTML(eee.toString()));
		}

	}

	final LatLngCallback geoint = new LatLngCallback() {
		@Override
		public void onFailure() {
			phLogin.add(new HTML("???"));
		}

		@Override
		public void onSuccess(LatLng point) {
			try {
				aritab.clear();

				place = point;
				Icon icon = Icon.newInstance(Icon.DEFAULT_ICON);
				icon.setImageURL("blue.png");
				MarkerOptions ops = MarkerOptions.newInstance(icon);
				ops.setIcon(icon);
				final Marker md = new Marker(place, ops);
				md.addMarkerClickHandler(new MarkerClickHandler() {
					public void onClick(MarkerClickEvent event) {
						map.getInfoWindow().open(
								md,
								new InfoWindowContent("Origin point:<br>"
										+ tbox1.getText()));
					}
				});
				map.addOverlay(md);
				String skm = km.getItemText(km.getSelectedIndex());
				if (skm.equals("10 km")) {
					map.setCenter(place, 11);
					for (int i = 0; i < ar.size(); i++) {
						double dis = place.distanceFrom(ar.get(i).getLatLng());
						if (dis < 10000) {
							map.addOverlay(ar.get(i));
							aritab.add(i);
						}
					}
				}

				if (skm.equals("20 km")) {
					map.setCenter(place, 10);
					for (int i = 0; i < ar.size(); i++) {
						double dis = place.distanceFrom(ar.get(i).getLatLng());
						if (dis < 20000) {
							map.addOverlay(ar.get(i));
							aritab.add(i);
						}

					}
				}
				if (skm.equals("50 km")) {
					map.setCenter(place, 9);
					for (int i = 0; i < ar.size(); i++) {
						double dis = place.distanceFrom(ar.get(i).getLatLng());
						if (dis < 100000) {
							map.addOverlay(ar.get(i));
							aritab.add(i);
						}

					}
				}

				if (skm.equals("100 km")) {
					map.setCenter(place, 8);

					for (int i = 0; i < ar.size(); i++) {
						double dis = place.distanceFrom(ar.get(i).getLatLng());
						if (dis < 100000) {
							map.addOverlay(ar.get(i));
							aritab.add(i);
						}

					}
				}
				if (skm.equals("200 km")) {
					map.setCenter(place, 7);

					for (int i = 0; i < ar.size(); i++) {
						double dis = place.distanceFrom(ar.get(i).getLatLng());
						if (dis < 100000) {
							map.addOverlay(ar.get(i));
							aritab.add(i);
						}

					}
				}
				if (skm.equals("all")) {
					map.setCenter(place, 3);
					for (int i = 0; i < ar.size(); i++) {
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
