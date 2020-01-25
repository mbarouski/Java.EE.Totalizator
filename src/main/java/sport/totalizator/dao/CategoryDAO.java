package sport.totalizator.dao;

import sport.totalizator.dao.exception.DAOException;
import sport.totalizator.entity.Category;

import java.util.List;

public interface CategoryDAO {
    List<Category> getAllCategories() throws DAOException;

    Category addCategory(Category category) throws DAOException;
}
