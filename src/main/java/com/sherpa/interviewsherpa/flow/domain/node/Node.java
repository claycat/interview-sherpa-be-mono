package com.sherpa.interviewsherpa.flow.domain.node;

import com.sherpa.interviewsherpa.flow.domain.node.nodedata.NodeData;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Node {
	private String id;
	private String type;
	private NodeData data;
	private Position position;
	private int width;
	private int height;
	private boolean selected;
	private boolean dragging;
	private PositionAbsolute positionAbsolute;
}
