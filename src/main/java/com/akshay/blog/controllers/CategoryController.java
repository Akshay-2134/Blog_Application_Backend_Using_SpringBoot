package com.akshay.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.akshay.blog.payloads.ApiResponse;
import com.akshay.blog.payloads.CategoryDto;
import com.akshay.blog.services.CategoryService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/categories")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	

	/*
	 *  create API for Categories
	 */

	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory(@Valid    @RequestBody CategoryDto cateoryDto) {
		CategoryDto createCategoryDto = this.categoryService.createCategory(cateoryDto);
		return new ResponseEntity<CategoryDto>(createCategoryDto, HttpStatus.CREATED);
	}

	/*
	 * Update API for Categories
	 */

	@PutMapping("/{catId}")
	public ResponseEntity<CategoryDto> updateCategory(@Valid    @RequestBody CategoryDto cateoryDto,
			@PathVariable Integer catId) {
		CategoryDto updatedCategory = this.categoryService.updateCategory(cateoryDto, catId);
		return new ResponseEntity<CategoryDto>(updatedCategory, HttpStatus.CREATED);
	}

	/*
	 * Delete Api for Categories
	 */

	@DeleteMapping("/{catId}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer catId) {
		this.categoryService.deleteCategory(catId);
		 return  new ResponseEntity<ApiResponse>(new ApiResponse("user deleted successfully",true), HttpStatus.OK);
	}

	/*
	 * Get AllData  API Of Categories
	 */
	
	@GetMapping("/{catId}")
	public ResponseEntity<CategoryDto> getCategory(@PathVariable Integer catId) {
		CategoryDto categoryDto=this.categoryService.getCategory(catId);
		 return  new ResponseEntity<CategoryDto>(categoryDto,HttpStatus.OK);
	}
	
	/*
	 * Get  CategoryData  API Of Categories by Category Id
	 */
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getCategories() {
		List<CategoryDto> categories=this.categoryService.getCategories();
		return ResponseEntity.ok(categories);
	}
}
