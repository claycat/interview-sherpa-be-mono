package com.sherpa.interviewsherpa.auth.application.port.in.dto;

import java.util.UUID;

public record CreateFlowAccessTokenResult(
	UUID token
) {
}
