package persist;

import java.util.*;

/**
 * 
 */
public interface DAO<T> {

    /**
     * @param object 
     * @return
     */
    public T create(T object);

    /**
     * @param object 
     * @return
     */
    public boolean delete(T object);

    /**
     * @param object 
     * @return
     */
    public boolean update(T object);

    /**
     * @param id 
     * @return
     */
    public T findById(int id);

    /**
     * 
     */
    public void error();

    /**
     * @return
     */
    public Collection<T> findAll();

}