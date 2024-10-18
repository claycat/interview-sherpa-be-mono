package com.sherpa.interviewsherpa.flow.adapter.out.persistence;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sherpa.interviewsherpa.flow.domain.flow.Flow;

@Component
public class FlowFactory {

	private final ObjectMapper objectMapper = new ObjectMapper();

	public Flow deserialize(String flowContent) {
		try {
			return objectMapper.readValue(flowContent, Flow.class);
		} catch (Exception e) {
			throw new RuntimeException("Error converting JSON to flow", e);
		}
	}

}
