package sport.totalizator.dao;

import sport.totalizator.dao.exception.DAOException;
import sport.totalizator.entity.Category;

import java.util.List;

/**
 * CategoryDAO is interface that defines methods for interaction of service layer with database from side of {@link Category}.
 */
public interface CategoryDAO {
    /**
     * Method that returns list of all {@link Category}s.
     * @return
     * @throws DAOException
     */
    List<Category> getAllCategories() throws DAOException;

    /**
     * Method adds {@link Category} to database.
     * @param category
     * @return
     * @throws DAOException
     */
    Category addCategory(Category category) throws DAOException;
}
