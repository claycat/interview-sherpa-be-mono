package com.sherpa.interviewsherpa.flow.domain.flow;

import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sherpa.interviewsherpa.flow.domain.edge.Edge;
import com.sherpa.interviewsherpa.flow.domain.node.Node;
import com.sherpa.interviewsherpa.flow.domain.viewport.Viewport;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Flow {
	private UUID id;
	private List<Node> nodes;
	private List<Edge> edges;
	private Viewport viewport;

	public static Flow withoutId(List<Node> nodes, List<Edge> edges, Viewport viewport) {
		return new Flow(null, nodes, edges, viewport);
	}

	public static Flow withId(UUID id, List<Node> nodes, List<Edge> edges, Viewport viewport) {
		return new Flow(id, nodes, edges, viewport);
	}
}
