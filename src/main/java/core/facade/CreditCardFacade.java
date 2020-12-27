package core.facade;

import core.models.CreditCard;
import persist.DAOFactory;
import persist.dao.CreditCardDAO;
import persist.dao.mysql.MySqlDAOFactory;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;

public class CreditCardFacade {


    private CreditCardDAO creditCardDAO;

    private static CreditCardFacade creditCardFacade;

    private static DAOFactory daoFactory = new MySqlDAOFactory();


    public static CreditCardFacade getInstance(){
        if(creditCardFacade==null){
            creditCardFacade = new CreditCardFacade();
        }
        return creditCardFacade;
    }

    private CreditCardFacade(){

        this.creditCardDAO = daoFactory.createCreditCardDao();
    }

    public void createCreditCard(String number, String nameOwner, Date date, String cvv){
        CreditCard creditCard = new CreditCard(null,number,nameOwner, date, cvv);
        this.creditCardDAO.createCreditCard(creditCard);

    }

    public void deleteCreditCard(String number, String nameOwner, Date date, String cvv){
        CreditCard creditCard = new CreditCard(null,number,nameOwner, date, cvv);
        this.creditCardDAO.deleteCreditCard(creditCard);
    }

    public ArrayList<CreditCard> getCards() {
        return this.creditCardDAO.getCards();
    }
}
