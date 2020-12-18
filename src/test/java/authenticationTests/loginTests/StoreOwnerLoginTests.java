package authenticationTests.loginTests;

import core.facade.UserFacade;
import org.junit.Test;

public class StoreOwnerLoginTests {
    private UserFacade user = UserFacade.getUserFacade();
    @Test
    public void correctStoreOwnerEmailLogin() throws Exception{
        user.emailLogIn("delarte34@hotmail.com","delarte34");
    }

    @Test(expected = persist.exception.login.PasswordLoginDAOException.class)
    public void incorrectStoreOwnerEmailLogin() throws Exception{
        user.emailLogIn("delarte34@hotmail.com","wrongpassword");
    }

    @Test
    public void correctStoreOwnerPhoneLogin() throws Exception{
        user.phoneLogIn("0767342312","delarte34");
    }

    @Test(expected = persist.exception.login.PasswordLoginDAOException.class)
    public void incorrectStoreOwnerPhoneLogin() throws Exception{
        user.phoneLogIn("0767342312","wrongpassword");
    }
}