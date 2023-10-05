package com.user.UserService.entity;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Blogs {

	private Long blogId;
	
	private String blogTitle;
	
	private String blogBody;
	
	private LocalDate blogCreatedDate;
	
	private String blogCategory;
	
}
