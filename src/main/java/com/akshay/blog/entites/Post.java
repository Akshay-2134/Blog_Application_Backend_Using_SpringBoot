package com.akshay.blog.entites;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.xml.crypto.Data;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "post")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int postId;
	
	@Column(name = "post_title",length = 100,nullable = false)
	private String title;
	
	@Column(length = 100)
	private String content;
	
	private String imageName;
	
	private Date addedDate;
	

	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;
	
	@ManyToOne
    @JoinColumn(name = "user_id")
	@JsonIgnore
	private User user;
	
     @OneToMany(mappedBy = "post",cascade = CascadeType.ALL)
     @JsonIgnore
	private Set<Comment> comments=new HashSet<>();
}
