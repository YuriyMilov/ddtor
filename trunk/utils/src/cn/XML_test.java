package cn;

import java.io.*;
import java.net.*;

public class XML_test {
	public static void main(String[] args) throws Exception {
		String s = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
				+ "<ShippingStatus xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">"
				+ " <DocumentId> "
				+ " <DateTime>2008-07-03T13:29:11.6381472+02:00</DateTime>"
				+ " <UniqueRefNb>n000007798889</UniqueRefNb>"
				+ " </DocumentId>"
				+ " <Sender>"
				+ " <Code Type=\"GWS\">axsun</Code>"
				+ " <Descr>Axsun Transport</Descr>"
				+ " </Sender>"
				+ " <Receiver>"
				+ " <Code Type=\"FreightForwarder\">saqvp</Code>"
				+ " <Descr>SOCIETE DES ALCOOLS DU QUEBEC</Descr>"
				+ " </Receiver>"
				+ " <PurchaseOrder>"
				+ " <Header>"
				+ " <DateTime>2008-07-03T13:29:58.0619159+02:00</DateTime>"
				+ " <PurchaseOrderNb>OP-62034</PurchaseOrderNb>"
				+ " <ShippingInstructionNb>"
				+ " <MarketplaceRefNb>45347789</MarketplaceRefNb>"
				+ " <ImporterRefNb>OP-62034</ImporterRefNb>"
				+ " <FreightForwarderRefNb>OP-62034</FreightForwarderRefNb>"
				+ " </ShippingInstructionNb>"
				+ " <Importer>"
				+ " <Company>"
				+ " <Code Type=\"FreightForwarder\">saqvp</Code>"
				+ " <CompanyName>SOCIETE DES ALCOOLS DU QUEBEC</CompanyName>"
				+ " <Coordinates>"
				+ " <Address>SERVICE TRANSPORT 2021 RUE DES FUTAILLES</Address>"
				+ " <City>MONTREAL</City>"
				+ " <StateProv>QUEBEC</StateProv>"
				+ " <ZipPc>H1N 3M7</ZipPc>"
				+ " <Country>CA</Country>"
				+ " </Coordinates>"
				+ " </Company>"
				+ " <Contact>"
				+ " <ContactName>Guerite / Reception</ContactName>"
				+ " <Phone>514-254-6000 ext.593</Phone>"
				+ " <Fax />"
				+ " <Email />"
				+ " </Contact>"
				+ " </Importer>"
				+ " <Carrier>"
				+ " <Code Type=\"Marketplace\">SUDU</Code>"
				+ " <Descr />"
				+ " </Carrier>"
				+ " <Supplier>"
				+ " <Company>"
				+ " <Code Type=\"FreightForwarder\" />"
				+ " <CompanyName>Goundrey Wines (Vincor Int.)</CompanyName>"
				+ " <Coordinates>"
				+ " <Address>Langton Muirs Highway</Address>"
				+ " <City>Mount Baker</City>"
				+ " <StateProv/>"
				+ " <ZipPc>6324</ZipPc>"
				+ " <Country>AU</Country>"
				+ " </Coordinates>"
				+ " </Company>"
				+ " <Contact>"
				+ " <ContactName></ContactName>"
				+ " <Phone>01161 898511777</Phone>"
				+ " <Fax>01161 898511997</Fax>"
				+ " <Email>export@vincor-au.ca</Email>"
				+ " </Contact>"
				+ " </Supplier>"
				+ " <FreightForwarder>"
				+ " <Company>"
				+ " <Code Type=\"FreightForwarder\">NZA</Code>"
				+ " <CompanyName>Axsun Transport Californie</CompanyName>"
				+ " <Coordinates>"
				+ " <Address>250 South Vasco Road</Address>"
				+ " <City>Livermore</City>"
				+ " <StateProv>CA</StateProv>"
				+ " <ZipPc>94551</ZipPc>"
				+ " <Country>US</Country>"
				+ " </Coordinates>"
				+ " </Company>"
				+ " <Contact>"
				+ " <ContactName>Amanda Warren</ContactName>"
				+ " <Phone />"
				+ " <Fax />"
				+ " <Email>a.warren@jfhillebrand.com</Email>"
				+ " </Contact>"
				+ " </FreightForwarder>"
				+ " <Vehicle>"
				+ " <Descr>20' Dry with Hillebrand Insulation</Descr>"
				+ " <EquipmentNb>CONTAINER101</EquipmentNb>"
				+ " </Vehicle>"
				+ " <Incoterm>"
				+ " <Code>FCA</Code>"
				+ " <Description>FREE CARRIER</Description>"
				+ " </Incoterm>"
				+ " <Events>"
				+ " <SupplierContactDate>2008-07-02</SupplierContactDate>"
				+ " <ReadyForPickupDate>2008-07-03</ReadyForPickupDate>"
				+ " </Events>"
				+ " <Manifest>"
				+ " <Nb />"
				+ " </Manifest>"
				+ " </Header>"
				+ " <Item>"
				+ " <LineNb>1</LineNb>"
				+ " <ProductCode>10495431</ProductCode>"
				+ " <Descr>CABERNET/MERLOT HOMESTEAD GOUNDREY WESTERN AUSTRALIA</Descr>"
				+ " <Qty>" + " <Value>112</Value>"
				+ " <UomPackaging>CS</UomPackaging>" + " </Qty>" + " </Item>"
				+ " </PurchaseOrder>" + "</ShippingStatus>";
		System.out.println(sendTransaction(s, "xmlaxsun", "axsun321",
				"http://transportqa.globalwinespirits.com/wxosc"));
	}

	public static String sendTransaction(String pXml, String pUsername,
			String pPassword, String pServerUrl) throws Exception {
		StringBuffer wResult = new StringBuffer();
		try {
			URL wUrl;
			wUrl = new URL(pServerUrl);
			HttpURLConnection wConn = (HttpURLConnection) wUrl.openConnection();
			// Authentification
			String wUser = new String(pUsername + ":" + pPassword);
			String wEncodedPassword = URLEncoder.encode(wUser);
			//String wEncoding = new sun.misc.BASE64Encoder().encode(wUser
			//		.getBytes());
			//wConn.setRequestProperty("Authorization", "Basic " + wEncoding);
			wConn.setRequestProperty("Content-Type", "text/xml; charset=UTF8");
			wConn.setDoOutput(true);
			// Write the transaction
			PrintWriter wPw = new PrintWriter(wConn.getOutputStream());
			wPw.println(pXml);
			wPw.close();
			// Read the result
			InputStreamReader wReader = new InputStreamReader(wConn
					.getInputStream());
			BufferedReader wBuff = new BufferedReader(wReader);
			String wUrlContent = null;
			while ((wUrlContent = wBuff.readLine()) != null) {
				wResult.append(wUrlContent);
				wResult.append("\n");
			}
			wBuff.close();
		} catch (IOException ioe) {
			System.out.println("Error reading URL: " + ioe.getMessage());
			ioe.printStackTrace();
		} catch (Exception e) {
			System.out.println("EXCEPTION CAUGHT e=" + e);
			e.printStackTrace();
		}
		return wResult.toString();
	}
}
