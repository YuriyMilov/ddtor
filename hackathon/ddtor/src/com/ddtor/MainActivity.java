package com.ddtor;

import java.io.InputStream;
import java.net.URL;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void qq(View view) {
	     // Kabloey
		
		TextView t = (TextView)findViewById(R.id.textView1);

		EditText tt   = (EditText)findViewById(R.id.editText1);
		
		String s1 = tt.getText().toString();
		//String stt=rfu("http://99.240.101.176/qq.txt");
		String s=rfu("http://ont.ddtor.com/qq");
		if(s.indexOf("<body>")>0)
			s = s.substring(s.indexOf("<body>")+6);
		if(s.indexOf("</body>")>0)
			s = s.substring(0,s.indexOf("</body>"));
		t.setText(s1 +" : "+s);
		 }
	
	   public static String rfu(String url) {
         //  StringBuffer s = new StringBuffer();
           StringBuilder s = new StringBuilder();
           try {
                   URL u = new URL(url);
                   InputStream in = u.openConnection().getInputStream();
                   for (int ch = in.read(); ch > 0; ch = in.read()) {
                           s.append((char) ch);
                   }
                   in.close();
           } catch (Exception e) {
                   return e.toString();
           }
           return s.toString();
   }
	   
	
}
