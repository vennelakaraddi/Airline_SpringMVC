package com.airline.controllers; 
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.view.document.AbstractPdfView;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import com.airline.bean.BookFlightBean;
 
public class PDFView extends AbstractPdfView {
protected void buildPdfDocument(Map model, Document document, PdfWriter writer, HttpServletRequest req, HttpServletResponse resp) throws Exception {
	BookFlightBean bfb = (BookFlightBean) model.get("command");
	Paragraph header = new Paragraph(new Chunk("Ticket Confirmed",FontFactory.getFont(FontFactory.HELVETICA, 30)));
	Paragraph by = new Paragraph(new Chunk("Customer Name   : " + bfb.getCustomername(),FontFactory.getFont(FontFactory.HELVETICA, 20)));
	Paragraph by1 = new Paragraph(new Chunk("Airline Name   : " + bfb.getAirlinename(),FontFactory.getFont(FontFactory.HELVETICA, 20)));
	Paragraph by2 = new Paragraph(new Chunk("From Location  : " + bfb.getFromlocation(),FontFactory.getFont(FontFactory.HELVETICA, 20)));
	Paragraph by3 = new Paragraph(new Chunk("To Location    : " + bfb.getTolocation(),FontFactory.getFont(FontFactory.HELVETICA, 20)));
	Paragraph by4 = new Paragraph(new Chunk("Flight Date    : " + bfb.getFlightdate(),FontFactory.getFont(FontFactory.HELVETICA, 20)));
	Paragraph by5 = new Paragraph(new Chunk("Total Tickets  : " + bfb.getTotaltickets(),FontFactory.getFont(FontFactory.HELVETICA, 20)));
	
	document.add(header);
	document.add(by);
	document.add(by1);
	document.add(by2);
	document.add(by3);
	document.add(by4);
	document.add(by5);
}
}