// Copyright (c) 2006 - 2008, Clark & Parsia, LLC. <http://www.clarkparsia.com>
// This source code is available under the terms of the Affero General Public
// License v3.
//
// Please see LICENSE.txt for full license terms, including the availability of
// proprietary exceptions.
// Questions, comments, or requests for clarification: licensing@clarkparsia.com

package com.clarkparsia.pellet.tutorial.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.swing.AbstractListModel;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.OWL;

public class ColumnModel extends AbstractListModel {
	private LabeledResource				type;
	private LabeledResource				filterType;
	private Property					labelProperty;
	private Property					linkProperty;
	private boolean						inverseLink;

	private ArrayList<LabeledResource>	list;
	private SortedSet<LabeledResource>	set;
	private Map<String, List<Resource>>	labels;

	private Model						pelletModel;

	public ColumnModel(Model pelletModel, LabeledResource type, Property labelProperty, Property linkProperty,
			boolean inverseLink) {
		this.pelletModel = pelletModel;
		this.type = type;
		this.labelProperty = labelProperty;
		this.linkProperty = linkProperty;
		this.inverseLink = inverseLink;

		list = new ArrayList<LabeledResource>();
		set = new TreeSet<LabeledResource>();
		labels = new HashMap<String, List<Resource>>();

		filterType = null;
	}

	public void add(LabeledResource element) {
		List<Resource> resources = labels.get( element.toString() );
		if( resources == null ) {
			resources = new ArrayList<Resource>();
			labels.put( element.toString(), resources );
		}
		else {
			for( Resource resource : resources ) {
				if( pelletModel.contains( resource, OWL.sameAs, element.getResource() ) ) {
					return;
				}
			}
		}

		resources.add( element.getResource() );
		set.add( element );
	}

	public void clear() {
		int index = list.size() - 1;
		list.clear();
		set.clear();
		labels.clear();
		if( index >= 0 ) {
			fireIntervalRemoved( this, 0, index );
		}
	}

	public Object getElementAt(int index) {
		return list.get( index );
	}

	public boolean isFiltered() {
		return filterType != null;
	}

	public Resource getFilterType() {
		return filterType != null
			? filterType.getResource()
			: type.getResource();
	}

	public String getFilterLabel() {
		return filterType != null
			? filterType.toString()
			: null;
	}

	public Property getLabelProperty() {
		return labelProperty;
	}

	public Property getLinkProperty() {
		return linkProperty;
	}

	public String getLabel() {
		return type.toString();
	}

	public int getSize() {
		return list.size();
	}

	public Resource getType() {
		return type.getResource();
	}

	public boolean isInverseLink() {
		return inverseLink;
	}

	public boolean isRoot() {
		return linkProperty == null;
	}

	public void setFilterType(LabeledResource filterType) {
		if( filterType.getResource().equals( getType() ) )
			this.filterType = null;
		else
			this.filterType = filterType;
	}

	public void applyAdditions() {
		if( list.isEmpty() ) {
			list = new ArrayList<LabeledResource>( set );

			fireIntervalAdded( this, 0, list.size() );
		}
	}

}