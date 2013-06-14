package com.clarkparsia.pellet.tutorial;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JSplitPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.mindswap.pellet.PelletOptions;
import org.mindswap.pellet.jena.BuiltinTerm;
import org.mindswap.pellet.jena.PelletInfGraph;
import org.mindswap.pellet.jena.PelletReasonerFactory;

import com.clarkparsia.pellet.sparqldl.jena.SparqlDLExecutionFactory;
import com.clarkparsia.pellet.tutorial.ui.Column;
import com.clarkparsia.pellet.tutorial.ui.ColumnModel;
import com.clarkparsia.pellet.tutorial.ui.DatasetDialog;
import com.clarkparsia.pellet.tutorial.ui.FilterTypeSelector;
import com.clarkparsia.pellet.tutorial.ui.LabeledResource;
import com.hp.hpl.jena.graph.Node;
import com.hp.hpl.jena.graph.Triple;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.QuerySolutionMap;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.ResourceFactory;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.sparql.core.Var;
import com.hp.hpl.jena.sparql.syntax.ElementGroup;
import com.hp.hpl.jena.sparql.syntax.ElementOptional;
import com.hp.hpl.jena.sparql.syntax.ElementTriplesBlock;
import com.hp.hpl.jena.sparql.vocabulary.FOAF;
import com.hp.hpl.jena.util.FileManager;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;
import com.hp.hpl.jena.vocabulary.DC;
import com.hp.hpl.jena.vocabulary.RDF;
import com.hp.hpl.jena.vocabulary.RDFS;

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
public class Demo extends JFrame implements ListSelectionListener, ActionListener {
	private static final String		DATA_DIR	= "data/final/";

	private static final String[]	DATA_FILES	= new String[] {
		"alignment-004.ttl",
		"alignment-401.ttl",
		"alignment-402.ttl",
		"foaf-dl.ttl", 
		"ontology-010.ttl", 
		"data-101.ttl", 
		"doap-data-001.ttl",
		"eswc-2007-complete.rdf",
		"eswc-2008-complete.rdf",
		"eswc-2009-complete.rdf",
		"iswc-2006-complete.rdf",
		"iswc-aswc-2007-complete.rdf",
		"iswc-2008-complete.rdf"
	};

	private static final Query INIT_QUERY = QueryFactory.create( 
		"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
		"SELECT ?instance ?name \n" + 
		"WHERE {\n" + 
		"  ?instance a ?type ;" +
		"            ?labelProperty ?name \n" +
		"}" 
	);
	
	public static void main(String[] theArgs) {
		Demo demo = new Demo();

		demo.setVisible( true );
	}
	
	protected OntModel					pelletModel;

	protected PelletInfGraph			pelletGraph;

	protected List<Column>				columns;

	private Map<LabeledResource, Model>	loadedFiles;

	protected JProgressBar				progressBar;

	private boolean						isUpdating;
	
	public Demo() {
		super( "Pellet Tutorial Demo" );

		initPellet();
		initGUI();
		initData();
		initColumns();
	}
	
