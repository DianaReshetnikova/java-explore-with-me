package main.category.controller;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import main.category.CategoryMapper;
import main.category.dto.CategoryDto;
import main.category.service.CategoryService;
import main.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/categories", produces = "application/json")
public class PublicCategoryController {
    private final CategoryService categoryService;

    @GetMapping
    public Collection<CategoryDto> getCategories(
            @RequestParam(name = "from", defaultValue = "0") @PositiveOrZero Integer from,
            @RequestParam(name = "size", defaultValue = "10") @Positive Integer size
    ) {
        return categoryService.getCategories(from, size).stream()
                .map(CategoryMapper::toCategoryFullDto)
                .toList();
    }

    @GetMapping(path = "/{catId}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryDto getCategoryById(@PathVariable(name = "catId") @NotNull Long categoryId) throws NotFoundException {
        return CategoryMapper.toCategoryFullDto(categoryService.getCategoryById(categoryId));
    }
}
