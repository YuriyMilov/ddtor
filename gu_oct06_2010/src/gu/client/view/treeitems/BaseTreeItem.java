package gu.client.view.treeitems;

import gu.client.view.DatabaseEditorView;

import com.google.gwt.user.client.ui.TreeItem;

public class BaseTreeItem extends TreeItem {
	private final DatabaseEditorView view;
	public BaseTreeItem(String html, DatabaseEditorView view) {
		super(html);
		this.view = view;
	}
	public DatabaseEditorView getView() {
		return view;
	}
	public boolean isSelected(){
		return getTree().getSelectedItem() == this;
	}

	public void onTreeItemSelected(){}
	public void onTreeItemStateChanged(){}
	public void onRefresh(){}
}
