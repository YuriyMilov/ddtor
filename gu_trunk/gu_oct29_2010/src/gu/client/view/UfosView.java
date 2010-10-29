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
import com.google.gwt.user.datepicker.client.DateBox;

public class UfosView extends Composite {
	
	private HTML load = new HTML(
			"<font color=\"#DF0101\">&nbsp;&nbsp;&nbsp;Loading data...&nbsp;&nbsp;&nbsp;</font>");
	
	private HTML fail = new HTML("<font color=\"#DF0101\">&nbsp;&nbsp;&nbsp;Cannot resolve the Origin/Destination...&nbsp;&nbsp;&nbsp;</font>");
	
	String[][] ss = null;
	String sbox1 = "toronto", sbox2 = "calgary";
	boolean bo = true;
	LatLng a1 = LatLng.newInstance(44, -77);
	LatLng a2 = LatLng.newInstance(44, -77);
	public String s = "";
	HTML info = new HTML(s);
	private MapWidget map;
	private ObjectFactory objectFactory = new RPCObjectFactory(
			GWT.getModuleBaseURL() + "objectFactory");
	private static final int HeaderRowIndex = 0;
	private final dbServiceAsync srv = GWT.create(dbService.class);
	final ArrayList<String> ar = new ArrayList<String>();
	final ArrayList<Marker> ar1 = new ArrayList<Marker>();
	ArrayList<Marker> ar2 = new ArrayList<Marker>();
	ArrayList<Marker> ar3 = new ArrayList<Marker>();
	ArrayList<Marker> ar4 = new ArrayList<Marker>();
	ArrayList<Marker> ar5 = new ArrayList<Marker>();
	ArrayList<Marker> ar6 = new ArrayList<Marker>();
	ArrayList<String[]> ar7 = new ArrayList<String[]>();
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
	final Button but_map = new Button(" Map ");
	final Button but_board = new Button("Board");
	final Button but_database = new Button("Database");
	final Button but_search = new Button("Search");
	final Button but_all = new Button("All");
	final Button but_reload = new Button("Update");
	final FlexTable flexTable = new FlexTable();
	final ListBox dropBox1 = new ListBox(false);
	final ListBox km1 = new ListBox(false);
	final ListBox km2 = new ListBox(false);
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
		map.setSize("1200px", "800px");
		map.setScrollWheelZoomEnabled(true);
		map.addControl(new LargeMapControl());
		// map.addMapClickHandler(h);
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

	/*
	 * final MapClickHandler h = new MapClickHandler() { public void
	 * onClick(MapClickEvent event) { info.removeFromParent(); if
	 * (event.getLatLng() != null) info = new
	 * HTML(String.valueOf(event.getLatLng())); if (event.getOverlayLatLng() !=
	 * null) info = new HTML(String.valueOf(event.getOverlayLatLng()));
	 * phd.add(info);
	 * 
	 * } };
	 */

