package gu.client.view;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.ArrayList;

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
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.smartgwt.client.data.Criterion;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.DateRange;
import com.smartgwt.client.data.RelativeDate;
import com.smartgwt.client.types.TitleOrientation;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Window;
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
import com.smartgwt.client.widgets.form.fields.*;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;

public class ZgalaView extends Composite {
	Window ww=new Window();
	boolean bol = true;
	Geocoder geo = new Geocoder();
	Icon icon = Icon.newInstance(Icon.DEFAULT_ICON);
	Marker m = null;
	DynamicForm form = new DynamicForm();
	DynamicForm form1 = new DynamicForm();
	DataSource ds = null;
	SectionStack sectionStack = new SectionStack();
	ListGrid grid1 = new ListGrid();
	ArrayList<LatLng> ar = new ArrayList<LatLng>();
	MapWidget map = new MapWidget(LatLng.newInstance(44, -77), 3);
	IButton ba = new IButton("Reset All");
	IButton bm = new IButton("Show Mapped");
	IButton br = new IButton("Search");
	IButton bf = new IButton("Search");
	IButton bf1 = new IButton("Search");
	IButton bdbe = new IButton("Database Editor");
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
	VLayout layout = new VLayout(10);
	DynamicForm formr = new DynamicForm();
	DynamicForm formb = new DynamicForm();
	TextItem textItem1 = new TextItem();
	SliderItem sliderItem1 = new SliderItem();
	TextItem textItem2 = new TextItem();
	SliderItem sliderItem2 = new SliderItem();

