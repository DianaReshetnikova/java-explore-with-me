package main.category.service;

import main.category.Category;
import main.exception.ConditionsNotMetException;
import main.exception.DuplicateException;
import main.exception.NotFoundException;

import java.util.List;

public interface CategoryService {
    Category addCategory(Category categoryCreateDto) throws DuplicateException, ConditionsNotMetException;

    Category updateCategoryByAdmin(Category categoryDto, Long categoryId) throws NotFoundException, DuplicateException, ConditionsNotMetException;

    void deleteCategoryById(Long categoryId) throws NotFoundException, ConditionsNotMetException;

    List<Category> getCategories(Integer from, Integer size);

    Category getCategoryById(Long categoryId) throws NotFoundException;
}
