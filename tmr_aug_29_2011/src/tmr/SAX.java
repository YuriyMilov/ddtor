package tmr;

import java.io.*;

import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;

//import org.apache.xerces.parsers.SAXParser;

public class SAX extends DefaultHandler {

	String s="";
	PrintWriter out=null;


	public SAX(String s2, PrintWriter out1) throws Exception {
		try {
			out=out1;
			SAXParserFactory factory = SAXParserFactory.newInstance();
			javax.xml.parsers.SAXParser saxParser = factory.newSAXParser();
			InputStream in = StringToStream(s2);
			saxParser.parse(in, handler);
			out.println(s);


		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static InputStream StringToStream(String text) {
		InputStream is = null;
		/*
		 * Convert String to InputStream using ByteArrayInputStream class. This
		 * class constructor takes the string byte array which can be done by
		 * calling the getBytes() method.
		 */
		try {
			is = new ByteArrayInputStream(text.getBytes("UTF-8"));
			is.reset();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return is;
	}

	
	DefaultHandler handler = new DefaultHandler() {

		boolean bcity = false;
		boolean bstate = false;
		boolean blmiles = false;
		
		
		//boolean bfname = false;
		//boolean blname = false;
		//boolean bnname = false;
		//boolean bsalary = false;
		
		public void startElement(String uri, String localName,
				String qName, Attributes attributes)
				throws SAXException {
			

			//s=("Start Element :" + qName);
			//out.println(s+" \r\n");
			
			if (qName.equalsIgnoreCase("City")) {
				bcity = true;
			}
			if (qName.equalsIgnoreCase("State")) {
				bstate = true;
			}
			if (qName.equalsIgnoreCase("LMiles")) {
				blmiles = true;
			}
			
			
	/*		
			if (qName.equalsIgnoreCase("FIRSTNAME")) {
				bfname = true;
			}

			if (qName.equalsIgnoreCase("LASTNAME")) {
				blname = true;
			}

			if (qName.equalsIgnoreCase("NICKNAME")) {
				bnname = true;
			}

			if (qName.equalsIgnoreCase("SALARY")) {
				bsalary = true;
			}
*/
		}

		public void endElement(String uri, String localName,
				String qName) throws SAXException {

			//s=("End Element :" + qName);
			//out.println(s);

		}

		public void characters(char ch[], int start, int length)
				throws SAXException {
			
			if (bcity) {
				s=(new String(ch, start, length));
				out.print(s+", ");
				bcity = false;
			}

			if (bstate) {
				s=(new String(ch, start, length));
				out.print(s+" \r\n");
				bstate = false;
			}
			
			
			if (blmiles) {
				s=(new String(ch, start, length));
				out.println(s);
				blmiles = false;
			}

/*			if (bfname) {
				s=("First Name : "
						+ new String(ch, start, length));
				out.println(s+" \r\n");
				bfname = false;
			}

			if (blname) {
				s=("Last Name : "
						+ new String(ch, start, length));
				out.println(s+" \r\n");
				blname = false;
			}

			if (bnname) {
				s=("Nick Name : "
						+ new String(ch, start, length));
				out.println(s+" \r\n");
				bnname = false;
			}

			if (bsalary) {
				s=("Salary : "
						+ new String(ch, start, length));
				out.println(s+" \r\n");
				bsalary = false;
			}*/

		}

	};
	
}