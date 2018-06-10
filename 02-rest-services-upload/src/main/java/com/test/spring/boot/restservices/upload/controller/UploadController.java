package com.test.spring.boot.restservices.upload.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.tomcat.util.http.fileupload.FileItemIterator;
import org.apache.tomcat.util.http.fileupload.FileItemStream;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
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

	@PostMapping(path = "/file")
	public ResponseEntity<Object> uploadOneFile(@RequestParam(value = "file", required = true) MultipartFile file) {
		documentService.storeOne(file);
		UriComponentsBuilder uriBuilder = ServletUriComponentsBuilder.fromCurrentRequest().path("/{uuid}");
		BodyBuilder builder = ResponseEntity.created(uriBuilder.buildAndExpand(file.getOriginalFilename()).toUri());
		return builder.build();
	}

	
	private InputStream getInputStream(final HttpServletRequest request) throws IOException, FileUploadException {
	    final ServletFileUpload upload = new ServletFileUpload();
	    final FileItemIterator iterator = upload.getItemIterator(request);

	    InputStream is = null;

	    while (iterator.hasNext()) {
	        final FileItemStream item = iterator.next();

	        if (!item.isFormField()) {
	            is = item.openStream();

	            break;
	        }
	    }

	    return is;
	}
}
