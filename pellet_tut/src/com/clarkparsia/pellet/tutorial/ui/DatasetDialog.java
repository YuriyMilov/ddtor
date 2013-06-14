// Copyright (c) 2006 - 2008, Clark & Parsia, LLC. <http://www.clarkparsia.com>
// This source code is available under the terms of the Affero General Public
// License v3.
//
// Please see LICENSE.txt for full license terms, including the availability of
// proprietary exceptions.
// Questions, comments, or requests for clarification: licensing@clarkparsia.com

package com.clarkparsia.pellet.tutorial.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Collection;
import java.util.TreeSet;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.hp.hpl.jena.rdf.model.ResourceFactory;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2009
 * </p>
 * <p>
 * Company: Clark & Parsia, LLC. <http://www.clarkparsia.com>
 * </p>
 * 
 * @author Evren Sirin
 */
public class DatasetDialog extends JDialog implements ActionListener, ListSelectionListener {
	private JList				list;
	private JButton				loadButton;
	private JButton				unloadButton;
	private JButton				closeButton;

	private LabeledResource		result;

	public DatasetDialog(Frame owner, Collection<LabeledResource> loadedFiles) {
		super( owner, true );

		initGUI( loadedFiles );
	}

	public void actionPerformed(ActionEvent e) {
		if( e.getActionCommand().equals( "load" ) ) {
			JFileChooser	chooser	= new JFileChooser();
			chooser.setCurrentDirectory( new File( "data" ) );
			int returnVal = chooser.showOpenDialog( this );
			if( returnVal == JFileChooser.APPROVE_OPTION ) {
				File file = chooser.getSelectedFile();
				String label = file.getName();
				String fileURI = file.toURI().toString();
				result = new LabeledResource( ResourceFactory.createResource( fileURI ), label );
			}
		}
		else if( e.getActionCommand().equals( "unload" ) ) {
			result = (LabeledResource) list.getSelectedValue();
		}

		setVisible( false );
	}

	public LabeledResource getResult() {
		return result;
	}

	private void initGUI(Collection<LabeledResource> loadedFiles) {
		list = new JList( new TreeSet<LabeledResource>( loadedFiles ).toArray() );
		list.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );
		list.addListSelectionListener( this );

		JPanel frame = new SimpleInternalFrame( "Datasets" );
		frame.setLayout( new BoxLayout( frame, BoxLayout.Y_AXIS ) );
		JScrollPane scrollPane = new JScrollPane( list );
		scrollPane.setPreferredSize( new Dimension( 280, 240 ) );
		scrollPane.setBorder( null );
		frame.add( scrollPane );

		JPanel listPanel = new JPanel();
		listPanel.setBorder( BorderFactory.createEmptyBorder( 10, 5, 5, 5 ) );
		listPanel.add( frame );
		add( listPanel, BorderLayout.CENTER );

		JPanel buttonPanel = new JPanel();
		buttonPanel.setBorder( BorderFactory.createEmptyBorder( 5, 5, 5, 5 ) );
		buttonPanel.setLayout( new BoxLayout( buttonPanel, BoxLayout.X_AXIS ) );

		loadButton = new JButton( "Load" );
		loadButton.setActionCommand( "load" );
		loadButton.addActionListener( this );

		unloadButton = new JButton( "Unload" );
		unloadButton.setActionCommand( "unload" );
		unloadButton.addActionListener( this );
		unloadButton.setEnabled( false );

		closeButton = new JButton( "Close" );
		closeButton.setActionCommand( "close" );
		closeButton.addActionListener( this );

		buttonPanel.add( loadButton );
		buttonPanel.add( unloadButton );
		buttonPanel.add( Box.createHorizontalGlue() );
		buttonPanel.add( closeButton );

		add( buttonPanel, BorderLayout.SOUTH );

		setSize( 300, 350 );
		setLocationRelativeTo( getOwner() );
	}

	public void valueChanged(ListSelectionEvent e) {
		if( !e.getValueIsAdjusting() ) {
			if( list.getSelectedIndex() == -1 )
				unloadButton.setEnabled( false );
			else
				unloadButton.setEnabled( true );
		}
	}
}
