package com.sherpa.interviewsherpa.flow.application.port.in.dto;

import java.util.UUID;

import com.sherpa.interviewsherpa.flow.domain.flow.Flow;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
public class FlowRequestResult {
	private final UUID flowId;
	private final Flow flow;
}
