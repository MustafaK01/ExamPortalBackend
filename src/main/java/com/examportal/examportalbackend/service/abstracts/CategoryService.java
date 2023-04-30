package com.examportal.examportalbackend.service.abstracts;

import com.examportal.examportalbackend.core.utils.results.Result;
import com.examportal.examportalbackend.core.utils.results.ResultData;
import com.examportal.examportalbackend.dto.CategoryDto;
import com.examportal.examportalbackend.model.exam.Category;

import java.util.Set;

public interface CategoryService {

    Result addCategory(Category category);
    Result updateCategory(Category category);
    Result deleteCategory(Long categoryId);
    Set<CategoryDto> getCategories();
    CategoryDto getCategory(Long categoryId);



}
