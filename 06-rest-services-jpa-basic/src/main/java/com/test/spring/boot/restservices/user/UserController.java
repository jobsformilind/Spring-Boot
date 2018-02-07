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

import com.test.spring.boot.restservices.user.post.Post;
import com.test.spring.boot.restservices.user.post.PostRepository;

@RestController
@RequestMapping(path = "/jpa/users", produces = { "application/json", "application/xml" })
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PostRepository postRepository;

	@GetMapping
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@GetMapping(path = "/{id}")
	public Resource<User> findOne(@PathVariable int id) {
		User user = userRepository.findOne(id);
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
		return userRepository.save(user);
	}

	@PostMapping
	public ResponseEntity<Object> create(@Valid @RequestBody User user) {
		User dbUser = userRepository.save(user);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dbUser.getId()).toUri();
		BodyBuilder builder = ResponseEntity.created(uri);
		return builder.build();
	}

	@DeleteMapping(path = "/{id}")
	public void delete(@PathVariable int id) {
		userRepository.delete(id);
	}

	@GetMapping(path = "/{id}/posts")
	public List<Post> findPosts(@PathVariable int id) {
		User dbUser = getDBUser(id);
		return dbUser.getPosts();
	}

	@PostMapping(path = "/{id}/posts")
	public ResponseEntity<Object> createPost(@PathVariable int id, @RequestBody Post post) {
		User dbUser = getDBUser(id);
		post.setUser(dbUser);
		Post dbPost = postRepository.save(post);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dbPost.getId()).toUri();
		BodyBuilder builder = ResponseEntity.created(uri);
		return builder.build();
	}

	private User getDBUser(int id) {
		User user = userRepository.findOne(id);
		if (user == null) {
			throw new UserNotFoundException("Invalid User : " + id);
		}
		return user;
	}

}
