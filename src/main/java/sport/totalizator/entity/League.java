package sport.totalizator.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * {@link League} represents entity of league in database.
 */
public class League {
    @JsonProperty("id")
    private int id;

    @JsonProperty("name")
    private String name;

    private int categoryId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
}
