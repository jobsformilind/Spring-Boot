package com.test.spring.boot.bank.rest.base;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.test.spring.boot.bank.model.base.BaseEntity;
import com.test.spring.boot.bank.service.base.BaseService;

public abstract class BaseRestController<T extends BaseEntity> {

	private BaseService<T> service;

	protected BaseRestController(BaseService<T> service) {
		this.service = service;
	}

	@GetMapping
	public List<T> findAll() {
		return service.findAll();
	}

	@GetMapping(path = "/{id}")
	public Resource<T> findById(@PathVariable int id) {
		T dbEntity = service.findById(id);
		Resource<T> resource = new Resource<T>(dbEntity);
		Link links = linkTo(methodOn(this.getClass()).findAll()).withRel("all");
		resource.add(links);
		return resource;
	}

	@PostMapping
	public ResponseEntity<Object> create(@Valid @RequestBody T entity) {
		T dbEntity = service.save(entity);
		URI uri = fromCurrentRequest().path("/{id}").buildAndExpand(dbEntity.getId()).toUri();
		BodyBuilder builder = ResponseEntity.created(uri);
		return builder.build();
	}

	@DeleteMapping(path = "/{id}")
	public void deleteById(@PathVariable int id) {
		service.delete(id);
	}

}
