package com.akshay.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.akshay.blog.entites.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer> {

}
