package com.test.spring.boot.restservices.filtering;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
@RequestMapping(path = "/filters", produces = { "application/json", "application/xml" })
public class FilterController {

	// Static Filtering
	@GetMapping(path = "/static")
	public FilterBean filter() {
		return new FilterBean();
	}

	// Dynamic Filtering
	@GetMapping(path = "/dynamic")
	public MappingJacksonValue filter1() {
		FilterDynamicBean filterDynamicBean = new FilterDynamicBean();
		SimpleBeanPropertyFilter propertYfilters = SimpleBeanPropertyFilter.filterOutAllExcept("field1");
		FilterProvider profiler = new SimpleFilterProvider().addFilter("DynamicFilterBeanFilter", propertYfilters);
		MappingJacksonValue value = new MappingJacksonValue(filterDynamicBean);
		value.setFilters(profiler);
		return value;
	}

}
