package cn;

import javax.xml.parsers.*;
import org.w3c.dom.*;

public class _2DomParserExample {
	public static void main(String[] args) throws Exception{

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document dom = db.parse("2.xml");
		Element e = dom.getDocumentElement();
		NodeList ns = e.getElementsByTagName("Header");
		e = (Element) ns.item(0);
		ns=e.getElementsByTagName("Carrier");
		e = (Element) ns.item(0);
		ns=e.getElementsByTagName("Descr");
		e = (Element) ns.item(0);


		System.out.println(e.getTextContent());
	}


}
