package core.facade;

import core.models.Bill;
import persist.DAOFactory;
import persist.dao.BillDAO;
import persist.dao.mysql.MySqlDAOFactory;

import java.util.Collection;

public class BillFacade {

    DAOFactory factory = new MySqlDAOFactory();

    BillDAO dao = factory.createBillDao();

    public Collection<Bill> getUserBills(){
        return null;
    }
}
