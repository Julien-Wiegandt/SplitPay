package persist.dao;

import core.models.CreditCard;


import java.sql.Connection;
import java.util.ArrayList;

public abstract class CreditCardDAO {
    private Connection connection;

    public Connection getConnection(){
        return this.connection;
    }

    public abstract void createCreditCard(CreditCard creditCard);

    public abstract void deleteCreditCard(CreditCard creditCard);

    public abstract ArrayList<CreditCard> getCards();
}
