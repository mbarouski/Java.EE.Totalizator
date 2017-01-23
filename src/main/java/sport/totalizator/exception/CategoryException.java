package sport.totalizator.exception;

import sport.totalizator.entity.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryException extends ExceptionWithErrorList {
    private Category category;

    public CategoryException() {
        super();
    }

    public CategoryException(String message) {
        super(message);
    }

    public CategoryException(String message, Throwable cause) {
        super(message, cause);
    }

    public CategoryException(Throwable cause) {
        super(cause);
    }

    public CategoryException(Category category) {
        super();
        this.category = category;
    }

    public Category getCategory() {
        return category;
    }
}
