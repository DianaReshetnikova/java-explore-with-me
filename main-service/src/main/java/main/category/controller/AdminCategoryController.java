package main.category.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import main.category.Category;
import main.category.CategoryMapper;
import main.category.dto.NewCategoryDto;
import main.category.dto.CategoryDto;
import main.category.service.CategoryService;
import main.exception.ConditionsNotMetException;
import main.exception.DuplicateException;
import main.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/admin/categories", produces = "application/json")
public class AdminCategoryController {
    private final CategoryService categoryService;

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryDto addCategory(@RequestBody @Valid NewCategoryDto categoryCreateDto) throws ConditionsNotMetException, DuplicateException {
        Category category = categoryService.addCategory(categoryCreateDto);
        return CategoryMapper.toCategoryFullDto(category);
    }

    @PatchMapping(
            path = "/{catId}",
            consumes = "application/json"
    )
    @ResponseStatus(HttpStatus.OK)
    public CategoryDto updateCategoryByAdmin(@PathVariable(name = "catId") @NotNull Long categoryId,
                                             @RequestBody @Valid NewCategoryDto categoryDto) throws ConditionsNotMetException, DuplicateException, NotFoundException {
        Category category = categoryService.updateCategoryByAdmin(categoryDto, categoryId);
        return CategoryMapper.toCategoryFullDto(category);
    }

    @DeleteMapping(path = "/{catId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCategoryById(@PathVariable(name = "catId") @NotNull Long categoryId) throws ConditionsNotMetException, NotFoundException {
        categoryService.deleteCategoryById(categoryId);
    }
}
