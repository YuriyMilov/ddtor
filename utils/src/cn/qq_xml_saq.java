package cn;

import com.google.gdata.client.spreadsheet.*;
import com.google.gdata.data.*;
import com.google.gdata.data.TextContent;
import com.google.gdata.data.spreadsheet.*;
import com.google.gdata.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.*;
import java.net.*;
import com.google.gdata.client.http.*;
import com.google.gdata.client.batch.*;
import com.google.gdata.client.calendar.*;
import com.google.gdata.client.codesearch.*;
import com.google.gdata.data.*;
import com.google.gdata.data.batch.*;
import com.google.gdata.data.calendar.*;
import com.google.gdata.data.codesearch.*;
import com.google.gdata.data.extensions.*;
import com.google.gdata.data.introspection.*;
import com.google.gdata.util.*;
import com.google.gdata.util.common.util.*;
import com.google.gdata.util.common.base.*;
import com.google.gdata.util.common.html.*;
import com.google.gdata.util.common.xml.*;

public class qq_xml_saq {

	public static void main(String[] args) throws Exception {
//test();
		String s="<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><Acknowledgment><Success/></Acknowledgment>";
	String s2="<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>"+
	"<ShippingInstruction>"+
	" <DocumentId>"+
	"  <DateTime>2008-07-03T11:41:25Z</DateTime>"+
	"  <UniqueRefNb>n0000077984611</UniqueRefNb>"+
	" </DocumentId>"+
	" <Sender>"+
	"  <Code Type=\"GWS\">dev1</Code>"+
	"  <Descr>GWS</Descr>"+
	" </Sender>"+
	" <Receiver>"+
	"  <Code Type=\"GWS\">axsun</Code>"+
	"  <Descr>Axsun Transport Californie</Descr>"+
	" </Receiver>"+
	" <PurchaseOrder>"+
	"  <Header>"+
	"   <DateTime>2008-07-03T11:41:25Z</DateTime>"+
	"   <Status>New</Status>"+
	"   <PurchaseOrderNb>OP-62034</PurchaseOrderNb>"+
	"   <ShippingInstructionNb>"+
	"    <MarketplaceRefNb>45347789</MarketplaceRefNb>"+
	"    <ImporterRefNb>900080</ImporterRefNb>"+
	"   </ShippingInstructionNb>"+
	"   <Importer>"+
	"    <Company>"+
	"     <Code Type=\"GWS\">saqvp</Code>"+
	"     <CompanyName>Centre de distribution Mtl</CompanyName>"+
	"     <Coordinates>"+
	"      <Address>1900, rue de Boucherville</Address>"+
	"      <City>Montreal</City>"+
	"      <StateProv>QC</StateProv>"+
	"      <ZipPc>H1N3M7</ZipPc>"+
	"      <Country>CA</Country>"+
	"     </Coordinates>"+
	"    </Company>"+
	"    <Contact>"+
	"     <ContactName>Service des transports</ContactName>"+
	"     <Fax>514 864-3585</Fax>"+
	"     <Email>transports@saq.qc.ca</Email>"+
	"    </Contact>"+
	"   </Importer>"+
	"   <Supplier>"+
	"    <Company>"+
	"     <Code Type=\"Importer\">516331</Code>"+
	"     <Code Type=\"GWS\">s1368791</Code>"+
	"     <CompanyName>Goundrey Wines (Vincor Int.)</CompanyName>"+
	"     <Coordinates>"+
	"      <Address>Langton Muirs Highway</Address>"+
	"      <City>Mount Baker</City>"+
	"      <StateProv/>"+
	"      <ZipPc>6324</ZipPc>"+
	"      <Country>AU</Country>"+
	"     </Coordinates>"+
	"    </Company>"+
	"    <Contact>"+
	"     <Phone>01161 898511777</Phone>"+
	"     <Fax>01161 898511997</Fax>"+
	"     <Email>export@vincor-au.ca</Email>"+
	"    </Contact>"+
	"   </Supplier>"+
	"   <FreightForwarder>"+
	"    <Company>"+
	"     <Code Type=\"Importer\">3862781</Code>"+
	"     <CompanyName>Axsun Transport Californie</CompanyName>"+
	"     <Coordinates>"+
	"      <Address>250 South Vasco Road</Address>"+
	"      <City>Livermore</City>"+
	"      <StateProv>CA</StateProv>"+
	"      <ZipPc>94551</ZipPc>"+
	"      <Country>US</Country>"+
	"     </Coordinates>"+
	"    </Company>"+
	"    <Contact>"+
	"     <Phone>450 4453003</Phone>"+
	"     <Email>jjscheall@winedelivery.biz</Email>"+
	"    </Contact>"+
	"   </FreightForwarder>"+
	"   <Carrier>"+
	"    <Code Type=\"Marketplace\">NOT PROVIDED</Code>"+
	"    <Descr>BEAUCE EXPRESS INC.</Descr>"+
	"   </Carrier>"+
	"   <TransportAlreadyOrganized>true</TransportAlreadyOrganized>"+
	"   <Incoterm>"+
	"    <Code>FCA</Code>"+
	"    <Description>ORIGINE FOURNISSEUR</Description>"+
	"   </Incoterm>"+
	"   <RequestedShippingDate>2007-04-27</RequestedShippingDate>"+
	"   <RequestedDeliveryDate>2007-07-23</RequestedDeliveryDate>"+
	"   <TotalQty>"+
	"    <Value>112</Value>"+
	"    <UomPackaging>CS</UomPackaging>"+
	"   </TotalQty>"+
	"   <Vehicle>"+
	"    <Type>CST20</Type>"+
	"    <Descr>Standard Container 20'</Descr>"+
	"    <Indication>blue vehicule</Indication>"+
	"   </Vehicle>"+
	"  </Header>"+
	"  <Item>"+
	"   <LineNb>1</LineNb>"+
	"   <ProductCode>10495431</ProductCode>"+
	"   <Status>New</Status>"+
	"   <Descr>CABERNET/MERLOT HOMESTEAD GOUNDREY WESTERN AUSTRALIA</Descr>"+
	"   <Qty>"+
	"    <Value>112</Value>"+
	"    <UomPackaging>CS</UomPackaging>"+
	"    <BottleFormatInPackage>750</BottleFormatInPackage>"+
	"    <UomBottleFormat>ml</UomBottleFormat>"+
	"   </Qty>"+
	"   <Weight>"+
	"    <Value>1</Value>"+
	"    <Uom>KG</Uom>"+
	"   </Weight>"+
	"   <Volume>"+
	"    <Value>1</Value>"+
	"    <Uom>M3</Uom>"+
	"   </Volume>"+
	"   <TopSeller>0</TopSeller>"+
	"  </Item>"+
	" </PurchaseOrder>"+
	"</ShippingInstruction>";	
	
	String s3=sta.rff("../../../1.txt");
	s3=sta.rep(s3, "\r\n", "\"+\r\n\"");
	System.out.println(s3);
	//System.out.println(sendTransaction( s3, "xmlaxsun", "axsun321", "http://transportqa.globalwinespirits.com/wxosc"));
	//System.out.println(sendTransaction( s, "asxun", "temp123", "http://transportqa.globalwinespirits.com/wxosc"));

		
	}
	
