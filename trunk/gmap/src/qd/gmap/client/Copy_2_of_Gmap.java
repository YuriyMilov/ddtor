package qd.gmap.client;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.maps.client.InfoWindow;
import com.google.gwt.maps.client.InfoWindowContent;

import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.control.LargeMapControl;
import com.google.gwt.maps.client.control.MapTypeControl;

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
import com.google.gwt.maps.client.overlay.PolyStyleOptions;
import com.google.gwt.maps.client.overlay.Polyline;
import com.google.gwt.user.client.Timer;
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

public class Copy_2_of_Gmap implements EntryPoint {
	private final GreetingServiceAsync greetingService = GWT
	.create(GreetingService.class);
	
    MapWidget map;
    static final int TIMEOUT = 30;
    List<LatLng> tmpPointList = new ArrayList<LatLng>();

    class LatitudeLongitude {
        private double latitude;
        private double longitude;

        public LatitudeLongitude(double latitude, double longitude) {
            this.latitude = latitude;
            this.longitude = longitude;
        }

        public double getLatitude() {
            return latitude;
        }

        public double getLongitude() {
            return longitude;
        }
    }

    // GWT module entry point method.
    public void onModuleLoad() {
        LatLng city = LatLng.newInstance(-34.402130, 150.899139);

        map = new MapWidget(city, 17);
        map.setSize("500px", "300px");
        map.setScrollWheelZoomEnabled(true);
        map.addControl(new MapTypeControl());

        LatitudeLongitude point;
        List<LatitudeLongitude> pointList = new ArrayList<LatitudeLongitude>();
        // Change from this point with your own coordinates.
        point = new LatitudeLongitude(-34.402130, 150.899139);
        pointList.add(point);

        point = new LatitudeLongitude(-34.402239, 150.900324);
        pointList.add(point);

        point = new LatitudeLongitude(-34.403191, 150.900188);
        pointList.add(point);

        point = new LatitudeLongitude(-34.404122, 150.899975);
        pointList.add(point);

        point = new LatitudeLongitude(-34.405421, 150.899713);
        pointList.add(point);

        point = new LatitudeLongitude(-34.406351, 150.899245);
        pointList.add(point);

        point = new LatitudeLongitude(-34.407102, 150.898747);
        pointList.add(point);

        point = new LatitudeLongitude(-34.407932, 150.898080);
        pointList.add(point);

        point = new LatitudeLongitude(-34.409443, 150.897013);
        pointList.add(point);

        point = new LatitudeLongitude(-34.410098,150.896829);
        pointList.add(point);

        point = new LatitudeLongitude(-34.411022,150.897490);
        pointList.add(point);

        point = new LatitudeLongitude(-34.411949,150.898663);
        pointList.add(point);

        point = new LatitudeLongitude(-34.412683,150.899658);
        pointList.add(point);

        Timer timer;

        int i = 0;
        for (final LatitudeLongitude ll : pointList) {
            i += 1;
            timer = new Timer() {
                public void run() {
                   addPoints(ll.getLatitude(), ll.getLongitude());

                }
            };

            timer.schedule(i * 1500);
        }

        // Add some controls for the zoom level
        map.addControl(new LargeMapControl());

        // Add an info window to highlight a point of interest
        map.getInfoWindow().open(map.getCenter(),
                new InfoWindowContent("Start from here"));

        // Add the map to the HTML host page
        //RootPanel.get("maps").add(map);
        RootPanel.get().add(map);
    }

    private void addPoints(double latitude, double longitude) {

    	greetingService.greetServer("textToServer",
				new AsyncCallback<String>() {
					public void onFailure(Throwable caught) {
					}

					public void onSuccess(String result) {

					}
				});
    	
        LatLng point = LatLng.newInstance(latitude, longitude);
        if (tmpPointList.size() == 2) {
            tmpPointList.remove(0);
        }
        tmpPointList.add(point);
        Marker marker = new Marker(point);

        map.addOverlay(marker);
        map.panTo(point);

        LatLng[] points = tmpPointList.toArray(new LatLng[0]);

        Polyline line = new Polyline(points);
        PolyStyleOptions lineStyle = PolyStyleOptions.getInstance();
        lineStyle.setColor("red");
        line.setStrokeStyle( lineStyle );
        map.addOverlay(line);
        
        map.getInfoWindow().open(marker,
                new InfoWindowContent("qqqqqqqq"));
    }
}