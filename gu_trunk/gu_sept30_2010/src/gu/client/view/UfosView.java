package gu.client.view;

import java.util.ArrayList;

import gu.client.dbService;
import gu.client.dbServiceAsync;
import gu.client.dao.ObjectFactory;
import gu.client.dao.ObjectFactoryListener;
import gu.client.dao.RPCObjectFactory;
import gu.client.ui.GoogleMap;
import gu.client.ui.LoadingPanel;
import gu.client.ui.RoundedPanel;
import gu.client.view.treeitems.BaseTreeItem;
import gu.client.view.treeitems.ShippersTreeItem;
import gu.client.view.treeitems.ConsigneesTreeItem;
import gu.client.view.treeitems.StoriesTreeItem;
import gu.client.view.treeitems.UsersTreeItem;
import gu.client.view.treeitems.WordersTreeItem;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.DoubleClickHandler;
import com.google.gwt.maps.client.InfoWindowContent;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.control.LargeMapControl;
import com.google.gwt.maps.client.event.MapClickHandler;
import com.google.gwt.maps.client.event.MapDoubleClickHandler;
import com.google.gwt.maps.client.event.MapDoubleClickHandler.MapDoubleClickEvent;
import com.google.gwt.maps.client.event.MarkerClickHandler.MarkerClickEvent;
import com.google.gwt.maps.client.event.MarkerClickHandler;
import com.google.gwt.maps.client.event.MarkerDoubleClickHandler;
import com.google.gwt.maps.client.event.MarkerDragEndHandler;
import com.google.gwt.maps.client.event.MarkerDragEndHandler.MarkerDragEndEvent;
import com.google.gwt.maps.client.event.MarkerMouseOverHandler;
import com.google.gwt.maps.client.geocode.Geocoder;
import com.google.gwt.maps.client.geocode.LatLngCallback;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.overlay.Icon;
import com.google.gwt.maps.client.overlay.Marker;
import com.google.gwt.maps.client.overlay.MarkerOptions;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DecoratedTabPanel;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.HorizontalSplitPanel;
import com.google.gwt.user.client.ui.InsertPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.gwt.user.client.ui.TreeListener;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.PopupPanel;

public class UfosView extends Composite {
	private HTML load = new HTML("<font color=\"#DF0101\">&nbsp;&nbsp;loading...&nbsp;</font>");
	//private LoadingPanel load = new LoadingPanel(new HTML("<font color=\"#DF0101\">&nbsp;loading...&nbsp;</font>"));

	boolean bo = true;
	LatLng a1 = LatLng.newInstance(44, -77);
	LatLng a2 = LatLng.newInstance(44, -77);
	String s = "";
	HTML info = new HTML(s);
	private MapWidget map;
	// private VerticalPanel mm = new VerticalPanel();
	private ObjectFactory objectFactory = new RPCObjectFactory(GWT
			.getModuleBaseURL() + "objectFactory");

	private static final int HeaderRowIndex = 0;
	private final dbServiceAsync srv = GWT.create(dbService.class);
	final ArrayList<Marker> ar = new ArrayList<Marker>();
	ArrayList<Marker> ar2 = new ArrayList<Marker>();
	final ArrayList<Integer> aritab = new ArrayList<Integer>();
	final ArrayList<Integer> aritab2 = new ArrayList<Integer>();
	final ArrayList<String> arwo = new ArrayList<String>();
	LatLng place = LatLng.newInstance(44, -77);
	private static String[][] tt_srv = null;
	private static String[][] tt_clt = null;
	private static String[][] tt_clt2 = null;
	private DatabaseEditorView view = new DatabaseEditorView();
	int rowIndex = 1;

	VerticalPanel mainPanel = new VerticalPanel();
	// VerticalPanel pvMap = new VerticalPanel();
	HorizontalPanel phd = new HorizontalPanel();
	VerticalPanel pvL = new VerticalPanel();
	VerticalPanel pvR = new VerticalPanel();
	private DecoratorPanel pDecor = new DecoratorPanel();
	HorizontalPanel phTop = new HorizontalPanel();
	HorizontalPanel phGlav = new HorizontalPanel();
	HorizontalPanel phLogin = new HorizontalPanel();
	HTML sign = new HTML();
	final Geocoder geo = new Geocoder();
	final LatLng gde = LatLng.newInstance(44, -77);

