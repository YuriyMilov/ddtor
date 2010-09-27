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
import com.google.gwt.maps.client.event.MarkerMouseOverHandler;
import com.google.gwt.maps.client.geocode.Geocoder;
import com.google.gwt.maps.client.geocode.LatLngCallback;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.overlay.Icon;
import com.google.gwt.maps.client.overlay.Marker;
import com.google.gwt.maps.client.overlay.MarkerOptions;
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

public class UfosView extends Composite implements ObjectFactoryListener {
	private HTML info = new HTML();
	private MapWidget map;
	// private VerticalPanel mm = new VerticalPanel();
	private ObjectFactory objectFactory;
	private DecoratorPanel dp2 = new DecoratorPanel();
	private LoadingPanel loading = new LoadingPanel(new Label("loading..."));

	// //////////

	private static final int HeaderRowIndex = 0;
	private final dbServiceAsync srv = GWT.create(dbService.class);
	final ArrayList<Marker> ar = new ArrayList<Marker>();
	final ArrayList<Integer> aritab = new ArrayList<Integer>();
	LatLng place = LatLng.newInstance(44, -77);
	private static String[][] tt_srv = null;
	private static String[][] tt_clt = null;
	private DatabaseEditorView view = new DatabaseEditorView();
	int rowIndex = 1;

	VerticalPanel mainPanel = new VerticalPanel();
	VerticalPanel pvMap = new VerticalPanel();
	HorizontalPanel phSearch = new HorizontalPanel();
	VerticalPanel pvL = new VerticalPanel();
	VerticalPanel pvR = new VerticalPanel();
	HorizontalPanel phTop = new HorizontalPanel();
	HorizontalPanel phMain = new HorizontalPanel();
	HorizontalPanel phLogin = new HorizontalPanel();
	HTML sign=new HTML();
	final Geocoder geo = new Geocoder();
	final LatLng gde = LatLng.newInstance(44, -77);

	final Button but_map = new Button("Map");
	final Button but_qq = new Button("Board");

	//final Button but_LoadBoard = new Button("Board");
	final Button but_database = new Button("Database");
	// final Button but_search = new Button("Search");
	final Button but_submit = new Button("Submit");
	final Button but_reload = new Button("Update");
	final FlexTable flexTable = new FlexTable();
	final ListBox km = new ListBox(false);
	// final ListBox dropBox_shipper = new ListBox(false);
	final ListBox drop_box_equip = new ListBox(false);
	final FlexTable layout = new FlexTable();
	final TextBox tbox1 = new TextBox();
	final TextBox tbox2 = new TextBox();

	public UfosView() {
		RootPanel.get().add(loading);
		initWidget(mainPanel);
		mainPanel.add(phLogin);
		mainPanel.add(phTop);
		mainPanel.add(pvMap);
		setStyleName("databaseEditorView");
		map = new MapWidget(LatLng.newInstance(44, -77), 3);
		map.setSize("600px", "400px");
		map.setScrollWheelZoomEnabled(true);
		map.addControl(new LargeMapControl());
		map.addMapClickHandler(h);

		srv.getData("login", new AsyncCallback<String[][]>() {
			public void onFailure(Throwable caught) {
				phLogin.add(new HTML(caught.toString()));

			}

			public void onSuccess(final String r[][]) {

				if (r[0][0].indexOf("Logout") > -1) {
					//onSigned(r[0][0]);
					onSigned_qq(r[0][0]);
					

				} else {
					sign=new HTML("<center>"+r[0][0]+"</center>");
					sign.setWidth("400px");
					phLogin.add(sign);
					onLoadingFinish();
				}

			}
		});

	}

	final MapClickHandler h = new MapClickHandler() {
		public void onClick(MapClickEvent event) {
			info.removeFromParent();
			if (event.getLatLng() != null)
				info = new HTML(String.valueOf(event.getLatLng()));
			if (event.getOverlayLatLng() != null)
				info = new HTML(String.valueOf(event.getOverlayLatLng()));
			mainPanel.add(info);

		}
	};

