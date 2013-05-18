package ru;

import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class sta {

	public static void re (HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		ServletOutputStream out = resp.getOutputStream();
		resp.setContentType("text/html; charset=UTF8");
		String sh = req.getScheme() + "://" + req.getServerName() + ":"
				+ req.getServerPort() + req.getContextPath();
		String s = Statik.rfu_utf(sh + "/ru.txt");
		String s1 = Statik.rfu_utf(sh + "/text.txt");
		s = s.replace("qq1>", "qq1>" + s1);
		s1 = Statik.rfu_utf(sh + "/vopros.txt");
		s = s.replace("name=\"sq\" value=\"\"", "name=\"sq\" value=\"" + s1
				+ "\"");

		byte[] b = s.getBytes("UTF8");
		out.write(b);
	}
	
	/*
	 * if (s5.indexOf(" ") == -1) {
					String[] stok = null;
					String phrase = null;
					String s8 = "[.]+";
					String delims = "[ ]+";
					s7 = st;
					s7 = s7.replace("Любой", "");
					s7 = s7.replace("Любая", "");
					s7 = s7.replace("Любое", "");
					s7 = s7.replace("Всякий", "");
					s7 = s7.replace("Всякая", "");
					s7 = s7.replace("Всякое", "");
					s7 = s7.replace("Каждый", "");
					s7 = s7.replace("Каждая", "");
					s7 = s7.replace("Каждое", "");
					s7 = s7.replace("есть", "");
					s7 = s7.replace("это", "-");
					String[] ss9 = s7.split(s8);
					s = open_rdf();
					{
						for (int i = 0; i < ss9.length; i++) {
							phrase = ss9[i].trim();
							stok = phrase.split(delims);

							if (stok.length == 2)
							// s = Statik.add_subclass(s, stok[1], stok[0]);
							{
								s = add_class_rdf(s, stok[1]);
								s = add_subclass_rdf(s, stok[0], stok[1]);
							}
							stok = phrase.split(delims);
							if (stok.length == 3)
								// s = Statik.add_classassertion(s, stok[2],
								// stok[0]);
								
								
								
								
///////////////////////////////////////////////
								
								s = add_inividual_rdf(s, stok[0], stok[2]);
							
							//if(stok[1].equals("-"))
							//add_a_has_b(s,stok[0], stok[1], stok[2]);
							
							
							
							
							
							
			/////////////////////////////////////////////////				
							
							
						}
					}
					// s = Statik.close_owl(s);
					s = close_rdf(s);
					clear_blobstore();

					Statik.s = s;
					
					s = wf("test.owl", s);
	 * */
}
