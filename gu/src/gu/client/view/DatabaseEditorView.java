package gu.client.view;

import gu.client.dao.ObjectFactory;
import gu.client.dao.ObjectFactoryListener;
import gu.client.ui.LoadingPanel;
import gu.client.ui.RoundedPanel;
import gu.client.view.treeitems.BaseTreeItem;
import gu.client.view.treeitems.ShippersTreeItem;
import gu.client.view.treeitems.ConsigneesTreeItem;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.HorizontalSplitPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.gwt.user.client.ui.TreeListener;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.PopupPanel;

@SuppressWarnings("deprecation")
public class DatabaseEditorView extends Composite implements TreeListener, ObjectFactoryListener{

	private Tree treeList = new Tree();
	private HorizontalPanel mainPanel = new HorizontalPanel();
	private VerticalPanel vp1 = new VerticalPanel();
	private DecoratorPanel vp2 = new DecoratorPanel();
	private ObjectFactory objectFactory;
	private ShippersTreeItem shipperItems = new ShippersTreeItem(this);
	private ConsigneesTreeItem consigneeItems = new ConsigneesTreeItem(this);
	private Label loadingLabel = new Label("loading...");
	private LoadingPanel loading = new LoadingPanel(new Label("loading..."));
	
	public DatabaseEditorView(){
		initWidget( mainPanel );
		setStyleName("databaseEditorView");
		RoundedPanel rounded = new RoundedPanel( "#f0f4f8" );
		rounded.setWidget(treeList);
		vp1.add(rounded);
		mainPanel.add(vp1);
		treeList.addItem( shipperItems );
		treeList.addItem( consigneeItems );
		
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
		vp2.clear();
		vp2.removeFromParent();
		vp2.add( view );
		mainPanel.add(vp2);
	}

	public void setMainPaneLoading() {
		vp2.clear();
		vp2.removeFromParent();
		vp2.add(loadingLabel);
		mainPanel.add(vp2);
	}

	public Tree getTree() {
		return treeList;
	}

	public void onRefresh() {
		BaseTreeItem item = (BaseTreeItem)treeList.getSelectedItem();
		if( item == null ){
			vp2.clear();
			vp2.removeFromParent();
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