	private QueryExecution buildFilterQuery() {

		ElementGroup result = new ElementGroup();		
		QuerySolutionMap params = new QuerySolutionMap();
		
		Node rootVar = Var.alloc( columns.get( 0 ).getModel().getLabel() );
		
		for( Column column : columns ) {
			ColumnModel cm = column.getModel();
			Node var = Var.alloc( cm.getLabel() );
			
			boolean valueSelected = column.getSelectedValue() != null;
			
			ElementTriplesBlock pattern = new ElementTriplesBlock();
			
			if( !cm.isRoot() ) {
				Triple triple = cm.isInverseLink() 
					? Triple.create( var, cm.getLinkProperty().asNode(), rootVar )
					: Triple.create( rootVar, cm.getLinkProperty().asNode(), var );
				pattern.addTriple( triple );
			}
			
			if( valueSelected ) {
				LabeledResource selectedValue = column.getSelectedValue();				
				params.add( var.getName(), selectedValue.getResource() );
			}
			else {
				pattern.addTriple( Triple.create( var, RDF.type.asNode(), cm.getFilterType().asNode() ) );
				pattern.addTriple( Triple.create( var, cm.getLabelProperty().asNode(), Var.alloc( var.getName() + "Label" ) ) );
			}
			
			if( !valueSelected && !cm.isRoot() ) {
				ElementOptional optional = new ElementOptional( pattern );
				result.addElement( optional );
			}
			else {
				result.addElement( pattern );
			}
		}
		
		if( !params.varNames().hasNext() ) {
			return null;
		}		
		
		Query query = QueryFactory.make();
		query.setPrefixMapping( pelletModel );
		query.setQuerySelectType();			
		query.setQueryPattern( result );
		query.setQueryResultStar( true );
		
		System.out.println( query );
		System.out.println( params );

		QueryExecution qe = SparqlDLExecutionFactory.create( query, pelletModel );		
		qe.setInitialBinding( params );
		
		return qe;
	}
	
	private Column createColumn(String name, Resource type, Property labelProperty,
			Property linkProperty, boolean inverseLink) {
		ColumnModel model = new ColumnModel( pelletModel, new LabeledResource( type, name ),
				labelProperty, linkProperty, inverseLink );
		Column column = new Column( model );

		columns.add( column );

		column.addListSelectionListener( this );
		column.addTitleMouseListener( new FilterTypeSelector( this, pelletModel, column ) );

		return column;
	}
	
	public void filterColumns() {	
		if( isUpdating )
			return;
		
		isUpdating = true;

		showProgress( "Filtering" );
		
		QueryExecution qe = buildFilterQuery();
		
		if( qe == null ) {
			isUpdating = false;
			initColumns();
			return;
		}

		for( Column column : columns ) {
			LabeledResource selectedValue = column.getSelectedValue();
			column.getModel().clear();
			if( selectedValue != null ) {
				column.getModel().add( selectedValue );
				column.refresh();
				column.setSelectedIndex( 0 );
			}
		}
		
		ResultSet rs = qe.execSelect();
		while( rs.hasNext() ) {
			QuerySolution soln = rs.next();
			
			for( Column column : columns ) {
				String var = column.getModel().getLabel();
				Literal label = soln.getLiteral( var + "Label" );
				if( label != null ) {
					Resource r = soln.getResource( var );
					column.getModel().add( new LabeledResource( r, label.getString() ) );
				}	
			}
		}
		
		for( Column column : columns ) {
			column.refresh();
		}
		
		isUpdating = false;
		
		hideProgress();
	}

	private void hideProgress() {
		progressBar.setIndeterminate( false );
		progressBar.setVisible( false );
	}
	
	private void initColumn(Column column) {
		System.out.println( "Initializing column: " + column.getName() );
		
		column.removeSelection();
		
		ColumnModel model = column.getModel();
		model.clear();
		
		QuerySolutionMap params = new QuerySolutionMap();
		params.add( "type", column.getModel().getFilterType() );	
		params.add( "labelProperty", column.getModel().getLabelProperty() );
		
		QueryExecution qe = SparqlDLExecutionFactory.create( INIT_QUERY, pelletModel );		
		qe.setInitialBinding( params );

		ResultSet rs = qe.execSelect();
		while( rs.hasNext() ) {
			QuerySolution soln = rs.next();
			Resource r = soln.getResource( "instance" ) ;
			Literal labelNode = soln.getLiteral( "name" );
			String label = labelNode.getString();
			if( label.trim().length() == 0 )
				continue;
			model.add( new LabeledResource( r, label ) );
		}
		
		column.refresh();	

		System.out.println( "Column: " + column.getName() );
		System.out.println( params );
		System.out.println( "Rows: " + rs.getRowNumber() + " Distinct: " + model.getSize());
	}
	
