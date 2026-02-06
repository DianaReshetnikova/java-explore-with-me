package main.category;

import main.category.dto.CategoryDto;
import main.category.dto.NewCategoryDto;

public final class CategoryMapper {

    private CategoryMapper() {
        throw new UnsupportedOperationException();
    }

    public static Category toCategory(NewCategoryDto categoryCreateDto) {
        return Category.builder()
                .id(null)
                .name(categoryCreateDto.getName())
                .build();
    }

    public static CategoryDto toCategoryFullDto(Category category) {
        return CategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }
}
