package qd.gmap.client;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.maps.client.InfoWindowContent;

import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.control.LargeMapControl;

import com.google.gwt.maps.client.event.InfoWindowCloseClickHandler;
import com.google.gwt.maps.client.event.MarkerClickHandler;
import com.google.gwt.maps.client.event.InfoWindowCloseClickHandler.InfoWindowCloseClickEvent;
import com.google.gwt.maps.client.event.MarkerClickHandler.MarkerClickEvent;
import com.google.gwt.maps.client.geocode.Geocoder;
import com.google.gwt.maps.client.geocode.LatLngCallback;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.geom.Point;
import com.google.gwt.maps.client.geom.Size;
import com.google.gwt.maps.client.overlay.Icon;
import com.google.gwt.maps.client.overlay.Marker;
import com.google.gwt.maps.client.overlay.MarkerOptions;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DecoratedTabPanel;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;
import com.google.gwt.user.datepicker.client.DateBox;

public class CopyOfGmap implements EntryPoint {

	private final GreetingServiceAsync greetingService = GWT
	.create(GreetingService.class);

	public static String qq="aa";
	
	public void onModuleLoad() {
		final DecoratedTabPanel tabPanel = new DecoratedTabPanel();
		tabPanel.setAnimationEnabled(true);
		final VerticalPanel vp_map = new VerticalPanel();
		final HorizontalPanel hp_map = new HorizontalPanel();
		vp_map.add(hp_map);
		
		final MapWidget map1 = new MapWidget();
		map1.setContinuousZoom(true);
		map1.setScrollWheelZoomEnabled(false);
		map1.setSize("700px", "444px");
		map1.addControl(new LargeMapControl());
		map1.setCenter(LatLng.newInstance(42.6898961957299, -71.1611878218555));
		map1.setZoomLevel(8);
		//initMarkers(map1);

		final MapWidget map2 = new MapWidget();
		//final HTML htm1 = new HTML("Trucks");
		//final HTML htm2 = new HTML("Shippers");
		
		hp_map.add(new Button("Shippers", new ClickHandler() {
			public void onClick(ClickEvent event) {
					map2.setContinuousZoom(true);
					map2.setScrollWheelZoomEnabled(false);
					map2.setSize("700px", "444px");
					map2.addControl(new LargeMapControl());
					map2.setCenter(LatLng.newInstance(42.6898961957299, -71.1611878218555));
					map2.setZoomLevel(7);
					initMarkers2(map2);
					vp_map.remove(map1);
					//vp_map.remove(htm1);
					vp_map.add(map2);
					//vp_map.add(htm2);
				}

			}));
	
		hp_map.add(new Button("Info 2", new ClickHandler() {
			public void onClick(ClickEvent event) {
				greetingService.greetServer("textToServer",
						new AsyncCallback<String>() {
							public void onFailure(Throwable caught) {
							}

							public void onSuccess(String result) {
								qq=result;
								//initMarkers(map1);
								LatLng alt=LatLng.newInstance(42.6099024760077, -72.4211022247452);
								Marker m=new Marker(alt,MarkerOptions.newInstance());
								map1.addOverlay(m);
								map1.getInfoWindow().open(alt, new InfoWindowContent(result));
							      
								vp_map.remove(map2);
								vp_map.add(map1);
								//vp_map.remove(htm2);
								//vp_map.add(htm1);
							}
						});

			}
		}));
		
	
	
		RootPanel.get().add(vp_map);
	}


	private Map<String, LatLng> initLocs1() {


		final HashMap<String, LatLng> locs = new HashMap<String, LatLng>();

		//locs.put("010", LatLng.newInstance(42.212301588702, -72.6411211406311));
		//locs.put("011", LatLng.newInstance(42.1170983094661,-72.5479789187525));
		//locs.put("012", LatLng.newInstance(42.0805009057921,-72.2070179770502));
		
		//locs.put(qq, LatLng.newInstance(42.6099024760077, -72.4211022247452));					

		//locs.put("023", LatLng.newInstance(42.0560003625449,-70.8723659993611));
		//locs.put("024", LatLng.newInstance(42.3311431237047, -71.255936541659));
		//locs.put("026", LatLng.newInstance(41.6709403777211,-70.0727587919982));
		//locs.put("027", LatLng.newInstance(41.667849144924, -70.8173767564175));

		return locs;
	}