	private void initColumns() {
		if( isUpdating )
			return;
		
		isUpdating = true;
		
		showProgress( "Initializing columns" );
		
		final AtomicInteger runningThreads = new AtomicInteger( 0 );
		for( final Column column : columns ) {
			new Thread() {
				public void run() {
					runningThreads.incrementAndGet();
					initColumn( column );
					if( runningThreads.decrementAndGet() == 0 ) {
						hideProgress();
						
						isUpdating = false;
					}
				}
			}.start();
		}
	}
	
	private void initGUI() {
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		setSize( 800, 600 );
		setLocationRelativeTo( null );
		
		columns = new ArrayList<Column>();

		Column peopleColumn = createColumn( "Person", FOAF.Person, FOAF.name, null, false );
		Column projectColumn = createColumn( "Project", FOAF.Project, RDFS.label, FOAF.currentProject, false );
		Column organizationColumn = createColumn( "Organization", FOAF.Organization, RDFS.label, FOAF.member, true );
		Column publicationColumn = createColumn( "Publication", FOAF.Document, DC.title, FOAF.made, false );

		JSplitPane aLeftPanel = new JSplitPane( JSplitPane.HORIZONTAL_SPLIT, organizationColumn,
				projectColumn );
		aLeftPanel.setBorder( BorderFactory.createEmptyBorder( 0, 0, 0, 0 ) );
		aLeftPanel.setResizeWeight( .5 );
		aLeftPanel.setDividerLocation( (int) (.33 * getWidth()) );

		JSplitPane aTopPane = new JSplitPane( JSplitPane.HORIZONTAL_SPLIT, aLeftPanel,
				peopleColumn );
		aTopPane.setBorder( BorderFactory.createEmptyBorder( 0, 0, 0, 0 ) );

		aTopPane.setResizeWeight( .66 );
		aTopPane.setDividerLocation( (int) (.66 * getWidth()) );

		JSplitPane aSplitPane = new JSplitPane( JSplitPane.VERTICAL_SPLIT, aTopPane,
				publicationColumn );
		aSplitPane.setBorder( BorderFactory.createEmptyBorder( 10, 10, 0, 10 ) );

		aSplitPane.setResizeWeight( .55 );
		aSplitPane.setDividerLocation( (int) (.55 * getHeight()) );

		JPanel aContentPane = new JPanel();
		aContentPane.setLayout( new BorderLayout() );

		aContentPane.add( aSplitPane, BorderLayout.CENTER );

		JPanel aBottomPanel = new JPanel();
		aBottomPanel.setLayout( new BorderLayout() );
		aBottomPanel.setBorder( BorderFactory.createEmptyBorder( 5, 10, 5, 15 ) );

		progressBar = new JProgressBar( JProgressBar.HORIZONTAL );
		progressBar.setBorder( BorderFactory.createEmptyBorder( 0, 0, 0, 10 ) );
		aBottomPanel.add( progressBar, BorderLayout.CENTER );

		JLabel copyright = new JLabel( "<html>&copy; Copyright 2009 Clark &amp; Parsia, LLC</html>" );
		aBottomPanel.add( copyright, BorderLayout.EAST );

		JButton loadDataButton = new JButton( "Datasets" );
		loadDataButton.setActionCommand( "datasets" );
		loadDataButton.addActionListener( this );
		aBottomPanel.add( loadDataButton, BorderLayout.WEST );

		aContentPane.add( aBottomPanel, BorderLayout.SOUTH );

		setContentPane( aContentPane );
		
		setVisible( true );
	}
	
	private void initPellet() {
		PelletOptions.USE_TRACING = true;
		PelletOptions.USE_ANNOTATION_SUPPORT = true;
		PelletOptions.SAMPLING_RATIO = 0;

		// create an inferencing model using the raw model
		pelletModel = ModelFactory.createOntologyModel( PelletReasonerFactory.THE_SPEC );

		pelletGraph = (PelletInfGraph) pelletModel.getGraph();
	}
	
