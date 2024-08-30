package com.ms.UserRecertification.service;


import com.ms.UserRecertification.model.User;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.task.TaskExecutionProperties;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

//import javax.activation.DataHandler;
//import javax.activation.DataSource;
//import javax.mail.*;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeBodyPart;
//import javax.mail.internet.MimeMessage;
//import javax.mail.internet.MimeMultipart;
//import javax.mail.util.ByteArrayDataSource;
import java.io.File;
import java.util.*;
import java.util.Properties;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

//    String from = "srivignesh.vv@olamnet.com";


    final String username = "harikrishna.t@mindsprint.org";//change accordingly
    final String password = "";//change accordingly

    String host = "smtp.office365.com";


    Properties props = new Properties();

//    Map<String, String> props = new HashMap<>();

    public void justEmail(String to) {
        String subject = "Hello world";
        String body = "Test";

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("harikrishna03092@gmail.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);

        mailSender.send(message);
        System.out.println("Mail Send");
//
//
    }


    public void sendEmail(String[] to,String[] cc, String excelPath) {

        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message,true);
            helper.setTo(to);
            helper.setCc(cc);
            helper.setSubject("User Data Report");
            helper.setText("please find the attached user data report");
            helper.setFrom("harikrishna03092@gmail.com");
            File file = new File(excelPath);
            helper.addAttachment(file.getName(), file);
            mailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.starttls.enable", "true");
//        props.put("mail.smtp.host", host);
//        props.put("mail.smtp.port", 587);
//
//        Authenticator auth = new Authenticator() {
//            public PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication(username, password);
//            }
//        };
//
//        Session session = Session.getInstance(props, auth);
//
//
//        // Construct the message
//        try {
//            // Create a default MimeMessage object
//            Message message = new MimeMessage(session);
//
//            // Set From: header field of the header.
//            message.setFrom(new InternetAddress(username));
//
//            // Set To: header field of the header.
//            message.setRecipients(Message.RecipientType.TO,
//                    InternetAddress.parse(to[0]));
//            message.setRecipients(Message.RecipientType.CC,
//                    InternetAddress.parse(cc[0]));
//
//            // Set Subject: header field
//            message.setSubject("Weekly Report");
//            message.setText("Hello, this is a test mail");
            // Create the message part
            //BodyPart messageBodyPart = new MimeBodyPart();

            // Now set the actual message
//	             messageBodyPart.setText("<html><body>Hi Team<br>"
//	             		+regObs+transObs+" <br> Regards,<br>Sri</body></html>");

           // messageBodyPart.setText("Test");



//
//            // Create a multipar message
//            Multipart multipart = new MimeMultipart();
//
//            // Set text message part
//            multipart.addBodyPart(messageBodyPart);
//
//            // Part two is attachment
//            messageBodyPart = new MimeBodyPart();
//
////            DataSource source = new ByteArrayDataSource(bytes, excelPath);
////
////            messageBodyPart.setDataHandler(new DataHandler(source));
////            messageBodyPart.setFileName(fileName);
//
//            multipart.addBodyPart(messageBodyPart);
//
//
//            // Send the complete message parts
//            message.setContent(multipart);



            // Send message
          //  Transport.send(message);

          //  System.out.println("Sent message successfully....");

        }








       /* MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message,true);
            helper.setTo(to);
            helper.setCc(cc);
            helper.setSubject("User Data Report");
            helper.setText("please find the attached user data report");
            helper.setFrom("harikrishna03092@gmail.com");
            File file = new File(excelPath);
            helper.addAttachment(file.getName(), file);
            mailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
*/

    }





//import org.springframework.stereotype.Service;
//
//import java.util.Properties;
//import javax.mail.Message;
//import javax.mail.MessagingException;
//import javax.mail.PasswordAuthentication;
//import javax.mail.Session;
//import javax.mail.Transport;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeMessage;
//
//
//@Service
//public class EmailService {
//    public static void main(String[] args) {
//        // Recipient's email ID needs to be mentioned.
//        String to = "recipient@example.com";
//
//        // Sender's email ID needs to be mentioned
//        String from = "your-email@example.com";
//        final String username = "your-email@example.com"; // change accordingly
//        final String password = "your-password"; // change accordingly
//
//        // Assuming you are sending email through relay.jangosmtp.net
//        String host = "smtp.office365.com";
//
//        Properties props = new Properties();
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.starttls.enable", "true");
//        props.put("mail.smtp.host", host);
//        props.put("mail.smtp.port", "587");
//
//        // Get the Session object.
//        Session session = Session.getInstance(props,
//                new javax.mail.Authenticator() {
//                    protected PasswordAuthentication getPasswordAuthentication() {
//                        return new PasswordAuthentication(username, password);
//                    }
//                });
//
//        try {
//            // Create a default MimeMessage object.
//            Message message = new MimeMessage(session);
//
//            // Set From: header field of the header.
//            message.setFrom(new InternetAddress(from));
//
//            // Set To: header field of the header.
//            message.setRecipients(Message.RecipientType.TO,
//                    InternetAddress.parse(to));
//
//            // Set Subject: header field
//            message.setSubject("Testing Subject");
//
//            // Now set the actual message
//            message.setText("Hello, this is a test email!");
//
//            // Send message
//            Transport.send(message);
//
//            System.out.println("Sent message successfully....");
//
//        } catch (MessagingException e) {
//            throw new RuntimeException(e);
//        }
//    }
//}



