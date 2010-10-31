package gu.client.view;

import java.util.ArrayList;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.control.LargeMapControl;
import com.google.gwt.maps.client.event.MapClickHandler;
import com.google.gwt.maps.client.event.MarkerDoubleClickHandler;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.overlay.Marker;
import com.google.gwt.maps.client.overlay.MarkerOptions;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.datepicker.client.DateBox;
import com.smartgwt.client.data.Criterion;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.DateRange;
import com.smartgwt.client.data.RelativeDate;
import com.smartgwt.client.types.TitleOrientation;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.DrawEvent;
import com.smartgwt.client.widgets.events.DrawHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.DateRangeItem;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;

public class ZgalaView extends Composite {

		DataSource ds=null;
		
		ListGrid grid1 = new ListGrid();

		ArrayList<LatLng> ar = new ArrayList<LatLng>();
		MapWidget map = new MapWidget(LatLng.newInstance(44, -77), 3);
		IButton bf = new IButton("Apply Delivery Date Range");
		IButton bf1 = new IButton("Apply Shipping Date Range");
		DateRangeItem rangeItem = new DateRangeItem("dd");
		DateRangeItem rangeItem1 = new DateRangeItem("shd");
		VLayout layoutV = new VLayout(11);
		public ZgalaView() {

			
			layoutV.setWidth("888");
			//layoutV2.setWidth("888");
			
			DynamicForm form = new DynamicForm();
			DynamicForm form1 = new DynamicForm();

			rangeItem.setWidth("*");
			rangeItem.setTitle("Delivery Date Range");
			rangeItem.setShowTitle(true);
			rangeItem.setAllowRelativeDates(true);

			rangeItem1.setWidth("*");
			rangeItem1.setTitle("Shipping Date Range");
			rangeItem1.setShowTitle(true);
			rangeItem1.setAllowRelativeDates(true);

			DateRange dateRange = new DateRange();
			DateRange dateRange1 = new DateRange();
			
			dateRange.setRelativeStartDate(new RelativeDate("-2m"));
			dateRange.setRelativeEndDate(new RelativeDate("+1m"));

			dateRange1.setRelativeStartDate(new RelativeDate("-2m"));
			dateRange1.setRelativeEndDate(new RelativeDate("+1m"));
			
			rangeItem.setValue(dateRange);
			rangeItem1.setValue(dateRange1);
			
			form.setItems(rangeItem);
			form1.setItems(rangeItem1);
			
			///////////////////////////////
			
			form.setTitleOrientation(TitleOrientation.TOP);
			form1.setTitleOrientation(TitleOrientation.TOP);

			// Create a ListGrid displaying data from the worldDS
			grid1.setHeight(250);
			ds=DaSo.getInstance();
			
			grid1.setDataSource(ds);
			grid1.fetchData();
			bf.setAutoFit(true);
			bf.addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					Criterion criteria = rangeItem.getCriterion();
					grid1.fetchData(criteria);
				}
			});
			
			bf1.setAutoFit(true);
			bf1.addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					Criterion criteria = rangeItem1.getCriterion();
					grid1.fetchData(criteria);
				}
			});

			//layoutV.addDrawHandler(new DrawHandler() {
			//	@Override
			//	public void onDraw(DrawEvent event) {
					//Criterion criteria = rangeItem.getCriterion();
					//grid1.fetchData(criteria);
					//Criterion criteria1 = rangeItem1.getCriterion();
					//grid1.fetchData(criteria1);
			//	}
			//});

			layoutV.addMember(form1);
			layoutV.addMember(bf1);
			layoutV.addMember(form);
			layoutV.addMember(bf);
			layoutV.addMember(grid1);
			initWidget(layoutV);
		}
}
