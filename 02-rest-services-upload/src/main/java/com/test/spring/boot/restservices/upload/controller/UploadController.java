package com.test.spring.boot.restservices.upload.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import com.test.spring.boot.restservices.upload.DocumentMetaData;
import com.test.spring.boot.restservices.upload.service.DocumentService;

@RestController
public class UploadController {

	@Autowired
	private DocumentService documentService;

	@PostMapping(path = "/files")
	public ResponseEntity<Object> uploadFile(@RequestParam(value = "file", required = true) MultipartFile file,
			@RequestParam(value = "uploadedBy", required = true) String uploadedBy) {

		DocumentMetaData metadata = new DocumentMetaData(file.getOriginalFilename(), new Date(), uploadedBy);
		documentService.store(file, metadata);

		UriComponentsBuilder uriBuilder = ServletUriComponentsBuilder.fromCurrentRequest().path("/{uuid}");
		BodyBuilder builder = ResponseEntity.created(uriBuilder.buildAndExpand(metadata.getUuid()).toUri());
		return builder.build();
	}

	@GetMapping(path = "/files/{uuid}")
	public ResponseEntity<Object> getMetadata(@PathVariable String uuid) {
		DocumentMetaData metadata = documentService.getMetaData(uuid);
		return ResponseEntity.ok(metadata);
	}

	@GetMapping(path = "/files")
	public ResponseEntity<Object> getAllMetadata() {
		List<DocumentMetaData> metadata = documentService.getAllMetaData();
		return ResponseEntity.ok(metadata);
	}

}
