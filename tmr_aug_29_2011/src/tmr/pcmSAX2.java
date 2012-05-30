package tmr;

import java.io.*;

import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;

//import org.apache.xerces.parsers.SAXParser;

public class pcmSAX2 extends DefaultHandler {

	public static String s="";
	PrintWriter out=null;


	public pcmSAX2(String s2) throws Exception {
		try {
			
			SAXParserFactory factory = SAXParserFactory.newInstance();
			javax.xml.parsers.SAXParser saxParser = factory.newSAXParser();
			InputStream in = StringToStream(s2);
			saxParser.parse(in, handler);
			
 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String get_result(){
		return s;
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

		boolean bstate = false;
		boolean blmiles = false;
		public void startElement(String uri, String localName,
				String qName, Attributes attributes)
				throws SAXException {
			//out.println(" start ");
			
			if (qName.equalsIgnoreCase("StCntry")) {
				bstate = true;
			}
			if (qName.equalsIgnoreCase("STotal")) {
				blmiles = true;
			}
		}

		public void endElement(String uri, String localName,
				String qName) throws SAXException {

			//out.println(" end ");

		}

		public void characters(char ch[], int start, int length)
				throws SAXException {
			if (bstate) {
				s=(new String(ch, start, length));
				out.print(s+"-");
				bstate = false;
			}
			if (blmiles) {
				s=(new String(ch, start, length));
				out.print(s +",");
				blmiles = false;
			}
		}
	};
}