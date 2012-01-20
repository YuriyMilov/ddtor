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

	VerticalPanel vPanel= new VerticalPanel();
	  public void onModuleLoad() 
	  {   
	      Runnable onLoadCallback = new Runnable() 
	      {
	          public void run() 
	          {
	        	  DataTable data = DataTable.create();
	  			data.addColumn(ColumnType.STRING, "Year");
	  			data.addColumn(ColumnType.NUMBER, "Sales");
	  			data.addColumn(ColumnType.NUMBER, "Expenses");
	  			data.addRows(4);
	  			data.setValue(0, 0, "2004");
	  			data.setValue(0, 1, 1055);
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
	  			
	            Options options = Options.create(); 
		          options.setWidth(400); 
		          options.setHeight(240); 
		          options.setTitle("NFL Picks"); 
		          
	              ColumnChart pie = new ColumnChart(data, options);
	              vPanel.add(pie);
	          }
	      };
	      VisualizationUtils.loadVisualizationApi(onLoadCallback, LineChart.PACKAGE);
	      RootPanel.get().add(vPanel);
	  }

	
}

