package com.test.spring.boot.restservices.filtering;

import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("DynamicFilterBeanFilter")
public class FilterDynamicBean {
	private String field1 = "Dyna IND";
	private String field2 = "Dyna USA";
	private String field3 = "Dyna GES";

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
