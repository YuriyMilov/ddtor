$Id: README.txt 61 2009-10-26 03:04:39Z evren $ 


Ontology development
===

ontology-dev/ontology-001.ttl
+ Boiler plate with ontology name

ontology-dev/ontology-002.ttl
+ Named classes and properties

ontology-dev/ontology-003.ttl
+ Add class and property relations
- An untyped property is in a restriction
  . Problem is visible from classify (a bnode as class) and identified
    using lint -o RDF

ontology-dev/ontology-004.ttl
+ Add property assertion missing in -003

ontology-dev/ontology-005.ttl
+ A person and a project added
  . Demonstrate realization to show person in hierarchy

ontology-dev/ontology-006.ttl
+ Two inconsistencies introduced
  . Identify problem using realize, then use explain (with -x 0)

ontology-dev/ontology-007.ttl
+ Inconsistent abox assertions removed

ontology-dev/ontology-008.ttl
+ Unsatisfiable class introduced
  . Identify using classify or unsat, then use explain (with --unsat)

ontology-dev/ontology-009.ttl
+ Removed unsatisfiable class
+ Use employeeId ranges to classify as civil servant or contractor

ontology-dev/ontology-010.ttl
+ EmployeeId a key
+ Name datatype properties
+ Project hierarchy (transitive subProjectOf)
  . Demonstrate trans-tree
+ Property chain axiom ( worksOnProject subProjectOf )
+ No individuals (use data-*.ttl)

ontology-dev/query-221.rq
+ Query to demonstrate property chain axiom in ontology-010.ttl

ontology-dev/data-010.ttl
+ Individual for use with ontology-010.ttl


Integrity Constraint Evaluation
===

constraints/ontology-107.ttl
constraints/constraints-107.ttl
+ Data range constraint on employee ids

constraints/ontology-108.ttl
constraints/constraints-108.ttl
+ Range constraint on employee and organization relationship

constraints/ontology-109.ttl
constraints/constraints-109.ttl
+ Functional relationship between organization and manager


Query Evaluation
===

These queries can be run with any of ontology-00[5789].ttl

sparql/query-20[123].rq
+ Match querying via ontology API queries

sparql/query-211.rq
+ Matches SPARQL query example slide

sparql/query-212.rq
+ Matches SPARQL-DL query example slide


Ontology Alignment
===

alignment/alignment-001.ttl
+ Aligns ontology-010.ttl with foaf-dl.ttl

alignment/alignment-002.ttl
+ Enhances previous alignment using a SWRL rules

alignment/query-301.rq
+ Query to demonstrate impact of data munging in alignment-002.ttl

alignment/alignment-003.ttl
+ Uses SWRL IndividualPropertyAtom for project alignment

alignment/alignment-004.ttl
+ Uses OWL 2 property chain axiom for project alignment


Application Data
===

final/data-101.ttl
+ Enterprise person and project data

final/doap-data-001.ttl
+ Doap data that links some foaf people to projects

final/iswc-2006-complete.ttl
final/iswc-aswc-2007-complete.ttl
final/iswc-2008-complete.ttl
+ Foaf people, organizations, and documents

final/alignment-401.ttl
+ Aligns publication data to foaf

final/alignment-402.ttl
+ Aligns doap data to foaf

final/query-401.rq
+ Query across POPS and OWLED data using FOAF vocabulary
