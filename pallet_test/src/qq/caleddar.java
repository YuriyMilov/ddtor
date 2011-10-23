package qq;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class caleddar {


	public static void main(String[] args) throws Exception {

		Calendar cal2 = Calendar.getInstance(TimeZone.getTimeZone("EDT"));
		String s=cal2.getTime().toString();
		
		TimeZone tz=TimeZone.getTimeZone("America/Montreal");
		SimpleDateFormat parser = new SimpleDateFormat(
		"MMM dd, yyyy 'at' HH:mm:ss z");
		//Date d = parser.parse("Oct 25, 2007 at 18:35:07 EDT");
		Date d = Calendar.getInstance(TimeZone.getTimeZone("EST")).getTime();
		SimpleDateFormat formatter = new SimpleDateFormat(
		"yyyy/MM/dd  HH:mm:ss z'('Z')'");
		formatter.setTimeZone(tz);
		String sdt=formatter.format(d);
		
		System.err.println(sdt);
	}

}
