package com.examportal.examportalbackend.dto;

import lombok.Builder;


@Builder
public class CategoryDto {

    private String categoryTitle;
    private String categoryDescription;

    public CategoryDto() {
    }

    public CategoryDto(String categoryTitle, String categoryDescription) {
        this.categoryTitle = categoryTitle;
        this.categoryDescription = categoryDescription;
    }

    public String getCategoryTitle() {
        return categoryTitle;
    }

    public void setCategoryTitle(String categoryTitle) {
        this.categoryTitle = categoryTitle;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }

}
