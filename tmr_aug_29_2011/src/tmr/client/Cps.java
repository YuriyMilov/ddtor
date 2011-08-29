package tmr.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.maps.client.InfoWindowContent;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.control.LargeMapControl;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.overlay.Icon;
import com.google.gwt.maps.client.overlay.Marker;
import com.google.gwt.maps.client.overlay.MarkerOptions;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.cellview.client.TextColumn;

import java.util.Arrays;
import java.util.List;

public class Cps extends Composite {
	Icon icon = Icon.newInstance(Icon.DEFAULT_ICON);
	MarkerOptions ops = MarkerOptions.newInstance(icon);

	private final VerticalPanel widget = new VerticalPanel();
	private final GreetingServiceAsync greetingService = GWT
			.create(GreetingService.class);

	MapWidget map = new MapWidget(LatLng.newInstance(44, -77), 3);

	// private static final List<Info> iii = Arrays.asList(new Info("",
	// ""));
	public static String sx = "";
	public static String sx2 = "";

	public Cps() {
		map.setSize("800px", "600px");
		map.setScrollWheelZoomEnabled(true);
		map.addControl(new LargeMapControl());
		icon.setImageURL("markerGreen.png");

		// table.addColumn(address1, "From");
		// table.addColumn(address2, "To");
		// table.setRowCount(iii.size(), true);

		// Push the data into the widget.
		// table.setRowData(0, iii);
		// widget.add(table);
		widget.add(map);
		initWidget(widget);
		timer.run();
		timer.scheduleRepeating(60000);
	}

	// ////////////////////////

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
							sx = r;
							sx2 = r;
							timer2.run();
							timer2.scheduleRepeating(5000);
						}
					});

		}
	};

	Timer timer2 = new Timer() {
		public void run() {
			int i = sx.indexOf("<shiplat>");
			if (i < 0) {
				sx = sx2;
				i = sx.indexOf("<shiplat>");
			}
			// else
			{
				sx = sx.substring(i + 1);
				String slat = sx.substring(sx.indexOf("shiplat>") + 8,
						sx.indexOf("</shiplat>")).trim();
				String slng = sx.substring(sx.indexOf("<shiplng>") + 9,
						sx.indexOf("</shiplng>")).trim();
				String sclat = sx.substring(sx.indexOf("<conslat>") + 9,
						sx.indexOf("</conslat>")).trim();
				String sclng = sx.substring(sx.indexOf("<conslng>") + 9,
						sx.indexOf("</conslng>")).trim();
				String swo = sx.substring(sx.indexOf("<woNumber>") + 10,
						sx.indexOf("</woNumber>")).trim();
				String sfrom = sx.substring(sx.indexOf("<from1>") + 7,
						sx.indexOf("</from1>")).trim()
						+ ", "
						+ sx.substring(sx.indexOf("<from2>") + 7,
								sx.indexOf("</from2>")).trim();
				String sto = sx.substring(sx.indexOf("<to1>") + 5,
						sx.indexOf("</to1>")).trim()
						+ ", "
						+ sx.substring(sx.indexOf("<to2>") + 5,
								sx.indexOf("</to2>")).trim();

				LatLng lal = LatLng.newInstance((Double.valueOf("0.0")),
						Double.valueOf("0.0"));

				LatLng lalc = LatLng.newInstance((Double.valueOf("0.0")),
						Double.valueOf("0.0"));

				if (slat.length() > 0 && slng.length() > 0
						&& sclat.length() > 0 && sclng.length() > 0) {
					lal = LatLng.newInstance((Double.valueOf(slat)),
							Double.valueOf(slng));

					lalc = LatLng.newInstance((Double.valueOf(sclat)),
							Double.valueOf(sclng));

					Marker m = new Marker(lal);
					map.clearOverlays();
					map.addOverlay(m);

					ops = MarkerOptions.newInstance(icon);
					ops.setIcon(icon);
					Marker mc = new Marker(lalc, ops);
					map.addOverlay(mc);

					map.getInfoWindow().open(
							m.getLatLng(),
							new InfoWindowContent("From: " + sfrom + "<br>To: "
									+ sto + "<br>WO#: " + swo));
					// iii.get(0).address1 = sfrom;
					// iii.get(0).address2 = sto;
					// table.setRowData(0, iii);
					// table.redraw();
				}
			}
		}
	};

	/**
	 * A simple data type that represents Info.
	 */
	/*
	 * private static class Info { private String address1; private String
	 * address2;
	 * 
	 * public Info(String address1, String address2) { this.address1 = address1;
	 * this.address2 = address2; } }
	 * 
	 * // Create a CellTable. CellTable<Info> table = new CellTable<Info>();
	 * 
	 * // Add a text column to show the address1. TextColumn<Info> address1 =
	 * new TextColumn<Info>() {
	 * 
	 * @Override public String getValue(Info object) { return object.address1; }
	 * };
	 * 
	 * // Add a text column to show the address2. TextColumn<Info> address2 =
	 * new TextColumn<Info>() {
	 * 
	 * @Override public String getValue(Info object) { return object.address2; }
	 * };
	 */
}
