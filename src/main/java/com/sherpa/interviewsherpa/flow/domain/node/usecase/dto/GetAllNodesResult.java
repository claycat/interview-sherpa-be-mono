package com.sherpa.interviewsherpa.flow.domain.node.usecase.dto;

import java.util.List;

import com.sherpa.interviewsherpa.flow.domain.node.Node;

import lombok.Builder;

@Builder
public record GetAllNodesResult(List<Node> nodes) {

}
