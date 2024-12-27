package ma.ensa.lis.utils;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.layout.properties.HorizontalAlignment;
import com.itextpdf.layout.properties.TextAlignment;
import lombok.Getter;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;

public class PDFGenerator {

    private String filePath;
    private String content;

    @Getter
    private PdfDocument pdfDocument;

    public PDFGenerator(String filePath, String content) {
        this.filePath = filePath;
        this.content = content;
        try {
            PdfWriter writer = new PdfWriter(filePath);

            // Initialisation du PdfDocument
            pdfDocument = new PdfDocument(writer);
            Document document = new Document(pdfDocument);
            String path = "C:/Users/ayoug/IdeaProjects/LIS/src/main/resources/logo.png";
            ImageData imageData=ImageDataFactory.create(path);
            Image image=new Image(imageData).setWidth(150).setHeight(100).setHorizontalAlignment(HorizontalAlignment.CENTER);
            document.add(image);
            Paragraph styledParagraph=new Paragraph(content).setFontSize(14).setBold().setItalic().setTextAlignment(TextAlignment.CENTER);
            document.add(styledParagraph);
            document.close();
            System.out.println("Le fichier PDF a été généré avec succès à : " + filePath);
        } catch (FileNotFoundException | MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String fileP="output.pdf";
        String cont="LABORATORY INFORMATION SYSTEM.";
        PDFGenerator pdfGenerator=new PDFGenerator(fileP,cont);
        if (pdfGenerator.getPdfDocument()!=null) {
            System.out.println("PDF généré avec succès !");
        } else {
            System.out.println("Une erreur est survenue lors de la génération du PDF.");
        }
    }
}
