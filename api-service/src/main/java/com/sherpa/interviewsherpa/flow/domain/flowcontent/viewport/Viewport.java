package com.sherpa.interviewsherpa.flow.domain.flowcontent.viewport;

import lombok.Builder;

@Builder
public record Viewport(Double x, Double y, Double zoom) {
}
