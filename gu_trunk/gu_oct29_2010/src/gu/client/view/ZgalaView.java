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
		IButton bf = new IButton(" Filter Date Range ");
		DateRangeItem rangeItem = new DateRangeItem("independence");
		VLayout layoutV = new VLayout(11);
		VLayout layoutV2 = new VLayout(11);
		HLayout layoutH = new HLayout(11);

		public ZgalaView() {

			layoutH.setWidth("555");
			layoutV.setWidth("888");
			layoutV2.setWidth("888");
			DynamicForm form = new DynamicForm();

			rangeItem.setWidth("*");
			rangeItem.setShowTitle(false);
			rangeItem.setAllowRelativeDates(true);

			DateRange dateRange = new DateRange();
			dateRange.setRelativeStartDate(new RelativeDate("-1200m"));
			dateRange.setRelativeEndDate(new RelativeDate("-1000m"));
			rangeItem.setValue(dateRange);
			form.setItems(rangeItem);
			//form.setTitleOrientation(TitleOrientation.TOP);

			// Create a ListGrid displaying data from the worldDS
			grid1.setHeight(250);
			ds=WorldXmlDS.getInstance();
			grid1.setDataSource(ds);
			bf.setAutoFit(true);
			bf.addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					Criterion criteria = rangeItem.getCriterion();
					grid1.fetchData(criteria);
				}
			});

			layoutV.addDrawHandler(new DrawHandler() {
				@Override
				public void onDraw(DrawEvent event) {
					Criterion criteria = rangeItem.getCriterion();
					grid1.fetchData(criteria);
				}
			});

			layoutH.addMember(form);
			layoutH.addMember(bf);
			layoutV.addMember(layoutH);
			layoutV2.addMember(grid1);
			layoutV.addMember(layoutV2);
			initWidget(layoutV);
		}
}
