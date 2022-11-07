package com.company.inventory.response;
import com.company.inventory.models.Category;

import java.util.List;


public class CategoryResponse {

    private List<Category>category;

    public List<Category> getCategory() {
        return category;
    }

    public void setCategory(List<Category> category) {
        this.category = category;
    }
}
