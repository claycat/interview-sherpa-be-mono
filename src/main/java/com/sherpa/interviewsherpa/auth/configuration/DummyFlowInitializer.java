package com.sherpa.interviewsherpa.auth.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sherpa.interviewsherpa.flow.adapter.out.persistence.entity.FlowJpaEntity;
import com.sherpa.interviewsherpa.flow.adapter.out.persistence.repository.FlowRepository;
import com.sherpa.interviewsherpa.flow.domain.flow.Flow;

@Component
@Profile("dev")
public class DummyFlowInitializer implements CommandLineRunner {

	@Autowired
	private FlowRepository flowRepository;
	@Autowired
	private ObjectMapper objectMapper;

	private final String flowString = "{\n"
		+ "  \"nodes\": [\n"
		+ "    {\n"
		+ "      \"id\": \"node-1\",\n"
		+ "      \"type\": \"customNode\",\n"
		+ "      \"data\": {\n"
		+ "        \"label\": \"Node 1\",\n"
		+ "        \"question\": \"what\",\n"
		+ "        \"answers\": [\n"
		+ "          \"answer\"\n"
		+ "        ]\n"
		+ "      },\n"
		+ "      \"position\": {\n"
		+ "        \"x\": 250,\n"
		+ "        \"y\": 5\n"
		+ "      },\n"
		+ "      \"width\": 83,\n"
		+ "      \"height\": 43,\n"
		+ "      \"selected\": false,\n"
		+ "      \"dragging\": false,\n"
		+ "      \"positionAbsolute\": {\n"
		+ "        \"x\": 250,\n"
		+ "        \"y\": 5\n"
		+ "      }\n"
		+ "    },\n"
		+ "    {\n"
		+ "      \"id\": \"node-2\",\n"
		+ "      \"type\": \"customNode\",\n"
		+ "      \"data\": {\n"
		+ "        \"label\": \"Node node-2\",\n"
		+ "        \"question\": \"aaaa\",\n"
		+ "        \"answers\": [\n"
		+ "          \"bbbb\"\n"
		+ "        ]\n"
		+ "      },\n"
		+ "      \"position\": {\n"
		+ "        \"x\": 88,\n"
		+ "        \"y\": -105\n"
		+ "      },\n"
		+ "      \"width\": 83,\n"
		+ "      \"height\": 43,\n"
		+ "      \"selected\": false,\n"
		+ "      \"dragging\": false,\n"
		+ "      \"positionAbsolute\": {\n"
		+ "        \"x\": 88,\n"
		+ "        \"y\": -105\n"
		+ "      }\n"
		+ "    }\n"
		+ "  ],\n"
		+ "  \"edges\": [\n"
		+ "    {\n"
		+ "      \"id\": \"enode-2-node-1\",\n"
		+ "      \"source\": \"node-2\",\n"
		+ "      \"target\": \"node-1\"\n"
		+ "    }\n"
		+ "  ],\n"
		+ "  \"viewport\": {\n"
		+ "    \"x\": -74,\n"
		+ "    \"y\": 741,\n"
		+ "    \"zoom\": 2\n"
		+ "  }\n"
		+ "}";

	@Override
	public void run(String... args) throws Exception {
		var flow = objectMapper.readValue(flowString, Flow.class);

		System.out.println("flow.toString() = " + flow.toString());

		FlowJpaEntity flowEntity = new FlowJpaEntity(flow);
		flowRepository.save(flowEntity);
	}
}
