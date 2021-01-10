package core.facade;

import core.models.Bill;
import persist.DAOFactory;
import persist.dao.BillDAO;
import persist.dao.mysql.MySqlDAOFactory;

import java.util.Collection;

public class BillFacade {

    DAOFactory daoFactory = new MySqlDAOFactory();

    private BillDAO dao;

    private static BillFacade instance;

    private BillFacade(){
        this.dao = daoFactory.createBillDao();
    }

    public static BillFacade getInstance(){

        if(instance == null){
            instance = new BillFacade();
        }
        return instance;
    }

    public Collection<Bill> getUserBills(int userId){
        return dao.findAllUserBills(userId);
    }

    public Collection<Bill> getLoggedUserBills(){
        return dao.findAllUserBills(Integer.parseInt(UserFacade.getUserFacade().getLoggedUser().getId()));
    }
}
