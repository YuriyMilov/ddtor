// Copyright (c) 2006 - 2008, Clark & Parsia, LLC. <http://www.clarkparsia.com>
// This source code is available under the terms of the Affero General Public
// License v3.
//
// Please see LICENSE.txt for full license terms, including the availability of
// proprietary exceptions.
// Questions, comments, or requests for clarification: licensing@clarkparsia.com

package com.clarkparsia.pellet.tutorial;

import javax.swing.JProgressBar;

import org.mindswap.pellet.utils.progress.AbstractProgressMonitor;

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
public class ClassificationMonitor extends AbstractProgressMonitor {
	private JProgressBar	progressBar;

	public ClassificationMonitor(JProgressBar progressBar) {
		this.progressBar = progressBar;
	}

	@Override
	public void setProgressLength(int progressLength) {
		progressBar.setMaximum( progressLength );
	}

	@Override
	public void setProgressTitle(String title) {
		progressBar.setString( title );
	}

	@Override
	public void taskFinished() {
		progressBar.setVisible( false );
	}

	@Override
	public void taskStarted() {
		progressBar.setVisible( true );
		progressBar.setValue( 0 );
	}

	@Override
	protected void updateProgress() {
		progressBar.setValue( progress );
	}

}
