package gu.client.view;

import java.util.ArrayList;

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
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.smartgwt.client.data.Criterion;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.DateRange;
import com.smartgwt.client.data.RelativeDate;
import com.smartgwt.client.types.TitleOrientation;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.DateRangeItem;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.events.DataArrivedEvent;
import com.smartgwt.client.widgets.grid.events.DataArrivedHandler;
import com.smartgwt.client.widgets.layout.SectionStack;
import com.smartgwt.client.widgets.layout.SectionStackSection;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.types.OperatorId;
import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.types.VisibilityMode;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;

public class ZgalaView extends Composite {
	boolean bol = true;
	Geocoder geo = new Geocoder();
	Icon icon = Icon.newInstance(Icon.DEFAULT_ICON);
	Marker m = null;
	FlexTable layout = new FlexTable();
	ListBox km1 = new ListBox(false);
	ListBox km2 = new ListBox(false);
	TextBox tbox1 = new TextBox();
	TextBox tbox2 = new TextBox();
	DynamicForm form = new DynamicForm();
	DynamicForm form1 = new DynamicForm();
	DataSource ds = null;
	SectionStack sectionStack = new SectionStack();
	// ListGrid grid = new ListGrid();
	ListGrid grid1 = new ListGrid();
	ArrayList<LatLng> ar = new ArrayList<LatLng>();
	MapWidget map = new MapWidget(LatLng.newInstance(44, -77), 3);
	IButton bc = new IButton("View all");
	IButton bt = new IButton("Search");
	IButton bf = new IButton("Search");
	IButton bf1 = new IButton("Search");
	DateRangeItem rangeItem = new DateRangeItem("dd");
	DateRangeItem rangeItem1 = new DateRangeItem("shd");
	VLayout layoutV = new VLayout(11);
	VLayout vah = new VLayout();
	VLayout vah1 = new VLayout();
	VLayout vah2 = new VLayout();
	HLayout vahr = new HLayout();
	VLayout vahdb = new VLayout();
	HLayout vps = new HLayout();
	HLayout vpd = new HLayout();
	TabSet tabSet = new TabSet();
	Tab mTab = new Tab("Map");
	Tab bTab = new Tab("Board");
	TabSet tabSet1 = new TabSet();
	Tab dTab = new Tab("Delivery Date");
	Tab sTab = new Tab("Shipping Date");
	Tab rTab = new Tab("Radius");
	Tab dbTab = new Tab("Database");
	SectionStackSection bmSec = new SectionStackSection();
	SectionStackSection sdrSec = new SectionStackSection();
	DateRange dateRange = new DateRange();
	DateRange dateRange1 = new DateRange();

