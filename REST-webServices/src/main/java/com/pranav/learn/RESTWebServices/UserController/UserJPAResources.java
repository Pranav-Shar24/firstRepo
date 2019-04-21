package com.pranav.learn.RESTWebServices.UserController;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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

import com.pranav.learn.RESTWebServices.UserExceptions.UserNotFoundException;
import com.pranav.learn.RESTWebServices.UserRepo.PostRepository;
import com.pranav.learn.RESTWebServices.UserRepo.UserRepository;
import com.pranav.learn.RESTWebServices.bean.Post;
import com.pranav.learn.RESTWebServices.bean.User;

@RestController
public class UserJPAResources {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private PostRepository postRepo;

	// retriveAllUsers {get: /users}
	@GetMapping(path = "jpa/users")
	public List<User> retriveAllUsers() {

		return userRepo.findAll();
	}

	// finUserById(int id) {get: /users/{id}}
	@GetMapping("jpa/users/{id}")
	public Resource<User> findUserById(@PathVariable int id) {
		Optional<User> user = userRepo.findById(id);

		if (!user.isPresent()) {
			throw new UserNotFoundException(id + " Not present in Database");
		}

		// HATEOAS
		/*
		 * H => HyperMedia A => As T => The E => Engine O => Of A => Application S =>
		 * State
		 **/

		Resource<User> resource = new Resource<>(user.get());
		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retriveAllUsers());
		resource.add(linkTo.withRel("all-user-list"));
		return resource;
	}

	@PostMapping("jpa/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User createdUser = userRepo.save(user);
		System.out.println(createdUser);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("{/id}")
				.buildAndExpand(createdUser.getId()).toUri();
		ResponseEntity<Object> responseEntity = ResponseEntity.created(location).build();
		return responseEntity;
	}

	@DeleteMapping("jpa/users/{id}")
	public ResponseEntity<Void> deleteUserById(@PathVariable int id) {
		try {
			userRepo.deleteById(id);
			;
			return ResponseEntity.noContent().build();
		} catch (UserNotFoundException e) {
			return ResponseEntity.notFound().build();
		}

	}

	@GetMapping(path = "jpa/users/{id}/posts")
	public List<Post> getPostByUserId(@PathVariable int id) {

		Optional<User> userOptional = userRepo.findById(id);
		if (!userOptional.isPresent()) {
			throw new UserNotFoundException(id + " Not present in Database");
		}
		return userOptional.get().getPosts();
	}

	@PostMapping("jpa/users/{id}/posts")
	public ResponseEntity<Object> createPost(@PathVariable int id, @RequestBody Post posts) {
		Optional<User> userOptional = userRepo.findById(id);
		if (!userOptional.isPresent()) {
			throw new UserNotFoundException(id + " User does not exists");
		}
		User user = userOptional.get();
		System.out.println("user details ==> " + user);

		posts.setUsers(user);
		postRepo.save(posts);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("{/id}").buildAndExpand(posts.getId())
				.toUri();
		ResponseEntity<Object> responseEntity = ResponseEntity.created(location).build();

		return responseEntity;
	}

}
