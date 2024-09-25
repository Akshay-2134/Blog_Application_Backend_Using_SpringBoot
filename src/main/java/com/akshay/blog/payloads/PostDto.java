 package com.akshay.blog.payloads;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.akshay.blog.entites.Category;
import com.akshay.blog.entites.Comment;
import com.akshay.blog.entites.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PostDto {

	private Integer postId;
	private String title;
	private String content;

	private String imageName;

	private Date addedDate;

	private Category category;

	private User user;
	
	private Set<CommentDto> comments=new HashSet<>();
}
