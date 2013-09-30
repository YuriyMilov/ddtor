package guestbook;

import static org.semanticweb.owlapi.vocab.OWLFacet.MAX_EXCLUSIVE;
import static org.semanticweb.owlapi.vocab.OWLFacet.MIN_INCLUSIVE;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.channels.Channels;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.TimeZone;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;
import javax.mail.util.ByteArrayDataSource;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsoup.Jsoup;
import org.mindswap.pellet.jena.PelletReasonerFactory;
import org.semanticweb.owlapi.model.AddAxiom;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLClassAxiom;
import org.semanticweb.owlapi.model.OWLClassExpression;
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLDataRange;
import org.semanticweb.owlapi.model.OWLDataSomeValuesFrom;
import org.semanticweb.owlapi.model.OWLDatatype;
import org.semanticweb.owlapi.model.OWLDifferentIndividualsAxiom;
import org.semanticweb.owlapi.model.OWLDisjointClassesAxiom;
import org.semanticweb.owlapi.model.OWLEquivalentClassesAxiom;
import org.semanticweb.owlapi.model.OWLFacetRestriction;
import org.semanticweb.owlapi.model.OWLIndividual;
import org.semanticweb.owlapi.model.OWLObjectAllValuesFrom;
import org.semanticweb.owlapi.model.OWLObjectHasValue;
import org.semanticweb.owlapi.model.OWLObjectIntersectionOf;
import org.semanticweb.owlapi.model.OWLObjectOneOf;
import org.semanticweb.owlapi.model.OWLObjectProperty;

import para.st;

import com.clarkparsia.pellet.sparqldl.jena.SparqlDLExecutionFactory;
import com.google.appengine.api.blobstore.BlobInfo;
import com.google.appengine.api.blobstore.BlobInfoFactory;
import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.files.AppEngineFile;
import com.google.appengine.api.files.FileService;
import com.google.appengine.api.files.FileServiceFactory;
import com.google.appengine.api.files.FileWriteChannel;
import com.google.gwt.core.client.EntryPoint;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.ModelFactory;

public class stq {

	//private static Object String;
public static String sfx="";
	public static void init(HttpServletRequest req, HttpServletResponse resp) {
		stat.stop = "";
		stat.sr = "";	
		String sh = req.getScheme() + "://" + req.getServerName() + ":"
				+ req.getServerPort() + req.getContextPath();

		add_sr("", sh);
		stat.page(req, resp, "");
	}
	
