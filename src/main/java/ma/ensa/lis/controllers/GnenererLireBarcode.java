package ma.ensa.lis.controllers;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import ma.ensa.lis.utils.SerialCommunicator;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.itextpdf.io.font.cmap.CMapContentParser.toHex;

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
    private void handleRetourClick(ActionEvent event) throws IOException {
        // Charger la première page (AjoutPatient-view.fxml)
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ma/ensa/lis/GenererBarcode.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 700, 600);


        // Afficher la première page
        stage.setTitle("LIS");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void handleLireCodeBarButtonClick(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ma/ensa/lis/LirecodeBar.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 700, 600);
        String css = Objects.requireNonNull(this.getClass().getResource("/ma/ensa/lis/ajoutpa_style.css")).toExternalForm();
        scene.getStylesheets().add(css);
        stage.setTitle("Lire Code Bar");
        stage.setScene(scene);
        stage.show();
    }
    String[] Lirelistinfotest() throws IOException {//lire info stocker sur foichier et supprimer ces info de fichier
        BufferedReader br = new BufferedReader(new FileReader("selectedTests.txt"));
        String line;
        String[] valeurs = new String[0];
        while ((line = br.readLine()) != null) {
            valeurs = line.split(",");
            return valeurs;
        }
        return null;
    }
    String[] Lirelistinfo() throws IOException {//lire info stocker sur foichier et supprimer ces info de fichier
        BufferedReader br = new BufferedReader(new FileReader("infosurpatient.txt"));
        String line;
        String[] valeurs = new String[0];
        while ((line = br.readLine()) != null) {
            valeurs = line.split(",");
            return valeurs;
        }
        return null;
    }
    public void ClearFile() {
            try (FileWriter fileWriter = new FileWriter("infosurpatient.txt")) {
                System.out.println("File contents cleared successfully.");
            } catch (IOException e) {
                System.err.println("An error occurred while clearing the file: " + e.getMessage());
            }
        try (FileWriter fileWriter = new FileWriter("selectedTests.txt")) {
            System.out.println("File contents cleared successfully.");
        } catch (IOException e) {
            System.err.println("An error occurred while clearing the file: " + e.getMessage());
        }
    }
    @FXML
    void onGenerateBarcodeClick(ActionEvent event) throws IOException {
        String[] val=Lirelistinfo();
        String[] tests=Lirelistinfotest();
        String nom = val[0];
        String prenom = val[1];
        String age = val[2];
        System.out.println(nom+"nomm de patient");
        System.out.println(prenom);
        System.out.println(age);
        int size =tests.length;
        String test = null;
        for (int i=0;i<size;i++) test=test+" "+tests[i];
        System.out.println(test);
//   = idtest.getText().trim();

        if (nom.isEmpty() || prenom.isEmpty() || age.isEmpty() || test.isEmpty()) {
//            idresultat.setText("Veuillez remplir tous les champs !");
            return; // Ne pas continuer si les champs sont vides
        }

        String barcodeData = nom + " " + prenom + " " + age + " " + test;

        try {
            Image barcodeImage = generateBarcode(barcodeData);
            idimagegenerer.setImage(barcodeImage); // Afficher le code-barres dans l'ImageView

            saveBarcodeToFile(barcodeImage);

//            idresultat.setText("Code-barres généré avec succès!");

        } catch (Exception e) {
            e.printStackTrace();
//            idresultat.setText("Erreur de génération du code-barres");
        }
    }

    public static String toHex(String input) {
        StringBuilder hexString = new StringBuilder();
        for (char c : input.toCharArray()) {
            hexString.append(String.format("%02X", (int) c));
        }
        return hexString.toString();
    }
    private Image generateBarcode(String data) throws Exception {
        int width = 300;
        int height = 250;
        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.MARGIN, 0); // Pas de marge

        MultiFormatWriter writer = new MultiFormatWriter();
        String hexData = toHex(data);
        BitMatrix bitMatrix =writer.encode(hexData, BarcodeFormat.CODE_128, width, height, hints);

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
        String directoryPath = "BareCodes/imagecodebar"; // zido hna chemin fin bghiito itstockaw les codes bares

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
//            idresultat.setText("Code-barres enregistré sous : " + outputFile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
//            idresultat.setText("Erreur lors de l'enregistrement du fichier.");
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


    public void returnn(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ma/ensa/lis/AjoutPatient-view.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 700, 600);
        String css = Objects.requireNonNull(this.getClass().getResource("/ma/ensa/lis/ajoutpa_style.css")).toExternalForm();
        scene.getStylesheets().add(css);
        stage.setTitle("LIS");
        stage.setScene(scene);
        stage.show();
    }

    public void logout(ActionEvent actionEvent) throws IOException {
        ClearFile();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ma/ensa/lis/login-view.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 700, 600);
        String css = Objects.requireNonNull(this.getClass().getResource("/ma/ensa/lis/STYLE.css")).toExternalForm();
        scene.getStylesheets().add(css);
        stage.setTitle("LIS");
        stage.setScene(scene);
        stage.show();
    }

    public void connectEsp(ActionEvent actionEvent) {
        SerialCommunicator.listAvailablePorts();
        SerialCommunicator communicator=new SerialCommunicator("COM3",115200);
        if (communicator.start()){
            communicator.sendMessage("Helloooooooooo!");
            try {
                Thread.sleep(30000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            communicator.stop();//w
        }
    }
}
