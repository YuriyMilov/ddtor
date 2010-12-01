package a5_interface;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class test {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		Document doc = new Document();
		ByteArrayOutputStream baosPDF = new ByteArrayOutputStream();
		PdfWriter docWriter = null;
		docWriter = PdfWriter.getInstance(doc, baosPDF);

		// ...
		// Adding content to a Document is done with the add method.

		doc.open();

		doc.add(new Paragraph("This document was created by a class named: "));

		doc.add(new Paragraph("This document was created on "
				+ new java.util.Date()));

		// When you are done adding content, close the Document and PdfWriter
		// objects.

		doc.close();
		docWriter.close();
		// baosPDF.writeTo(System.out);
		File file = new File("test.pdf");
		FileOutputStream foStream = new FileOutputStream(file);
		System.out.println("Stream write into the file ...");
		baosPDF.writeTo(foStream);

	}

}
