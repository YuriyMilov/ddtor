package tmr;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.soap.Text;

import jxl.Workbook;
import jxl.write.*;
import jxl.write.Number;

import jxl.write.DateFormats;
import java.util.Date;


public class jxl_old extends HttpServlet {
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
   WritableSheet s = w.createSheet("Demo", 0);
   //s.addCell(new Label(0, 0, "Hello World"));

   
   writeDataSheet(s);
   
   
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

	    WritableFont wfobj=new WritableFont(WritableFont.TIMES, 10, WritableFont.BOLD);
	    WritableCellFormat cfobj=new WritableCellFormat(wfobj);
	    
	    cfobj.setShrinkToFit(true);
	    cfobj.setWrap(true);
	    WritableFont wfobj2=new WritableFont(WritableFont.COURIER, 10, WritableFont.NO_BOLD);
	    WritableCellFormat wcf=new WritableCellFormat(wfobj2);
	    wcf.setShrinkToFit(true);
	    wcf.setWrap(true);
	    Label lblName=new Label(0,0,"Names", cfobj);
	    Label lblFloatNum1=new Label(1,0,"Floatnum1",cfobj);
	    Label lblFloatNum2=new Label(2,0,"Floatnum2", cfobj);
	    Label lblFormula=new Label(3,0,"Formulas", cfobj);
	    Label lblDate=new Label(4,0,"Dates", cfobj);

	    sheet.addCell(lblName);
	    lblName=new Label(0,1,"Ram",wcf);
	    sheet.addCell(lblName);
	    lblName=new Label(0,2,"Krishna", wcf);
	    sheet.addCell(lblName);
	    lblName=new Label(0,3,"Shiva", wcf);
	    sheet.addCell(lblName);
	    sheet.addCell(lblFloatNum1);
	    WritableCellFormat wcf1=new WritableCellFormat(NumberFormats.DEFAULT);

	    wcf1.setShrinkToFit(true);
	    wcf1.setWrap(true);
	    Number num=new Number(1,1,4.567778, wcf1);
	    sheet.addCell(num);
	    num=new Number(1,2, 5.657657, wcf1);
	    sheet.addCell(num);
	    num=new Number(1,3, 14.45456, wcf1);
	    sheet.addCell(num);
	    sheet.addCell(lblFloatNum2);
	    num=new Number(2,1,5.6787, wcf1);
	    sheet.addCell(num);
	    num=new Number(2,2, 39.56576, wcf1);
	    sheet.addCell(num);
	    num=new Number(2, 3, 7.000, wcf1);
	    sheet.addCell(num);
	    sheet.addCell(lblFormula);
	    WritableCellFormat wcfmuls=new WritableCellFormat();
	    Formula fmla=new Formula(3, 1, "SUM(B2:C2)", wcfmuls);
	    sheet.addCell(fmla);
	    fmla=new Formula(3, 2, "SUM(B3:C3)", wcfmuls);
	    sheet.addCell(fmla);
	    fmla=new Formula(3, 3, "SUM(B4:C4)", wcfmuls);
	    sheet.addCell(fmla);
	    sheet.addCell(lblDate);
	    WritableCellFormat wcfdate=new WritableCellFormat(DateFormats.FORMAT3);

	    wcfdate.setWrap(true);
	    DateTime dtobj=new DateTime(4, 1, new Date(), wcfdate);
	    sheet.addCell(dtobj);
	    dtobj=new DateTime(4, 2, new Date(), wcfdate);
	    sheet.addCell(dtobj);
	    dtobj=new DateTime(4, 3, new Date(), wcfdate);
	    sheet.addCell(dtobj);
}
}