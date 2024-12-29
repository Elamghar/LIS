package ma.ensa.lis.utils;

import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;

public class EmailSender {
    public void sendemail(String email){
        String recipient = email;
        String sender = "ayougilzakaria@gmail.com";
        String password = ""; //pass dial gmail b double auth


        String host = "smtp.gmail.com";

        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.port", "587");


        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(sender, password);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(sender));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            message.setSubject("Message Alert");

            BodyPart messageBodyPart1 = new MimeBodyPart();
            messageBodyPart1.setContent(
                    "<h1 style=\"color: #2c3e50; text-align: center; font-size: 24px; margin-bottom: 20px; font-family: Arial, sans-serif;\">"
                            + "Thank You for Visiting Our Laboratory</h1>"
                            + "<p style=\"font-size: 16px; font-family: Arial, sans-serif; color: #34495e; line-height: 1.5; margin-bottom: 15px;\">"
                            + "Dear Visitor,</p>"
                            + "<p style=\"font-size: 16px; font-family: Arial, sans-serif; color: #34495e; line-height: 1.5; margin-bottom: 15px;\">"
                            + "We are truly delighted to have had you visit our laboratory. It was an honor to showcase our work and share "
                            + "the passion and innovation that drives our team.</p>"
                            + "<p style=\"font-size: 16px; font-family: Arial, sans-serif; color: #34495e; line-height: 1.5; margin-bottom: 15px;\">"
                            + "Your interest and support mean so much to us. We hope you found the visit engaging, informative, and inspiring. "
                            + "If you have any questions or would like further details about our research or projects, please do not hesitate to reach out to us.</p>"
                            + "<p style=\"font-size: 16px; font-family: Arial, sans-serif; color: #34495e; line-height: 1.5; margin-bottom: 15px;\">"
                            + "Thank you again for your time and enthusiasm. We look forward to welcoming you again in the future and sharing "
                            + "even more about our work!</p>"
                            + "<p style=\"font-size: 16px; font-family: Arial, sans-serif; color: #34495e; line-height: 1.5; margin-top: 30px;\">"
                            + "Warm regards,</p>"
                            + "<p style=\"font-size: 16px; font-family: Arial, sans-serif; color: #34495e; line-height: 1.5;\">"
                            + "<strong style=\"color: #2c3e50;\">The Laboratory Team</strong></p>",
                    "text/html"
            );


            MimeBodyPart messageBodyPart2 =new MimeBodyPart();
            String filename ="output.pdf"; // Full path to the file
            DataSource source =new FileDataSource(filename);
            messageBodyPart2.setDataHandler(new DataHandler(source));
            messageBodyPart2.setFileName("medicalefile.pdf");
            Multipart multipart =new MimeMultipart();
            multipart.addBodyPart(messageBodyPart1);
            multipart.addBodyPart(messageBodyPart2);
            message.setContent(multipart);
            Transport.send(message);
            System.out.println("Message sent successfully...");
        } catch (MessagingException ex) {
            ex.printStackTrace();
        }
    }
}
