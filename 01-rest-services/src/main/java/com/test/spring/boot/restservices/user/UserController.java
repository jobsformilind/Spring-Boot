package com.test.spring.boot.restservices.user;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping(path = "/users")
	public List<User> findAll() {
		return userService.findAll();
	}

	@GetMapping(path = "/users/{id}")
	public Resource<User> findOne(@PathVariable int id) {
		User user = userService.findOne(id);
		if (user == null) {
			throw new UserNotFoundException("Invalid User : " + id);
		}

		Resource<User> resource = new Resource<User>(user);
		ControllerLinkBuilder linkTo = ControllerLinkBuilder
				.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).findAll());
		resource.add(linkTo.withRel("all-users"));
		return resource;
	}

	@PostMapping(path = "/users/{id}")
	public User update(User user) {
		return userService.save(user);
	}

	@PostMapping(path = "/users")
	public ResponseEntity<Object> create(@Valid @RequestBody User user) {
		User dbUser = userService.save(user);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dbUser.getId()).toUri();
		BodyBuilder builder = ResponseEntity.created(uri);
		return builder.build();
	}

	@DeleteMapping(path = "/users/{id}")
	public void delete(@PathVariable int id) {
		User user = userService.delete(id);
		if (user == null) {
			throw new UserNotFoundException("Invalid User : " + id);
		}
	}

}
