package qdc.client;

import com.google.gwt.user.client.*;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.visualization.client.*;
import com.google.gwt.visualization.client.AbstractDataTable.ColumnType;
import com.google.gwt.visualization.client.visualizations.*;
import com.google.gwt.xml.client.*;
import com.google.gwt.xml.client.Element;
import com.google.gwt.core.client.*;
import com.google.gwt.http.client.*;

public class Copy_2_of_Qdc implements EntryPoint {
	static String s = "qq";
	static double f=1222.2;

	
	static DataTable getCompanyPerformance() {
		DataTable data = DataTable.create();
		data.addColumn(ColumnType.STRING, "Year");
		data.addColumn(ColumnType.NUMBER, "Sales");
		data.addColumn(ColumnType.NUMBER, "Expenses");
		data.addRows(4);
		 data.setValue(0, 0, "2004");
		//data.setValue(0, 0, s);
		data.setValue(0, 1, f);
		data.setValue(0, 2, 400);
		data.setValue(1, 0, "2005");
		data.setValue(1, 1, 1170);
		data.setValue(1, 2, 460);
		data.setValue(2, 0, "2006");
		data.setValue(2, 1, 660);
		data.setValue(2, 2, 1120);
		data.setValue(3, 0, "2007");
		data.setValue(3, 1, 1030);
		data.setValue(3, 2, 540);
		return data;
	}

	static DataTable getDailyActivities() {
		DataTable data = DataTable.create();
		data.addColumn(ColumnType.STRING, "Task");
		data.addColumn(ColumnType.NUMBER, "Hours per Day");
		data.addRows(5);
		data.setValue(0, 0, "Work");
		data.setValue(0, 1, 11);
		data.setValue(1, 0, "Eat");
		data.setValue(1, 1, 2);
		data.setValue(2, 0, "Commute");
		data.setValue(2, 1, 2);
		data.setValue(3, 0, "Watch TV");
		data.setValue(3, 1, 2);
		data.setValue(4, 0, "Sleep");
		data.setValue(4, 1, 7);
		return data;
	}

	static DataTable getSugarSaltAndCaloriesComparison() {
		DataTable data = DataTable.create();
		data.addColumn(ColumnType.NUMBER, "Sugar");
		data.addColumn(ColumnType.NUMBER, "Calories");
		data.addColumn(ColumnType.NUMBER, "Salt");
		data.addRows(4);
		data.setValue(0, 0, 20);
		data.setValue(0, 1, 11);
		data.setValue(0, 2, 30);
		data.setValue(1, 0, 30);
		data.setValue(1, 1, 10);
		data.setValue(1, 2, 20);
		data.setValue(2, 0, 27);
		data.setValue(2, 1, 2);
		data.setValue(2, 2, 34);
		data.setValue(3, 0, 22);
		data.setValue(3, 1, 2);
		data.setValue(3, 1, 9);
		return data;
	}

	public void onModuleLoad() {
//		RequestBuilder multic = new RequestBuilder(RequestBuilder.GET, "greet.aspx");

		RootPanel.get().add(new HTML(""));

		try {
			(new RequestBuilder(RequestBuilder.GET, "greet.aspx")).sendRequest(
					null, new RequestCallback() {
						public void onError(Request request, Throwable exception) {

						}

						public void onResponseReceived(Request request,
								Response response) {
							s = response.getText();
							parseMessage(s);
							//RootPanel.get().add(new HTML("-> " + s));

							VisualizationUtils.loadVisualizationApi(
									"1.1",
									new Runnable() {
										public void run() {
											final VerticalPanel vp = new VerticalPanel();
											vp.setSpacing(15);
											RootPanel.get().add(vp);
											LeftTabPanel tabby = new LeftTabPanel();
											vp.add(tabby);
											ColumnDemo annotatedWidget = new ColumnDemo();
											tabby.add(new AnnotatedDemo(),
													"AnnotatedTimeLine");
											tabby.add(new AreaDemo(),
													"AreaChart");
											tabby.add(new BarDemo(), "BarChart");
											tabby.add(new ColumnDemo(),
													"ColumnChart");
											tabby.add(new GaugeDemo(), "Gauge");
											tabby.add(new GeoDemo(), "Geo Map");
											tabby.add(new IntensityDemo(),
													"IntensityMap");
											tabby.add(new LineDemo(),
													"LineChart");
											tabby.add(new MapDemo(), "Map");
											tabby.add(new MotionDemo(),
													"MotionChart");
											tabby.add(new OrgDemo(), "OrgChart");
											tabby.add(new PieDemo(), "PieChart");
											tabby.add(new ScatterDemo(),
													"ScatterChart");
											tabby.add(new TableDemo(), "Table");
											tabby.setWidget(annotatedWidget);
										}
									}, AnnotatedTimeLine.PACKAGE,
									AreaChart.PACKAGE, BarChart.PACKAGE,
									ColumnChart.PACKAGE, Gauge.PACKAGE,
									GeoMap.PACKAGE, IntensityMap.PACKAGE,
									LineChart.PACKAGE,
									MapVisualization.PACKAGE,
									MotionChart.PACKAGE, OrgChart.PACKAGE,
									PieChart.PACKAGE, ScatterChart.PACKAGE,
									Table.PACKAGE);

						}
					});
		} catch (RequestException ex) {
			Window.alert(ex.toString());
		}
	}

