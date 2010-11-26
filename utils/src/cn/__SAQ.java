package cn;

import javax.xml.parsers.*;
import org.w3c.dom.*;

public class __SAQ {
	
	public static void main(String[] args) throws Exception {
		String s="";
		
		s=sta.rff("../../../wofile_str.txt");
		
		String s2="";
		while (s.indexOf("\r\n")>-1){
		s2=s.substring(29,s.indexOf("\r\n"));
		s=s.substring(s.indexOf("\r\n")+2);
		s2=s2.substring(0,s2.indexOf(" "));
		System.out.print(s2+",");
		}
		
		s=sta.rff("../../../wofile_str.txt");

		while (s.indexOf("\r\n")>-1){
		s2=s.substring(29,s.indexOf("\r\n"));
		s=s.substring(s.indexOf("\r\n")+2);
		s2=s2.substring(0,s2.indexOf(" "));
		System.out.print(s2+",");
		}
		
	
		
/*
		DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		//Element e = db.parse("1.xml").getDocumentElement();
		Element e = db.parse("3.xml").getDocumentElement();
		
		s = e.getElementsByTagName("DateTime").item(0).getTextContent();	
		System.out.println(s);

		s = e.getElementsByTagName("UniqueRefNb").item(0).getTextContent();	
		System.out.println(s);

		s = e.getElementsByTagName("Code").item(0).getTextContent();	
		System.out.println(s);

		s = e.getElementsByTagName("Descr").item(0).getTextContent();	
		System.out.println(s);

		s = e.getElementsByTagName("Code").item(1).getTextContent();	
		System.out.println(s);

		s = e.getElementsByTagName("Descr").item(1).getTextContent();	
		System.out.println(s);

		s = e.getElementsByTagName("DateTime").item(1).getTextContent();	
		System.out.println(s);

		s = e.getElementsByTagName("Status").item(0).getTextContent();	
		System.out.println(s);

		s = e.getElementsByTagName("PurchaseOrderNb").item(0).getTextContent();	
		System.out.println(s);

		s = e.getElementsByTagName("MarketplaceRefNb").item(0).getTextContent();	
		System.out.println(s);

		s = e.getElementsByTagName("ImporterRefNb").item(0).getTextContent();	
		System.out.println(s);

		s = e.getElementsByTagName("Code").item(2).getTextContent();	
		System.out.println(s);

		s = e.getElementsByTagName("CompanyName").item(0).getTextContent();	
		System.out.println(s);

	
		s = e.getElementsByTagName("Address").item(0).getTextContent();	
		System.out.println(s);
		
		
		s = e.getElementsByTagName("City").item(0).getTextContent();	
		System.out.println(s);
	
		s = e.getElementsByTagName("StateProv").item(0).getTextContent();	
		System.out.println(s);
	
		s = e.getElementsByTagName("ZipPc").item(0).getTextContent();	
		System.out.println(s);
	
		s = e.getElementsByTagName("Country").item(0).getTextContent();	
		System.out.println(s);
	
		s = e.getElementsByTagName("ContactName").item(0).getTextContent();	
		System.out.println(s);
	
		s = e.getElementsByTagName("Fax").item(0).getTextContent();	
		System.out.println(s);
		
		s = e.getElementsByTagName("Email").item(0).getTextContent();	
		System.out.println(s);
		
		s = e.getElementsByTagName("Code").item(3).getTextContent();	
		System.out.println(s);
		
		s = e.getElementsByTagName("Code").item(4).getTextContent();	
		System.out.println(s);
		
		s = e.getElementsByTagName("CompanyName").item(1).getTextContent();	
		System.out.println(s);
		
		s = e.getElementsByTagName("Address").item(1).getTextContent();	
		System.out.println(s);
		
		s = e.getElementsByTagName("City").item(1).getTextContent();	
		System.out.println(s);
		
		s = e.getElementsByTagName("StateProv").item(1).getTextContent();	
		System.out.println(s);
	

		
		s = e.getElementsByTagName("ZipPc").item(1).getTextContent();	
		System.out.println(s);
		
		s = e.getElementsByTagName("Country").item(1).getTextContent();	
		System.out.println(s);

		s = e.getElementsByTagName("Phone").item(0).getTextContent();	
		System.out.println(s);
	
		s = e.getElementsByTagName("Fax").item(1).getTextContent();	
		System.out.println(s);
		
		s = e.getElementsByTagName("Email").item(1).getTextContent();	
		System.out.println(s);
		
		s = e.getElementsByTagName("Code").item(5).getTextContent();	
		System.out.println(s);
		
		s = e.getElementsByTagName("CompanyName").item(2).getTextContent();	
		System.out.println(s);
	
		
		
		s = e.getElementsByTagName("Address").item(2).getTextContent();	
		System.out.println(s);
		
		
		s = e.getElementsByTagName("City").item(2).getTextContent();	
		System.out.println(s);
	
		s = e.getElementsByTagName("StateProv").item(2).getTextContent();	
		System.out.println(s);
	
		s = e.getElementsByTagName("ZipPc").item(2).getTextContent();	
		System.out.println(s);
	
		s = e.getElementsByTagName("Country").item(2).getTextContent();	
		System.out.println(s);
		
		
		s = e.getElementsByTagName("Phone").item(1).getTextContent();	
		System.out.println(s);
		
		s = e.getElementsByTagName("Email").item(2).getTextContent();	
		System.out.println(s);
	
		s = e.getElementsByTagName("Code").item(6).getTextContent();	
		System.out.println(s);

		s = e.getElementsByTagName("Descr").item(2).getTextContent();	
		System.out.println(s);
	
		s = e.getElementsByTagName("TransportAlreadyOrganized").item(0).getTextContent();	
		System.out.println(s);
		
		s = e.getElementsByTagName("Code").item(7).getTextContent();	
		System.out.println(s);
		
		s = e.getElementsByTagName("RequestedShippingDate").item(0).getTextContent();	
		System.out.println(s);
		
		s = e.getElementsByTagName("RequestedDeliveryDate").item(0).getTextContent();	
		System.out.println(s);
		
		s = e.getElementsByTagName("Value").item(0).getTextContent();	
		System.out.println(s);

		s = e.getElementsByTagName("UomPackaging").item(0).getTextContent();	
		System.out.println(s);

		s = e.getElementsByTagName("Type").item(0).getTextContent();	
		System.out.println(s);

		s = e.getElementsByTagName("Descr").item(3).getTextContent();	
		System.out.println(s);

		//s = e.getElementsByTagName("Indication").item(0).getTextContent();	
		//System.out.println(s);



		
		
		System.out.println("----------------------------");	
		System.out.println("            ITEMS ");
		System.out.println("----------------------------");
		
		NodeList ns = e.getElementsByTagName("Item");
		for (int i = 0; i < ns.getLength(); i++) {
			s = ((Element)((Element)ns.item(i)).getElementsByTagName("LineNb").item(0)).getTextContent();
			System.out.println(s);

			s = ((Element)((Element)ns.item(i)).getElementsByTagName("ProductCode").item(0)).getTextContent();
			System.out.println(s);

			s = ((Element)((Element)ns.item(i)).getElementsByTagName("Status").item(0)).getTextContent();
			System.out.println(s);

			s = ((Element)((Element)ns.item(i)).getElementsByTagName("Descr").item(0)).getTextContent();
			System.out.println(s);
			System.out.println("-------- Qty ");	
			// *****************         Qty        ******************************
			NodeList ns2 = e.getElementsByTagName("Qty");
			s = ((Element)((Element)ns2.item(i)).getElementsByTagName("Value").item(0)).getTextContent();
			System.out.println(s);
			s = ((Element)((Element)ns2.item(i)).getElementsByTagName("UomPackaging").item(0)).getTextContent();
			System.out.println(s);
			s = ((Element)((Element)ns2.item(i)).getElementsByTagName("BottleFormatInPackage").item(0)).getTextContent();
			System.out.println(s);
			s = ((Element)((Element)ns2.item(i)).getElementsByTagName("UomBottleFormat").item(0)).getTextContent();
			System.out.println(s);
			// ******************************************************************
			System.out.println("-------- Weight ");		
			// *****************         Weight        ******************************
			NodeList ns3 = e.getElementsByTagName("Weight");
			s = ((Element)((Element)ns3.item(i)).getElementsByTagName("Value").item(0)).getTextContent();
			System.out.println(s);
			s = ((Element)((Element)ns3.item(i)).getElementsByTagName("Uom").item(0)).getTextContent();
			System.out.println(s);
			// ******************************************************************
			System.out.println("-------- Volume ");		
			// *****************         Volume        ******************************
			NodeList ns4 = e.getElementsByTagName("Volume");
			s = ((Element)((Element)ns4.item(i)).getElementsByTagName("Value").item(0)).getTextContent();
			System.out.println(s);
			s = ((Element)((Element)ns4.item(i)).getElementsByTagName("Uom").item(0)).getTextContent();
			System.out.println(s);
			// ******************************************************************
			s = ((Element)((Element)ns.item(i)).getElementsByTagName("TopSeller").item(0)).getTextContent();
			System.out.println(s);
			System.out.println("********************************");
		}
*/
	}

}
