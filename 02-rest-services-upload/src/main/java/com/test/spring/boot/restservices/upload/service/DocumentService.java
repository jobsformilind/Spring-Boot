package com.test.spring.boot.restservices.upload.service;

import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import com.test.spring.boot.restservices.upload.DocumentMetaData;

public interface DocumentService {

	DocumentMetaData store(MultipartFile file, DocumentMetaData metadata);

	void storeOne(MultipartFile file);

	Resource load(String uuid);

	DocumentMetaData getMetaData(String uuid);

	List<DocumentMetaData> getAllMetaData();
}