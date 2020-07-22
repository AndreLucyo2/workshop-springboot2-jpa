package com.homeAls.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.homeAls.course.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
