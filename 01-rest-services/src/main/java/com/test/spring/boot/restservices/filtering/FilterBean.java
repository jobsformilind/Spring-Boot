package com.test.spring.boot.restservices.filtering;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//One Way, use @JsonIgnoreProperties - class level
@JsonIgnoreProperties(value= {"field2","field3"})
public class FilterBean {
	private String field1 = "IND";
	private String field2 = "USA";
	
	//Other Way, use @JsonIgnore method level
	@JsonIgnore
	private String field3 = "GES";

	public String getField1() {
		return field1;
	}

	public void setField1(String field1) {
		this.field1 = field1;
	}

	public String getField2() {
		return field2;
	}

	public void setField2(String field2) {
		this.field2 = field2;
	}

	public String getField3() {
		return field3;
	}

	public void setField3(String field3) {
		this.field3 = field3;
	}
}
