package com.ecommerce.project.service;

import com.ecommerce.project.model.Category;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class CategoryServiceImp implements CategoryService {
    /**
     * This class implements the CategoryService interface and provides the
     * implementation for the methods defined in the interface.
     * It interacts with the database to perform CRUD operations on categories.
     */

    private List<Category> categories = new ArrayList<>();
    private int idCounter = 1;

    @Override
    public List<Category> getAllCategories() {
        return categories;
    }

    @Override
    public String createCategory(Category category) {
        category.setCategoryId(idCounter++);
        categories.add(category);
        return "Category created successfully";
    }

    @Override
    public String deleteCategory(long id) {
        Category category = categories.stream().filter(obj -> obj.getCategoryId() == id).findFirst().orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found"));
        categories.remove(category);
        return "Category deleted successfully";
    }

    @Override
    public Category updateCategory(long id, Category category) {
        Optional<Category> optionalCategory = categories.stream().filter(obj -> obj.getCategoryId() == id).findFirst();

        if (optionalCategory.isPresent()) {
            Category existingCategory = optionalCategory.get();
            existingCategory.setCategoryName(category.getCategoryName());
            return existingCategory;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found");
        }
    }
}
