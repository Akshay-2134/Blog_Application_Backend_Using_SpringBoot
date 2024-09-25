package com.akshay.blog.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.security.SecuritySchemes;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
	
		info =@Info(
				title = "Blog App Apis",
				description = "All Modules APis",
				contact = @Contact(
						name = "Akshay Chauhan",
						email = "akshaychauhan8808@gmail.com"
				), 
				version = "v1"
				   
				),
		servers= {
				@Server(
						description = "Local ENV",
						url = "http://localhost:9090"
						)
				
		},
		security = {
				@SecurityRequirement(
					name="bearerAuth"
				)
		}
)
@SecurityScheme(
		name = "bearerAuth",
		description = "JWT auth description",
		scheme = "bearer",
		type = SecuritySchemeType.HTTP,
		bearerFormat = "JWT",
		in=SecuritySchemeIn.HEADER
		)
public class OpenApiConfig {

	
}
