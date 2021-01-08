package util;


import javax.mail.Session;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class Mail {
    private static Session newSession;
    private static MimeMessage mimeMessage;
    private static Properties prop;

    public static void main(String[] args) throws MessagingException, IOException {

    }

    private static void setupServerProperties() throws IOException {

        FileInputStream file = new FileInputStream("src/main/resources/config.properties");
        Properties prop = new Properties();
        prop.load(file);
        Mail.prop = prop;
        Properties properties = System.getProperties();
        properties.put("mail.smtp.port","587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable","true");
        newSession = Session.getDefaultInstance(properties,null);

    }

    private static MimeMessage draftEmail(String email, String message) throws MessagingException {
        String emailSubject = "Splitpay :" + message;
        String body = message;
        mimeMessage = new MimeMessage(newSession);
        mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
        mimeMessage.setSubject(emailSubject);
        MimeMultipart multipart = new MimeMultipart();
        MimeBodyPart bodyPart = new MimeBodyPart();
        try {
            bodyPart.setContent(body, "html/text");
            Multipart multiPart = new MimeMultipart();
            multiPart.addBodyPart(bodyPart);
            mimeMessage.setContent(multiPart);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return mimeMessage;

    }


    public static void sendEmail(String email, String message) throws MessagingException, IOException {
        setupServerProperties();
        draftEmail(email, message);
        String fromUser = prop.getProperty("SPLIT_MAIL");
        String fromUserpassword = prop.getProperty("SPLIT_MDP");
        String emailHost = "smtp.gmail.com";
        Transport transport = newSession.getTransport("smtp");
        transport.connect(emailHost,fromUser,fromUserpassword);
        transport.sendMessage(mimeMessage,mimeMessage.getAllRecipients());
        transport.close();
        System.out.println("Email succesfully sent to " + email);


    }


    }

