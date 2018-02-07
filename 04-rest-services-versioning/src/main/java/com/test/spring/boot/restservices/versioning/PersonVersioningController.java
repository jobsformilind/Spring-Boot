package com.test.spring.boot.restservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonVersioningController {

	// Using URI versioning (twitter)
	@GetMapping(path = "v1/person")
	public PersonV1 getPersonV1() {
		return new PersonV1("John Mac");
	}

	@GetMapping(path = "v2/person")
	public PersonV2 getPersonV2() {
		return new PersonV2(new Name("John", "Mac"));
	}

	// Using request parameters versioning (Amazon)
	@GetMapping(path = "person", params = "version=1")
	public PersonV1 paramV1() {
		return new PersonV1("John Mac");
	}

	@GetMapping(path = "person", params = "version=2")
	public PersonV2 paramV2() {
		return new PersonV2(new Name("John", "Mac"));
	}

	// Using header parameter versioning (Microsoft)
	@GetMapping(path = "person", headers = "API-VERSION=1")
	public PersonV1 headerV1() {
		return new PersonV1("John Mac");
	}

	@GetMapping(path = "person", headers = "API-VERSION=2")
	public PersonV2 headerV2() {
		return new PersonV2(new Name("John", "Mac"));
	}

	// Using MIME type or produces (gihub)
	@GetMapping(path = "person", produces = "application/vnd.company.app-v1+json")
	public PersonV1 producesV1() {
		return new PersonV1("John Mac");
	}

	@GetMapping(path = "person", produces = "application/vnd.company.app-v2+json")
	public PersonV2 producesV2() {
		return new PersonV2(new Name("John", "Mac"));
	}
}