	public ZgalaView() {
		// grid1.addCellClickHandler(cch);
		tbox1.setText("toronto");
		tbox2.setText("calgary");

		// km1.addItem("");
		km1.addItem("10 km");
		km1.addItem("20 km");
		km1.addItem("50 km");
		km1.addItem("100 km");
		km1.addItem("200 km");
		km1.setItemSelected(2, true);

		// km2.addItem("");
		km2.addItem("10 km");
		km2.addItem("20 km");
		km2.addItem("50 km");
		km2.addItem("100 km");
		km2.addItem("200 km");
		km2.setItemSelected(2, true);
		layout.setCellSpacing(22);
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
		layout.setWidget(0, 4, bt);
		layout.setWidget(1, 4, bc);

		map.setSize("1200px", "800px");
		map.setScrollWheelZoomEnabled(true);
		map.addControl(new LargeMapControl());

		grid1.setHeight("100%");
		layoutV.setWidth("100%");
		layoutV.setHeight("100%");

		sectionStack.setWidth("100%");
		sectionStack.setHeight("100%");
		sectionStack.setVisibilityMode(VisibilityMode.MULTIPLE);
		sectionStack.setAnimateSections(true);
		sectionStack.setOverflow(Overflow.HIDDEN);

		vpd.setWidth(444);
		vpd.addMember(form);
		vpd.addMember(bf);
		dTab.setPane(vpd);

		vps.setWidth(444);
		vps.addMember(form1);
		vps.addMember(bf1);
		sTab.setPane(vps);

		vahr.addMember(layout);
		bt.addClickHandler(hbt);
		bc.addClickHandler(hbc);
		vah2.addMember(bt);
		vah2.addMember(bc);
		vahr.addMember(vah2);
		rTab.setPane(vahr);

		vah.addMember(map);
		mTab.setPane(vah);

		vah1.addMember(grid1);
		bTab.setPane(vah1);

		vahdb.addMember(new Application());
		dbTab.setPane(vahdb);

		tabSet.setTabs(rTab, dTab, sTab, dbTab);
		tabSet.setHeight(133);
		tabSet1.setTabs(mTab, bTab);
		

		bmSec.setTitle("Board & Map");
		bmSec.setExpanded(true);
		bmSec.setResizeable(true);
		bmSec.addItem(tabSet1);
		bmSec.setResizeable(true);

		sdrSec.setTitle("Search");
		sdrSec.setExpanded(false);
		sdrSec.setItems(tabSet);
		sectionStack.setSections(sdrSec, bmSec);
		sectionStack.draw();

		rangeItem.setWidth("*");
		rangeItem.setTitle("Delivery Date Range");
		rangeItem.setShowTitle(false);
		rangeItem.setAllowRelativeDates(true);

		rangeItem1.setWidth("*");
		rangeItem1.setTitle("Shipping Date Range");
		rangeItem1.setShowTitle(false);
		rangeItem1.setAllowRelativeDates(true);

		dateRange.setRelativeStartDate(new RelativeDate("-2m"));
		dateRange.setRelativeEndDate(new RelativeDate("+1m"));

		dateRange1.setRelativeStartDate(new RelativeDate("-2m"));
		dateRange1.setRelativeEndDate(new RelativeDate("+1m"));

		rangeItem.setValue(dateRange);
		rangeItem1.setValue(dateRange1);

		form.setItems(rangeItem);
		form1.setItems(rangeItem1);

		form.setTitleOrientation(TitleOrientation.TOP);
		form1.setTitleOrientation(TitleOrientation.TOP);

		// Create a ListGrid displaying data from the worldDS
		ds = DaSo.getInstance();

		grid1.setDataSource(ds);
		grid1.fetchData();
		grid1.addDataArrivedHandler(dah);
		// bf.setAutoFit(true);
		bf.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				Criterion criteria = rangeItem.getCriterion();
				grid1.fetchData(criteria);
				map.clearOverlays();
				get_map();
			}
		});

		// bf1.setAutoFit(true);
		bf1.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				Criterion criteria = rangeItem1.getCriterion();
				grid1.fetchData(criteria);
				map.clearOverlays();
				get_map();
			}
		});

		layoutV.addMember(sectionStack);
		initWidget(layoutV);

	}

	void get_map() {
		int i = grid1.getDataAsRecordList().getLength();
		while (i-- > 0) {
			icon.setImageURL("marker.png");
			MarkerOptions ops = MarkerOptions.newInstance(icon);
			ops = MarkerOptions.newInstance(icon);
			ops.setIcon(icon);
			//from, to, shd, dd, equipment, pieces, type, lbs
			String s="";
			s=s+"WO#: "+grid1.getRecord(i).getAttribute("prefix")+"-"+grid1.getRecord(i).getAttribute("woNumber")+"\r\n";
			s=s+"From: "+grid1.getRecord(i).getAttribute("from")+"\r\n";
			s=s+"To: "+grid1.getRecord(i).getAttribute("to")+"\r\n";
			s=s+" "+grid1.getRecord(i).getAttribute("equipment");
			s=s+" "+grid1.getRecord(i).getAttribute("pieces");
			s=s+" "+grid1.getRecord(i).getAttribute("type")+"\r\n";
			if(grid1.getRecord(i).getAttribute("lbs").trim().length()>1)
				s=s+"Weight: "+grid1.getRecord(i).getAttribute("lbs")+" LBS\r\n";
					
			ops.setTitle(s);//
			m = new Marker(LatLng.newInstance(
					Double.parseDouble(grid1.getRecord(i).getAttribute(
							"shiplat")),
					Double.parseDouble(grid1.getRecord(i).getAttribute(
							"shiplng"))), ops);

			m.addMarkerClickHandler(mch);
			map.addOverlay(m);

			icon.setImageURL("markerGreen.png");
			ops = MarkerOptions.newInstance(icon);
			ops.setIcon(icon);
			
			s="";
			s=s+"WO#: "+grid1.getRecord(i).getAttribute("prefix")+"-"+grid1.getRecord(i).getAttribute("woNumber")+"\r\n";
			s=s+"From: "+grid1.getRecord(i).getAttribute("from")+"\r\n";
			s=s+"To: "+grid1.getRecord(i).getAttribute("to")+"\r\n";
			s=s+" "+grid1.getRecord(i).getAttribute("equipment");
			s=s+" "+grid1.getRecord(i).getAttribute("pieces");
			s=s+" "+grid1.getRecord(i).getAttribute("type")+"\r\n";
			if(grid1.getRecord(i).getAttribute("lbs").trim().length()>1)
				s=s+"Weight: "+grid1.getRecord(i).getAttribute("lbs")+" LBS\r\n";
			ops.setTitle(s);//
			m = new Marker(LatLng.newInstance(
					Double.parseDouble(grid1.getRecord(i).getAttribute(
							"conslat")),
					Double.parseDouble(grid1.getRecord(i).getAttribute(
							"conslng"))), ops);
			m.addMarkerClickHandler(mch);
			map.addOverlay(m);
		}
	}

	final LatLngCallback geoint1 = new LatLngCallback() {
		@Override
		public void onFailure() {
		}

		@Override
		public void onSuccess(LatLng point) {
			LatLng pp = LatLng.newInstance(point.getLatitude(),
					point.getLongitude());

			int i = grid1.getDataAsRecordList().getLength();
			while (i-- > 0) {
				try {
					double dbl = pp.distanceFrom(LatLng.newInstance(
							Double.parseDouble(grid1.getRecord(i).getAttribute(
									"shiplat")),
							Double.parseDouble(grid1.getRecord(i).getAttribute(
									"shiplng"))));
					grid1.getRecord(i).setAttribute("orig", (int) dbl);
				} catch (Exception e) {
					grid1.getRecord(i).setAttribute("orig", 111111111);
				}
			}

			geo.getLatLng(tbox2.getText(), geoint2);

		}
	};

	final LatLngCallback geoint2 = new LatLngCallback() {
		@Override
		public void onFailure() {
		}

		@Override
		public void onSuccess(LatLng point) {
			LatLng pp = LatLng.newInstance(point.getLatitude(),
					point.getLongitude());

			int i = grid1.getDataAsRecordList().getLength();

			while (i-- > 0) {
				try {
					double dbl = pp.distanceFrom(LatLng.newInstance(
							Double.parseDouble(grid1.getRecord(i).getAttribute(
									"conslat")),
							Double.parseDouble(grid1.getRecord(i).getAttribute(
									"conslng"))));
					grid1.getRecord(i).setAttribute("dest", (int) dbl);
				} catch (Exception e) {
					grid1.getRecord(i).setAttribute("dest", 111111111);
				}
			}

			int i1 = 0;
			String skm = km1.getItemText(km1.getSelectedIndex());
			if (skm.equals("10 km"))
				i1 = 10000;
			if (skm.equals("20 km"))
				i1 = 20000;
			if (skm.equals("50 km"))
				i1 = 50000;
			if (skm.equals("100 km"))
				i1 = 100000;
			if (skm.equals("200 km"))
				i1 = 200000;
			int i2 = 0;
			String skm2 = km2.getItemText(km2.getSelectedIndex());
			if (skm2.equals("10 km"))
				i2 = 10000;
			if (skm2.equals("20 km"))
				i2 = 20000;
			if (skm2.equals("50 km"))
				i2 = 50000;
			if (skm2.equals("100 km"))
				i2 = 100000;
			if (skm2.equals("200 km"))
				i2 = 200000;

			Criterion cc = new Criterion("orig", OperatorId.LESS_THAN, i1);
			cc.addCriteria("dest", OperatorId.LESS_THAN, i2);
			grid1.fetchData(cc);
			map.clearOverlays();
			get_map();
		}
	};

	DataArrivedHandler dah = new DataArrivedHandler() {

		@Override
		public void onDataArrived(DataArrivedEvent event) {
			// TODO Auto-generated method stub
			if (bol)
				get_map();
			bol = false;

		}
	};

	ClickHandler hbt = new ClickHandler() {

		@Override
		public void onClick(ClickEvent event) {
			Criterion crit = new Criterion();
			grid1.fetchData(crit);
			geo.getLatLng(tbox1.getText(), geoint1);

			// TODO Auto-generated method stub
		}
	};

	ClickHandler hbc = new ClickHandler() {

		@Override
		public void onClick(ClickEvent event) {
			Criterion crit = new Criterion();
			grid1.fetchData(crit);
			map.clearOverlays();
			get_map();
			// TODO Auto-generated method stub
		}
	};
	
	MarkerClickHandler mch = new MarkerClickHandler() {

		public void onClick(MarkerClickEvent event) {
			Marker m=event.getSender();
			
			map.getInfoWindow().open(
					m.getLatLng(),
					new InfoWindowContent(m.getTitle().replace("\r\n", "<br>")));

		}
	};

	/*
	 * CellClickHandler cch=new CellClickHandler(){
	 * 
	 * @Override public void onCellClick(CellClickEvent event) {
	 * 
	 * 
	 * 
	 * 
	 * icon.setImageURL("marker.png"); MarkerOptions ops =
	 * MarkerOptions.newInstance(icon); ops = MarkerOptions.newInstance(icon);
	 * ops.setIcon(icon);
	 * ops.setTitle(event.getRecord().getAttribute("woNumber")); m = new
	 * Marker(LatLng.newInstance(
	 * Double.parseDouble(event.getRecord().getAttribute( "shiplat")),
	 * Double.parseDouble(event.getRecord().getAttribute( "shiplng"))), ops);
	 * 
	 * map.addOverlay(m);
	 * 
	 * icon.setImageURL("markerGreen.png"); ops =
	 * MarkerOptions.newInstance(icon); ops.setIcon(icon);
	 * ops.setTitle(event.getRecord().getAttribute("woNumber")); m = new
	 * Marker(LatLng.newInstance(
	 * Double.parseDouble(event.getRecord().getAttribute( "conslat")),
	 * Double.parseDouble(event.getRecord().getAttribute( "conslng"))), ops);
	 * map.addOverlay(m);
	 * 
	 * // TODO Auto-generated method stub
	 * 
	 * }};
	 */
}
