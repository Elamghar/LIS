package ma.ensa.lis.controllers;
import javafx.embed.swing.SwingFXUtils;
import com.google.zxing.*;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import javafx.scene.paint.Color;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class GnenererLireBarcode {


    @FXML
    private TextField idage;

    @FXML
    private Button idchoisirimage;

    @FXML
    private Button idgenerer;

    @FXML
    private ImageView idimagechoisir;

    @FXML
    private ImageView idimagegenerer;

    @FXML
    private Button idlireimage;

    @FXML
    private TextField idnom;

    @FXML
    private TextField idprenom;

    @FXML
    private Label idresultat;

    @FXML
    private TextField idtest;


    @FXML
    void onChooseImageClick(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg", "*.jpeg"));
        File file = fileChooser.showOpenDialog(idchoisirimage.getScene().getWindow());

        if (file != null) {
            Image image = new Image(file.toURI().toString());
            idimagechoisir.setImage(image);
        }
    }

    @FXML
    void onGenerateBarcodeClick(ActionEvent event) {
        String nom = idnom.getText().trim();
        String prenom = idprenom.getText().trim();
        String age = idage.getText().trim();
        String test = idtest.getText().trim();

        if (nom.isEmpty() || prenom.isEmpty() || age.isEmpty() || test.isEmpty()) {
            idresultat.setText("Veuillez remplir tous les champs !");
            return; // Ne pas continuer si les champs sont vides
        }

        String barcodeData = nom + " " + prenom + " " + age + " " + test;

        try {
            Image barcodeImage = generateBarcode(barcodeData);
            idimagegenerer.setImage(barcodeImage); // Afficher le code-barres dans l'ImageView

            saveBarcodeToFile(barcodeImage);

            idresultat.setText("Code-barres généré avec succès!");

        } catch (Exception e) {
            e.printStackTrace();
            idresultat.setText("Erreur de génération du code-barres");
        }
    }


    private Image generateBarcode(String data) throws Exception {
        int width = 300;
        int height = 150;
        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.MARGIN, 0); // Pas de marge

        MultiFormatWriter writer = new MultiFormatWriter();
        BitMatrix bitMatrix = writer.encode(data, BarcodeFormat.CODE_128, width, height, hints);

        Image image = createImageFromMatrix(bitMatrix);
        return image;
    }

    private Image createImageFromMatrix(BitMatrix matrix) {
        int width = matrix.getWidth();
        int height = matrix.getHeight();
        javafx.scene.image.WritableImage image = new javafx.scene.image.WritableImage(width, height);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.getPixelWriter().setColor(x, y, matrix.get(x, y) ? Color.BLACK : Color.WHITE);
            }
        }
        return image;
    }

    private void saveBarcodeToFile(Image barcodeImage) {
        // Répertoire de destination
        String directoryPath = "C:/Users/21265/OneDrive/Documents/imagecodebar"; // zido hna chemin fin bghiito itstockaw les codes bares

        File directory = new File(directoryPath);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        String uniqueFileName = "barcode_" + System.currentTimeMillis() + ".png";

        File outputFile = new File(directory, uniqueFileName);

        try {
            javafx.scene.image.WritableImage writableImage = (javafx.scene.image.WritableImage) barcodeImage;
            java.awt.image.BufferedImage bufferedImage = javafx.embed.swing.SwingFXUtils.fromFXImage(writableImage, null);

            ImageIO.write(bufferedImage, "PNG", outputFile);
            idresultat.setText("Code-barres enregistré sous : " + outputFile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
            idresultat.setText("Erreur lors de l'enregistrement du fichier.");
        }
    }

    // Méthode pour générer un résultat ou afficher un message
    @FXML
    void onChooseImageClick2(ActionEvent event) {
        idresultat.setText("Code-barres généré avec succès!");
    }
    @FXML
    void onlireimageClick(ActionEvent event) {
        if (idimagechoisir.getImage() != null) {
            try {
                Image image = idimagechoisir.getImage();

                BufferedImage bufferedImage = SwingFXUtils.fromFXImage(image, null);

                BufferedImageLuminanceSource source = new BufferedImageLuminanceSource(bufferedImage);
                BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

                // Décoder le code-barres ou QR code
                MultiFormatReader reader = new MultiFormatReader();
                Result result = reader.decode(bitmap);

                System.out.println("Résultat du code-barres : " + result.getText());

                idresultat.setText("Résultat : " + result.getText());
            } catch (Exception e) {
                System.out.println("Erreur lors de la lecture du code-barres.");
                e.printStackTrace();

                idresultat.setText("Erreur : Impossible de lire l'image.");
            }
        } else {
            idresultat.setText("Veuillez d'abord choisir une image contenant un code-barres.");
        }
    }


}
