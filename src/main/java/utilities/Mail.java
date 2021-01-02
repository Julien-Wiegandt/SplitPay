package utilities;


import javax.mail.Session;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;


public class Mail {
    private static Session newSession;
    private static MimeMessage mimeMessage;

    public static void main(String[] args) throws MessagingException {
        Mail mail = new Mail();
        String email = "imadlaouani47@gmail.com";
        mail.sendEmail(email, "testtttttttttttttttttttttttttttttttt");
    }

    private static void setupServerProperties() {
        Properties properties = System.getProperties();
        properties.put("mail.smtp.port","587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable","true");
        newSession = Session.getDefaultInstance(properties,null);

    }

    private static MimeMessage draftEmail(String email, String message) throws MessagingException {
        String[] emailRecipients = {"imadlaouani47@gmail.com"};
        String emailSubject = "Splitpay :" + message;
        String body = message;
        mimeMessage = new MimeMessage(newSession);
        /*for (int i = 0; i < emailRecipients.length; i++) {
            mimeMessage.addRecipients(Message.RecipientType.TO, new InternetAddress[]{new InternetAddress(emailRecipients[i])});
        }*/
        mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
        //mimeMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse("imadlaouani47@gmail.com"));
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


    public static void sendEmail(String email, String message) throws  MessagingException {
        setupServerProperties();
        draftEmail(email, message);
        String fromUser = System.getenv("SPLIT_MAIL");
        System.out.println(fromUser);
        String fromUserpassword = System.getenv("SPLIT_MDP");
        System.out.println(fromUserpassword);
        String emailHost = "smtp.gmail.com";
        Transport transport = newSession.getTransport("smtp");
        transport.connect(emailHost,fromUser,fromUserpassword);
        System.out.println(mimeMessage);
        //System.out.println(mimeMessage.getAllRecipients());
        transport.sendMessage(mimeMessage,mimeMessage.getAllRecipients());
        transport.close();
        System.out.println("Email sent");


    }


    }