    public static String sendTransaction( String pXml, String pUsername,
    		String pPassword, String pServerUrl ) throws Exception {

    		 

    		        StringBuffer wResult = new StringBuffer();

    		 

    		        try {
    		            URL wUrl;
    		            wUrl = new URL( pServerUrl );

    		 

    		            HttpURLConnection wConn = (HttpURLConnection)
    		wUrl.openConnection();

    		 

    		            //Authentification
    		            String wUser            = new String( pUsername + ":" +
    		pPassword );
    		            String wEncodedPassword = URLEncoder.encode( wUser );
    		           // String wEncoding        = new
    		//sun.misc.BASE64Encoder().encode( wUser.getBytes() );

    		 

    		   //         wConn.setRequestProperty("Authorization", "Basic " +
    		//wEncoding );
    		            wConn.setRequestProperty("Content-Type", "text/xml;	charset=UTF8" );

    		 

    		            wConn.setDoOutput( true );

    		 

    		            //Write the transaction
    		            PrintWriter wPw = new PrintWriter( wConn.getOutputStream()
    		);

    		 

    		            wPw.println(  pXml );
    		            wPw.close();

    		 


    		            // Read the result
    		            InputStreamReader wReader = new InputStreamReader(
    		wConn.getInputStream() );
    		            BufferedReader    wBuff   = new BufferedReader( wReader );

    		 

    		            String wUrlContent = null;

    		 

    		            while ( ( wUrlContent = wBuff.readLine() ) != null ) {
    		                wResult.append( wUrlContent );
    		                wResult.append( "\n" );
    		            }
    		            wBuff.close();
    		        }
    		        catch( IOException ioe ) {
    		            System.out.println( "Error reading URL:  "  +
    		ioe.getMessage() );
    		            ioe.printStackTrace();
    		        }
    		        catch( Exception e ) {
    		            System.out.println( "EXCEPTION CAUGHT e="  + e );
    		            e.printStackTrace();
    		        }

    		 

    		        return wResult.toString();
    		    }

    
	
	public static void test() throws Exception {
		SpreadsheetService service = new SpreadsheetService(
				"exampleCo-exampleApp-1");

		service.setUserCredentials("33@quicklydone.com", "quicklydone");
		URL metafeedUrl = new URL("http://spreadsheets.google.com/feeds/spreadsheets/private/full");
		SpreadsheetFeed feed = service.getFeed(metafeedUrl, SpreadsheetFeed.class);
		List spreadsheets = feed.getEntries();
		System.out.println("> "+spreadsheets.size());
		for (int i = 0; i < spreadsheets.size(); i++) {
		  SpreadsheetEntry spreadsheetEntry = (SpreadsheetEntry)spreadsheets.get(0);
		  System.out.println(">> \t-" + spreadsheetEntry.getTitle().getPlainText());
		  List worksheets = spreadsheetEntry.getWorksheets();
		  WorksheetEntry worksheet = (WorksheetEntry)worksheets.get(0);
		  ListEntry newEntry = new ListEntry();
		  newEntry.getCustomElements().setValueLocal("name1", "aaaa");
		 // ListEntry insertedRow = service.insert(feedUrl, newEntry);
		 service.insert(worksheet.getListFeedUrl(), newEntry);
		 
		 
		 ////////////////////
		 

		}
	}
}
