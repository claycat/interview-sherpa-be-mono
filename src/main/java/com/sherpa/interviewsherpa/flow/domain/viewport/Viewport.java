package com.sherpa.interviewsherpa.flow.domain.viewport;

import lombok.Builder;

@Builder
public record Viewport(int x, int y, int zoom) {
}
