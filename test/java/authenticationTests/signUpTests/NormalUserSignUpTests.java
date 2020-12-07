package authenticationTests.signUpTests;

import core.UserFaçade;
import org.junit.Before;
import org.junit.Test;
import persist.models.NormalUser;

public class NormalUserSignUpTests {
    private UserFaçade user;

    @Before // setup()
    public void before() throws Exception {
        System.out.println("Setting it up!");
        user = UserFaçade.getUserFaçade();
    }

    @Test
    public void correctNormalUserEmailSignup() throws Exception{
        user.normalUserEmailSignUp("Test1@hotmail.test", "FirstName1","LastName1","TestUser1", "TestPassword");
    }

    @Test(expected = java.lang.Exception.class)
    public void duplicateNormalUserEmailSignup() throws Exception{
        user.normalUserEmailSignUp("Test2@hotmail.test", "FirstName2","LastName2","TestUser2", "TestPassword");
        user.normalUserEmailSignUp("Test2@hotmail.test", "FirstName2","LastName2","TestUser2", "TestPassword");
    }



//    @Test
//    public void correctNormalUserPhoneSignup() throws Exception{
//        user.phoneLogIn("0767865931","splitpay");
//    }
//
//    @Test(expected = persist.exception.PasswordLoginDAOException.class)
//    public void incorrectNormalUserPhoneSignup() throws Exception{
//        user.phoneLogIn("0767865931","wrongpassword");
//    }
}