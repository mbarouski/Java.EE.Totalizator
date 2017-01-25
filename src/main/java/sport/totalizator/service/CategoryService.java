package sport.totalizator.service;

import sport.totalizator.entity.Category;
import sport.totalizator.exception.ExceptionWithErrorList;
import sport.totalizator.service.exception.ServiceException;

/**
 * {@link CategoryService} provides methods for interaction of controller and DAO layer from side of category.
 */
public interface CategoryService {
    /**
     * Method checks data for {@link Category} adding and manages this process.
     * @param name
     * @return
     * @throws ServiceException
     * @throws ExceptionWithErrorList
     */
    Category addCategory(String name) throws ServiceException, ExceptionWithErrorList;
}
