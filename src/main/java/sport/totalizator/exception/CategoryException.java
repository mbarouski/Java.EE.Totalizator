package sport.totalizator.exception;

import sport.totalizator.entity.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryException extends Exception {
    private Category category;
    private List<String> errorMessageList;

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
        this.category = category;
        this.errorMessageList = new ArrayList<>();
    }

    public Category getCategory() {
        return category;
    }

    public void addMessage(String message){
        this.errorMessageList.add(message);
    }

    public List<String> getErrorMessageList() {
        return errorMessageList;
    }
}
