package ma.ensa.lis.utils;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

import lombok.Getter;

import java.io.FileNotFoundException;

public class PDFGenerator {

    private String filePath;
    private String content;

    @Getter
    private PdfDocument pdfDocument;

    public PDFGenerator(String filePath, String content) {
        this.filePath = filePath;
        this.content = content;

        try {
            // Création du writer pour écrire dans le fichier PDF
            PdfWriter writer = new PdfWriter(filePath);

            // Initialisation du PdfDocument
            pdfDocument = new PdfDocument(writer);

            // Création du document avec le pdfDocument
            Document document = new Document(pdfDocument);

            // Ajouter le contenu au document
            document.add(new Paragraph(content));

            // Fermer le document après avoir ajouté le contenu
            document.close();

            System.out.println("Le fichier PDF a été généré avec succès à : " + filePath);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


}
