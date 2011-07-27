package tmr.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.control.LargeMapControl;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.overlay.Marker;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.cellview.client.TextColumn;
import java.util.Arrays;
import java.util.List;

public class Tmr implements EntryPoint {

	private final GreetingServiceAsync greetingService = GWT
			.create(GreetingService.class);
	MapWidget map = new MapWidget(LatLng.newInstance(44, -77), 3);



	private static final List<Info> iii = Arrays.asList(new Info("1111",
			"2222222"));

	int i = 1;
	Timer timer = new Timer() {
		public void run() {
			greetingService.greetServer("textToServer",
					new AsyncCallback<String>() {

						public void onFailure(Throwable caught) {
							RootPanel.get().clear();
							RootPanel.get().add(new HTML(caught.toString()));
						}

						public void onSuccess(String r) {
							//RootPanel.get().add(new HTML(r));
							Marker m = new Marker(LatLng.newInstance(
									40 + (Integer.valueOf(r)),
									-(88 + Integer.valueOf(r))));
							map.clearOverlays();
							map.addOverlay(m);

							iii.get(0).address1 = r;
							iii.get(0).address2 = r;
							table.setRowData(0, iii);
							table.redraw();
						}
					});
		}
	};

	/**
	 * A simple data type that represents Info.
	 */
	private static class Info {
		private String address1;
		private String address2;

		public Info(String address1, String address2) {
			this.address1 = address1;
			this.address2 = address2;
		}
	}
	// Create a CellTable.
	CellTable<Info> table = new CellTable<Info>();

	// Add a text column to show the address1.
	TextColumn<Info> address1 = new TextColumn<Info>() {
		@Override
		public String getValue(Info object) {
			return object.address1;
		}
	};

	// Add a text column to show the address2.
	TextColumn<Info> address2 = new TextColumn<Info>() {
		@Override
		public String getValue(Info object) {
			return object.address2;
		}
	};

	public void onModuleLoad() {
		map.setSize("400px", "300px");
		map.setScrollWheelZoomEnabled(true);
		map.addControl(new LargeMapControl());

		table.addColumn(address1, "Address1");
		table.addColumn(address2, "Address2");
		table.setRowCount(iii.size(), true);

		// Push the data into the widget.
		table.setRowData(0, iii);

		// Add it to the root panel.
		RootPanel.get().add(table);

		RootPanel.get().add(map);
		timer.scheduleRepeating(3000);
	}
}
