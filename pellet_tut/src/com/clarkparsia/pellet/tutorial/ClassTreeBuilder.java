// Copyright (c) 2006 - 2008, Clark & Parsia, LLC. <http://www.clarkparsia.com>
// This source code is available under the terms of the Affero General Public
// License v3.
//
// Please see LICENSE.txt for full license terms, including the availability of
// proprietary exceptions.
// Questions, comments, or requests for clarification: licensing@clarkparsia.com

package com.clarkparsia.pellet.tutorial;

import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;

import com.clarkparsia.pellet.tutorial.ui.LabeledResource;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.OWL;
import com.hp.hpl.jena.vocabulary.ReasonerVocabulary;

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
public class ClassTreeBuilder {

	public static JTree createTree(Model pelletModel, Resource cls) {
		String label = pelletModel.qnameFor( cls.getURI() );
		LabeledResource resource = new LabeledResource( cls, label );

		DefaultMutableTreeNode root = createNode( pelletModel, resource );

		// create the tree
		JTree classTree = new JTree( new DefaultTreeModel( root ) );

		ImageIcon icon = new ImageIcon( Demo.class.getResource( "images/class.gif" ) );

		DefaultTreeCellRenderer renderer = (DefaultTreeCellRenderer) classTree.getCellRenderer();

		// Remove the icons
		renderer.setLeafIcon( icon );
		renderer.setClosedIcon( icon );
		renderer.setOpenIcon( icon );

		// expand everything
		for( int r = 0; r < classTree.getRowCount(); r++ )
			classTree.expandRow( r );

		return classTree;
	}

	private static DefaultMutableTreeNode createNode(Model pelletModel,
			LabeledResource labeledResource) {
		DefaultMutableTreeNode node = new DefaultMutableTreeNode( labeledResource );

		Resource concept = labeledResource.getResource();
		Iterator<Resource> subClasses = pelletModel.listSubjectsWithProperty(
				ReasonerVocabulary.directSubClassOf, concept );

		SortedSet<LabeledResource> sortedSubClasses = new TreeSet<LabeledResource>();
		while( subClasses.hasNext() ) {
			Resource subClass = subClasses.next();

			if( subClass.equals( OWL.Nothing ) )
				continue;

			String label = pelletModel.qnameFor( subClass.getURI() );

			sortedSubClasses.add( new LabeledResource( subClass, label ) );
		}

		for( LabeledResource subClass : sortedSubClasses ) {
			DefaultMutableTreeNode child = createNode( pelletModel, subClass );
			node.add( child );
		}

		return node;
	}

}
