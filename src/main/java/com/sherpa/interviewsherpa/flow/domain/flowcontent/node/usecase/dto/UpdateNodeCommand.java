package com.sherpa.interviewsherpa.flow.domain.flowcontent.node.usecase.dto;

import com.sherpa.interviewsherpa.flow.domain.flowcontent.node.Node;

import lombok.Builder;

@Builder
public record UpdateNodeCommand(Node node) {
}
