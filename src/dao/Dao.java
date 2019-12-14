package dao;


import java.util.List;

/**
 * The interface Dao.
 *
 * @param <T> the type parameter
 */
public interface Dao<T> {

    /**
     * Delete.
     *
     * @param obj the obj
     * @throws Exception the exception
     */
    void delete(T obj) throws Exception;


    /**
     * Add.
     *
     * @param obj the obj
     * @throws Exception the exception
     */
    void add(T obj) throws Exception;


    /**
     * Get t.
     *
     * @param id the id
     * @return the t
     * @throws Exception the exception
     */
    T get(int id) throws Exception;


    /**
     * Add range.
     *
     * @param items the items
     * @throws Exception the exception
     */
    void addRange(List<T> items) throws Exception;


    /**
     * Gets all.
     *
     * @return the all
     * @throws Exception the exception
     */
    List<T> getAll() throws Exception;
}