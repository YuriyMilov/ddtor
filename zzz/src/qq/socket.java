package qq;


import java.net.*;
import java.io.*;

public class socket {

	public static void main(String args[]) throws Exception {

        String host = "173.230.149.178";
        String s = "/sandbox/api/mom/soap";
        String postData = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><env:Envelope xmlns:env=\"http://www.w3.org/2003/05/soap-envelope\" xmlns:ns1=\"http://173.230.149.178/sandbox/api/mom/soap\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:enc=\"http://www.w3.org/2003/05/soap-encoding\" xmlns:ns2=\"http://xml.apache.org/xml-soap\"><env:Body><ns1:createReceipt env:encodingStyle=\"http://www.w3.org/2003/05/soap-encoding\"><credentials xsi:type=\"ns1:Mainfreight_Api_Library_Credentials\"><userName xsi:type=\"xsd:string\">rogueapi</userName><password xsi:type=\"xsd:string\">#32kjfs!4@4334Bds</password><accountId xsi:type=\"xsd:int\">2142</accountId></credentials><receipt xsi:type=\"ns1:Mainfreight_Api_Model_Mom_Inventory_Receipt\"><itemsCollection xsi:type=\"ns1:Mainfreight_Api_Model_Mom_Inventory_Item_Collection\"><items enc:itemType=\"ns1:Mainfreight_Api_Model_Mom_Inventory_Item\" enc:arraySize=\"1\" xsi:type=\"ns1:ArrayOfMainfreight_Api_Model_Mom_Inventory_Item\"><item xsi:type=\"ns1:Mainfreight_Api_Model_Mom_Inventory_Item\"><id xsi:nil=\"true\"/><data xsi:type=\"ns2:Map\"><item><key xsi:type=\"xsd:string\">item_type_id</key><value xsi:type=\"xsd:string\">32748</value></item><item><key xsi:type=\"xsd:string\">item_quantity</key><value xsi:type=\"xsd:int\">5</value></item><item><key xsi:type=\"xsd:string\">item_location</key><value xsi:type=\"xsd:string\">rear of building</value></item></data></item></items><size xsi:nil=\"true\"/><filtersCollection xsi:nil=\"true\"/></itemsCollection><id xsi:nil=\"true\"/><data xsi:type=\"ns2:Map\"><item><key xsi:type=\"xsd:string\">site</key><value xsi:type=\"xsd:string\">158905</value></item><item><key xsi:type=\"xsd:string\">segment</key><value xsi:type=\"xsd:string\">158902</value></item><item><key xsi:type=\"xsd:string\">date_received</key><value xsi:type=\"xsd:string\">2012-05-07 00:00:00</value></item><item><key xsi:type=\"xsd:string\">address_id_carrier</key><value xsi:type=\"xsd:string\">1330780994</value></item><item><key xsi:type=\"xsd:string\">total_weight</key><value xsi:type=\"xsd:string\">20.00</value></item><item><key xsi:type=\"xsd:string\">is_transfer</key><value xsi:type=\"xsd:string\">0</value></item></data></receipt></ns1:createReceipt></env:Body></env:Envelope>";
        s = "POST " + s + " HTTP/1.1\r\nHOST: " + host + "\r\nContent-Type: text/xml\r\nContent-Length: " + String.valueOf(postData.length()) + "\r\n\r\n" + postData;

        // send request
        
    	Socket js = new Socket(host, 80);
		PrintStream outStream = new PrintStream(js.getOutputStream());
		outStream.println(s);
		outStream.flush();
		
		//  1111111
		
		// read response
		
		DataInputStream inStream = new DataInputStream(js.getInputStream());
		StringBuffer stb = new StringBuffer();
		
		for (int ch = inStream.read(); ch > 0; ch = inStream.read()) {
			stb.append((char) ch);
		}
			s = stb.toString();
			System.out.println(s);
	}
}
