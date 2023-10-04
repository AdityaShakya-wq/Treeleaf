package com.blog.service.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="blogs_table")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Blogs {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long blogId;
	
	private String blogTitle;
	
	private String blogBody;
	
	private LocalDate blogCreatedDate;
	
	private String blogCategory;
	
	private Long userId;
}
