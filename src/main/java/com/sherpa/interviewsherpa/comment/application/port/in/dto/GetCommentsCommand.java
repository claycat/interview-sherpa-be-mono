package com.sherpa.interviewsherpa.comment.application.port.in.dto;

import java.util.UUID;

public record GetCommentsCommand(
	UUID flowId,
	UUID nodeId
) {
}
