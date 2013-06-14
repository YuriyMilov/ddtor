// Copyright (c) 2006 - 2008, Clark & Parsia, LLC. <http://www.clarkparsia.com>
// This source code is available under the terms of the Affero General Public License v3.
//
// Please see LICENSE.txt for full license terms, including the availability of proprietary exceptions.
// Questions, comments, or requests for clarification: licensing@clarkparsia.com

package com.clarkparsia.pellet.tutorial.ui;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTree;
import javax.swing.Popup;
import javax.swing.PopupFactory;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import com.clarkparsia.pellet.tutorial.ClassTreeBuilder;
import com.clarkparsia.pellet.tutorial.Demo;
import com.hp.hpl.jena.rdf.model.Model;

public class FilterTypeSelector extends MouseAdapter implements TreeSelectionListener {
	private Demo	demo;
	private Model 	pellet;
	private Column	column;
	private Popup	popup;
	private JTree	tree;

	public FilterTypeSelector(Demo demo, Model pellet, Column column) {
		this.demo = demo;
		this.pellet = pellet;
		this.column = column;
	}

	public JTree getTree() {
		if( tree == null ) {
			tree = ClassTreeBuilder.createTree( pellet, column.getModel().getType() );
			tree.addTreeSelectionListener( this );
		}
		return tree;
	}

	public void hidePopup() {
		popup.hide();
		popup = null;
	}

	public void mouseClicked(MouseEvent e) {
		if( popup == null ) {
			showPopup();
		}
		else {
			hidePopup();
		}
	}

	public void showPopup() {
		JTree tree = getTree();
		tree.setPreferredSize( new Dimension( column.getWidth() - 5, tree.getPreferredSize().height ) );
		Point p = column.getPopupLocation();
		popup = PopupFactory.getSharedInstance().getPopup( column, tree, p.x, p.y );
		popup.show();
	}

	public void valueChanged(TreeSelectionEvent e) {
		hidePopup();

		LabeledResource filterType = (LabeledResource) ((DefaultMutableTreeNode) e.getPath()
				.getLastPathComponent()).getUserObject();

		column.getModel().setFilterType( filterType );
		
		demo.filterColumns();
	}
}