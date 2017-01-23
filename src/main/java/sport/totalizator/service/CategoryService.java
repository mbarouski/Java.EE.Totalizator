package sport.totalizator.service;

import sport.totalizator.entity.Category;
import sport.totalizator.exception.ExceptionWithErrorList;
import sport.totalizator.service.exception.ServiceException;

public interface CategoryService {
    Category addCategory(String name) throws ServiceException, ExceptionWithErrorList;
}
