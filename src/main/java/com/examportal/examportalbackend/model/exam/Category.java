package com.examportal.examportalbackend.model.exam;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name="categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long categoryId;
    private String categoryTitle;
    private String categoryDescription;

    @OneToMany(mappedBy = "category",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Quiz> quizzes = new LinkedHashSet<>();

    public Category() {
    }

    public Category(Long categoryId, String title, String categoryDescription) {
        this.categoryId = categoryId;
        this.categoryTitle = title;
        this.categoryDescription = categoryDescription;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getTitle() {
        return categoryTitle;
    }

    public void setTitle(String title) {
        this.categoryTitle = title;
    }

    public String getDescription() {
        return categoryDescription;
    }

    public void setDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }
}