	static void parseMessage(String messageXml) {
		try {
			Document messageDom = XMLParser.parse(messageXml);
			
			//Node fromNode = messageDom.getElementsByTagName("from").item(0);
			//s = ((Element) fromNode).getAttribute("displayName");
			
			s = messageDom.getElementsByTagName("month").item(0).getFirstChild().getNodeValue();
			String s2=messageDom.getElementsByTagName("income").item(0).getFirstChild().getNodeValue();
			//System.out.println(s2);
			f = Double.valueOf(s2);
			

		} catch (DOMException e) {
			Window.alert(e.toString());
		}
	}
}

/*
 * Runnable qq = new Runnable(){ public void run() { final VerticalPanel vp =
 * new VerticalPanel(); vp.setSpacing(15); RootPanel.get().add(vp); LeftTabPanel
 * tabby = new LeftTabPanel(); vp.add(tabby); ColumnDemo annotatedWidget = new
 * ColumnDemo(); tabby.add(new AnnotatedDemo(), "AnnotatedTimeLine");
 * tabby.add(new AreaDemo(), "AreaChart"); tabby.add(new BarDemo(), "BarChart");
 * tabby.add(annotatedWidget, "ColumnChart"); tabby.add(new GaugeDemo(),
 * "Gauge"); tabby.add(new GeoDemo(), "Geo Map"); tabby.add(new IntensityDemo(),
 * "IntensityMap"); tabby.add(new LineDemo(), "LineChart"); tabby.add(new
 * MapDemo(), "Map"); tabby.add(new MotionDemo(), "MotionChart"); tabby.add(new
 * OrgDemo(), "OrgChart"); tabby.add(new PieDemo(), "PieChart"); tabby.add(new
 * ScatterDemo(), "ScatterChart"); tabby.add(new TableDemo(), "Table");
 * tabby.setWidget(annotatedWidget); } };
 * 
 * 
 * 
 * public void onModuleLoad() { try { (new
 * RequestBuilder(RequestBuilder.GET,"greet.aspx")).sendRequest(null, new
 * RequestCallback() { public void onError(Request request, Throwable exception)
 * {
 * 
 * } public void onResponseReceived(Request request, Response response) {
 * RootPanel.get().add(new HTML(response.getText())); } }); } catch
 * (RequestException ex) { } }
 * 
 * static void parseMessage(String messageXml) { try { // parse the XML document
 * into a DOM Document messageDom = XMLParser.parse(messageXml);
 * 
 * // find the sender's display name in an attribute of the <from> tag Node
 * fromNode = messageDom.getElementsByTagName("from").item(0); String from =
 * ((Element)fromNode).getAttribute("displayName"); s = from;
 * 
 * // fromLabel.setText(from);
 * 
 * // get the subject using Node's getNodeValue() function String subject =
 * messageDom
 * .getElementsByTagName("subject").item(0).getFirstChild().getNodeValue();
 * //subjectLabel.setText(subject); //s=s+" "+subject;
 * 
 * // get the message body by explicitly casting to a Text node Text bodyNode =
 * (Text)messageDom.getElementsByTagName("body").item(0).getFirstChild(); String
 * body = bodyNode.getData(); // bodyLabel.setText(body); // s=s+" "+body;
 * 
 * } catch (DOMException e) { Window.alert("Could not parse XML document."); } }
 */