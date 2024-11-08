package com.sherpa.interviewsherpa.flow.application.port.in.dto.patchflow;

import java.util.UUID;

public record PatchFlowTitleCommand(UUID flowId, String title) {
}
