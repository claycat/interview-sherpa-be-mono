package com.sherpa.interviewsherpa.flow.domain.node.usecase.dto;

import com.sherpa.interviewsherpa.flow.domain.node.Node;

import lombok.Builder;

@Builder
public record UpdateNodeCommand(Node node) {
}
