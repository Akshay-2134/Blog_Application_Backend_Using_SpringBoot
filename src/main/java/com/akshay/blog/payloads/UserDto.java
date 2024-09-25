
package com.akshay.blog.payloads;

import java.util.HashSet;
import java.util.Set;

import com.akshay.blog.entites.Role;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {

	private int id;
	
	@NotEmpty
	@Size(min=4,message="Username must br min of 4 characters ||")
	private String name;
	
	@Email(message="Email address is not valid !!")
	private String email;
	
	@NotEmpty
	@Size(min=3,message="Password must br min of 3 characters !!")
	private String password;
	
	@NotNull
    private String about;
	
	
	private Set<RoleDto> roles=new HashSet<>();
}
