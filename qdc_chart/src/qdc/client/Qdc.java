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

public class Qdc implements EntryPoint {

	VerticalPanel vPanel = new VerticalPanel();
	VerticalPanel vPanel1 = new VerticalPanel();
	VerticalPanel vPanel2 = new VerticalPanel();
	String[] s1=new String[]{"","","","","","","","","","","",""};
	String[] ss=new String[]{"","","","","","","","","","","",""};
	double[] i1={0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0};
	double[] j1={0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0};
	int n=0;

	public void onModuleLoad() {
		vPanel.setWidth("100%");
		vPanel.setHorizontalAlignment(vPanel.ALIGN_CENTER);
//		vPanel.setHorizontalAlignment(vPanel.ALIGN_LEFT);

		RequestBuilder qdc3 = new RequestBuilder(RequestBuilder.GET,
				"qdc3.aspx");

	

		try {
			qdc3.sendRequest(null, new RequestCallback() {
				public void onError(Request request, Throwable exception) {
					RootPanel.get().add(new HTML(exception.toString()));
				}

				public void onResponseReceived(Request request,
						Response response) {
					String s33=response.getText();
						vPanel2.add(new HTML(response.getText()));
				
						
						
						s33=response.getText().substring(s33.indexOf("<!--sn"));
						s33=s33.substring(6,s33.indexOf("-->"));
						
						System.out.println(s33.trim());
						
							n = Integer.parseInt(s33);

						System.out.println("n="+n);
						
						s33=response.getText();
						s33=response.getText().substring(s33.indexOf("<!--sm"));
						s33=s33.substring(6,s33.indexOf("-->"));
						ss=s33.split("\r\n");
						
					
						int w=0;
						while (w<ss.length)
						{
							s1[w]=ss[w];
							System.out.println("w="+w + ss[w]);
							w++;
						}
					
						s33=response.getText();
						s33=response.getText().substring(s33.indexOf("<!--si"));
						s33=s33.substring(6,s33.indexOf("-->"));
						ss=s33.split("\r\n");
					
						w=0;
						while (w<ss.length)
						{
							if(ss[w].trim().length()==0)
								i1[w]=0.0;
							else
							{
								System.out.println("w="+w + "--> "+ ss[w]);
								i1[w]=Double.parseDouble(ss[w]);
							}
							w++;
						}
						s33=response.getText();
						s33=response.getText().substring(s33.indexOf("<!--sj"));
						s33=s33.substring(6,s33.indexOf("-->"));
						ss=s33.split("\r\n");
					
						w=0;
						while (w<ss.length)
						{
							if(ss[w].trim().length()==0)
								j1[w]=0.0;
							else
							{
								System.out.println("w="+w + "--> "+ ss[w]);
								j1[w]=Double.parseDouble(ss[w]);
							}
							w++;
						}
					
					VisualizationUtils.loadVisualizationApi(onLoadCallback,
							LineChart.PACKAGE);
				}
			});
		} catch (RequestException ex) {
			RootPanel.get().add(new HTML(ex.toString()));
		}
		
	
		
		vPanel.add(vPanel1);
		vPanel.add(vPanel2);
		
		//RootPanel.get().add(new HTML("<table style=\"width: 100%\"><tr><td align=\"center\"><img src=\"images/logo20.jpg\" /></td></tr></table><br/>&nbsp;&nbsp;<a href=multic2.aspx>Main Page</a>"));
		RootPanel.get().add(vPanel);
	}

	Runnable onLoadCallback = new Runnable() {
		public void run() {
			DataTable data = DataTable.create();
			data.addColumn(ColumnType.STRING, "Month");
			data.addColumn(ColumnType.NUMBER, "Invoiced");
			data.addColumn(ColumnType.NUMBER, "Accrued");
			//data.addColumn(ColumnType.NUMBER, "PCS");
			//data.addColumn(ColumnType.NUMBER, "Total");
			data.addRows(n);

			for (int i=0;i<n;i++)
			{
				data.setValue(i, 0, s1[i]);
				data.setValue(i, 1, i1[i]);
				data.setValue(i, 2, j1[i]);
				//data.setValue(i, 3, 411);
				//data.setValue(i, 4, 377);
				
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
			options.setWidth(600);
			options.setHeight(300);
			options.set3D(true);
			options.setTitle("Company Performance");
		

			ColumnChart pie = new ColumnChart(data, options);
			vPanel1.add(pie);
		}
	};

}
