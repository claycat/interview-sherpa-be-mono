package com.sherpa.interviewsherpa.flow.domain.edge;

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
}