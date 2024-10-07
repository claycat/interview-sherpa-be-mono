package com.sherpa.interviewsherpa.flow.adapter.out.persistence.converter;

import org.junit.jupiter.api.Test;

class FlowContentConverterTest {

	private final String flowString = "{\n"
		+ "  \"nodes\": [\n"
		+ "    {\n"
		+ "      \"_id\": \"node-1\",\n"
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
		+ "      \"_id\": \"node-2\",\n"
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
		+ "      \"_id\": \"enode-2-node-1\",\n"
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

	@Test
	void convertToDatabaseColumn() {
		//given

		//when

		//then
	}

	@Test
	void convertToEntityAttribute() {
		//given

		//when

		//then
	}
}