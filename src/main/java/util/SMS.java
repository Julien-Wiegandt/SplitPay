package util;

// Install the Java helper library from twilio.com/docs/java/install

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;

public class SMS {
    // Find your Account Sid and Token at twilio.com/console
    // and set the environment variables. See http://twil.io/secure
    public static final String ACCOUNT_SID = System.getenv("ACCOUNT_SID");
    public static final String AUTH_TOKEN = System.getenv("AUTH_TOKEN");
    private static final String SMS_START = "SplitPay your validation code is : ";

    public static void main(String[] args) {
        System.out.println(System.getenv("ACCOUNT_SID"));
        String tel = "+33783312483";
        String message = " et ouiiii aaaaaa";
        SMS.sendSMS(tel,message);
    }

    public static void sendSMS(String phone,String message){
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message sms = Message.creator(
                new com.twilio.type.PhoneNumber(phone),
                new com.twilio.type.PhoneNumber("+14692990087"),
                SMS_START+message)
                .create();

        System.out.println(sms.getSid());
    }


}