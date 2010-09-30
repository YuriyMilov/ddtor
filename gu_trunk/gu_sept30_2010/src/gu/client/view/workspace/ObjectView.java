package gu.client.view.workspace;

import gu.client.ui.TitleCommandBar;
import gu.client.view.DatabaseEditorView;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class ObjectView extends Composite {

	private FlexTable fields = new FlexTable();
	private TitleCommandBar title;
	private final DatabaseEditorView view;
	public ObjectView( final String titleValue, DatabaseEditorView view ){	
		this.view = view;
		VerticalPanel mainPanel = new VerticalPanel();
		initWidget( mainPanel );
		mainPanel.setWidth("100%");
		fields.setWidth("100%");
		title = new TitleCommandBar(titleValue);
		mainPanel.add( title );
		mainPanel.add(fields);
		
		getTitleCommandBar().addCommand("edit", new ClickListener(){ 
			public void onClick( Widget sender ) {
				onEdit();
			}
		});		
		getTitleCommandBar().addCommand("delete", new ClickListener(){ 
			public void onClick( Widget sender ) {
				if( Window.confirm( "Are you sure that you want to delete this object?") ){
					onDelete();
					getView().getTree().getSelectedItem().remove();
				}
			}
		});
	}

	protected void onDelete() {
	}	
	protected void onEdit() {
	}
	
	protected void addField(String name, String value ) {
		int row = fields.getRowCount();
		Label fieldName = new Label( name );
		Label fieldValue = new Label( value );
		fieldName.setStyleName("gwtapps-FieldName");
		fieldValue.setStyleName("gwtapps-FieldValue");
		fields.getCellFormatter().setWidth(row,1,"100%");
		fields.setWidget(row, 0, fieldName);
		fields.setWidget(row, 1, fieldValue);	
		if( row%2 == 0)
			fieldValue.addStyleName("Even");
	}
	
	
	public TitleCommandBar getTitleCommandBar() {
		return title;
	}

	public DatabaseEditorView getView() {
		return view;
	}

}
