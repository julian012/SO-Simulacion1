package persistence;

import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import models.Process;
import models.ProcessInfo;
import utilities.Utilities;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;

public class CreatePDF {
    private static final Font chapterFont = FontFactory.getFont(FontFactory.HELVETICA, 16, Font.BOLD);
    private static final Font paragraphFont = FontFactory.getFont(FontFactory.HELVETICA, 12, Font.NORMAL);
    private static String FILE = System.getProperty("user.home") + "/Reporte Simulacion.pdf";

    private static void addMetaData(Document document) {
        document.addTitle("My first PDF");
        document.addSubject("Using iText");
        document.addKeywords("Java, PDF, iText");
        document.addAuthor("Lars Vogel");
        document.addCreator("Lars Vogel");
        document.left(100f);
        document.top(150f);

    }

    private static void addTitlePage(Document document) throws DocumentException {
        Paragraph preface = new Paragraph();
        // We add one empty line
        addEmptyLine(preface, 1);
        // Lets write a big header
        preface.add(new Paragraph("Reporte simulaci"+"\u00f3"+"n procesador", chapterFont));

        addEmptyLine(preface, 1);
        // Will create: Report generated by: _name, _date
        preface.add(new Paragraph(
                "Reporte generado por: " + System.getProperty("user.name") + ", " + new Date(), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                paragraphFont));
        addEmptyLine(preface, 3);
        document.add(preface);
        // Start a new page
        //document.newPage();
    }

    private static void addContent(Document document, ArrayList<ProcessInfo> list) throws DocumentException {
       // add a table
        for (int i = 0; i< list.size() ; i++){
            createTable(document,list.get(i));
        }
    }

    private static void createTable(Document document, ProcessInfo info) throws DocumentException{
        Paragraph preface = new Paragraph();
        addEmptyLine(preface,1);
        preface.add(new Paragraph(info.getName(), chapterFont));
        System.out.println(info.getName());
        addEmptyLine(preface, 1);
        PdfPTable table = new PdfPTable(8);

        // table.setBorderColor(BaseColor.GRAY);
        // t.setPadding(4);
        // t.setSpacing(4);
        // t.setBorderWidth(1);

        PdfPCell c1 = new PdfPCell(new Phrase("Prioridad"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Nombre procesador"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Tiempo"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);
        
        c1 = new PdfPCell(new Phrase("En ejecución"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Bloqueado"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);
        table.setHeaderRows(1);
        
        c1 = new PdfPCell(new Phrase("Destruir"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);
        table.setHeaderRows(1);
        
        c1 = new PdfPCell(new Phrase("Suspender"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);
        table.setHeaderRows(1);
        
        c1 = new PdfPCell(new Phrase("Comunicar"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);
        table.setHeaderRows(1);

        for (int i = 0; i< info.getProcessList().size(); i++){
            Process process = info.getProcessList().get(i);
            table.addCell(String.valueOf(process.getNewProcessPriority()));
            table.addCell(process.getProcessName());
            table.addCell(Utilities.booleanToString(process.isExcecute()));
            table.addCell(String.valueOf(Utilities.quitNegativeNumbers(process.getProcessTime())));
            table.addCell(Utilities.booleanToString(process.isProcessBlock()));
            table.addCell(Utilities.booleanToString(process.isProcessDestroy()));
            table.addCell(Utilities.booleanToString(process.isProcessLayoff()));
            table.addCell(process.getConnectProcess());
        }
        preface.add(table);
        document.add(preface);


    }


    private static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }

    public void createReport(ArrayList<ProcessInfo> list) throws DocumentException, IOException {
            Document document = new Document(PageSize.A4.rotate());
            FileOutputStream pdf = new FileOutputStream(FILE);
            PdfWriter.getInstance(document,pdf);
            document.open();
            addMetaData(document);
            addTitlePage(document);
            addContent(document, list);
            document.close();
            File f = new File(FILE);
            Desktop.getDesktop().open(f);
    }
}
