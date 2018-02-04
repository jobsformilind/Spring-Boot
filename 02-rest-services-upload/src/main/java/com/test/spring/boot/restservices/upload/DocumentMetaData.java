package com.test.spring.boot.restservices.upload;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class DocumentMetaData implements Serializable {
	private static final long serialVersionUID = -2249391492722871125L;
	public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

	private String uuid;
	private String name;
	private Date createdDate;
	private String createdBy;

	public DocumentMetaData(String name, Date createdDate, String createdBy) {
		this(UUID.randomUUID().toString(), name, createdDate, createdBy);
	}

	public DocumentMetaData(String uuid, String name, Date createdDate, String createdBy) {
		super();
		this.uuid = uuid;
		this.name = name;
		this.createdDate = createdDate;
		this.createdBy = createdBy;
	}

	public String getUuid() {
		return uuid;
	}

	public String getName() {
		return name;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}
}
