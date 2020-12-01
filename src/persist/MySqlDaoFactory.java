package persist;

import persist.DaoFactory;

import java.util.*;

/**
 * 
 */
public abstract class MySqlDaoFactory extends persist.DaoFactory {

    /**
     * @return
     */
    public MySqlUserDao createUserDao() {
        return null;
    }

}