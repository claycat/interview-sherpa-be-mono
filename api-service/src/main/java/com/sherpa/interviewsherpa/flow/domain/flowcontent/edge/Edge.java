package com.sherpa.interviewsherpa.flow.domain.flowcontent.edge;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Edge {
	private String id;
	private String source;
	private String target;
	private String type;
	private String sourceHandle;
	private String targetHandle;
}