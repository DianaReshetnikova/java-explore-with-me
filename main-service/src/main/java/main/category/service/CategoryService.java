package main.category.service;

import main.category.Category;
import main.category.dto.NewCategoryDto;
import main.exception.ConditionsNotMetException;
import main.exception.DuplicateException;
import main.exception.NotFoundException;

import java.util.Collection;

public interface CategoryService {
    Category addCategory(NewCategoryDto categoryCreateDto) throws DuplicateException, ConditionsNotMetException;

    Category updateCategoryByAdmin(NewCategoryDto categoryDto, Long categoryId) throws NotFoundException, DuplicateException, ConditionsNotMetException;

    void deleteCategoryById(Long categoryId) throws NotFoundException, ConditionsNotMetException;

    Collection<Category> getCategories(Integer from, Integer size);

    Category getCategoryById(Long categoryId) throws NotFoundException;
}
