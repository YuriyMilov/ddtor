package gu.client.view;

import gu.client.dao.ObjectFactory;
import gu.client.dao.ObjectFactoryListener;
import gu.client.ui.LoadingPanel;
import gu.client.ui.RoundedPanel;
import gu.client.view.treeitems.BaseTreeItem;
import gu.client.view.treeitems.StoriesTreeItem;
import gu.client.view.treeitems.UsersTreeItem;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalSplitPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.gwt.user.client.ui.TreeListener;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.PopupPanel;

public class DatabaseEditorView extends Composite implements TreeListener, ObjectFactoryListener{

	private Tree treeList = new Tree();
	private HorizontalSplitPanel mainPanel = new HorizontalSplitPanel();
	private ObjectFactory objectFactory;
	private UsersTreeItem userItems = new UsersTreeItem(this);
	private StoriesTreeItem storyItems = new StoriesTreeItem(this);
	private Label loadingLabel = new Label("loading...");
	private LoadingPanel loading = new LoadingPanel(new Label("loading..."));
	
	public DatabaseEditorView(){
		initWidget( mainPanel );
		setStyleName("databaseEditorView");
		RoundedPanel rounded = new RoundedPanel( "#f0f4f8" );
		rounded.setWidget(treeList);
		rounded.setWidth("100%");
		mainPanel.setLeftWidget( rounded );
		mainPanel.setSplitPosition("250px");
		treeList.addItem( storyItems );
		treeList.addItem( userItems );
		treeList.addTreeListener(this);
		RootPanel.get().add( loading);
	}
	
	public void onTreeItemSelected(TreeItem item) {
		if( item instanceof BaseTreeItem ){
			BaseTreeItem eventsItem = (BaseTreeItem)item;
			eventsItem.onTreeItemSelected();
		}
	}

	public void onTreeItemStateChanged(TreeItem item) {		
		if( item instanceof BaseTreeItem ){
			BaseTreeItem eventsItem = (BaseTreeItem)item;
			eventsItem.onTreeItemStateChanged();
		}
	}
	
	public ObjectFactory getObjectFactory() {
		return objectFactory;
	}	
	
	public void setObjectFactory( ObjectFactory objectFactory ) {
		this.objectFactory = objectFactory;
		objectFactory.setListener(this);
	}	

	public void setMainPane(Widget view) {
		mainPanel.setRightWidget( view );
	}

	public void setMainPaneLoading() {
		mainPanel.setRightWidget( loadingLabel );		
	}

	public Tree getTree() {
		return treeList;
	}

	public void onRefresh() {
		BaseTreeItem item = (BaseTreeItem)treeList.getSelectedItem();
		if( item == null ){
			mainPanel.setRightWidget( null );
		}
		else{
			item.onRefresh();
		}
	}
	
	public void onError( String error ){
		PopupPanel popup = new PopupPanel(true);
		popup.setStyleName( "error" );
		popup.setWidget( new HTML(error) );
		popup.show();
		popup.center();
	}

	public void onLoadingFinish() {
		loading.loadingEnd();
		
	}

	public void onLoadingStart() {
		loading.loadingBegin();
	}

	
}
