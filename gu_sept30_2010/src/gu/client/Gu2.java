package gu.client;

import gu.client.view.Application;
import gu.client.view.Application2;
import gu.client.view.DatabaseEditorView;
//import gu.client.view.LoginView;
import gu.client.view.ZgalaView;
import gu.client.view.ZgalaView2;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;


import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;
import com.smartgwt.client.data.DataSourceField;
import com.smartgwt.client.data.XJSONDataSource;
import com.smartgwt.client.data.fields.DataSourceImageField;
import com.smartgwt.client.data.fields.DataSourceIntegerField;
import com.smartgwt.client.data.fields.DataSourceLinkField;
import com.smartgwt.client.types.FieldType;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.form.SearchForm;
import com.smartgwt.client.widgets.form.fields.ButtonItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.grid.ListGrid;


public class Gu2 implements EntryPoint {

	  public void onModuleLoad() {
						
	      ZgalaView view = new ZgalaView(); 
	      RootPanel.get().add(view);


		  //RootPanel.get().add(new Application2());
		  
		  }
	  
	  public Canvas get() {

		    Canvas canvas = new Canvas();

		    Canvas yahooAttribution = new Canvas();
		    yahooAttribution.setHeight(31);
		    yahooAttribution.setContents("<a href='http://developer.yahoo.net/about'>"
		        + "<img src='http://us.dev1.yimg.com/us.yimg.com/i/us/nt/bdg/websrv_88_1.gif' border='0'>"
		        + "</a>");
		    canvas.addChild(yahooAttribution);

		    XJSONDataSource yahooDS = new XJSONDataSource();
		    yahooDS
		        .setDataURL("http://api.search.yahoo.com/ImageSearchService/V1/imageSearch?appid=YahooDemo&output=json&query=snowboard");
		    yahooDS.setRecordXPath("/ResultSet/Result");
		    DataSourceImageField thumbnail = new DataSourceImageField("Thumbnail", "Thumbnail");
		    thumbnail.setWidth(150);
		    thumbnail.setImageHeight("imageHeight");
		    thumbnail.setImageWidth("imageWidth");
		    thumbnail.setValueXPath("Thumbnail/Url");

		    DataSourceIntegerField imageWidth = new DataSourceIntegerField("imageWidth");
		    imageWidth.setValueXPath("Thumbnail/Width");
		    imageWidth.setAttribute("hidden", true);

		    DataSourceIntegerField imageHeight = new DataSourceIntegerField("imageHeight");
		    imageHeight.setValueXPath("Thumbnail/Height");
		    imageHeight.setAttribute("hidden", true);

		    DataSourceField title = new DataSourceField("Title", FieldType.TEXT);
		    DataSourceField summary = new DataSourceField("Summary", FieldType.TEXT);

		    DataSourceLinkField fullImage = new DataSourceLinkField("link", "Full Image");
		    fullImage.setValueXPath("Url");
		    fullImage.setAttribute("target", "_blank");

		    yahooDS.addField(thumbnail);
		    yahooDS.addField(imageWidth);
		    yahooDS.addField(imageHeight);
		    yahooDS.addField(title);
		    yahooDS.addField(summary);
		    yahooDS.addField(fullImage);

		    final ListGrid grid = new ListGrid();
		    grid.setTop(120);
		    grid.setWidth100();
		    grid.setHeight(300);
		    grid.setWrapCells(true);
		    grid.setFixedRecordHeights(false);
		    grid.setShowAllRecords(true);
		    grid.setAlternateRecordStyles(true);
		    grid.setDataSource(yahooDS);

		    final SearchForm form = new SearchForm();
		    form.setTop(50);
		    form.setNumCols(3);
		    TextItem query = new TextItem();
		    query.setName("query");
		    query.setTitle("Query");
		    query.setDefaultValue("snowboarding");

		    ButtonItem button = new ButtonItem();
		    button.setTitle("Search");
		    button.setStartRow(false);
		    ClickHandler hh = new ClickHandler() {
			      public void onClick(ClickEvent event) {
			        grid.fetchData(form.getValuesAsCriteria());
			      }
			    };
		    
		    button.addClickHandler((com.smartgwt.client.widgets.form.fields.events.ClickHandler) hh);

		    form.setItems(query, button);

		    canvas.addChild(form);
		    canvas.addChild(grid);
		    return canvas;

		  }
	  
	  public Canvas getViewPanel() {

		    Canvas canvas = new Canvas();

		    Canvas yahooAttribution = new Canvas();
		    yahooAttribution.setHeight(31);
		    yahooAttribution.setContents("<a href='http://developer.yahoo.net/about'>"
		        + "<img src='http://us.dev1.yimg.com/us.yimg.com/i/us/nt/bdg/websrv_88_1.gif' border='0'>"
		        + "</a>");
		    canvas.addChild(yahooAttribution);

		    XJSONDataSource yahooDS = new XJSONDataSource();
		    yahooDS
		        .setDataURL("http://api.search.yahoo.com/ImageSearchService/V1/imageSearch?appid=YahooDemo&output=json&query=snowboard");
		    yahooDS.setRecordXPath("/ResultSet/Result");
		    DataSourceImageField thumbnail = new DataSourceImageField("Thumbnail", "Thumbnail");
		    thumbnail.setWidth(150);
		    thumbnail.setImageHeight("imageHeight");
		    thumbnail.setImageWidth("imageWidth");
		    thumbnail.setValueXPath("Thumbnail/Url");

		    DataSourceIntegerField imageWidth = new DataSourceIntegerField("imageWidth");
		    imageWidth.setValueXPath("Thumbnail/Width");
		    imageWidth.setAttribute("hidden", true);

		    DataSourceIntegerField imageHeight = new DataSourceIntegerField("imageHeight");
		    imageHeight.setValueXPath("Thumbnail/Height");
		    imageHeight.setAttribute("hidden", true);

		    DataSourceField title = new DataSourceField("Title", FieldType.TEXT);
		    DataSourceField summary = new DataSourceField("Summary", FieldType.TEXT);

		    DataSourceLinkField fullImage = new DataSourceLinkField("link", "Full Image");
		    fullImage.setValueXPath("Url");
		    fullImage.setAttribute("target", "_blank");

		    yahooDS.addField(thumbnail);
		    yahooDS.addField(imageWidth);
		    yahooDS.addField(imageHeight);
		    yahooDS.addField(title);
		    yahooDS.addField(summary);
		    yahooDS.addField(fullImage);

		    final ListGrid grid = new ListGrid();
		    grid.setTop(120);
		    grid.setWidth100();
		    grid.setHeight(300);
		    grid.setWrapCells(true);
		    grid.setFixedRecordHeights(false);
		    grid.setShowAllRecords(true);
		    grid.setAlternateRecordStyles(true);
		    grid.setDataSource(yahooDS);

		    final SearchForm form = new SearchForm();
		    form.setTop(50);
		    form.setNumCols(3);
		    TextItem query = new TextItem();
		    query.setName("query");
		    query.setTitle("Query");
		    query.setDefaultValue("snowboarding");

		    ButtonItem button = new ButtonItem();
		    button.setTitle("Search");
		    button.setStartRow(false);
		    ClickHandler hh = new ClickHandler() {
			      public void onClick(ClickEvent event) {
			        grid.fetchData(form.getValuesAsCriteria());
			      }
			    };
		    
		    button.addClickHandler((com.smartgwt.client.widgets.form.fields.events.ClickHandler) hh);

		    form.setItems(query, button);

		    canvas.addChild(form);
		    canvas.addChild(grid);
		    return canvas;

		  }

}
