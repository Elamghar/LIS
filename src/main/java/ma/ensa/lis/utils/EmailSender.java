package ma.ensa.lis.utils;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class EmailSender {
    public static void main(String[] args) {
        String recipient="ayougilzaki@gmail.com";
        String sender="ayougilzakaria@gmail.com";
        String password="";
        String host="smtp.gmail.com";
        Properties properties =new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "587");
        Session session =Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(sender, password);
            }
        });

        try {
            MimeMessage message =new MimeMessage(session);
            message.setFrom(new InternetAddress(sender));
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(recipient));
            message.setSubject("This is Subject");
            message.setContent(
                    "<h1>Thank You for Visiting Our Laboratory</h1>" +
                            "<p>Dear Visitor,</p>" +
                            "<p>We sincerely appreciate your time and interest in visiting our laboratory. " +
                            "It was a pleasure to have you with us, and we hope you found the experience insightful and inspiring.</p>" +
                            "<p>Your support and enthusiasm mean a lot to us, and we are grateful for the opportunity to share our work with you. " +
                            "Should you have any questions or need further information, please don't hesitate to reach out.</p>" +
                            "<p>Thank you once again for your visit. We look forward to welcoming you again in the future!</p>" +
                            "<p>Best regards,</p>" +
                            "<p><strong>The Laboratory Team</strong></p>",
                    "text/html"
            );
            Transport.send(message);
            System.out.println("Mail successfully sent!");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}