	void onSigned(String logout) {
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
				pvR.add(pDecor);
				phd.setHeight("11px");
				phd.add(info);
				pvR.add(phd);
				// pvR.add(map);
				phGlav.add(pvR);

				setMarkers(r);
				ss = new String[r.length + r.length][2];
				for (int i = 0; i < r.length; i++) {
					try{
					String s3=r[i][0].trim();
					s3=String.valueOf(Double.parseDouble(s3));
					String s4=r[i][1].trim();
					s4=String.valueOf(Double.parseDouble(s4));
					ss[i][0] =  s3+ s4;
					}catch(Exception e){
						ss[i][0] =  "";
					}
					ss[i][1] = "<b>WO#:</b> "+r[i][4].trim() +"<br><b>From:</b> "+r[i][6].trim()+"<br><b>To:</b> "+r[i][8].trim()+"<br>"+r[i][9].trim()+" "+r[i][10].trim()+" "+r[i][11].trim()+" "+r[i][14].trim()+" LBS<br><b>Pick up:</b> "+r[i][16].trim()+"<br><b>Delivery:</b> "+r[i][17].trim();
				}
				for (int i = 0; i < r.length; i++) {
					try{
						
					
					String s3=r[i][2].trim();
					s3=String.valueOf(Double.parseDouble(s3));
					String s4=r[i][3].trim();
					s4=String.valueOf(Double.parseDouble(s4));

					ss[r.length + i][0] = s3 + s4;
					
					}catch(Exception e){
						ss[r.length + i][0] =  "";
					}
					ss[r.length + i][1] = "<b>WO#:</b> "+r[i][4].trim() +"<br><b>From:</b> "+r[i][6].trim()+"<br><b>To:</b> "+r[i][8].trim()+"<br>"+r[i][9].trim()+" "+r[i][10].trim()+" "+r[i][11].trim()+" "+r[i][14].trim()+" LBS<br><b>Pick up:</b> "+r[i][16].trim()+"<br><b>Delivery:</b> "+r[i][17].trim();
				}
				for (int i = 0; i < ss.length; i++)
					ar7.add(ss[i]);

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

	public void prepButtons() {

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
				get_map();

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
				ar1.clear();
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
						map.setSize("1200px", "800px");
						map.setScrollWheelZoomEnabled(true);
						map.addControl(new LargeMapControl());
						// map.addMapClickHandler(h);

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
				sbox1 = tbox1.getText();
				sbox2 = tbox2.getText();
				bo = true;
		/*		int h=km1.getSelectedIndex();
				if(h==0)
					{sbox1 = "";
					tbox1.setText("");
					}
				h=km2.getSelectedIndex();
				if(h==0)
				{sbox2 = "";
				tbox2.setText("");
				}
				
				if (sbox1.length() == 0 && sbox2.length() == 0) {
					km1.setSelectedIndex(0);
					km2.setSelectedIndex(0);
					sbox1 = "toronto";
					sbox2 = "toronto";

				} else if (sbox1.length() > 0 && sbox2.length() == 0) {
					km2.setSelectedIndex(0);
					sbox2 = "toronto";
				} else if (sbox1.length() == 0 && sbox2.length() > 0) {
					km1.setSelectedIndex(0);
					sbox1 = "toronto";
				}
*/
				if (sbox1.length() != 0 && sbox2.length() != 0)
					geo.getLatLng(sbox1, geoint);
				else
				{
					Window.alert("Please fill in Origin/Destination");
					//phLogin.add(new HTML("Please fill in the Origin/Destination "));
				}

			}
		});

		but_all.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {

				//
				map.removeFromParent();
				view.removeFromParent();
				flexTable.removeAllRows();
				flexTable.removeFromParent();
				//
				map.clearOverlays();
				for (int i = 0; i < ar5.size(); i++)
					map.addOverlay(ar5.get(i));
				for (int i = 0; i < ar6.size(); i++)
					map.addOverlay(ar6.get(i));
				pvR.add(map);
				fin();
			}
		});
	}

	void prepSearchPanel() {
		tbox1.setText("toronto");
		tbox2.setText("calgary");

		//km1.addItem("");
		km1.addItem("10 km");
		km1.addItem("20 km");
		km1.addItem("50 km");
		km1.addItem("100 km");
		km1.addItem("200 km");
		km1.setItemSelected(2, true);

		//km2.addItem("");
		km2.addItem("10 km");
		km2.addItem("20 km");
		km2.addItem("50 km");
		km2.addItem("100 km");
		km2.addItem("200 km");
		km2.setItemSelected(2, true);
		layout.setWidget(0, 0, new HTML(" "));
		layout.setCellSpacing(7);
		layout.setWidget(0, 0, new Label("Origin:"));
		layout.setWidget(1, 0, new Label("Destination:"));
		layout.setWidget(0, 1, tbox1);
		layout.setWidget(1, 1, tbox2);
		layout.setWidget(0, 2, new Label("Origin-Radius:"));
		layout.setWidget(1, 2, new Label("Destin-Radius:"));
		layout.setWidget(0, 3, km1);
		layout.setWidget(1, 3, km2);
		but_search.setWidth("77px");
		but_board.setWidth("77px");
		but_map.setWidth("77px");
		but_all.setWidth("77px");
		but_database.setWidth("77px");
		but_reload.setWidth("77px");
		layout.setWidget(2, 0, new Label("Delivery on: "));	
		final DateBox dtb = new DateBox();

		layout.setWidget(2, 1, dtb);
		
		final ListBox km3 = new ListBox(false);
		km3.addItem("");
		km3.addItem("53' VAN");
		km3.addItem("REEFER");
		km3.addItem("HI-CUBE VAN");
		km3.addItem("TRI-AXLE VAN");
		
		
		
		km3.setItemSelected(0, true);
		layout.setWidget(2, 2, new Label("Equipment: "));	
		
		
		layout.setWidget(2, 3, km3);
		layout.setWidget(2, 4, but_search);
		layout.setWidget(0, 4, but_map);
		layout.setWidget(1, 4, but_board);
		layout.setWidget(0, 5, but_all);
		layout.setWidget(1, 5, but_database);
		//layout.setWidget(1, 6, but_reload);

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
								+ r[i][4] + "<br>From: " + r[i][6] + "<br>To: "
								+ r[i][8];

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
						ops.setTitle(new String(String.valueOf(i)));

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

						mm1.addMarkerClickHandler(mmh);

						ar1.add(mm1);
						ar5.add(mm1);
						// aritab.add(i);

						map.addOverlay(mm1);

					}

					for (int i = 0; i < r.length; i++) {
						
						final String s2 = "WO#: " + r[i][4] + "<br>From: "
								+ r[i][6] + "<br>To: " + r[i][8];

						try {
							d2 = Double.parseDouble(r[i][2]);
							d3 = Double.parseDouble(r[i][3]);
						} catch (Exception e) {
						}

						final Icon icon = Icon.newInstance(Icon.DEFAULT_ICON);
						icon.setImageURL("markerGreen.png");
						MarkerOptions ops = MarkerOptions.newInstance(icon);
						ops.setIcon(icon);
						ops.setTitle(new String(String.valueOf(r.length + i)));
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

						mm2.addMarkerClickHandler(mmh);

						ar2.add(mm2);
						ar6.add(mm2);
						// aritab2.add(i);

						map.addOverlay(mm2);

					}

				} catch (Exception eee) {
					phLogin.add(new HTML(String.valueOf(r.length) + " "
							+ eee.toString()));
				}

	}

	public void get_map() {
		ar.clear();
		ar3.clear();
		ar4.clear();
		if (tt_srv != null)
			if (tt_srv.length > 0)
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
									if (arwo.contains(tt_clt2[row2][0])) {
										ar3.add(ar1.get(row2));
										ar4.add(ar2.get(row2));

										ar.add(tt_clt2[row2][0]);

									}

							}
						}

						s = "";
						int n=0;

						map.clearOverlays();
						for (int i = 0; i < ar3.size(); i++) {
							String s2 = "";
							s = String.valueOf(ar3.get(i).getLatLng()
									.getLatitude())
									+ String.valueOf(ar3.get(i).getLatLng()
											.getLongitude());
							for (int j = 0; j < ar7.size(); j++) {
								if (ar7.get(j)[0].equals(s)) {
									//s2 = s2 + c + " ";
									
									for(int k = 0; k < ar.size(); k++)
									{
										//TODO
										if (ar.get(k).equals(ar7.get(j)[1]))
										{
											s2 = s2 + ar7.get(j)[1] + "<br>";
											ss[j][1]=s2;
										}
									}
								}
							}
			
							map.addOverlay(ar3.get(i));
						}
						for (int i = 0; i < ar4.size(); i++) {
								String s2 = "";
								
								
								s = String.valueOf(ar4.get(i).getLatLng()
										.getLatitude())
										+ String.valueOf(ar4.get(i).getLatLng()
												.getLongitude());
								
								
								
								for (int j = 0; j < ar7.size(); j++) {

									if (ar7.get(j)[0].equals(s)) {
										//s2 = s2 + c + " ";
										
										for(int k = 0; k < ar.size(); k++)
										{
											//TODO
											if (ar.get(k).equals(ar7.get(j)[1]))
											{
												s2 = s2 + ar7.get(j)[1] + "<br>";
												ss[j][1]=s2;
											}
											
										}
									}
								}
								
							map.addOverlay(ar4.get(i));

						}

					} catch (Exception aa) {
						phLogin.add(new HTML("getMap *** " + aa.toString()));
					}
				}
	}

	public void get_board() {
		rowIndex = 1;
		flexTable.insertRow(HeaderRowIndex);
		flexTable.getRowFormatter().addStyleName(HeaderRowIndex,
				"FlexTable-Header");
		addColumn("WO#");
		addColumn("From:");
		addColumn("Origin");
		addColumn("To:");
		addColumn("Destination");
		addColumn("Equipment");
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
								addRow(tt_clt2[row2]);
						
					}
				}
	/*			for (int row2 = 0; row2 < tt_clt.length; row2++) {
//					for (int col = 0; col < tt_clt[row2].length; col++) {
						if (aritab2.contains(row2))
							if (arwo.contains(tt_clt2[row2][0]))
								addRow(tt_clt2[row2]);
	
//					}
				}*/

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
			fail.removeFromParent();
			phLogin.add(fail);
		}

		@Override
		public void onSuccess(LatLng point) {
			fail.removeFromParent();
			
			if (bo) {
				a1 = LatLng.newInstance(point.getLatitude(),
						point.getLongitude());
				bo = false;
				geo.getLatLng(sbox2, geoint);

			} else {
				a2 = LatLng.newInstance(point.getLatitude(),
						point.getLongitude());

				bo = true;

				qqqq();
				qqqq();

				// flexTable.removeAllRows();
				flexTable.removeAllRows();
				get_board();

				// flexTable.removeFromParent();
				flexTable.removeFromParent();
				pvR.add(flexTable);
				fin();
			}
		}
	};

	public void qqqq() {

		aritab.clear();

		place = a1;
		Icon icon = Icon.newInstance(Icon.DEFAULT_ICON);
		icon.setImageURL("blue.png");
		MarkerOptions ops = MarkerOptions.newInstance(icon);
		ops.setIcon(icon);
		final Marker md = new Marker(place, ops);
		md.addMarkerClickHandler(new MarkerClickHandler() {
			public void onClick(MarkerClickEvent event) {
				map.getInfoWindow().open(md,
						new InfoWindowContent("Origin point:<br>" + sbox1));
			}
		});
		map.addOverlay(md);
		String skm = km1.getItemText(km1.getSelectedIndex());
		if (skm.equals("10 km")) {
			// map.setCenter(place, 11);
			for (int i = 0; i < ar1.size(); i++) {
				double dis = place.distanceFrom(ar1.get(i).getLatLng());
				if (dis < 10000) {
					map.addOverlay(ar1.get(i));
					aritab.add(i);
				}
			}
		} else if (skm.equals("20 km")) {
			// map.setCenter(place, 10);
			for (int i = 0; i < ar1.size(); i++) {
				double dis = place.distanceFrom(ar1.get(i).getLatLng());
				if (dis < 20000) {
					map.addOverlay(ar1.get(i));
					aritab.add(i);
				}

			}
		} else if (skm.equals("50 km")) {
			// map.setCenter(place, 9);
			for (int i = 0; i < ar1.size(); i++) {
				double dis = place.distanceFrom(ar1.get(i).getLatLng());
				if (dis < 50000) {
					map.addOverlay(ar1.get(i));
					aritab.add(i);
				}

			}
		} else if (skm.equals("100 km")) {
			// map.setCenter(place, 8);

			for (int i = 0; i < ar1.size(); i++) {
				double dis = place.distanceFrom(ar1.get(i).getLatLng());
				if (dis < 100000) {
					map.addOverlay(ar1.get(i));
					aritab.add(i);
				}

			}
		} else if (skm.equals("200 km")) {
			// map.setCenter(place, 7);

			for (int i = 0; i < ar1.size(); i++) {
				double dis = place.distanceFrom(ar1.get(i).getLatLng());
				if (dis < 200000) {
					map.addOverlay(ar1.get(i));
					aritab.add(i);
				}

			}
		} else if (skm.equals("")) {
			// map.setCenter(place, 3);
			for (int i = 0; i < ar1.size(); i++) {
				map.addOverlay(ar1.get(i));
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
				map.getInfoWindow()
						.open(md2,
								new InfoWindowContent("Destination point:<br>"
										+ sbox2));
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

	void start() {
		phLogin.add(load);
	}

	void fin() {
		load.removeFromParent();
	}

	MarkerClickHandler mmh = new MarkerClickHandler() {

		public void onClick(MarkerClickEvent event) {
			// map.removeOverlay(mm2);
			map.getInfoWindow().open(
					event.getSender().getLatLng(),
					new InfoWindowContent(ss[Integer.valueOf(event.getSender()
							.getTitle())][1]));

		}
	};
}