	public void onSigned_qq(String logout) {
		phLogin.add(new HTML(logout));
		
		onLoadingStart();
		srv.getData("qq", new AsyncCallback<String[][]>() {
			public void onFailure(Throwable caught) {
				onLoadingFinish();
				phLogin.add(new HTML(caught.toString()));
			}

			public void onSuccess(String[][] r) {

				prep_Search_Panel_dp();
				prepButtons();

				pvR.add(phSearch);
				pvR.add(map);

				phTop.add(but_qq);
				phTop.add(but_map);
				phTop.add(but_database);
				phTop.add(but_reload);

				setMarkers(r);
				pvMap.add(dp2);
				pvMap.add(pvR);
				onLoadingFinish();
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

		but_qq.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {

				map.removeFromParent();
				// dp.removeFromParent();
				view.removeFromParent();
				flexTable.removeAllRows();
				flexTable.removeFromParent();

	/*			String s="";
				for(int i=0;i<tt_srv.length;i++){
					for(int j=0;j<tt_srv[0].length;j++){
						s=	s+tt_srv[i][j];
					}
					s=s+"<br>";
					}
				
				info = new HTML(s);
				mainPanel.add(info);*/
				
				
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
				
				
				
				try {
					tt_clt = new String[tt_srv.length][tt_srv[0].length];
					for (int row = 0; row < tt_srv.length; row++) {
						for (int col = 0; col < tt_srv[row].length; col++) {
							tt_clt[row][col] = tt_srv[row][col];
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
				
				pvR.add(flexTable);

			}
		});
		
		but_map.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {


				
				//
				map.removeFromParent();
				view.removeFromParent();
				flexTable.removeAllRows();
				flexTable.removeFromParent();
				//
				

				pvR.add(map);
			}
		});
		
		but_database.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {


				
				//
				map.removeFromParent();
				view.removeFromParent();
				flexTable.removeAllRows();
				flexTable.removeFromParent();
				//
				

				RPCObjectFactory objectFactory = new RPCObjectFactory(GWT
						.getModuleBaseURL() + "objectFactory");
				view.setObjectFactory(objectFactory);

				pvR.add(view);
			}
		});
		
		
		but_reload.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {

				onLoadingStart();
				map.clearOverlays();
				ar.clear();
				srv.getData("qq", new AsyncCallback<String[][]>() {
					public void onFailure(Throwable caught) {
						onLoadingFinish();
						phLogin.add(new HTML(caught.toString()));
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
						onLoadingFinish();
						pvR.add(map);
					}
				});
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
				onLoadingStart();
	
				
				//
				map.removeFromParent();
				view.removeFromParent();
				flexTable.removeAllRows();
				flexTable.removeFromParent();
				//
				
				
				map.clearOverlays();
				geo.getLatLng(tbox1.getText(), geoint);
				onLoadingFinish();
				pvR.add(map);
			}
		});
	}

	void prep_Search_Panel_dp() {
		tbox1.setText("Toronto");
		// tbox2.setText("Atlanta");
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
		// layout.setWidget(0, 4, new Label("Destination "));
		// layout.setWidget(0, 5, tbox2);
		layout.setWidget(0, 4, but_submit);

		dp2.add(layout);
	}

	public void setMarkers(String[][] r) {
		try {
			tt_srv=r;
			double d0=0,d1=0,d2=0,d3=0;
			for (int i = 0; i < r.length; i++) {
				final String s1 = "WO#: " + r[i][4]+
				"<br>" + r[i][5]+
				"<br>" + r[i][6];
				try{
						d0=Double.parseDouble(r[i][0]);
						d1=Double.parseDouble(r[i][1]);
				}catch(Exception e){}
				
				
				final Icon icon = Icon.newInstance(Icon.DEFAULT_ICON);
				icon.setImageURL("marker.png");
				//	icon.setImageURL("markerGreen.png");
				MarkerOptions ops = MarkerOptions.newInstance(icon);
				ops.setIcon(icon);
				final Marker mm = new Marker(LatLng.newInstance(d0,d1),
						ops);

				mm.addMarkerMouseOverHandler(new MarkerMouseOverHandler(){

					@Override
					public void onMouseOver(MarkerMouseOverEvent event) {
						map.getInfoWindow().open(mm, new InfoWindowContent(s1));
					}});
				
				mm.addMarkerClickHandler(new MarkerClickHandler() {

					public void onClick(MarkerClickEvent event) {
						map.removeOverlay(mm);
						//map.getInfoWindow().open(mm, new InfoWindowContent(s1));
					}
				});
				//mm.addMarkerDoubleClickHandler(new MarkerDoubleClickHandler() {

				//	public void onDoubleClick(MarkerDoubleClickEvent event) {
				//		map.removeOverlay(mm);
				//	}
				//});
				ar.add(mm);
				aritab.add(i);
				
				map.addOverlay(mm);
				
				
			}
			
			for (int i = 0; i < r.length; i++) {
				final String s1 = "WO#: " + r[i][4]+
				"<br>" + r[i][5]+
				"<br>" + r[i][6];
				
				try{
					d2=Double.parseDouble(r[i][2]);
					d3=Double.parseDouble(r[i][3]);
			}catch(Exception e){}
			
			
				final Icon icon = Icon.newInstance(Icon.DEFAULT_ICON);
				//icon.setImageURL("marker.png");
					icon.setImageURL("markerGreen.png");
				MarkerOptions ops = MarkerOptions.newInstance(icon);
				ops.setIcon(icon);
				final Marker mm = new Marker(LatLng.newInstance(d2, d3),
						ops);
				mm.addMarkerMouseOverHandler(new MarkerMouseOverHandler(){

					@Override
					public void onMouseOver(MarkerMouseOverEvent event) {
						map.getInfoWindow().open(mm, new InfoWindowContent(s1));
					}});
				
				mm.addMarkerClickHandler(new MarkerClickHandler() {

					public void onClick(MarkerClickEvent event) {
						map.removeOverlay(mm);
						//map.getInfoWindow().open(mm, new InfoWindowContent(s1));
					}
				});
				//mm.addMarkerDoubleClickHandler(new MarkerDoubleClickHandler() {

				//	public void onDoubleClick(MarkerDoubleClickEvent event) {
				//		map.removeOverlay(mm);
				//	}
				//});
				ar.add(mm);
				aritab.add(i);
				map.addOverlay(mm);
				
				
			}
			
		} catch (Exception eee) {
			phLogin.add(new HTML(String.valueOf(r.length)+" "+ eee.toString()));
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

	public ObjectFactory getObjectFactory() {
		return objectFactory;
	}

	public void setObjectFactory(ObjectFactory objectFactory) {
		this.objectFactory = objectFactory;
		objectFactory.setListener(this);
	}

	public void onError(String error) {
		PopupPanel popup = new PopupPanel(true);
		popup.setStyleName("error");
		popup.setWidget(new HTML(error));
		popup.show();
		popup.center();
	}

	public void onLoadingFinish() {
		loading.loadingEnd();
	}

	public void onLoadingStart() {
		loading.loadingBegin();
	}

	@Override
	public void onRefresh() {
		// TODO Auto-generated method stub

	}

}
