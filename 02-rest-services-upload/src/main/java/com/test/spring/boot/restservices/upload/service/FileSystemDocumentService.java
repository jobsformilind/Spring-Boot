package com.test.spring.boot.restservices.upload.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.test.spring.boot.restservices.upload.DocumentMetaData;
import com.test.spring.boot.restservices.upload.exception.StorageException;

@Component("documentService")
public class FileSystemDocumentService implements DocumentService {
	private final String STORAGE = "storage";
	private static Map<String, DocumentMetaData> documents = new HashMap<String, DocumentMetaData>();

	@PostConstruct
	public void init() {
		File file = new File(STORAGE);
		file.mkdirs();
	}

	@Override
	public DocumentMetaData store(MultipartFile file, DocumentMetaData metadata) {
		try {
			Path path = toPath(metadata.getUuid());
			Files.copy(file.getInputStream(), path);
			documents.put(metadata.getUuid(), metadata);
			return metadata;
		} catch (IOException ex) {
			throw new StorageException("Error while storing document : " + metadata.getName(), ex);
		}
	}

	public void storeOne(MultipartFile file) {
		try {
			Path path = toPath(file.getOriginalFilename());
			Files.copy(file.getInputStream(), path);
		} catch (IOException ex) {
			throw new StorageException("Error while storing document.", ex);
		}
	}

	@Override
	public Resource load(String uuid) {
		DocumentMetaData metadata = getLocalMetaData(uuid);
		return getResource(metadata);
	}

	public List<DocumentMetaData> getAllMetaData() {
		return new ArrayList<DocumentMetaData>(documents.values());
	}

	public DocumentMetaData getMetaData(String uuid) {
		DocumentMetaData metadata = getLocalMetaData(uuid);
		getResource(metadata);
		return metadata;
	}

	private Resource getResource(DocumentMetaData metadata) {
		try {
			Path path = toPath(metadata.getUuid());
			Resource resource = new UrlResource(path.toUri());
			if (!resource.exists() || !resource.isReadable()) {
				throw new StorageException("Requested document not exists : " + metadata.getUuid());
			}
			return resource;
		} catch (IOException ex) {
			throw new StorageException("Requested document not exists : " + metadata.getUuid());
		}
	}

	private DocumentMetaData getLocalMetaData(String uuid) {
		DocumentMetaData metadata = documents.get(uuid);
		if (metadata == null) {
			throw new StorageException("No document found by UUID : " + uuid);
		}
		return metadata;
	}

	private Path toPath(String uuid) {
		File file = new File(STORAGE, uuid);
		return file.toPath();
	}
}
