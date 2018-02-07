package com.test.spring.boot.restservices.user;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(path = "/users", produces = { "application/json", "application/xml" })
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping
	public List<User> findAll() {
		return userService.findAll();
	}

	@GetMapping(path = "/{id}")
	public Resource<User> findOne(@PathVariable int id) {
		User user = userService.findOne(id);
		if (user == null) {
			throw new UserNotFoundException("Invalid User : " + id);
		}
		Resource<User> resource = new Resource<User>(user);
		Link links = linkTo(methodOn(this.getClass()).findAll()).withRel("all-users");
		resource.add(links);
		return resource;
	}

	@PostMapping(path = "/{id}")
	public User update(User user) {
		return userService.save(user);
	}

	@PostMapping
	public ResponseEntity<Object> create(@Valid @RequestBody User user) {
		User dbUser = userService.save(user);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dbUser.getId()).toUri();
		BodyBuilder builder = ResponseEntity.created(uri);
		return builder.build();
	}

	@DeleteMapping(path = "/{id}")
	public void delete(@PathVariable int id) {
		User user = userService.delete(id);
		if (user == null) {
			throw new UserNotFoundException("Invalid User : " + id);
		}
	}
}
