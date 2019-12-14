package Service;

import java.util.Comparator;
import java.util.List;

/**
 * The interface Service.
 *
 * @param <T> the type parameter
 */
public interface Service<T> {

    /**
     * Create.
     *
     * @param item the item
     * @throws ServiceException the service exception
     */
    void create(T item) throws ServiceException;

    /**
     * Read t.
     *
     * @param id the id
     * @return the t
     * @throws ServiceException the service exception
     */
    T read(int id) throws ServiceException;

    /**
     * Update.
     *
     * @param item the item
     * @throws ServiceException the service exception
     */
    void update(T item) throws ServiceException;


    /**
     * Delete.
     *
     * @param id the id
     * @throws ServiceException the service exception
     */
    void delete(int id) throws ServiceException;


    /**
     * Sort.
     *
     * @param comparator the comparator
     * @throws ServiceException the service exception
     */
    void sort(Comparator<T> comparator) throws ServiceException;



    /**
     * Gets all.
     *
     * @return the all
     * @throws ServiceException the service exception
     */
    List<T> getAll() throws ServiceException;
}
