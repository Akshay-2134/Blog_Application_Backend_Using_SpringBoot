package com.akshay.blog.services.Impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.akshay.blog.entites.Comment;
import com.akshay.blog.entites.Post;
import com.akshay.blog.exceptions.ResourceNotFoundException;
import com.akshay.blog.payloads.CommentDto;
import com.akshay.blog.repositories.CommentRepo;
import com.akshay.blog.repositories.PostRepo;
import com.akshay.blog.services.CommentService;
@Service
public class CommentServiceImpl  implements CommentService{

	@Autowired
	private CommentRepo commentRepo;
	
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	/*
	 * craete comment 
	 */
	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId) {
		Post post=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "Post Id", postId));
		Comment comment=this.modelMapper.map(commentDto, Comment.class);
		comment.setPost(post);
		
		Comment savedComment=this.commentRepo.save(comment);
	    return this.modelMapper.map(savedComment, CommentDto.class);
	    
	}

	/*
	 * delete comment
	 */
	@Override
	public void deleteComment(Integer commentId) {
		Comment com=this.commentRepo.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment", "Comment Id", commentId));
		this.commentRepo.delete(com);
	}

	

}
