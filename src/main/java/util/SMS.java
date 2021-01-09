package util;

// Install the Java helper library from twilio.com/docs/java/install

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class SMS {
    // Find your Account Sid and Token at twilio.com/console
    // and set the environment variables. See http://twil.io/secure

    private static final String SMS_START = "SplitPay your validation code is : ";

    public static void main(String[] args) throws IOException {

    }

    public static void sendSMS(String phone,String message) throws IOException {
        FileInputStream file = new FileInputStream("src/main/resources/config.properties");
        Properties prop = new Properties();
        prop.load(file);
        String ACCOUNT_SID = prop.getProperty("ACCOUNT_SID");
        String AUTH_TOKEN = prop.getProperty("AUTH_TOKEN");
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        phone ="+33"+phone.substring(1);
        Message sms = Message.creator(
                new com.twilio.type.PhoneNumber(phone),
                new com.twilio.type.PhoneNumber("+14692990087"),
                SMS_START+message)
                .create();

        System.out.println("SMS succesfully sent to " + phone);
    }


}