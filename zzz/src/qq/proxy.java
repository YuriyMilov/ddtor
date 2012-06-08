package qq;


import java.net.*;
import java.io.*;

public class proxy {

	public static void main(String args[]) throws Exception {

		Socket socket = (new ServerSocket(80)).accept();
		DataInputStream get_in = new DataInputStream(socket
				.getInputStream());
		while (true) {
			char cc=(char)get_in.read();
			System.out.print(cc);
			 
		}		
	}
}
