package com.sherpa.interviewsherpa.flow.adapter.out.persistence.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sherpa.interviewsherpa.flow.domain.flow.Flow;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class FlowContentConverter implements AttributeConverter<Flow, String> {

	private static final ObjectMapper objectMapper = new ObjectMapper();

	@Override
	public String convertToDatabaseColumn(Flow flow) {
		try {
			return objectMapper.writeValueAsString(flow);
		} catch (Exception e) {
			throw new RuntimeException("Error converting flow to JSON", e);
		}
	}

	@Override
	public Flow convertToEntityAttribute(String s) {
		try {
			return objectMapper.readValue(s, Flow.class);
		} catch (Exception e) {
			throw new RuntimeException("Error converting JSON to flow", e);
		}
	}
}