	final Button but_map = new Button("Map");
	final Button but_board = new Button("Board");

	// final Button but_LoadBoard = new Button("Board");
	final Button but_database = new Button("Database");
	// final Button but_search = new Button("Search");
	final Button but_search = new Button("Search");
	final Button but_reload = new Button("Update");
	final FlexTable flexTable = new FlexTable();

	final ListBox dropBox1 = new ListBox(false);
	final ListBox km1 = new ListBox(false);
	final ListBox km2 = new ListBox(false);
	// final ListBox dropBox_shipper = new ListBox(false);
	final ListBox drop_box_equip = new ListBox(false);
	final FlexTable layout = new FlexTable();
	final TextBox tbox1 = new TextBox();
	final TextBox tbox2 = new TextBox();

	public UfosView() {
		view.setObjectFactory(objectFactory);

		initWidget(mainPanel);
		mainPanel.add(load);
		mainPanel.add(phLogin);
		
		mainPanel.add(phGlav);

		// mainPanel.add(pvMap);
		setStyleName("databaseEditorView");
		map = new MapWidget(LatLng.newInstance(44, -77), 3);
		map.setSize("600px", "400px");
		map.setScrollWheelZoomEnabled(true);
		map.addControl(new LargeMapControl());
		map.addMapClickHandler(h);
		prepSearchPanel();
		onSigned("<br>");

		/*
		 * srv.getData("login", new AsyncCallback<String[][]>() { public void
		 * onFailure(Throwable caught) { phLogin.add(new
		 * HTML(caught.toString()));
		 * 
		 * }
		 * 
		 * public void onSuccess(final String r[][]) {
		 * 
		 * onSigned_qq("<br>");
		 * 
		 * 
		 * if (r[0][0].indexOf("Logout") > -1) { //onSigned(r[0][0]);
		 * onSigned_qq(r[0][0]);
		 * 
		 * 
		 * } else { sign=new HTML("<center>"+r[0][0]+"</center>");
		 * sign.setWidth("400px"); phLogin.add(sign); onLoadingFinish(); }
		 * 
		 * 
		 * } });
		 */
	}

	final MapClickHandler h = new MapClickHandler() {
		public void onClick(MapClickEvent event) {
			info.removeFromParent();
			if (event.getLatLng() != null)
				info = new HTML(String.valueOf(event.getLatLng()));
			if (event.getOverlayLatLng() != null)
				info = new HTML(String.valueOf(event.getOverlayLatLng()));
			phd.add(info);

		}
	};

