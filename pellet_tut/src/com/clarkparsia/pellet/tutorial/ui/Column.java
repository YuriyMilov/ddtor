// Copyright (c) 2006 - 2008, Clark & Parsia, LLC. <http://www.clarkparsia.com>
// This source code is available under the terms of the Affero General Public License v3.
//
// Please see LICENSE.txt for full license terms, including the availability of proprietary exceptions.
// Questions, comments, or requests for clarification: licensing@clarkparsia.com

package com.clarkparsia.pellet.tutorial.ui;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;


public class Column extends SimpleInternalFrame {		
	private JList list;
	private ColumnModel model;
	
	public Column(ColumnModel model) {
		super( model.getLabel() );	
		
		setName( model.getLabel() );
		
		this.model = model;
		
		ListSelectionModel listSelectionModel = new ToggleSelectionModel();
		listSelectionModel.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );
		
		list = new JList( model ) {
			public String getToolTipText(MouseEvent e) {
				int index = locationToIndex( e.getPoint() );
				if( index != -1 ) {
					LabeledResource item = (LabeledResource) getModel().getElementAt( index );
					return item.getResource().toString();
				}
				else {
					return null;
				}
			}
		};
		list.setSelectionModel( listSelectionModel );
		
		JScrollPane scrollPane = new JScrollPane( list );
		scrollPane.setBorder( null );

		this.add( scrollPane );
	}

	public void addTitleMouseListener(MouseListener listener) {
		titleLabel.addMouseListener( listener );
	}
	
	public void addListSelectionListener(ListSelectionListener listener) {
		list.getSelectionModel().addListSelectionListener( listener );
	}
	
	public int getInnerWidth() {
		return list.getWidth();
	}
	
	public Point getPopupLocation() {
		return list.getLocationOnScreen();
	}
	
	public ColumnModel getModel() {
		return model;
	}
	
	public ListSelectionModel getSelectionModel() {
		return list.getSelectionModel();
	}
	
	public int getSelectedIndex() {
		return list.getSelectedIndex();
	}
	
	public void setSelectedIndex(int index) {
		list.setSelectedIndex(index);
	} 
	
	public void removeSelection() {
		int index = list.getSelectedIndex();
		if( index != -1 )
			list.removeSelectionInterval( index, index );
	}
	
	public LabeledResource getSelectedValue() {
		LabeledResource selected = (LabeledResource) list.getSelectedValue();
		
		return selected == null ? null : selected;
	}
	
	public void refresh() {
		model.applyAdditions();
		
		String title = model.getLabel();
		if( model.isFiltered() )
			title += " - " + model.getFilterLabel();
		title += " (" + model.getSize() + ")";
		setTitle( title );
	}
}