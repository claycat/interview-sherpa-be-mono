package com.sherpa.interviewsherpa.flow.domain.flowcontent;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sherpa.interviewsherpa.flow.domain.flowcontent.edge.Edge;
import com.sherpa.interviewsherpa.flow.domain.flowcontent.node.Node;
import com.sherpa.interviewsherpa.flow.domain.flowcontent.viewport.Viewport;

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
public class FlowContent {

	private List<Node> nodes;
	private List<Edge> edges;
	private Viewport viewport;

}
