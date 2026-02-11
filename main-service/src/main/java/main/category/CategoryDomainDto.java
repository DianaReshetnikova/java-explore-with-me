package main.category;

import main.category.dto.CategoryDto;
import main.category.dto.NewCategoryDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryDomainDto {

    Category dtoToDomain(NewCategoryDto dto);

    CategoryDto domainToDto(Category category);

    default Category fromId(Long id) {
        if (id == null) return null;
        Category category = new Category();
        category.setId(id);
        return category;
    }
}