	private Map<String, LatLng> initLocs2() {
		HashMap<String, LatLng> locs = new HashMap<String, LatLng>();

		locs.put("014", LatLng.newInstance(42.5351046840491, -71.488823946219));
		locs.put("015", LatLng.newInstance(42.318900636606, -72.1294472483989));
		locs.put("016", LatLng.newInstance(42.227204559281, -71.7873132074038));
		locs
				.put("017", LatLng.newInstance(42.2253562723998,
						-71.5379156217244));
		locs
				.put("018", LatLng.newInstance(42.6898961957299,
						-71.1611878218555));
		locs
				.put("019", LatLng.newInstance(42.5819438054134,
						-70.7692820289472));
		locs
				.put("020", LatLng.newInstance(42.1846288457393,
						-71.3049781598891));
		locs.put("021", LatLng.newInstance(42.393528203942, -71.133795067936));
		locs
				.put("022", LatLng.newInstance(42.3474743983872,
						-71.1016113746733));

		return locs;
	}

	private MarkerOptions makeMarkerOptions(final String locationLabel) {
		final MarkerOptions options = MarkerOptions.newInstance();
		options.setTitle(locationLabel);
		options.setClickable(true);
		options.setIcon(makeIcon());
		return options;
	}

	private Icon makeIcon() {
		final Icon icon = Icon.newInstance();
		final String imageURL = MarkerConstants.imageURL;

		icon.setImageURL(imageURL);
		icon.setShadowURL(MarkerConstants.shadowImageURL);
		icon.setShadowSize(MarkerConstants.defaultShadowSize);
		icon.setIconAnchor(MarkerConstants.defaultIconAnchor);
		icon.setInfoWindowAnchor(MarkerConstants.defaultInfoWindowAnchor);

		return icon;
	}

	private static final class MarkerConstants {
		private static final String imageURL = "http://www.google.com/intl/en_us/mapfiles/ms/micons/red-dot.png";
		private static final String shadowImageURL = "http://www.google.com/mapfiles/shadow50.png";
		private static final Size defaultShadowSize = Size.newInstance(37, 34);
		private static final Point defaultIconAnchor = Point.newInstance(9, 34);
		private static final Point defaultInfoWindowAnchor = Point.newInstance(
				15, 32);
	}

	private MarkerClickHandler makeMarkerClickHandler(final MapWidget map,
			final String locationLabel) {
		return new MarkerClickHandler() {
			public void onClick(final MarkerClickEvent event) {
				map.savePosition();
				final VerticalPanel infoWindowPanel = displayDataForLocation(locationLabel);

				map.getInfoWindow().open(event.getSender(),
						new InfoWindowContent(infoWindowPanel));
				map.getInfoWindow().getPixelOffset();
			}

			private VerticalPanel displayDataForLocation(
					final String locationLabel) {
				final HorizontalPanel headerPanel = new HorizontalPanel();
				headerPanel.add(new Image(MarkerConstants.imageURL));

				headerPanel.add(new Label(locationLabel));
				// headerPanel.add(new HTML("<b>" + locationLabel + "</b> "));

				final VerticalPanel infoWindowPanel = new VerticalPanel();
				infoWindowPanel.add(headerPanel);
				// infoWindowPanel.setHeight("200px");
				// infoWindowPanel.setWidth("300px");
				infoWindowPanel.setHeight("100px");
				infoWindowPanel.setWidth("300px");

				final VerticalPanel columns = new VerticalPanel();
				columns.add(new Label("Percentage: 4.0  Low threshold: 3.0"));
				columns.add(new Label("Expected: 2.0  High threshold: 4.5"));

				infoWindowPanel.add(columns);

				return infoWindowPanel;
			}
		};
	}


	private void initMarkers(MapWidget map) {
		Collection<Marker> markers = new ArrayList<Marker>();
		Map<String, LatLng> locs = initLocs1();
		for (String locationLabel : locs.keySet()) {
			final MarkerOptions options = makeMarkerOptions(locationLabel);
			LatLng coords = locs.get(locationLabel);
			final Marker marker = new Marker(coords, options);
			marker.addMarkerClickHandler(makeMarkerClickHandler(map,
					locationLabel));
			markers.add(marker);
			map.addOverlay(marker);
		}

	}

	private void initMarkers2(MapWidget map) {
		Collection<Marker> markers = new ArrayList<Marker>();
		Map<String, LatLng> locs = initLocs2();
		for (String locationLabel : locs.keySet()) {
			final MarkerOptions options = makeMarkerOptions(locationLabel);
			LatLng coords = locs.get(locationLabel);
			final Marker marker = new Marker(coords, options);
			marker.addMarkerClickHandler(makeMarkerClickHandler(map,
					locationLabel));
			markers.add(marker);
			map.addOverlay(marker);
		}

	}
	 


}
