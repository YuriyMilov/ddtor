// Copyright (c) 2006 - 2008, Clark & Parsia, LLC. <http://www.clarkparsia.com>
// This source code is available under the terms of the Affero General Public License v3.
//
// Please see LICENSE.txt for full license terms, including the availability of proprietary exceptions.
// Questions, comments, or requests for clarification: licensing@clarkparsia.com

package com.clarkparsia.pellet.tutorial.ui;

import com.hp.hpl.jena.rdf.model.Resource;

public final class LabeledResource implements Comparable<LabeledResource>{
	private Resource resource;
	private String label;
	
	public LabeledResource(Resource r, String label) {
		this.resource = r;
		this.label = label;
	}
	public int compareTo(LabeledResource that) {
		if( this.resource.equals( that.resource ) )
			return 0;
		
		int cmp = this.label.compareToIgnoreCase( that.label );
		return cmp != 0
			? cmp
			: this.resource.toString().compareTo( that.resource.toString() );
			
	}
	
	@Override
	public boolean equals(Object that) {
		if( that instanceof LabeledResource ) {
			return resource.equals( ((LabeledResource) that).resource );
		}
		return false;
	}

	public Resource getResource() {
		return resource;
	}

	@Override
	public int hashCode() {
		return resource.hashCode();
	}

	public String toString() {
		return label;
	}
}