	public ZgalaView() {

		textItem1.setTitle("Origin & Radius (kms)");
		textItem1.setDefaultValue("toronto");
		// textItem.setHint("<nobr>Type an origin address here</nobr>");

		sliderItem1.setShowTitle(false);
		sliderItem1.setWidth(400);
		sliderItem1.setMinValue(0);
		sliderItem1.setMaxValue(500);
		sliderItem1.setDefaultValue(50);
		sliderItem1.addChangedHandler(hslide);

		textItem2.setTitle("Destination & Radius (kms)");
		textItem2.setDefaultValue("calgary");
		// textItem.setHint("<nobr>Type an origin address here</nobr>");

		sliderItem2.setShowTitle(false);
		sliderItem2.setWidth(400);
		sliderItem2.setMinValue(0);
		sliderItem2.setMaxValue(500);
		sliderItem2.setDefaultValue(50);
		sliderItem2.addChangedHandler(hslide);

		formr.setFields(textItem1, sliderItem1, textItem2, sliderItem2);
		formr.setTitleOrientation(TitleOrientation.TOP);
		layout.addMember(formr);
		br.addClickHandler(hbt);
		ba.addClickHandler(hbc);
		bm.addClickHandler(hbm);
		
		layout.setWidth(500);
		vahr.addMember(layout);
		vahr.addMember(br);
		vahr.addMember(ba);
		vahr.addMember(bm);

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

		// vahr.addMember(layout);

		// vahr.setCellSpacing(22);
		// vahr.setWidget(0, 0, new HTML(" "));
		// vahr.setCellSpacing(7);
		// vahr.setWidget(0, 0, new Label("Origin:"));
		// vahr.setWidget(1, 0, new Label("Destination:"));
		// vahr.addMember(tbox1);
		// vahr.addMember(tbox2);

		// vahr.setWidget(0, 2, new Label("Origin Radius"));
		// vahr.setWidget(1, 2, new Label("Destin Radius"));
		// vahr.addMember(km1);
		// vahr.addMember(km2);

		// bt.addClickHandler(hbt);
		// bc.addClickHandler(hbc);
		// vah2.addMember(bt);
		// vah2.addMember(bc);
		// vahr.addMember(vah2);

		rTab.setPane(vahr);

		vah.addMember(map);
		mTab.setPane(vah);

		vah1.addMember(grid1);
		bTab.setPane(vah1);
		vahdb.addMember(new Application());

		dbTab.setPane(vahdb);

		tabSet.setTabs(rTab, dTab, sTab, dbTab);
		tabSet.setHeight(444);
		tabSet1.setTabs(mTab, bTab);

		bmSec.setTitle("Board & Map");
		bmSec.setExpanded(true);
		bmSec.setResizeable(true);
		bmSec.addItem(tabSet1);
		bmSec.setResizeable(true);

		sdrSec.setTitle("Search");
		sdrSec.setExpanded(true);
		sdrSec.setItems(tabSet);
		sectionStack.setSections(sdrSec, bmSec);

		sectionStack.redraw();

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
			try {
				icon.setImageURL("marker.png");
				MarkerOptions ops = MarkerOptions.newInstance(icon);
				ops = MarkerOptions.newInstance(icon);
				ops.setIcon(icon);
				// from, to, shd, dd, equipment, pieces, type, lbs
				String s = "";
				s = s + "WO#: " + grid1.getRecord(i).getAttribute("prefix")
						+ "-" + grid1.getRecord(i).getAttribute("woNumber")
						+ "\r\n";
				s = s + "From: " + grid1.getRecord(i).getAttribute("from")
						+ "\r\n";
				s = s + "To: " + grid1.getRecord(i).getAttribute("to") + "\r\n";
				s = s + " " + grid1.getRecord(i).getAttribute("equipment");
				s = s + " " + grid1.getRecord(i).getAttribute("pieces");
				s = s + " " + grid1.getRecord(i).getAttribute("type") + "\r\n";
				if (grid1.getRecord(i).getAttribute("lbs").trim().length() > 1)
					s = s + "Weight: " + grid1.getRecord(i).getAttribute("lbs")
							+ " LBS\r\n";

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

				s = "";
				s = s + "WO#: " + grid1.getRecord(i).getAttribute("prefix")
						+ "-" + grid1.getRecord(i).getAttribute("woNumber")
						+ "\r\n";
				s = s + "From: " + grid1.getRecord(i).getAttribute("from")
						+ "\r\n";
				s = s + "To: " + grid1.getRecord(i).getAttribute("to") + "\r\n";
				s = s + " " + grid1.getRecord(i).getAttribute("equipment");
				s = s + " " + grid1.getRecord(i).getAttribute("pieces");
				s = s + " " + grid1.getRecord(i).getAttribute("type") + "\r\n";
				if (grid1.getRecord(i).getAttribute("lbs").trim().length() > 1)
					s = s + "Weight: " + grid1.getRecord(i).getAttribute("lbs")
							+ " LBS\r\n";
				ops.setTitle(s);//
				m = new Marker(LatLng.newInstance(
						Double.parseDouble(grid1.getRecord(i).getAttribute(
								"conslat")),
						Double.parseDouble(grid1.getRecord(i).getAttribute(
								"conslng"))), ops);
				m.addMarkerClickHandler(mch);
				map.addOverlay(m);
			} catch (Exception e) {
			}
		}
	}

	void get_map2(int i1, int i2) {
		int i = grid1.getDataAsRecordList().getLength();
		while (i-- > 0) {

			boolean boolrad = false;
			try {
				boolrad = Integer.parseInt(grid1.getRecord(i).getAttribute(
						"orig")) < i1
						&& Integer.parseInt(grid1.getRecord(i).getAttribute(
								"dest")) < i2;
			} catch (Exception e) {
			}

			if (boolrad) {

				icon.setImageURL("marker.png");
				MarkerOptions ops = MarkerOptions.newInstance(icon);
				ops = MarkerOptions.newInstance(icon);
				ops.setIcon(icon);
				// from, to, shd, dd, equipment, pieces, type, lbs
				String s = "";
				s = s + "WO#: " + grid1.getRecord(i).getAttribute("prefix")
						+ "-" + grid1.getRecord(i).getAttribute("woNumber")
						+ "\r\n";
				s = s + "From: " + grid1.getRecord(i).getAttribute("from")
						+ "\r\n";
				s = s + "To: " + grid1.getRecord(i).getAttribute("to") + "\r\n";
				s = s + " " + grid1.getRecord(i).getAttribute("equipment");
				s = s + " " + grid1.getRecord(i).getAttribute("pieces");
				s = s + " " + grid1.getRecord(i).getAttribute("type") + "\r\n";
				if (grid1.getRecord(i).getAttribute("lbs").trim().length() > 1)
					s = s + "Weight: " + grid1.getRecord(i).getAttribute("lbs")
							+ " LBS\r\n";

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

				s = "";
				s = s + "WO#: " + grid1.getRecord(i).getAttribute("prefix")
						+ "-" + grid1.getRecord(i).getAttribute("woNumber")
						+ "\r\n";
				s = s + "From: " + grid1.getRecord(i).getAttribute("from")
						+ "\r\n";
				s = s + "To: " + grid1.getRecord(i).getAttribute("to") + "\r\n";
				s = s + " " + grid1.getRecord(i).getAttribute("equipment");
				s = s + " " + grid1.getRecord(i).getAttribute("pieces");
				s = s + " " + grid1.getRecord(i).getAttribute("type") + "\r\n";
				if (grid1.getRecord(i).getAttribute("lbs").trim().length() > 1)
					s = s + "Weight: " + grid1.getRecord(i).getAttribute("lbs")
							+ " LBS\r\n";
				ops.setTitle(s);//
				m = new Marker(LatLng.newInstance(
						Double.parseDouble(grid1.getRecord(i).getAttribute(
								"conslat")),
						Double.parseDouble(grid1.getRecord(i).getAttribute(
								"conslng"))), ops);
				m.addMarkerClickHandler(mch);
				map.addOverlay(m);
				grid1.getRecord(i).setAttribute("ii", true);

			} else
				grid1.getRecord(i).setAttribute("ii", false);
		}
		//Criterion crit = new Criterion("ii", OperatorId.EQUALS, true);
		//grid1.fetchData(crit);

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

			geo.getLatLng(textItem2.getDisplayValue(), geoint2);

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

			String skm = sliderItem1.getDisplayValue() + "000";
			int i1 = Integer.parseInt(skm);
			skm = sliderItem2.getDisplayValue() + "000";
			;
			int i2 = Integer.parseInt(skm);
			map.clearOverlays();
			get_map2(i1, i2);
		}
	};

	DataArrivedHandler dah = new DataArrivedHandler() {

		@Override
		public void onDataArrived(DataArrivedEvent event) {
			if (bol)
				get_map();
			bol = false;

		}
	};

	ClickHandler hbc = new ClickHandler() {

		@Override
		public void onClick(ClickEvent event) {
			Criterion crit = new Criterion();
			grid1.fetchData(crit);
			map.clearOverlays();
			get_map();
		}
	};
	
	ClickHandler hbm = new ClickHandler() {

		@Override
		public void onClick(ClickEvent event) {
			Criterion crit = new Criterion("ii", OperatorId.EQUALS, true);			grid1.fetchData(crit);
			grid1.fetchData(crit);
			map.clearOverlays();
			get_map();
		}
	};

	//Criterion crit = new Criterion("ii", OperatorId.EQUALS, true);
	//grid1.fetchData(crit);

	
	
	MarkerClickHandler mch = new MarkerClickHandler() {

		public void onClick(MarkerClickEvent event) {
			Marker m = event.getSender();

			map.getInfoWindow()
					.open(m.getLatLng(),
							new InfoWindowContent(m.getTitle().replace("\r\n",
									"<br>")));

		}
	};

	
	ClickHandler hbt = new ClickHandler() {

		@Override
		public void onClick(ClickEvent event) {
			Criterion crit = new Criterion();
			grid1.fetchData(crit);
			geo.getLatLng(textItem1.getDisplayValue(), geoint1);

			
		}
	};
	
	ChangedHandler hslide = new ChangedHandler(){

		@Override
		public void onChanged(ChangedEvent event) {
			Criterion crit = new Criterion();
			grid1.fetchData(crit);
			geo.getLatLng(textItem1.getDisplayValue(), geoint1);
			
		}};
	
	
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
	 * 
	 * 
	 * }};
	 */
}
