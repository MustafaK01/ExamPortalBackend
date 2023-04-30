package com.examportal.examportalbackend.service.concrete;

import com.examportal.examportalbackend.core.utils.results.Result;
import com.examportal.examportalbackend.core.utils.results.ResultData;
import com.examportal.examportalbackend.dto.CategoryDto;
import com.examportal.examportalbackend.dto.converter.CategoryDtoConverter;
import com.examportal.examportalbackend.exception.InvalidParamException;
import com.examportal.examportalbackend.model.exam.Category;
import com.examportal.examportalbackend.repository.CategoryRepository;
import com.examportal.examportalbackend.service.abstracts.CategoryService;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryDtoConverter categoryDtoConverter;

    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryDtoConverter categoryDtoConverter) {
        this.categoryRepository = categoryRepository;
        this.categoryDtoConverter = categoryDtoConverter;
    }


    @Override
    public Result addCategory(Category category) {
        if(category!=null) return new ResultData<>(this.categoryRepository.save(category),true);
        return null;
    }

    @Override
    public Result updateCategory(Category category) {
        if(category!=null) return new ResultData<>(this.categoryRepository.save(category),true);
        return null;    }

    @Override
    public Result deleteCategory(Long categoryId) {
        Optional<Category> category =  this.categoryRepository.findById(categoryId);
        if(category.isEmpty()) return new Result(false);
        category.ifPresent(this.categoryRepository::delete);
        return new Result(true);
    }

    @Override
    public Set<CategoryDto> getCategories() {
        List<Category> categories = this.categoryRepository.findAll();
        if(!categories.isEmpty()){
            return new LinkedHashSet<>(
                    this.categoryDtoConverter
                            .convertToDto(categories));
        }
        return null;
    }

    @Override
    public CategoryDto getCategory(Long categoryId) {
        return this.categoryDtoConverter.convertToDto(
                this.categoryRepository.findById(categoryId).orElseThrow(
                ()->new InvalidParamException("Invalid Param")));
        }
}
