package com.akshay.blog.controllers;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.akshay.blog.payloads.ApiResponse;
import com.akshay.blog.payloads.UserDto;
import com.akshay.blog.services.UserService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
@Tag(name = "User")
public class UserController {

	 @Autowired
     private UserService userService;
	 
	 // POST-create user
	 @PostMapping("/")
	 public ResponseEntity<UserDto> createUser(@Valid   @RequestBody UserDto userDto){
		 UserDto createdUserDto=this.userService.createUser(userDto);
		 return  new ResponseEntity<>(createdUserDto,HttpStatus.CREATED);
	 }
	 
	 
	 // PUT-update user
	 @PutMapping("/{userId}")
	   public ResponseEntity<UserDto> updateUser(@Valid  @RequestBody UserDto userDto,@PathVariable("userId") Integer uid ){
	    UserDto updatedUser= this.userService.updateUser(userDto, uid);
	    return  ResponseEntity.ok(updatedUser);
     }
	 
	 
	//DELETE-delete user
     @PreAuthorize("hasRole('ADMIN')")
	 @DeleteMapping("/{userId}")
	 public ResponseEntity<?> deleteUser(@PathVariable("userId") Integer uid){
		 this.userService.deleteUser(uid);
		 return  new ResponseEntity<ApiResponse>(new ApiResponse("user deleted successfully",true), HttpStatus.OK);
	 }
	 
	 
	 //GET-user get
	 @GetMapping("/")
	 public ResponseEntity<List<UserDto>> getAllUsers(){
		 return ResponseEntity.ok(this.userService.getAllusers());
	 }
	 
	 
	 //GET-get single user details
	 @GetMapping("/{userId}")
	 public ResponseEntity<UserDto> getSingleUser(@PathVariable Integer userId){
		 return ResponseEntity.ok(this.userService.getUserById(userId));
	 }
	 
	 
}
