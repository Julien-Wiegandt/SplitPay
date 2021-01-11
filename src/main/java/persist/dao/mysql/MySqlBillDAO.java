package persist.dao.mysql;

import core.facade.UserFacade;
import core.models.Bill;
import core.models.Transaction;
import persist.dao.BillDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class MySqlBillDAO implements BillDAO {
    @Override
    public Collection<Bill> findAllUserBills(int userId) throws SQLException {
        PreparedStatement statement = null;
        ArrayList<Bill> bills = new ArrayList<>();

        ResultSet rs;
        statement = ConnectionMySql.connection.prepareStatement("SELECT * FROM Bill WHERE store_owner_fk=?;");
        statement.setInt(1, userId);
        rs = statement.executeQuery();
        while (rs.next()) {
            String label = rs.getString("label");
            String content = rs.getString("content");
            Bill bill = new Bill(label,content);
            bills.add(bill);
        }


        return bills;
    }

}
