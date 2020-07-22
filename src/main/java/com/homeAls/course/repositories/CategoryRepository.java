package com.homeAls.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.homeAls.course.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