	public void onSigned(String logout) {
		phLogin.add(new HTML(logout));
		
		srv.getData("qq", new AsyncCallback<String[][]>() {
			public void onFailure(Throwable caught) {
				fin();
				phLogin.add(new HTML(caught.toString()));
			}

			public void onSuccess(String[][] r) {
				
				prepButtons();

				pvL.setWidth("11px");
				phGlav.add(pvL);
				phTop.add(but_board);
				phTop.add(but_map);
				phTop.add(but_database);
				phTop.add(but_reload);
				pvR.add(phTop);
				pvR.add(pDecor);
				phd.setHeight("11px");
				phd.add(info);
				pvR.add(phd);
				//pvR.add(map);
				phGlav.add(pvR);

				setMarkers(r);
				geo.getLatLng(tbox1.getText(), geoint);
				geo.getLatLng(tbox2.getText(), geoint);
				qqqq();
				fin();
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

	void prepButtons() {

		but_board.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				start();
				map.removeFromParent();
				// dp.removeFromParent();
				view.removeFromParent();
				flexTable.removeAllRows();
				flexTable.removeFromParent();

				get_board();

				pvR.add(flexTable);
				fin();
			}
		});

		but_map.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				start();
				//
				map.removeFromParent();
				view.removeFromParent();
				flexTable.removeAllRows();
				flexTable.removeFromParent();
				//

				pvR.add(map);
				fin();
			}
		});

		but_database.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				start();
				//
				map.removeFromParent();
				view.removeFromParent();
				flexTable.removeAllRows();
				flexTable.removeFromParent();
				//

				pvR.add(view);
				fin();
			}
		});

		but_reload.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				start();
				map.clearOverlays();
				ar.clear();
				srv.getData("qq", new AsyncCallback<String[][]>() {
					public void onFailure(Throwable caught) {

						phLogin.add(new HTML(caught.toString()));
						fin();
					}

					public void onSuccess(String[][] r) {

						//
						map.removeFromParent();
						view.removeFromParent();
						flexTable.removeAllRows();
						flexTable.removeFromParent();
						//
						map = new MapWidget(LatLng.newInstance(44, -77), 3);
						map.setSize("600px", "400px");
						map.setScrollWheelZoomEnabled(true);
						map.addControl(new LargeMapControl());
						map.addMapClickHandler(h);

						setMarkers(r);

						pvR.add(map);
						fin();
					}
				});
				
			}
		});

		but_search.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				start();
				map.removeFromParent();
				view.removeFromParent();

				map.clearOverlays();
				if(tbox1.getText().length()>0 && tbox2.getText().length()>0)
				{
				geo.getLatLng(tbox1.getText(), geoint);
				geo.getLatLng(tbox2.getText(), geoint);
				qqqq();
				}
				else Window.alert("Please fill in a location");

				flexTable.removeAllRows();
				flexTable.removeAllRows();
				get_board();

				flexTable.removeFromParent();
				flexTable.removeFromParent();
				pvR.add(flexTable);
				fin();
			}
		});
	}

	void prepSearchPanel() {
		tbox1.setText("toronto");
		tbox2.setText("calgary");

		km1.addItem("");
		km1.addItem("10 km");
		km1.addItem("20 km");
		km1.addItem("50 km");
		km1.addItem("100 km");
		km1.addItem("200 km");
		km1.setItemSelected(3, true);

		km2.addItem("");
		km2.addItem("10 km");
		km2.addItem("20 km");
		km2.addItem("50 km");
		km2.addItem("100 km");
		km2.addItem("200 km");
		km2.setItemSelected(5, true);

		layout.setWidget(0, 0, new HTML(" "));
		layout.setCellSpacing(7);
		// layout.setWidget(1, 0, new Label("Province "));

		// layout.setWidget(1, 1, dropBox1);
		// dropBox1.addItem("", "");
		// dropBox1.addItem("ON", "ON");
		// dropBox1.addItem("BC", "BC");

		// drop_box_equip.addItem("Skids", "Skids");
		// drop_box_equip.addItem("Boxes", "Boxes");
		// layout.setWidget(3, 1, new Label("Equipment Type "));
		// layout.setWidget(4, 1, drop_box_equip);

		layout.setWidget(0, 0, new Label("Origin "));
		layout.setWidget(0, 1, tbox1);
		layout.setWidget(0, 2, new Label("O-Radius"));
		layout.setWidget(0, 3, km1);
		// layout.setWidget(0, 4, new Label("Destination "));
		// layout.setWidget(0, 5, tbox2);
		// layout.setWidget(0, 4, but_submit);

		layout.setWidget(1, 0, new Label("Destination"));
		layout.setWidget(1, 1, tbox2);
		layout.setWidget(1, 2, new Label("D-Radius"));
		layout.setWidget(1, 3, km2);
		// layout.setWidget(0, 4, new Label("Destination "));
		// layout.setWidget(0, 5, tbox2);
		layout.setWidget(1, 4, but_search);

		pDecor.add(layout);
	}

	public void setMarkers(String[][] r) {
		if (r.length > 0)
			if (r[0].length > 6)
				try {
					tt_srv = r;
					double d0 = 0, d1 = 0, d2 = 0, d3 = 0;
					for (int i = 0; i < r.length; i++) {
						final String s1 = String.valueOf(r.length) + " WO#: "
								+ r[i][4] + "<br>" + r[i][6] + "<br>" + r[i][8];
						try {
							d0 = Double.parseDouble(r[i][0]);
							d1 = Double.parseDouble(r[i][1]);
						} catch (Exception e) {
						}

						final Icon icon = Icon.newInstance(Icon.DEFAULT_ICON);
						icon.setImageURL("marker.png");
						// icon.setImageURL("markerGreen.png");
						MarkerOptions ops = MarkerOptions.newInstance(icon);
						ops.setIcon(icon);
						final Marker mm1 = new Marker(
								LatLng.newInstance(d0, d1), ops);

						/*
						 * mm1.addMarkerMouseOverHandler(new
						 * MarkerMouseOverHandler() {
						 * 
						 * @Override public void
						 * onMouseOver(MarkerMouseOverEvent event) {
						 * map.getInfoWindow().open(mm1, new
						 * InfoWindowContent(s1)); } });
						 */

						mm1.addMarkerClickHandler(new MarkerClickHandler() {

							public void onClick(MarkerClickEvent event) {
								// map.removeOverlay(mm1);
								map.getInfoWindow().open(mm1,
										new InfoWindowContent(s1));
							}
						});

						ar.add(mm1);
						// aritab.add(i);

						map.addOverlay(mm1);

					}

					for (int i = 0; i < r.length; i++) {
						final String s2 = "WO#: " + r[i][4] + "<br>" + r[i][6]
								+ "<br>" + r[i][8];

						try {
							d2 = Double.parseDouble(r[i][2]);
							d3 = Double.parseDouble(r[i][3]);
						} catch (Exception e) {
						}

						final Icon icon = Icon.newInstance(Icon.DEFAULT_ICON);
						// icon.setImageURL("marker.png");
						icon.setImageURL("markerGreen.png");
						MarkerOptions ops = MarkerOptions.newInstance(icon);
						ops.setIcon(icon);
						final Marker mm2 = new Marker(
								LatLng.newInstance(d2, d3), ops);
						/*
						 * mm2.addMarkerMouseOverHandler(new
						 * MarkerMouseOverHandler() {
						 * 
						 * @Override public void
						 * onMouseOver(MarkerMouseOverEvent event) {
						 * 
						 * map.getInfoWindow().open(mm2, new
						 * InfoWindowContent(s2)); } });
						 */

						mm2.addMarkerClickHandler(new MarkerClickHandler() {

							public void onClick(MarkerClickEvent event) {
								// map.removeOverlay(mm2);
								map.getInfoWindow().open(mm2,
										new InfoWindowContent(s2));

							}
						});

						ar2.add(mm2);
						// aritab2.add(i);

						map.addOverlay(mm2);

					}

				} catch (Exception eee) {
					phLogin.add(new HTML(String.valueOf(r.length) + " "
							+ eee.toString()));
				}

	}

	void get_board() {
		rowIndex = 1;
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
		arwo.clear();
		// if (tt_srv != null)
		// if (tt_srv.length > 0)
		if (tt_srv[0].length > 4) {
			try {
				tt_clt = new String[tt_srv.length][tt_srv[0].length - 4];
				for (int row = 0; row < tt_srv.length; row++) {
					for (int col = 0; col < tt_srv[row].length - 4; col++) {
						tt_clt[row][col] = tt_srv[row][col + 4];
					}
					if (aritab.contains(row))
						arwo.add(tt_clt[row][0]);
				}

				if (tt_clt[0].length > 4) {
					tt_clt2 = new String[tt_clt.length][tt_clt[0].length];
					for (int row2 = 0; row2 < tt_clt.length; row2++) {
						for (int col = 0; col < tt_clt[row2].length; col++) {
							tt_clt2[row2][col] = tt_clt[row2][col];
						}
						if (aritab2.contains(row2))
							if (arwo.contains(tt_clt2[row2][0]))
								addRow(tt_clt2[row2++]);

					}
				}
				applyDataRowStyles();
				flexTable.setCellSpacing(0);
				flexTable.addStyleName("FlexTable");
			} catch (Exception aa) {
				phLogin.add(new HTML("getData *** " + aa.toString()));
			}
		}
	}

	final LatLngCallback geoint = new LatLngCallback() {
		@Override
		public void onFailure() {
			phLogin.add(new HTML("geoint failure; "));
		}

		@Override
		public void onSuccess(LatLng point) {
			if (bo) {
				a1 = LatLng.newInstance(point.getLatitude(),
						point.getLongitude());
				bo = false;
			} else {
				a2 = LatLng.newInstance(point.getLatitude(),
						point.getLongitude());

				bo = true;
			}
		}
	};

	void qqqq() {

		aritab.clear();

		place = a1;
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
		String skm = km1.getItemText(km1.getSelectedIndex());
		if (skm.equals("10 km")) {
			//map.setCenter(place, 11);
			for (int i = 0; i < ar.size(); i++) {
				double dis = place.distanceFrom(ar.get(i).getLatLng());
				if (dis < 10000) {
					map.addOverlay(ar.get(i));
					aritab.add(i);
				}
			}
		} else if (skm.equals("20 km")) {
			//map.setCenter(place, 10);
			for (int i = 0; i < ar.size(); i++) {
				double dis = place.distanceFrom(ar.get(i).getLatLng());
				if (dis < 20000) {
					map.addOverlay(ar.get(i));
					aritab.add(i);
				}

			}
		} else if (skm.equals("50 km")) {
			//map.setCenter(place, 9);
			for (int i = 0; i < ar.size(); i++) {
				double dis = place.distanceFrom(ar.get(i).getLatLng());
				if (dis < 50000) {
					map.addOverlay(ar.get(i));
					aritab.add(i);
				}

			}
		} else if (skm.equals("100 km")) {
			//map.setCenter(place, 8);

			for (int i = 0; i < ar.size(); i++) {
				double dis = place.distanceFrom(ar.get(i).getLatLng());
				if (dis < 100000) {
					map.addOverlay(ar.get(i));
					aritab.add(i);
				}

			}
		} else if (skm.equals("200 km")) {
			//map.setCenter(place, 7);

			for (int i = 0; i < ar.size(); i++) {
				double dis = place.distanceFrom(ar.get(i).getLatLng());
				if (dis < 200000) {
					map.addOverlay(ar.get(i));
					aritab.add(i);
				}

			}
		} else if (skm.equals("")) {
			//map.setCenter(place, 3);
			for (int i = 0; i < ar.size(); i++) {
				map.addOverlay(ar.get(i));
				aritab.add(i);
			}

		}

		aritab2.clear();

		place = a2;
		Icon icon2 = Icon.newInstance(Icon.DEFAULT_ICON);
		icon2.setImageURL("blue.png");
		ops = MarkerOptions.newInstance(icon2);
		ops.setIcon(icon2);
		final Marker md2 = new Marker(place, ops);
		md2.addMarkerClickHandler(new MarkerClickHandler() {
			public void onClick(MarkerClickEvent event) {
				map.getInfoWindow().open(
						md2,
						new InfoWindowContent("Destination point:<br>"
								+ tbox2.getText()));
			}
		});
		map.addOverlay(md2);
		skm = km2.getItemText(km2.getSelectedIndex());
		if (skm.equals("10 km")) {
			// map.setCenter(place, 11);
			for (int i = 0; i < ar2.size(); i++) {
				double dis = place.distanceFrom(ar2.get(i).getLatLng());
				if (dis < 10000) {
					map.addOverlay(ar2.get(i));
					aritab2.add(i);
				}
			}
		} else if (skm.equals("20 km")) {
			// map.setCenter(place, 10);
			for (int i = 0; i < ar2.size(); i++) {
				double dis = place.distanceFrom(ar2.get(i).getLatLng());
				if (dis < 20000) {
					map.addOverlay(ar2.get(i));
					aritab2.add(i);
				}

			}
		} else if (skm.equals("50 km")) {
			// map.setCenter(place, 9);
			for (int i = 0; i < ar2.size(); i++) {
				double dis = place.distanceFrom(ar2.get(i).getLatLng());
				if (dis < 50000) {
					map.addOverlay(ar2.get(i));
					aritab2.add(i);
				}

			}
		} else

		if (skm.equals("100 km")) {
			// map.setCenter(place, 8);

			for (int i = 0; i < ar2.size(); i++) {
				double dis = place.distanceFrom(ar2.get(i).getLatLng());
				if (dis < 100000) {
					map.addOverlay(ar2.get(i));
					aritab2.add(i);
				}

			}
		} else if (skm.equals("200 km")) {
			// map.setCenter(place, 7);

			for (int i = 0; i < ar2.size(); i++) {
				double dis = place.distanceFrom(ar2.get(i).getLatLng());
				if (dis < 200000) {
					map.addOverlay(ar2.get(i));
					aritab2.add(i);
				}

			}
		} else if (skm.equals("")) {
			// map.setCenter(place, 3);
			for (int i = 0; i < ar2.size(); i++) {
				map.addOverlay(ar2.get(i));
				aritab2.add(i);
			}

		}

	}


	public void start() {
		phLogin.add(load);
	}

	public void fin() {
		load.removeFromParent();
	}

}