	private void initData() {
		progressBar.setMaximum( 0 );
		progressBar.setMaximum( DATA_FILES.length );
		progressBar.setValue( 0 );
		progressBar.setStringPainted( true );

		loadedFiles = new HashMap<LabeledResource,Model>();
		for( int i = 0; i < DATA_FILES.length; ) {
			progressBar.setString( "Parsing: " + DATA_FILES[i] );
			String fileURI = new File( DATA_DIR + DATA_FILES[i] ).toURI().toString();
			Model subModel = FileManager.get().loadModel( fileURI );
			pelletModel.addSubModel( subModel );
			loadedFiles.put( new LabeledResource( ResourceFactory.createResource( fileURI ), DATA_FILES[i] ), subModel );
			progressBar.setValue( ++i );
		}
	
		classify();
	}
	
	private void classify() {
		checkAndResolveInconsistency();
		
		ClassificationMonitor progressMonitor = new ClassificationMonitor( progressBar );
		pelletGraph.getKB().getTaxonomyBuilder().setProgressMonitor( progressMonitor );

		pelletGraph.classify();
	}
	
	private void checkAndResolveInconsistency() {
		showProgress( "Consistency checking" );
		
		// continue until all inconsistencies are resolved
		while( !pelletGraph.isConsistent() ) {
			// get the explanation for current inconsistency
			Model explanation = pelletGraph.explainInconsistency();
			
			System.out.println( "Data is inconsistent:" );
			explanation.setNsPrefixes( pelletModel );
			explanation.write( System.out, "TTL" );
			
			// iterate over the axioms in the explanation
			for( Statement stmt : explanation.listStatements().toList() ) {
				// remove any individual assertion that contributes
				// to the inconsistency (assumption: all the axioms
				// in the schema are believed to be correct and
				// should not be removed)
				if( isIndividualAssertion( stmt ) ) {
					System.out.println( "Remove statement: "
							+ stmt.asTriple().toString( pelletModel ) );
					ExtendedIterator<OntModel> subModels = pelletModel.listSubModels();
					while( subModels.hasNext() ) {
						subModels.next().remove( stmt );
					}
				}
			}
			
			// The following statement is needed to workaround
			pelletGraph.getLoader().setKB( pelletGraph.getKB() );
			
			pelletModel.rebind();
		}
		
		hideProgress();
	}

	private boolean isIndividualAssertion(Statement stmt) {
		Property p = stmt.getPredicate();
		RDFNode o = stmt.getResource();

		return !isBuiltinTerm( p )
			|| !isBuiltinTerm( o )  && p.equals( RDF.type );
	}
	
	private boolean isBuiltinTerm(RDFNode r) {
		return BuiltinTerm.find( r.asNode() ) != null;
	}
	
	private void showProgress(String title) {
		progressBar.setIndeterminate( true );
		progressBar.setString( title );
		progressBar.setVisible( true );
	}
	
	public void valueChanged(ListSelectionEvent e) {
		if( !e.getValueIsAdjusting() ) {
			filterColumns();
		}
	}
	
	public void actionPerformed(ActionEvent e) {		
		DatasetDialog dialog = new DatasetDialog(this, loadedFiles.keySet());
		dialog.setVisible( true );
		
		final LabeledResource result = dialog.getResult();
		if( result == null ) {
			return;
		}
		
		new Thread() {
			public void run() {
				if( loadedFiles.containsKey( result ) ) {
					showProgress( "Removing dataset: " + result );
					
					Model model = loadedFiles.remove( result );
					pelletModel.removeSubModel( model );
				}
				else {
					showProgress( "Adding dataset: " + result );

					String fileURI = result.getResource().toString();
					Model subModel = FileManager.get().loadModel( fileURI );
					loadedFiles.put( new LabeledResource(
							ResourceFactory.createResource( fileURI ), result.toString() ),
							subModel );
					
					pelletModel.addSubModel( subModel );
				}
					
				classify();
				
				filterColumns();
			}
		}.start();
	}

}
