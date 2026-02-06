package main.category.controller;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import main.category.Category;
import main.category.CategoryDomainDto;
import main.category.dto.CategoryDto;
import main.category.service.CategoryService;
import main.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/categories", produces = "application/json")
public class PublicCategoryController {
    private final CategoryService categoryService;
    private final CategoryDomainDto mapper;

    @GetMapping
    public Collection<CategoryDto> getCategories(
            @RequestParam(name = "from", defaultValue = "0") @PositiveOrZero Integer from,
            @RequestParam(name = "size", defaultValue = "10") @Positive Integer size
    ) {
        List<Category> categories = categoryService.getCategories(from, size);
        return categories.stream()
                .map(mapper::domainToDto)
                .toList();
    }

    @GetMapping(path = "/{catId}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryDto getCategoryById(@PathVariable(name = "catId") @NotNull Long categoryId) throws NotFoundException {
        Category category = categoryService.getCategoryById(categoryId);
        return mapper.domainToDto(category);
    }
}
