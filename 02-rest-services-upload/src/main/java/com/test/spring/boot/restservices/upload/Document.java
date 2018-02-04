package com.test.spring.boot.restservices.upload;

import java.io.InputStream;
import java.util.Date;

public class Document extends DocumentMetaData {

	private static final long serialVersionUID = 3151681110561744027L;
	private InputStream stream;

	public InputStream getStream() {
		return stream;
	}

	public void setStream(InputStream stream) {
		this.stream = stream;
	}

	public Document(InputStream stream, String name, Date createdDate, String createdBy) {
		super(name, createdDate, createdBy);
		this.stream = stream;
	}

	public DocumentMetaData getMetaData() {
		return new DocumentMetaData(getUuid(), getName(), getCreatedDate(), getCreatedBy());
	}
}
