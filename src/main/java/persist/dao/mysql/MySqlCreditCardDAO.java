package persist.dao.mysql;


import core.facade.UserFacade;
import core.models.CreditCard;

import persist.dao.CreditCardDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class MySqlCreditCardDAO extends CreditCardDAO {

    public MySqlCreditCardDAO(){super();}


    public void createCreditCard(CreditCard creditCard) {
        Statement stmt = null;
        try {
            stmt = ConnectionMySql.connection.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            System.out.println("création..");
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Integer rs = stmt.executeUpdate("INSERT INTO CreditCard VALUES (NULL, '"+creditCard.getNumber()+"', '"+creditCard.getNameOwner()+"','"+ dateFormat.format(creditCard.getDate())+"', '"+creditCard.getCvv()+"', '"+ UserFacade.getUserFacade().getLoggedUser().getId()+"')");


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }


    public void deleteCreditCard(CreditCard creditCard) {
        Statement stmt = null;

        try {
            stmt = ConnectionMySql.connection.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        try {
            System.out.println("suppression...");

            stmt.executeUpdate("DELETE FROM CreditCard WHERE number = '" + creditCard.getNumber() + "'");
        }catch(SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public ArrayList<CreditCard> getCards() {
        ArrayList<CreditCard> ccs = new ArrayList<CreditCard>();

        Statement stmt = null;
        CreditCard creditCard= null;
        Date date = null;
        try {
            stmt = ConnectionMySql.connection.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            ResultSet rs = stmt.executeQuery("SELECT * FROM CreditCard WHERE normal_user_fk='" + UserFacade.getUserFacade().getLoggedUser().getId() + "'");

            while (rs.next()) {
                String dbId = rs.getString("credit_card_pk");
                String dbNumber = rs.getString("number");
                String dbCardName = rs.getString("cardName");
                String dbDate = rs.getString("date");
                try {
                    date = new SimpleDateFormat("yyyy-MM-dd").parse(dbDate);
                }catch(Exception e){
                    e.printStackTrace();
                }
                String dbcvv = rs.getString("cvv");


                creditCard = new CreditCard(dbId, dbNumber, dbCardName, date, dbcvv);
                //System.out.println(creditCard);
                ccs.add(creditCard);
            }
        }catch(SQLException throwables){
            throwables.printStackTrace();
        }
        return ccs;
    }

    public Collection<CreditCard> getCreditCards(){
        Statement stmt = null;
        ArrayList<CreditCard> creditCards = new ArrayList<CreditCard>();
        try {
            stmt = ConnectionMySql.connection.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            ResultSet rs = stmt.executeQuery("SELECT * FROM CreditCard WHERE normal_user_fk=" + UserFacade.getUserFacade().getUser().getId() + ";");

            while (rs.next()) {
                String dbId = rs.getString("credit_card_pk");
                String number = rs.getString("number");
                String nameOwner = rs.getString("cardName");
                Date date = rs.getDate("date");
                String cvv = rs.getString("cvv");

                creditCards.add(new CreditCard(dbId, number, nameOwner, date, cvv));
            }
        }catch(SQLException throwables){
            throwables.printStackTrace();
        }
        return creditCards;
    }

}
