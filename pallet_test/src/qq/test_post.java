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

public class test_post {

	public static void main(String[] args) throws Exception {

		System.out.println(postData("EM","EM70271","EMDataCon_test","Toronto","ON","New York","NY"));
	}

	public static String postData(String sUserID,String sPassword,String sAccount,String soCity,String soState,String doCity,String doState) throws Exception {
		URL endpoint = new URL("http://pcmws.alk.com/service.asmx");
		String s = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap12:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\">" +
				"<soap12:Body>" +
				"<PMWSGetReport xmlns=\"http://localhost/PCMilerWS/\">" +
				"<LoginInfo>" +
				//"<UserID>EM</UserID>" +
				//"<Password>EM70271</Password>" +
				//"<Account>EMDataCon_test</Account>" +
				"<UserID>"+sUserID+"</UserID>" +
				"<Password>"+sPassword+"</Password>" +
				"<Account>"+sAccount+"</Account>" +
				"</LoginInfo>" +
				"<TripOrigin>" +
				"<LocationInputType>" +
				//"<Zip>08540</Zip>" +
				"<City>"+soCity+"</City>" +
		        "<State>"+soState+"</State>" +
				"</LocationInputType>" +
				"</TripOrigin>" +
				"<TripDestination>" +
				"<LocationInputType>" +
				//"<Zip>46701</Zip>" +
				"<City>"+doCity+"</City>" +
		        "<State>"+doState+"</State>" +
				"</LocationInputType>" +
				"</TripDestination>" +
//				"<ReportType><string>d,m,s</string></ReportType>" +
				"<ReportType><string>m</string></ReportType>" +
				"</PMWSGetReport>" +
				"</soap12:Body></soap12:Envelope>";
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
		return sb.toString();
	}

}
