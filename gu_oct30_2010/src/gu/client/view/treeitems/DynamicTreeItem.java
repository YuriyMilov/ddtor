package gu.client.view.treeitems;

import gu.client.view.DatabaseEditorView;

import com.google.gwt.user.client.ui.TreeItem;

public class DynamicTreeItem extends BaseTreeItem {
	final int STATE_EMPTY=0;
	final int STATE_LOADING=1;
	final int STATE_LOADED=2;
	private int state;
	
	public DynamicTreeItem(String html, DatabaseEditorView view){
		super(html, view);
		setEmpty();
	}
	
	public void addItem( TreeItem item ){
		if( !isLoaded() ){
			state = STATE_LOADED;
			removeItems();
		}
		super.addItem(item);
	}
	
	public boolean isLoaded(){ return state == STATE_LOADED; }
	public boolean isLoading(){ return state == STATE_LOADING; }			
	public boolean isEmpty(){ return state == STATE_EMPTY; }		
	public void setToLoading(){ state = STATE_LOADING; }	
	public void setEmpty(){ 
		removeItems();
		addItem("loading...");
		state = STATE_EMPTY; 
	}
	
}
