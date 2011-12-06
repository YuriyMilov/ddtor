package tmr;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jxl.Workbook;
import jxl.write.*;


public class jxl extends HttpServlet {
 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

public void doGet
   (HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
  OutputStream out = null;
  
  
  
  try {
   response.setContentType("application/vnd.ms-excel");
   response.setHeader
     ("Content-Disposition", "attachment; filename=sampleName.xls");
   WritableWorkbook w = 
     Workbook.createWorkbook(response.getOutputStream());
   WritableSheet ws = w.createSheet("Demo", 0);
   //s.addCell(new Label(0, 0, "Hello World"));

   
   writeDataSheet(ws);
   
   
   w.write();
   w.close();
  } 
  catch (Exception e){
   throw new ServletException("Exception in Excel Sample Servlet", e);
  } 
  finally{
   if (out != null)
    out.close();
  }
 }
 
 private static void writeDataSheet(WritableSheet sheet) 
 throws WriteException
{
	 String s=shta.rfu("http://localhost:8888/pcmws");
	 String[] ss = s.split("\r\n");


	    WritableFont wfobj=new WritableFont(WritableFont.TIMES, 10, WritableFont.BOLD);
	    WritableCellFormat cfobj=new WritableCellFormat(wfobj);
	    
	    cfobj.setShrinkToFit(true);
	    cfobj.setWrap(true);
	    WritableFont wfobj2=new WritableFont(WritableFont.COURIER, 10, WritableFont.NO_BOLD);
	    WritableCellFormat wcf=new WritableCellFormat(wfobj2);
	    wcf.setShrinkToFit(true);
	    wcf.setWrap(true);
	    Label lblName=new Label(0,0,"qqqqqqqq", cfobj);
	    sheet.addCell(lblName);
	    int j=0;
		 for (int i = 0; i < ss.length-1; i++)
		 {
			 
			 System.out.println(ss[i]);
		 
			    lblName=new Label(0,j,ss[i++],wcf);
			    sheet.addCell(lblName);	 
			    lblName=new Label(1,j,ss[i],wcf);
			    sheet.addCell(lblName);	  
	    j++;
	    
	   WritableCellFormat wcf1=new WritableCellFormat(NumberFormats.DEFAULT);
	   wcf1.setShrinkToFit(true);
	   wcf1.setWrap(true);
	  // i++;
//	   String sd=ss[i];
	//   i++;
	  // Number num=new Number(1,i,Double.parseDouble(sd), wcf1);
	   //sheet.addCell(num);
		 }

}
}