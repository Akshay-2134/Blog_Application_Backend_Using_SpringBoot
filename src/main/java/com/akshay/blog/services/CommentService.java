package com.akshay.blog.services;

import com.akshay.blog.payloads.CommentDto;

public interface CommentService {
    
	CommentDto createComment(CommentDto commentDto,Integer postId);
	
	void deleteComment(Integer commentId);
}
