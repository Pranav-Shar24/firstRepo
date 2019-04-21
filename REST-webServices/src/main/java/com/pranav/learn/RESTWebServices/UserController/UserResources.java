package com.pranav.learn.RESTWebServices.UserController;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.pranav.learn.RESTWebServices.DAO.UserDaoService;
import com.pranav.learn.RESTWebServices.UserExceptions.UserNotFoundException;
import com.pranav.learn.RESTWebServices.bean.User;

@RestController
public class UserResources {

	@Autowired
	private UserDaoService service;

	// retriveAllUsers {get: /users}
	@GetMapping(path = "/users")
	public List<User> retriveAllUsers() {

		return service.findAll();

	}

	// finUserById(int id) {get: /users/{id}}
	@GetMapping("/users/{id}")
	public Resource<User> findUserById(@PathVariable int id) {
		User user = service.findOne(id);

		if (user == null) {
			throw new UserNotFoundException(id + " Not present in Database");
		}

		// HATEOAS
		/*
		 * H => HyperMedia A => As T => The E => Engine O => Of A => Application S =>  State
		 **/

		Resource<User> resource = new Resource<>(user);
		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retriveAllUsers());
		resource.add(linkTo.withRel("all-user-list"));
		return resource;
	}

	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User createdUser = service.save(user);
		System.out.println(createdUser);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("{/id}")
				.buildAndExpand(createdUser.getId()).toUri();
		ResponseEntity<Object> responseEntity = ResponseEntity.created(location).build();
		return responseEntity;
	}

	@DeleteMapping("/users/{id}")
	public ResponseEntity<Void> deleteUserById(@PathVariable int id) {
		try {
			service.deleteById(id);
			return ResponseEntity.noContent().build();
		} catch (UserNotFoundException e) {
			return ResponseEntity.notFound().build();
		}

	}

}
