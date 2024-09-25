package com.akshay.blog.payloads;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.constraints.NotBlank;

import com.akshay.blog.entites.Post;

import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {

	
	private Integer categoryId;
	@NotBlank
	@Size(min=4,message = "Min size of category title is 4")
	private String categoryTitle;
	@NotBlank
	
	@Size(min=10,message="min size of category desc is 10")
	private String categoryDescription;

	
		


}
