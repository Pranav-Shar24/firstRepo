package com.pranav.learn.RESTWebServices.bean;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description="documented user properties")

@Entity
public class User {

	@Id
	@GeneratedValue
	private Integer id;
	
	@Size(min=2,max=25)
	@ApiModelProperty(notes="Name should be between 2 to 25 characters.")
	private String name;
	@Past
	@ApiModelProperty(notes="D.O.B must be less than currrent date ")
	private Date dateOfBirth;
	
	@OneToMany(mappedBy="users")
	private List<Post> posts;



	public User() {
		// TODO Auto-generated constructor stub
	}

	public User(Integer id, String name, Date dateOfBirth) {
		super();
		this.id = id;
		this.name = name;
		this.dateOfBirth = dateOfBirth;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}
	
	@Override
	public String toString() {
		return String.format("User [id=%s, name=%s, dateOfBirth=%s]", id, name, dateOfBirth);
	}

}
