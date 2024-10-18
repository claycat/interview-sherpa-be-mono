package com.sherpa.interviewsherpa.flow.domain.node;

import com.fasterxml.jackson.annotation.JsonInclude;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Node {
	private String id;
	private String type;
	private NodeData data;
	private XYPosition position;
	private Integer width;
	private Integer height;
	private Boolean selected;
	private Boolean dragging;
	private Boolean resizing;

	private Object style;
	private String className;
	private XYPosition sourceXYPosition;
	private XYPosition targetXYPosition;
	private Boolean hidden;
	private Boolean draggable;
	private Boolean selectable;
	private Boolean connectable;
	private Boolean deletable;
	private String dragHandle;
	private String parentNode;
	private String parentId;
	private Integer zIndex;
	private Object extent;
	private Boolean expandParent;
	private XYPosition positionAbsolute;
	private String ariaLabel;
	private Boolean focusable;

	private Internals internals;

	public static class Internals {
		private Integer z;
		private Object handleBounds;
		private Boolean isParent;

	}
}
