package persist.dao;

import core.models.CreditCard;


import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collection;

public abstract class CreditCardDAO {
    private Connection connection;

    public Connection getConnection(){
        return this.connection;
    }

    public abstract void createCreditCard(CreditCard creditCard);

    public abstract void deleteCreditCard(CreditCard creditCard);

    public abstract ArrayList<CreditCard> getCards();

    public abstract Collection<CreditCard> getCreditCards();

}
