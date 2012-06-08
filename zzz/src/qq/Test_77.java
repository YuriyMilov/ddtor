package qq;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.*;

public class Test_77 {
	public static void main(String args[]) throws Exception{
		
//		String s = rfu("http://www.bankofcanada.ca/en/markets/csv/exchange_eng.csv");
		
		//String s = rfu("http://localhost/3.txt");
		//s=get_strep(s);
		
		//s=grate(s);
		
		String s="qq";
		
		//user=ACCOUNTING&pass=cc&ocity=Toronto&ostate=ON&dcity=Detroit&&dstate=MI
		
		
		s=postData2("ACCOUNTING","AC2012","PDFREIGHT1","","Toronto","ON","","Detroit","MI");
		
		 System.out.println(s);
	}
	
	
	
	
	public static String postData2(String sUserID,String sPassword,String sAccount,String soAddress1,String soCity,String soState,String sdAddress1,String sdCity,String sdState) throws Exception {
		//URL endpoint = new URL("http://pcmws.alk.com/service.asmx");
		//URL endpoint = new URL("http://173.230.149.178/sandbox/api/mom/soap");
		URL endpoint = new URL("http://173.230.149.178/sandbox/api/mom/soap");
		//http://173.230.149.178/sandbox/api/mom/soap?wsdl
		
		
		
		/*String s = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap12:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\">" +
				"<soap12:Body>" +
				"<PMWSGetReport xmlns=\"http://localhost/PCMilerWS/\">" +
				"<LoginInfo>" +
				//"<UserID>EM</UserID>" +
				//"<Password>EM70271</Password>" +
				//"<Account>EMDataCon_test</Account>" +
				
				"<UserID>"+sUserID+"</UserID>" +
				"<Password>"+sPassword+"</Password>" +
				"<Account>"+sAccount+"</Account>" +
				
				//"<DataVer></DataVer>" +
				
				"</LoginInfo>" +
				"<TripOrigin>" +
				"<LocationInputType>" +
				"<Address1>"+soAddress1+"</Address1>" +
				"<City>"+soCity+"</City>" +
		        "<State>"+soState+"</State>" +
				"</LocationInputType>" +
				"</TripOrigin>" +
				"<TripDestination>" +
				"<LocationInputType>" +
				"<Address1>"+sdAddress1+"</Address1>" +
				"<City>"+sdCity+"</City>" +
		        "<State>"+sdState+"</State>" +
				"</LocationInputType>" +
				"</TripDestination>" +
				"<ReportType>" +
				"<string>m</string>" +
				"</ReportType></PMWSGetReport>" +
				"</soap12:Body></soap12:Envelope>";
		*/
//		String s="<?xml version=\"1.0\" encoding=\"UTF-8\"?><env:Envelope xmlns:env=\"http://www.w3.org/2003/05/soap-envelope\" xmlns:ns1=\"http://173.230.149.178/sandbox/api/mom/soap\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:enc=\"http://www.w3.org/2003/05/soap-encoding\" xmlns:ns2=\"http://xml.apache.org/xml-soap\"><env:Body><ns1:createReceipt env:encodingStyle=\"http://www.w3.org/2003/05/soap-encoding\"><credentials xsi:type=\"ns1:Mainfreight_Api_Library_Credentials\"><userName xsi:type=\"xsd:string\">rogueapi</userName><password xsi:type=\"xsd:string\">#32kjfs!4@4334Bds</password><accountId xsi:type=\"xsd:int\">2142</accountId></credentials><receipt xsi:type=\"ns1:Mainfreight_Api_Model_Mom_Inventory_Receipt\"><itemsCollection xsi:type=\"ns1:Mainfreight_Api_Model_Mom_Inventory_Item_Collection\"><items enc:itemType=\"ns1:Mainfreight_Api_Model_Mom_Inventory_Item\" enc:arraySize=\"1\" xsi:type=\"ns1:ArrayOfMainfreight_Api_Model_Mom_Inventory_Item\"><item xsi:type=\"ns1:Mainfreight_Api_Model_Mom_Inventory_Item\"><id xsi:nil=\"true\"/><data xsi:type=\"ns2:Map\"><item><key xsi:type=\"xsd:string\">item_type_id</key><value xsi:type=\"xsd:string\">32748</value></item><item><key xsi:type=\"xsd:string\">item_quantity</key><value xsi:type=\"xsd:int\">5</value></item><item><key xsi:type=\"xsd:string\">item_location</key><value xsi:type=\"xsd:string\">rear of building</value></item></data></item></items><size xsi:nil=\"true\"/><filtersCollection xsi:nil=\"true\"/></itemsCollection><id xsi:nil=\"true\"/><data xsi:type=\"ns2:Map\"><item><key xsi:type=\"xsd:string\">site</key><value xsi:type=\"xsd:string\">158905</value></item><item><key xsi:type=\"xsd:string\">segment</key><value xsi:type=\"xsd:string\">158902</value></item><item><key xsi:type=\"xsd:string\">date_received</key><value xsi:type=\"xsd:string\">2012-05-07 00:00:00</value></item><item><key xsi:type=\"xsd:string\">address_id_carrier</key><value xsi:type=\"xsd:string\">1330780994</value></item><item><key xsi:type=\"xsd:string\">total_weight</key><value xsi:type=\"xsd:string\">20.00</value></item><item><key xsi:type=\"xsd:string\">is_transfer</key><value xsi:type=\"xsd:string\">0</value></item></data></receipt></ns1:createReceipt></env:Body></env:Envelope>";
		String s="<?xml version=\"1.0\" encoding=\"UTF-8\"?><env:Envelope xmlns:env=\"http://www.w3.org/2003/05/soap-envelope\" xmlns:ns1=\"http://173.230.149.178/sandbox/api/mom/soap\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:enc=\"http://www.w3.org/2003/05/soap-encoding\" xmlns:ns2=\"http://xml.apache.org/xml-soap\"><env:Body><ns1:createReceipt env:encodingStyle=\"http://www.w3.org/2003/05/soap-encoding\"><credentials xsi:type=\"ns1:Mainfreight_Api_Library_Credentials\"><userName xsi:type=\"xsd:string\">rogueapi</userName><password xsi:type=\"xsd:string\">#32kjfs!4@4334Bds</password><accountId xsi:type=\"xsd:int\">2142</accountId></credentials><receipt xsi:type=\"ns1:Mainfreight_Api_Model_Mom_Inventory_Receipt\"><itemsCollection xsi:type=\"ns1:Mainfreight_Api_Model_Mom_Inventory_Item_Collection\"><items enc:itemType=\"ns1:Mainfreight_Api_Model_Mom_Inventory_Item\" enc:arraySize=\"2\" xsi:type=\"ns1:ArrayOfMainfreight_Api_Model_Mom_Inventory_Item\"><item xsi:type=\"ns1:Mainfreight_Api_Model_Mom_Inventory_Item\" enc:id=\"ref1\"><id xsi:nil=\"true\"/><data xsi:type=\"ns2:Map\"><item><key xsi:type=\"xsd:string\">item_color</key><value xsi:type=\"xsd:string\">blue</value></item><item><key xsi:type=\"xsd:string\">item_type_id</key><value xsi:type=\"xsd:string\">435333</value></item><item><key xsi:type=\"xsd:string\">item_quantity</key><value xsi:type=\"xsd:int\">5</value></item><item><key xsi:type=\"xsd:string\">item_location</key><value xsi:type=\"xsd:string\">rear of building</value></item></data></item><item enc:ref=\"#ref1\"/></items><size xsi:nil=\"true\"/><filtersCollection xsi:nil=\"true\"/></itemsCollection><id xsi:nil=\"true\"/><data xsi:type=\"ns2:Map\"><item><key xsi:type=\"xsd:string\">site</key><value xsi:type=\"xsd:string\">158901</value></item><item><key xsi:type=\"xsd:string\">segment</key><value xsi:type=\"xsd:string\">158914</value></item><item><key xsi:type=\"xsd:string\">date_received</key><value xsi:type=\"xsd:string\">2012-03-13 00:00:00</value></item><item><key xsi:type=\"xsd:string\">address_id_shipper</key><value xsi:type=\"xsd:int\">1330780907</value></item><item><key xsi:type=\"xsd:string\">address_id_vendor</key><value xsi:type=\"xsd:int\">1330781020</value></item><item><key xsi:type=\"xsd:string\">total_weight</key><value xsi:type=\"xsd:string\">20.00</value></item><item><key xsi:type=\"xsd:string\">is_transfer</key><value xsi:type=\"xsd:string\">0</value></item></data></receipt></ns1:createReceipt></env:Body></env:Envelope>";
		
		HttpURLConnection urlc = (HttpURLConnection) endpoint.openConnection();
		urlc.setRequestMethod("POST");
		urlc.setDoOutput(true);
		urlc.setDoInput(true);
		urlc.setUseCaches(false);
		urlc.setAllowUserInteraction(false);
		urlc.setRequestProperty("Content-type", "text/xml; charset=" + "UTF-8");
		OutputStream out = urlc.getOutputStream();
		Writer writer = new OutputStreamWriter(out, "UTF-8");
		writer.write(s);
		writer.close();
		InputStream in = urlc.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		StringBuffer sb = new StringBuffer();
		s = "";
		while ((s = br.readLine()) != null) {
			sb.append(s);
			sb.append("\r\n");
		}
		br.close();
		s=sb.toString().replace("><", ">\r\n<");
		
		String sm="";
		int i=s.lastIndexOf("<LMiles>");
		if(i>-1)
			sm=s.substring(i);
		i=sm.indexOf("</LMiles>");
		if(i>-1)
			sm=sm.substring(8,i);
		sm=sm.trim();
		if (sm.length()>0) 
			s= sm;
		else 
			if (s.indexOf("<ErrorDesc>") > -1)
				s= "ERROR: " + s.substring(s.indexOf("<ErrorDesc>")+11,s.indexOf("</ErrorDesc>"));
			else
				s= "ERROR: " + s;
		
		return s;
	}
	
	
	static String grate(String s){
		String s2=s;
		String s3="";
		s = s.substring(s.indexOf("USD"));
		s = s.substring(3, 111);
		int n = s.indexOf("U");
		s = s.substring(0, n);
		s = s.substring(s.lastIndexOf(",") + 1);
		s = s.replace("\r", "");
		s = s.replace("\n", "");
		s3="USD:"+s+"/";
		s2=s2.substring(s2.indexOf("Argentine"));

		while(s2.indexOf("\n")>-1)
		{
		String s4=s2.substring(s2.indexOf(",")+2,s2.indexOf("\n")-1);//+"---"+s2.substring(s2.lastIndexOf(",")+2,s2.indexOf("\n")-1);
		s4=s4.substring(0,3)+":"+s4.substring(s4.lastIndexOf(",")+1)+"/";
		//System.out.println(s4);
		s2=s2.substring(s2.indexOf("\n")+1);
		s3=s3+s4;
		}
		return s3;
	}
	
	
 	static String get_strep (String s){
		
		s=s.substring(s.indexOf("<StateMiles>"));
		s=s.substring(12);
		s=s.substring(0,s.indexOf("</StateMiles>"));
		
		if(s.indexOf("<StCntry>")>-1);
		s=s.substring(s.indexOf("<StCntry>"));

		return s;
	};
	
	public static String rfu(String url) {
		StringBuffer s = new StringBuffer();
		try {
			URL u = new URL(url);
			InputStream in = u.openConnection().getInputStream();
			for (int ch = in.read(); ch > 0; ch = in.read()) {
				s.append((char) ch);
			}
			in.close();
		} catch (IOException e) {
			return e.toString();
		}
		return s.toString();
	}
	
	public static double get_d(String f,String t) {
		String s = "from:%20" + f + "%20to:%20" + t;
		s = rfu("http://maps.google.com/maps/nav?key=ABQIAAAACI6Ap2jcQ1oqProEJaR56RQK5EEH2t0xEwQ4uv9AyPUz7NEyYxQvir4iiXU1B5zMsah16FswrqdssA&output=txt&doflg=ptj&hl=en&gl=US&q="
				+ s);
		double d=0;
		try {

			s = s.substring(s.indexOf("Distance") + 20);
			s = s.substring(0, s.indexOf(","));
			d = Double.parseDouble(s);
			//d = d / 1609.344;
			 
		} catch (Exception ee) {
			d= -1;
		}
		
		return d;
	}
	
	
}