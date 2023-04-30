package com.examportal.examportalbackend.dto.converter;

import com.examportal.examportalbackend.dto.CategoryDto;
import com.examportal.examportalbackend.model.exam.Category;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CategoryDtoConverter {
    public CategoryDto convertToDto(Category category){
        return CategoryDto.builder()
                .categoryTitle(category.getTitle())
                .categoryDescription(category.getDescription())
                .build();
    }

    public List<CategoryDto> convertToDto(List<Category> categories){
        return categories.stream().map(this::convertToDto).collect(Collectors.toList());
    }
}
