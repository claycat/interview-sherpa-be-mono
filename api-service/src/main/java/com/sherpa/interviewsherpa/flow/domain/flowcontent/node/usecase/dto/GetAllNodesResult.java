package com.sherpa.interviewsherpa.flow.domain.flowcontent.node.usecase.dto;

import java.util.List;

import com.sherpa.interviewsherpa.flow.domain.flowcontent.node.Node;

import lombok.Builder;

@Builder
public record GetAllNodesResult(List<Node> nodes) {

}