	public static String srowl(String s, String sh,String sf) {
		try {
			sf="84";
			Owl2Model qw = new Owl2Model(sh + "/rff?"+sf);

			s = stat.prep_all(s);
			s = stat.prepare_83(s);
			
			/////////////
			

			String[] sss = s.split("[.]+");

			for (int i = 0; i < sss.length; i++) {

				String[] ss = sss[i].trim().split("[ ]+");

				String[] sss2 = s.split("[.]+");

				// проверка свойств на симметричность и т.п.

				for (int i4 = 0; i4 < sss2.length; i4++) {
					String[] ss4 = sss2[i4].trim().split("[ ]+");

					if (ss4[0].toLowerCase().equals("если")) {

						// ///////////
						s = s.replace(sss2[i4], "").trim();
						if (s.indexOf(".") == 0)
							s = s.substring(1).trim();

						sfx = "/сим";

						s = s.replace(ss4[2], ss4[2] + sfx);
						// ////////////

					}

				}
				sss2 = s.split("[.]+");
				// /////////////////////////////

				for (int i2 = 0; i2 < sss2.length; i2++) {
					String[] ss2 = sss2[i2].trim().split("[ ]+");

					if (ss2.length == 4) {
						qw.учитель_учит_N_учеников(qw.getOwlClass(ss2[0]),
								qw.getProperty(ss2[1].replace(sfx, "")),
								Integer.parseInt(ss2[2]),
								qw.getOwlClass(ss2[3]));

					}

					if (ss2.length == 1) {
						if (bim(ss2[0])) {
							qw.manager.applyChange(new AddAxiom(qw.ontology,
									qw.factory.getOWLSameIndividualAxiom(
											qw.getIndividual(ss2[0]),
											qw.getIndividual(ss2[0]))));
						} else if (!bim(ss2[0])) {
							qw.isSubClassOf(qw.getOwlClass(ss2[0]),
									qw.factory.getOWLThing());
						}
					}

					if (ss2.length == 2) {

						if (bim(ss2[0]) && ss2[0].indexOf("_") > -1
								&& !bim(ss2[1]))

						{
							String sss3 = ss2[0].replace("_", " ");
							String[] ss3 = sss3.split("[ ]+");

							Set<OWLIndividual> inds = new HashSet<OWLIndividual>();
							for (int i3 = 0; i3 < ss3.length; i3++)
								inds.add(qw.getIndividual(ss3[i3]));

							OWLClassAxiom аксиома = qw.factory
									.getOWLEquivalentClassesAxiom(
											qw.getOwlClass(ss2[1]),
											qw.factory.getOWLObjectOneOf(inds));
							qw.manager.addAxiom(qw.ontology, аксиома);

							qw.manager.addAxiom(qw.ontology, qw.factory
									.getOWLDifferentIndividualsAxiom(inds));
						}

						else

						if (bim(ss2[0]) && !bim(ss2[1])) {
							qw.hasClass(qw.getIndividual(ss2[0]),
									qw.getOwlClass(ss2[1]));
						} else if (!bim(ss2[0]) && !bim(ss2[1])) {
							qw.isSubClassOf(qw.getOwlClass(ss2[0]),
									qw.getOwlClass(ss2[1]));
						} else if (bim(ss2[0]) && bim(ss2[1])) {
							qw.manager.applyChange(new AddAxiom(qw.ontology,
									qw.factory.getOWLSameIndividualAxiom(
											qw.getIndividual(ss2[0]),
											qw.getIndividual(ss2[1]))));
						} else if (!bim(ss2[0]) && bim(ss2[1])) {

							qw.hasClass(qw.getIndividual(ss2[1]),
									qw.getOwlClass(ss2[0]));
						}
					}

					if (ss2.length == 3) {

						if (ss2[1].indexOf("/сим") > -1) {
							// ss2[1]=ss2[1].replace("/сим","");
							qw.isSymmetric(qw.getProperty(ss2[1].replace(sfx,
									"")));

						}

						if (bim(ss2[0]) && bim(ss2[2])
								&& ss2[1].equals("не")) {
							qw.differentIndividuals(qw.getIndividual(ss2[0]),
									qw.getIndividual(ss2[2]));
						} else if (!bim(ss2[0]) && !bim(ss2[2])
								&& ss2[1].equals("не")) {
							qw.manager.applyChange(new AddAxiom(qw.ontology,
									qw.factory.getOWLDisjointClassesAxiom(
											qw.getOwlClass(ss2[0]),
											qw.getOwlClass(ss2[2]))));

						} else if (bim(ss2[0]) && n(ss2[2])) {

							OWLDataProperty hasAge = qw.getDataProperty(ss2[1]
									.replace(sfx, ""));
							String sn = ss2[2].trim();
							int n = Integer.parseInt(sn);
							OWLIndividual ind = qw.getIndividual(ss2[0]);

							qw.assertFact(hasAge, ind, n);

						} else

						if (bim(ss2[0]) && bim(ss2[2])) {

							qw.assertFact(ss2[1].replace(sfx, ""), ss2[0],
									ss2[2]);

						} else if (!bim(ss2[0]) && !bim(ss2[2])) {
							qw.assertDomainAndRange(
									qw.getProperty(ss2[1].replace(sfx, "")),
									qw.getOwlClass(ss2[0]),
									qw.getOwlClass(ss2[2]));
						}
						else if (!bim(ss2[0]) && bim(ss2[2])) {
							OWLDataProperty любит = qw.getDataProperty(ss2[1]
									.replace(sfx, ""));
							String sn = ss2[2].trim();
							//int n = Integer.parseInt(sn);
							OWLIndividual Молоко = qw.getIndividual(ss2[0]);

							qw.assertFact(любит, Молоко, 1);
					}

					}
				}
				stat.sowl = qw.sowl();

				if (s.length() > -1)
					return s;

				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				// /////////////////////////////////////////
				// /////////////////////////////////////////
				// /////////////////////////////////////////

				if (ss.length == 3) {

					if (ss[1].equals("-")) {
						qw.isSubClassOf(qw.getOwlClass(ss[0]),
								qw.getOwlClass(ss[2]));
					} else if (ss[1].equals("это")) {
						qw.hasClass(qw.getIndividual(ss[0]),
								qw.getOwlClass(ss[2]));
					}

					else if (bim(ss[0]) && n(ss[2])) {

						OWLDataProperty hasAge = qw.getDataProperty(ss[1]);
						String sn = ss[2].trim();
						int n = Integer.parseInt(sn);
						OWLIndividual ind = qw.getIndividual(ss[0]);

						qw.assertFact(hasAge, ind, n);

					}

					else if (bim(ss[0]) && bim(ss[2])) {

						qw.assertFact(ss[1], ss[0], ss[2]);

					}

					else if (!bim(ss[0]) && !bim(ss[2])) {
						// qw.assertDomainAndRange(qw.getProperty(ss[1]),
						// qw.getOwlClass(ss[0]), qw.getOwlClass(ss[2]));
						qw.assertFact(ss[1], ss[0], ss[2]);
					}

				}

				// else if (socrat(ss[0]) && !socrat(ss[2])) {
				// qw.hasClass(qw.getIndividual(ss[0]), qw.getOwlClass(ss[2]));
				// qw.assertRange(qw.getProperty(ss[1]), qw.getOwlClass(ss[2]));
				// }

			}

			// //////////////////////////////////

			OWLIndividual john = qw.getIndividual("John");
			OWLIndividual mary = qw.getIndividual("Mary");
			OWLIndividual susan = qw.getIndividual("Susan");
			OWLIndividual bill = qw.getIndividual("Bill");
			OWLIndividual david = qw.getIndividual("David");
			OWLIndividual kate = qw.getIndividual("Kate");
			OWLDataProperty hasAge = qw.getDataProperty("hasAge");
			OWLObjectProperty hasSon = qw.getProperty("hasSon");
			OWLObjectProperty hasDaughter = qw.getProperty("hasDaughter");
			OWLObjectProperty hasWife = qw.getProperty("hasWife");
			OWLObjectProperty hasHusband = qw.getProperty("hasHusband");
			OWLObjectProperty hasChild = qw.getProperty("hasChild");
			OWLIndividual male = qw.getIndividual("male");
			OWLIndividual female = qw.getIndividual("female");
			OWLObjectProperty hasGender = qw.getProperty("hasGender");
			OWLClass man = qw.getOwlClass("man");
			OWLClass woman = qw.getOwlClass("woman");
			// OWLClass parent = qw.getOwlClass("parent");
			OWLClass person = qw.getOwlClass("person");

			qw.assertDomain(hasWife, person);
			qw.assertRange(hasWife, person);
			qw.assertDomain(hasSon, person);
			qw.assertRange(hasSon, person);
			qw.assertDomain(hasDaughter, person);
			qw.assertRange(hasDaughter, person);
			qw.assertDataDomain(hasAge, person);
			qw.assertRangeAsInteger(hasAge);
			qw.hasClass(david, person);
			qw.hasClass(bill, person);
			qw.hasClass(kate, person);
			qw.hasClass(mary, person);
			qw.hasClass(susan, person);
			qw.hasClass(john, person);
			qw.inverseProperties(hasWife, hasHusband);
			qw.subPropertyOf(hasSon, hasChild);
			qw.subPropertyOf(hasDaughter, hasChild);
			qw.isFunctional(hasAge);
			qw.isFunctional(hasWife);
			qw.isIrreflexive(hasWife);
			qw.isInverseFunctional(hasWife);
			qw.isAsymmetric(hasWife);

			OWLDataProperty hasGender2 = qw.getDataProperty("hasGender2");
			OWLClassExpression hasAgeRestriction = qw.exactCardinality(hasAge,
					1);
			OWLClassExpression hasGenderRestriction = qw.exactCardinality(
					hasGender2, 1);
			OWLObjectOneOf maleOrFemale = qw.factory.getOWLObjectOneOf(male,
					female);
			OWLObjectAllValuesFrom hasGenderOnlyMaleFemale = qw.factory
					.getOWLObjectAllValuesFrom(hasGender, maleOrFemale);

			// Finally, we bundle these restrictions up into an
			// intersection, since we want person
			// to be a subclass of the intersection of them
			OWLObjectIntersectionOf intersection = qw.factory
					.getOWLObjectIntersectionOf(hasAgeRestriction,
							hasGenderRestriction, hasGenderOnlyMaleFemale);
			// And now we set this anonymous intersection class to be a
			// superclass of Person using a subclass axiom
			qw.manager.addAxiom(qw.ontology,
					qw.factory.getOWLSubClassOfAxiom(person, intersection));

			// Restrictions and other anonymous classes can also be used
			// anywhere a named class can be used.
			// Let's set the range of hasSon to be Person and hasGender
			// value male. This requires an anonymous
			// class that is the intersection of Person, and also, hasGender
			// value male. We need to create
			// the hasGender value male restriction - this describes the
			// class of things that have a hasGender
			// relationship to the individual male.
			OWLObjectHasValue hasGenderValueMaleRestriction = qw.factory
					.getOWLObjectHasValue(hasGender, male);
			// Now combine this with Person in an intersection
			OWLClassExpression personAndHasGenderValueMale = qw.factory
					.getOWLObjectIntersectionOf(person,
							hasGenderValueMaleRestriction);
			// Now specify this anonymous class as the range of hasSon using
			// an object property range axioms
			qw.manager.addAxiom(qw.ontology, qw.factory
					.getOWLObjectPropertyRangeAxiom(hasSon,
							personAndHasGenderValueMale));

			// We can do a similar thing for hasDaughter, by specifying that
			// hasDaughter has a range
			// of Person and hasGender value female. This time, we will make
			// things a little more compact by
			// not using so many variables

			OWLClassExpression rangeOfHasDaughter = qw.factory
					.getOWLObjectIntersectionOf(person,
							qw.factory.getOWLObjectHasValue(hasGender, female));
			qw.manager.addAxiom(qw.ontology, qw.factory
					.getOWLObjectPropertyRangeAxiom(hasDaughter,
							rangeOfHasDaughter));

			// ////////////////////////////////////////////////////////////////////////////////////////////
			//
			// Data Ranges and Equivalent Classes axioms
			//
			// ////////////////////////////////////////////////////////////////////////////////////////////

			// In OWL 2, we can specify expressive data ranges. Here, we
			// will specify the classes
			// Teenage, Adult and Child by saying something about
			// individuals ages.

			// First we take the class Teenager, all of whose instance have
			// an age greater or equal to
			// 13 and less than 20. In Manchester Syntax this is written as
			// Person and hasAge some int[>=13, <20]
			// We create a data range by taking the integer datatype and
			// applying facet restrictions to it.
			// Note that we have statically imported the data range facet
			// vocabulary OWLFacet
			OWLFacetRestriction geq13 = qw.factory.getOWLFacetRestriction(
					MIN_INCLUSIVE, 12);
			// We don't have to explicitly create the typed constant, there
			// are convenience methods to do this
			OWLFacetRestriction lt20 = qw.factory.getOWLFacetRestriction(
					MAX_EXCLUSIVE, 25);
			// Restrict the base type, integer (which is just an XML Schema
			// Datatype) with the facet
			// restrictions.
			OWLFacetRestriction lt30 = qw.factory.getOWLFacetRestriction(
					MAX_EXCLUSIVE, 30);

			OWLClass teenager = qw.factory.getOWLClass(IRI
					.create(qw.ontologyIRI + "#Teenager"));

			OWLClass adult = qw.factory.getOWLClass(IRI.create(qw.ontologyIRI
					+ "#Adult"));

			// And finally Child
			OWLClass child = qw.factory.getOWLClass(IRI.create(qw.ontologyIRI
					+ "#Child"));

			// ////////////////////////////////////////////////////////////////////////////////////////////
			//
			// Different individuals
			//
			// ////////////////////////////////////////////////////////////////////////////////////////////

			// In OWL, we can say that individuals are different from each
			// other. To do this we use a
			// different individuals axiom. Since John, Mary, Bill and Susan
			// are all different individuals,
			// we can express this using a different individuals axiom.
			OWLDifferentIndividualsAxiom diffInds = qw.factory
					.getOWLDifferentIndividualsAxiom(john, mary, bill, susan,
							david, kate);
			qw.manager.addAxiom(qw.ontology, diffInds);
			// Male and Female are also different
			qw.manager.addAxiom(qw.ontology,
					qw.factory.getOWLDifferentIndividualsAxiom(male, female));

			// ////////////////////////////////////////////////////////////////////////////////////////////
			//
			// Disjoint classes
			//
			// ////////////////////////////////////////////////////////////////////////////////////////////

			// Two say that two classes do not have any instances in common
			// we use a disjoint classes
			// axiom:
			OWLDisjointClassesAxiom disjointClassesAxiom = qw.factory
					.getOWLDisjointClassesAxiom(man, woman);
			qw.manager.addAxiom(qw.ontology, disjointClassesAxiom);

			OWLDatatype integerDatatype = qw.factory.getIntegerOWLDatatype();
			OWLDataRange dataRng = qw.factory.getOWLDatatypeRestriction(
					integerDatatype, geq13, lt20);
			OWLDataRange dataRng2 = qw.factory.getOWLDatatypeRestriction(
					integerDatatype, geq13, lt30);
			// Now we have the data range of greater than equal to 13 and
			// less than 20 we can use this in a
			// restriction.
			OWLDataSomeValuesFrom teenagerAgeRestriction = qw.factory
					.getOWLDataSomeValuesFrom(hasAge, dataRng);
			OWLDataSomeValuesFrom lessThanThirtyAgeRestriction = qw.factory
					.getOWLDataSomeValuesFrom(hasAge, dataRng2);
			// Now make Teenager equivalent to Person and hasAge some
			// int[>=13, <20]
			// First create the class Person and hasAge some int[>=13, <20]
			OWLClassExpression teenagePerson = qw.factory
					.getOWLObjectIntersectionOf(person, teenagerAgeRestriction);
			// Do the same for Adult that has an age greater than 21
			OWLDataRange geq21 = qw.factory.getOWLDatatypeRestriction(
					integerDatatype,
					qw.factory.getOWLFacetRestriction(MIN_INCLUSIVE, 21));
			OWLEquivalentClassesAxiom teenagerDefinition = qw.factory
					.getOWLEquivalentClassesAxiom(teenager, teenagePerson);
			qw.manager.addAxiom(qw.ontology, teenagerDefinition);
			OWLClassExpression adultAgeRestriction = qw.factory
					.getOWLDataSomeValuesFrom(hasAge, geq21);
			OWLClassExpression adultPerson = qw.factory
					.getOWLObjectIntersectionOf(person, adultAgeRestriction);
			OWLAxiom adultDefinition = qw.factory.getOWLEquivalentClassesAxiom(
					adult, adultPerson);
			qw.manager.addAxiom(qw.ontology, adultDefinition);
			OWLDataRange notGeq21 = qw.factory.getOWLDataComplementOf(geq21);
			OWLClassExpression childAgeRestriction = qw.factory
					.getOWLDataSomeValuesFrom(hasAge, notGeq21);
			OWLClassExpression childPerson = qw.factory
					.getOWLObjectIntersectionOf(person, childAgeRestriction);
			OWLAxiom childDefinition = qw.factory.getOWLEquivalentClassesAxiom(
					child, childPerson);
			qw.manager.addAxiom(qw.ontology, childDefinition);

			// //////////////////////////////////
			s = qw.sowl();

			stat.posti(sh + "/w2f", "83.owl", s);

		} catch (Exception e2) {
			System.err.println(e2.toString());
		}
		return s;
	}


	
	public static void mail_admins(String subject, String text) {
		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);
		try {
			MimeMessage msg = new MimeMessage(session);
			msg.addHeader("Content-Type", " text/html; charset=utf-8");
			msg.setFrom(new InternetAddress("kuka@feofan.com", MimeUtility
					.encodeText("Феофан", "utf-8", "B")));
			msg.addRecipient(MimeMessage.RecipientType.TO, new InternetAddress(
					"admins"));
			msg.setSubject(MimeUtility.encodeText(subject, "utf-8", "B"));
			msg.setText(text);

			Transport.send(msg);
		} catch (Exception e) {
		}
	}

	public static String get_ans1(String sh, String s) {

		s = s.replace("?", "");
		String[] ss = s.split("[ ]+");
		int i = ss.length;
		String s3 = "";

		boolean bb = ss[0].toLowerCase().equals("кто")
				|| ss[0].toLowerCase().equals("что")
				|| ss[0].toLowerCase().equals("кого");

		if (bb) {

			// ///////////////////////////////////////////
			//
			// вопрос из 2-х слов - "кто Сократ?"
			//
			// ///////////////////////////////////////////

			if (i == 2) {

				OntModel mm = ModelFactory
						.createOntologyModel(PelletReasonerFactory.THE_SPEC);
				StringReader reader = new StringReader(stat.sowl);
				mm.read(reader, "");
				s = stat.sowl;

				String[] ssss = {
						// "SELECT ?кто  WHERE {?кто a qq:" + ss[1] + "}",
						"SELECT ?кто  WHERE {qq:" + ss[1] + " rdf:type ?кто}",
						"SELECT ?кто  WHERE {?кто rdf:type qq:" + ss[1] + "}" };

				for (String str : ssss) {

					s = stat.get_prefix(sh) + str;

					Query qq = QueryFactory.create(s);
					s = stat.sowl;
					s = "";
					ResultSet r = null;

					try {
						r = SparqlDLExecutionFactory.create(qq, mm)
								.execSelect();
						while (r.hasNext()) {
							s = r.next().toString();
							if (!s3.contains(s))
								s3 = s3 + s;
						}
					} catch (Exception er) {
						s = er.toString();
						// ber = true;
					}
					s3 = s3 + s;
				}

				s = s3;
				if (s.length() == 0)
					s = "Не знаю. Расскажи мне об этом";
			}

			// ///////////////////////////////////////////
			//
			// вопрос из 3-х слов - "кто любит Кнопочку?"
			//
			// ///////////////////////////////////////////
			else

			if (i == 3) {
				OntModel mm = ModelFactory
						.createOntologyModel(PelletReasonerFactory.THE_SPEC);

				StringReader reader = new StringReader(stat.sowl);
				mm.read(reader, "");
				s = stat.sowl;

				// ///////////////////////////////////////////////////////
				String[] ss1 = {
						"SELECT ?кто  WHERE {?кто qq:" + ss[1] + " qq:" + ss[2]
								+ "}",
						"SELECT ?кто  WHERE { qq:" + ss[2] + " qq:" + ss[1]
								+ " ?кто }" };
				String[] ss2 = {
						"select ?кто where {qq:" + ss[1]
								+ " rdfs:domain ?кто }",
						"select ?кто where {qq:" + ss[1] + " rdfs:range ?кто }"

				};

				String[] sss = ss1;

				if (!bim(ss[2]))
					sss = ss2;

				for (String str : sss) {

					s = stat.get_prefix(sh) + str;

					Query qq = QueryFactory.create(s);
					s = stat.sowl;
					s = "";
					ResultSet r = null;

					try {
						r = SparqlDLExecutionFactory.create(qq, mm)
								.execSelect();
						while (r.hasNext()) {
							s = r.next().toString();
							if (!s3.contains(s) && !s.contains(ss[2]))
								s3 = s3 + s;
						}
					} catch (Exception er) {
						s = er.toString();
						// ber = true;
					}
					// s3 = s3 + s;
				}

				// ///////////////////////////////////////////////////////

				s = s3;
				if (s.length() == 0)
					s = "Не знаю :-(";

			} else
				s = "Пока что вопрос должен начинается со слов Кто, Что, Кого и состоять из 2-х или 3-х слов.";
		}

		// if (ber) {
		// stat.page(req, resp, s);
		// }

		if (s.indexOf("#") > 0) {
			s = s.substring(s.indexOf("#") + 1);

			s = s.replaceAll("[<>]", "").replace("[", "");

			ss = s.split("[#]");
			s = "";
			s3 = ",";
			for (String sd : ss) {
				if (sd.indexOf(")") > 0) {
					s = sd.substring(0, sd.indexOf(")")).trim();
					if (!s3.contains(s))
						s3 = s3 + ", " + s;

				}
				s = s3.replace(",,", "");
			}
		} else
			s = "не знаю :-( ";

		s = s + ".";
		return s;
	}

	public static String mm_get_otvet(String sh, String sb, String s, String srp) {
		int i = s.lastIndexOf("-- ");
		if (i > 0)
			s = s.substring(0, i);
		s = smot(sh, s) + "\r\n__________\r\n\r\nЭто ответ для " + srp
				+ ", который написал Феофану:\r\n\r\n" + s;
		return s;
	}

	public static void add_sr(String s, String sh) {
		s = s.replaceAll("[ ]+", " ");
		String[] ss = s.split("[.]");
		try {
			for (String s7 : ss)
				if (!stat.sr.contains(s7.trim()))
					stat.sr = stat.sr.trim() + " " + s7.trim() + ".";

			stat.get_owl83(stat.sr, sh);

//			stat.w2f1("83.owl", stat.sowl);
//			stat.w2f1("sr.txt", stat.sr);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static String get_sr() {
		if (stat.sr == null)
			return "";
		else
			return stat.sr;
	}

	static void sm(String skomu, String sbj, String s) {
		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);
		Message msg = new MimeMessage(session);
		try {
			msg.setFrom(new InternetAddress("kuka@feofan.com", MimeUtility
					.encodeText("Феофан", "utf-8", "B")));

			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(
					skomu, " "));
			msg.setSubject(sbj);
			msg.setText(s);
			Transport.send(msg);
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	public static boolean bim(String s) {

		if (s.length() > 0)
			if (s.substring(0, 1).toUpperCase().equals(s.substring(0, 1)))
				return true;
			else
				return false;
		else
			return false;
	}

	public static String pripare(String s) {

		while (s.indexOf("(") > -1 && s.indexOf(")") > -1
				&& s.indexOf(")") > s.indexOf("("))
			s = s.replace(s.substring(s.indexOf("("), s.indexOf(")") + 1), "");

		s = s.replace("-", "").replace("это", "");

		return s.trim();
	}

	public static String smot(String sh, String s) {
		int i = s.indexOf("?");
		String s1 = "";
		if (i > -1)
			s1 = s.substring(0, i);
		i = s1.indexOf(".");
		if (i > 0) {
			s1 = s1.substring(0, s1.lastIndexOf(".") + 1);
			stat.sr = "";
			stq.add_sr(s1, sh);
		}
		s = stq.otvet(sh, s);
		return s;
	}

	public static String otvet(String sh, String s) {
		if (s.toLowerCase().contains("спаркля(")) {
			s = sparql(sh, s);
			return s;
		}

		if (s.contains("?")) {
			int i = s.indexOf("?");
			String s1 = "";
			if (i > -1)
				s1 = s.substring(0, i);
			i = s1.indexOf(".");
			if (i > 0) {
				s1 = s1.substring(0, s1.lastIndexOf(".") + 1);
				stat.sr = stat.sr + "";
				add_sr(s1, sh);
			}

			if (s.contains("."))
				s = s.substring(s.lastIndexOf(".") + 1).trim();

			String[] ss = s.split("[ ]+");
			boolean bb = ss[0].toLowerCase().equals("кто")
					|| ss[0].toLowerCase().equals("что")
					|| ss[0].toLowerCase().equals("кого");
			if (bb) {
				s = get_ans1(sh, s);
			} else {
				s = "Не понял вопрос. См. описание КРЯ и Спаркля";
			}
		}
		return s;
	}

	public static String sparql(String sh, String s) {

		stat.sh = sh;
		String s55 = s;

		add_sr(stat.sr, sh);
		int i = s.toLowerCase().indexOf("спаркля(");

		s = s.substring(i + 8);
		s = s.replace(")", "");

		String[] ss = s.split("[ ]+");
		String svar = "";
		s = "SELECT var  WHERE {";
		for (String str : ss) {

			str = str.trim();

			if (str.equals("это"))
				s = s + " rdf:type";
			else if (str.indexOf("?") == 0) {
				svar = svar + str.trim() + " ";
				s = s + " " + str;
			} else
				s = s + " qq:" + str;

		}
		s = s + ".}";
		s = s.replace("var", svar);
		s = stat.get_prefix(sh) + s;

		OntModel mm = ModelFactory
				.createOntologyModel(PelletReasonerFactory.THE_SPEC);

		mm.read(new StringReader(stat.sowl), "");

		try {
			Query qq = QueryFactory.create(s);
			s = "";
			ResultSet r = SparqlDLExecutionFactory.create(qq, mm).execSelect();
			while (r.hasNext())
				s = s + r.next().toString();

		} catch (Exception ee) {
			s = ee.toString();
		}
		s = s.replace("<", "[").replace(">", "]")
				.replace(sh + "/rff?83.owl#", "");
		// s = s.replace("[Root]", "<br/>").replace("-]", "");

		if (s.trim().length() == 0)
			s = "ответа нет.<br/>мир можно дописать, загрузить или добавить.";

		s = s.replace(")(", ")\r\n(");

		ss = s.split("\r\n");
		s = "";

		for (String sd : ss) {

			String[] sss = sd.split("[(]");
			boolean bb = false;
			for (String sf : sss) {
				if (sss.length > 3) {
					// bb=sss[1].contains("[") && sss[2].contains("[") &&
					// sss[3].contains("[");
					sd = "( " + sss[1] + " ( " + sss[2] + " (" + sss[3];
				}
			}
			if (sd.contains("["))
				s = s + sd + "\r\n";
		}
		s = s.replace("[Root]", "\r\n").replace(" -] ", "");
		s = s.substring(0, s.length() - 4);
		return "\r\n" + s;
	}
	public static boolean n(String str) {
		for (char c : str.toCharArray()) {
			if (!Character.isDigit(c))
				return false;
		}
		return true;
	}
}