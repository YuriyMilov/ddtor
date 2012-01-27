package qdc.client;

import com.google.gwt.user.client.*;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.visualization.client.*;
import com.google.gwt.visualization.client.AbstractDataTable.ColumnType;
import com.google.gwt.visualization.client.visualizations.*;
import com.google.gwt.visualization.client.visualizations.ColumnChart.Options;
import com.google.gwt.xml.client.*;
import com.google.gwt.xml.client.Element;
import com.google.gwt.core.client.*;
import com.google.gwt.http.client.*;

public class Copy_3_of_Qdc implements EntryPoint {

	VerticalPanel vPanel = new VerticalPanel();
	VerticalPanel vPanel1 = new VerticalPanel();
	VerticalPanel vPanel2 = new VerticalPanel();
	String s1="";
	int i1=0;
	int j1=0;
	int n=0;

	public void onModuleLoad() {
		vPanel.setWidth("100%");
		vPanel.setHorizontalAlignment(vPanel.ALIGN_CENTER);

		RequestBuilder greet = new RequestBuilder(RequestBuilder.GET,
				"greet.aspx");

		RequestBuilder qdc3 = new RequestBuilder(RequestBuilder.GET,
				"qdc3.aspx");

	

		try {
			qdc3.sendRequest(null, new RequestCallback() {
				public void onError(Request request, Throwable exception) {
					RootPanel.get().add(new HTML(exception.toString()));
				}

				public void onResponseReceived(Request request,
						Response response) {
					vPanel2.add(new HTML(response.getText()));
					s1="Mar(2011)";
					i1=555;
					j1=300;
					n=11;
					VisualizationUtils.loadVisualizationApi(onLoadCallback,
							LineChart.PACKAGE);
				}
			});
		} catch (RequestException ex) {
			RootPanel.get().add(new HTML(ex.toString()));
		}
		
	
		
		vPanel.add(vPanel1);
		vPanel.add(vPanel2);
		
		RootPanel.get().add(vPanel);
	}

	Runnable onLoadCallback = new Runnable() {
		public void run() {
			DataTable data = DataTable.create();
			data.addColumn(ColumnType.STRING, "Month");
			data.addColumn(ColumnType.NUMBER, "Invoiced");
			data.addColumn(ColumnType.NUMBER, "Accured");
			data.addRows(n);

			for (int i=0;i<n;i++)
			{
				data.setValue(i, 0, s1);
				data.setValue(i, 1, i1);
				data.setValue(i, 2, j1);
			}
			
			//data.setValue(1, 0, "2005");
			//data.setValue(1, 1, 1170);
			//data.setValue(1, 2, 460);
			//data.setValue(2, 0, "2006");
			//data.setValue(2, 1, 660);
			//data.setValue(2, 2, 1120);
			//data.setValue(3, 0, "2007");
			//data.setValue(3, 1, 1030);
			//data.setValue(3, 2, 540);

			Options options = Options.create();
			options.setWidth(700);
			options.setHeight(300);
			options.set3D(true);
			options.setTitle("Company Performance");
		

			ColumnChart pie = new ColumnChart(data, options);
			vPanel1.add(pie);
		}
	};

}
