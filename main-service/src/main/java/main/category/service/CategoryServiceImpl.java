package main.category.service;

import lombok.RequiredArgsConstructor;
import main.category.Category;
import main.category.CategoryDomainDto;
import main.category.CategoryMapper;
import main.category.CategoryRepository;
import main.category.dto.NewCategoryDto;
import main.event.repository.EventJpaRepository;
import main.exception.ConditionsNotMetException;
import main.exception.NotFoundException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final EventJpaRepository eventJpaRepository;
    private final CategoryDomainDto mapper;


    @Override
    @Transactional
    public Category addCategory(NewCategoryDto newCategory) throws ConditionsNotMetException {
        validateCategoryName(newCategory.getName());
        return categoryRepository.save(CategoryMapper.toCategory(newCategory));
    }

    @Override
    @Transactional
    public Category updateCategoryByAdmin(NewCategoryDto categoryDto, Long categoryId) throws NotFoundException, ConditionsNotMetException {
        Category category = mapper.dtoToDomain(categoryDto);

        Category old = getCategoryById(categoryId);
        if (old.getName().equals(categoryDto.getName())) {
            return old;
        }
        validateCategoryName(category.getName());
        category.setId(categoryId);

        return categoryRepository.save(category);
    }

    @Override
    @Transactional
    public void deleteCategoryById(Long categoryId) throws NotFoundException, ConditionsNotMetException {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new NotFoundException("Категория с указанным id: " + categoryId + " не найдена."));

        if (eventJpaRepository.findFistByCategoryId(categoryId).isPresent()) {
            throw new ConditionsNotMetException("Категория не пуста.");
        }
        categoryRepository.delete(category);
    }

    @Override
    public Collection<Category> getCategories(Integer from, Integer size) {
        return categoryRepository.findAll(PageRequest.of(from, size, Sort.by("id").ascending()))
                .getContent()
                .stream()
                .toList();
    }

    @Override
    public Category getCategoryById(Long categoryId) throws NotFoundException {
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new NotFoundException("Category not found by id: " + categoryId));
    }

    private void validateCategoryName(String name) throws ConditionsNotMetException {
        if (categoryRepository.existsByName(name))
            throw new ConditionsNotMetException("Такое имя уже существует:  " + name);
    }
}
