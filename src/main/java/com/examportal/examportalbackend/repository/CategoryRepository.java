package com.examportal.examportalbackend.repository;

import com.examportal.examportalbackend.model.exam.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
}
