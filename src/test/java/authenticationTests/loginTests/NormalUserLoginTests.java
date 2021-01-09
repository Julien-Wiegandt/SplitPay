package authenticationTests.loginTests;

import core.facade.UserFacade;
import org.junit.Test;

public class NormalUserLoginTests {
    private final UserFacade user = UserFacade.getUserFacade();
    @Test
    public void correctNormalUserEmailLogin() throws Exception{
        user.emailLogIn("test@test.com","splitpay");
    }

    @Test(expected = persist.exception.login.PasswordLoginDAOException.class)
    public void incorrectNormalUserEmailLogin() throws Exception{
        user.emailLogIn("test@test.com","wrongpassword");
    }

    @Test
    public void correctNormalUserPhoneLogin() throws Exception{
        user.phoneLogIn("0767865931","splitpay");
    }

    @Test(expected = persist.exception.login.PasswordLoginDAOException.class)
    public void incorrectNormalUserPhoneLogin() throws Exception{
        user.phoneLogIn("0767865931","wrongpassword");
    }
}