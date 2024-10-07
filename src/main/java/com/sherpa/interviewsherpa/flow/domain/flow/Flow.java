package com.sherpa.interviewsherpa.flow.domain.flow;

import java.util.List;

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
public class Flow {
	private List<Node> nodes;
	private List<Edge> edges;
	private Viewport viewport;

}
