package com.akshay.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.akshay.blog.entites.Category;

public interface CategoryRepo  extends JpaRepository<Category, Integer> {

	
	 
}
