package ma.ensa.lis.utils;

import ma.ensa.lis.utils.QRCodeGenerator;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import java.io.File;

class QRCodeGeneratorTest {

    @Test
    void testGenerateQRCode() {
        String data = "WALOU";
        String filePath = "test_qrcode.png"; // exemple
        QRCodeGenerator.generateQRCode(data, filePath, 300, 300);
        File qrFile = new File(filePath);
        assertTrue(qrFile.exists(), "QR Code file doit étre généré");
        qrFile.delete(); // Cleanup
    }
}

