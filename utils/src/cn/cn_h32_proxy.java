package cn;


import java.net.*;
import java.io.*;

public class cn_h32_proxy {
	public static void main(String args[]) throws Exception {
		StringBuffer sbuf = null;
		System.out.println("--- server started ---");
		ServerSocket serso = new ServerSocket(8080);
		Socket socket = null;
		String sb = "";
		int k = 1;
		while (true) {
			String s = "qq", ss = "";
			socket = serso.accept();
			DataInputStream get_in = new DataInputStream(socket
					.getInputStream());
			BufferedReader br = new BufferedReader(
					new InputStreamReader(get_in));
			try {
				while (!s.equals("")) {
					s = br.readLine();
					//System.out.println(s);
					ss = ss + s + "\r\n";
				}
				System.out.println("\r\n--- reading request ---\r\n");
				if (ss.toUpperCase().indexOf("POST ") == 0) {
					String si = ss.substring(ss.indexOf("Content-Length: "));
					si = si.substring(16, si.indexOf("\r\n"));
					int i = Integer.parseInt(si);
					sbuf = new StringBuffer();
					while (i-- > 0) {
						char c = (char) br.read();
						sbuf.append(c);
						System.out.print(c);
					}
					sb = sbuf.toString();
				}
				ss = ss + sb + "\r\n";
				//System.out.print(ss);
				//
				ss = sta.rep(ss, "Accept-Encoding: gzip,deflate\r\n", "");
				//sta.wtf(ss, "_1/kukafile_" + k++ + ".txt");
				//
				
				
				
				
				String host = ss.substring(ss.indexOf("//") + 2);
				host = host.substring(0, host.indexOf("/"));
				
				
				System.out.println("------\r\n" + ss + "\r\n---------");
				System.out.println("\r\nHost: > " + host + "\r\n");
				
				
				
				Socket js = new Socket(host, 80);
				PrintStream outStream = new PrintStream(js.getOutputStream());
				outStream.println(ss);
				outStream.flush();
				
				

				System.out.println("===  read response  ===");
				BufferedInputStream bis = new BufferedInputStream(js
						.getInputStream());
				BufferedOutputStream bos = new BufferedOutputStream(socket
						.getOutputStream());


				int b;
				boolean b13 = false;
				sbuf = new StringBuffer();
				try {
					while ((b = bis.read()) != -1) {
						bos.write(b);
						System.out.print((char)b);
						if (b13)
							//bos2.write(b);
						sbuf.append((char) b);
					}
				} catch (Exception e) {
					System.err.println(e);
				}
				System.err.println(sbuf);
				bis.close();
				bos.flush();
				bos.close();
				js.close();
				outStream.close();
			} catch (Exception e1) {
				System.err.println(e1);
			}
			System.out.println("===    FINISH    ===\r\n");
		}
	}

}
