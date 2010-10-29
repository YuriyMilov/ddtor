package gu.client.view.workspace;

import gu.client.ui.TitleCommandBar;
import gu.client.view.DatabaseEditorView;

import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class ListView extends Composite {
	private final DatabaseEditorView view;
	private TitleCommandBar title;
	private FlexTable rows = new FlexTable();
	public ListView( final String titleValue, DatabaseEditorView view ){
		this( titleValue, view, "create" );
	}
	public ListView( final String titleValue, DatabaseEditorView view, String createLabel ){
		
		this.view = view;

		VerticalPanel mainPanel = new VerticalPanel();
		initWidget( mainPanel );
		mainPanel.setWidth("100%");
		rows.setWidth("100%");
		title = new TitleCommandBar(titleValue);
		mainPanel.add( title );
		mainPanel.add(rows);
		rows.getRowFormatter().setStyleName(0, "gwtapps-ListHeaderRow");
		getTitleCommandBar().addCommand(createLabel, new ClickListener(){ 
			public void onClick( Widget sender ) {
				onCreate();
			}
		});
	}
	private TitleCommandBar getTitleCommandBar() {
		return title;
	}
	public DatabaseEditorView getView() {
		return view;
	}
	protected void addColumn( String name ){
		int column = rows.getRowCount()>0?rows.getCellCount(0):0;
		rows.setWidget(0, column, new Label(name));
	}
	protected void addField( int row, String value ){
		row = row + 1;
		int column = 0;
		if( rows.getRowCount()==row ){
			if( row%2 == 0)
				rows.getRowFormatter().setStyleName(row,"Even");
		}
		else{
			column = rows.getCellCount(row);
		}
		Label fieldValue = new Label(value);
		fieldValue.setStyleName("gwtapps-FieldValue");
		rows.setWidget(row, column, fieldValue);
		
	}
	protected void onCreate(){}
}
