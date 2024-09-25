package com.akshay.blog.services.Impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.akshay.blog.entites.Category;
import com.akshay.blog.entites.Post;
import com.akshay.blog.entites.User;
import com.akshay.blog.exceptions.ResourceNotFoundException;
import com.akshay.blog.payloads.CategoryDto;
import com.akshay.blog.payloads.PostDto;
import com.akshay.blog.payloads.PostResponse;
import com.akshay.blog.payloads.UserDto;
import com.akshay.blog.repositories.CategoryRepo;
import com.akshay.blog.repositories.PostRepo;
import com.akshay.blog.repositories.UserRepo;
import com.akshay.blog.services.PostService;



@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepo postRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private CategoryRepo categoryRepo;

	// create post

	@Override
	public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {

		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User ", "User id", userId));

		Category category = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "category id ", categoryId));

		Post post = this.modelMapper.map(postDto, Post.class);
		post.setImageName("default.png");
		post.setAddedDate(new Date());
		post.setUser(user);
		post.setCategory(category);

		Post newPost = this.postRepo.save(post);

		return this.modelMapper.map(newPost, PostDto.class);
	}

	// update post

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
       Post  post=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "Post Id", postId));
	   
       post.setTitle(postDto.getTitle());
	   post.setContent(postDto.getContent());
	   post.setImageName(postDto.getImageName());
       
	   Post savedPost=this.postRepo.save(post);
		return this.modelMapper.map(savedPost, PostDto.class);
	}

	// delete post

	@Override
	public void deletePost(Integer postId) {
		Post post = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "Post Id", postId));
		this.postRepo.delete(post);
	}

	// get Post By Id

	@Override
	public PostDto getPostById(Integer postId) {
		Post post = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "post id", postId));
		return this.modelMapper.map(post, PostDto.class);
	}

	/*
	 * get All Post
	 */

	@Override
	public PostResponse getAllPost(Integer pageNumber,Integer pageSize,String sortBy,String sortDir) {
		Sort sort=null;
		if(sortDir.equalsIgnoreCase("asc")) {
			sort=Sort.by(sortBy).ascending();
		}else {
			sort=Sort.by(sortBy).descending();
		}
	     PageRequest p=PageRequest.of(pageNumber, pageSize,sort);
		
//		List<Post> allPosts = this.postRepo.findAll();
//		List<PostDto> postDtos = allPosts.stream().map((post) -> this.modelMapper.map(post, PostDto.class))
//				.collect(Collectors.toList());
	     Page<Post> pagePost=this.postRepo.findAll(p);
	     List<Post> allPosts=pagePost.getContent();
	     List<PostDto> postDtos = allPosts.stream().map((post) -> this.modelMapper.map(post, PostDto.class))
					.collect(Collectors.toList());
	     PostResponse postResponse=new PostResponse();
	     postResponse.setContent(postDtos);
	     postResponse.setPageNumber(pagePost.getNumber());
	     postResponse.setPageSize(pagePost.getSize());
	     postResponse.setTotalElements(pagePost.getTotalElements());
	     postResponse.setTotalPage(pagePost.getTotalPages());
	     postResponse.setLastPage(pagePost.isLast());
	     
		return postResponse;

	}

	@Override
	public List<PostDto> getPostsByUser(Integer userId) {
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "user id", userId));
		List<Post> posts = this.postRepo.findByUser(user);

		List<PostDto> postDtos = posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());
		return postDtos;
	}
    /*
     * get Post by categoryId
     */
	@Override
	public List<PostDto> getPostsByCategory(Integer categoryId) {
		Category cat = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "Category id", categoryId));

		List<Post> posts = this.postRepo.findByCategory(cat);

		List<PostDto> postDtos = posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());
		return postDtos;
	}

	
	/*
	 * search Post
	 */

	@Override
	public List<PostDto> searchPosts(String keyword) {
	   List<Post> posts=this.postRepo.searchByTitle("%"+keyword+"%");
	   List<PostDto> postDtos=posts.stream().map(post -> modelMapper.map(post, PostDto.class))
               .collect(Collectors.toList());
		return postDtos;
	 
	}

}
