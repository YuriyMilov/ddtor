package zz;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class qq {
	
	//pdf charset setup проверка фонтов

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		String str="";
		URL u = new URL("file:///" + System.getProperties().getProperty("user.dir")
				+ "/1.txt");
		BufferedReader in = new BufferedReader(new InputStreamReader(u
				.openStream(), "CP1251"));// enc - c, UTF-8, so on
		StringBuffer s = new StringBuffer();
	
			boolean qq=false;
			//InputStream in = u.openConnection().getInputStream();
			for (int ch = in.read(); ch > 0; ch = in.read()) {
				//s.append((char) ch);
				if(ch<129)
					s.append((char)ch);
				if(ch==1038) s.append("&#1040;");
				if(ch==1032) s.append("&#1042;");
				if(ch==164) s.append("&#1043;");
				if(ch==1168) s.append("&#1044;");
				if(ch==1025) s.append("&#1046;");
				if(ch==169) s.append("&#1047;");
				if(ch==1028) s.append("&#1048;");
				

				
				if(ch==172) s.append("&#1050;");
				if(ch==173) s.append("&#1051;");
				if(ch==174) s.append("&#1052;");
				if(ch==1031) s.append("&#1053;");
				if(ch==176) s.append("&#1054;");
				if(ch==177) s.append("&#1055;");
				if(ch==1030) s.append("&#1056;");
				if(ch==1110) s.append("&#1057;");
				if(ch==1169) s.append("&#1058;");
				if(ch==181) s.append("&#1059;");
				if(ch==182) s.append("&#1060;");
				if(ch==183) s.append("&#1061;");
				
				if(ch==8470) s.append("&#1063;");
				if(ch==1108) s.append("&#1064;");
				if(ch==1111) s.append("&#1069;");
							
				
				if(ch==1040) s.append("&#1070;");
				if(ch==1041) s.append("&#1071;");
				if(ch==1042) s.append("&#1072;");
				if(ch==1043) s.append("&#1073;");
				if(ch==1044) s.append("&#1074;");
				if(ch==1045) s.append("&#1075;");
				if(ch==1046) s.append("&#1076;");
				if(ch==1047) s.append("&#1077;");
				if(ch==1048) s.append("&#1077;");
				if(ch==1049) s.append("&#1078;");
				if(ch==1050) s.append("&#1079;");
				if(ch==1051) s.append("&#1080;");
				if(ch==1052) s.append("&#1081;");
				if(ch==1053) s.append("&#1082;");
				if(ch==1054) s.append("&#1083;");
				if(ch==1055) s.append("&#1084;");
				if(ch==1056) s.append("&#1085;");
				if(ch==1057) s.append("&#1086;");
				if(ch==1058) s.append("&#1087;");
				if(ch==1059) s.append("&#1088;");
				if(ch==1060) s.append("&#1089;");
				if(ch==1061) s.append("&#1090;");
				if(ch==1062) s.append("&#1091;");
				if(ch==1063) s.append("&#1092;");
				if(ch==1064) s.append("&#1093;");
				if(ch==1065) s.append("&#1094;");
				if(ch==1066) s.append("&#1095;");
				if(ch==1067) s.append("&#1096;");
				if(ch==1068) s.append("&#1097;");
				if(ch==1069) s.append("&#1098;");
				if(ch==1070) s.append("&#1099;");
				if(ch==1071) s.append("&#1100;");
				if(ch==1072) s.append("&#1101;");
				if(ch==1073) s.append("&#1102;");
				if(ch==1074) s.append("&#1103;");
				if(ch==1084) s.append(" - ");
				if(ch==1118) s.append("&#1041;");
			
			
				
				/*
				 * 
				 
				Б 	
				В 	
				Г 	
				Д 	
				Е 	&#1045;


З 	1048&#1047;
И 	
Й 	&#1049;
К 	
Л 	
М 	
Н 	
О 	
П 	
С 	
Т 	
У 	&#1059;
у 	
Ф 	&#1060;
Х 	
х 	&#1093;
Ц 	&#1062;
ш 	&#1096;
Щ 	&#1065;
щ 	&#1097;
Ъ 	&#1066;
ъ 	&#1098;
Ы 	&#1067;
Ь 	&#1068;
ь 	&#1100;
Ю 	&#1070;
Я 	&#1071;

*/
				
				System.out.println(ch);
			}
			in.close();
		sta.wtf_utf("<meta http-equiv=\"content-type\" content=\"text/html;charset=utf-8\" />\r\n"+s.toString().replace("\r\n", "<br>\r\n"),"2.htm");
		System.out.println("\r\n------ OK -------");
	}

}
//"q                  q      kk";
//s = s.replaceAll("\\s+", " ");
//s=sta.rffw("1.txt");

/*
А 	&#1040;
а 	&#1072;42
Б 	&#1041;
б 	&#1073;
В 	&#1042;
в 	&#1074;
Г 	&#1043;
г 	&#1075;
Д 	&#1044;
д 	&#1076;
Е 	&#1045;
е 	&#1077;47
Ж 	&#1046;
ж 	&#1078;
З 	&#1047;
з 	&#1079;
И 	&#1048;
и 	&#1080;
Й 	&#1049;
й 	&#1081;
К 	&#1050;
к 	&#1082;
Л 	&#1051;
л 	&#1083;
М 	&#1052;
м 	&#1084;55

Н 	&#1053;
н 	&#1085;56
О 	&#1054;
о 	&#1086;57
П 	&#1055;
п 	&#1087;
Р 	&#1056;30
р 	&#1088;59
С 	&#1057;
с 	&#1089;60
Т 	&#1058;
т 	&#1090;1061
У 	&#1059;
у 	&#1091;
Ф 	&#1060;
ф 	&#1092;
Х 	&#1061;
х 	&#1093;
Ц 	&#1062;
ц 	&#1094;
Ч 	&#1063;
ч 	&#1095;
Ш 	&#1064;
ш 	&#1096;
Щ 	&#1065;
щ 	&#1097;
Ъ 	&#1066;
ъ 	&#1098;
Ы 	&#1067;
ы 	&#1099;
Ь 	&#1068;
ь 	&#1100;
Э 	&#1069;
э 	&#1101;
Ю 	&#1070;
ю 	&#1102;
Я 	&#1071;
я 	&#1103;

*/