package gu.client;

import java.util.ArrayList;

import gu.client.dao.RPCObjectFactory;
import gu.client.view.UfosView;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.maps.client.InfoWindowContent;
import com.google.gwt.maps.client.event.MarkerClickHandler;
import com.google.gwt.maps.client.event.MarkerClickHandler.MarkerClickEvent;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.overlay.Icon;
import com.google.gwt.maps.client.overlay.Marker;
import com.google.gwt.maps.client.overlay.MarkerOptions;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;


public class GWTClient implements EntryPoint {
	private UfosView vu = new UfosView();
	public void onModuleLoad() {
				
				//RPCObjectFactory objectFactory = new RPCObjectFactory(GWT
				//		.getModuleBaseURL() + "objectFactory");
				//vu.setObjectFactory(objectFactory);

				RootPanel.get().add(vu);

	}

}
