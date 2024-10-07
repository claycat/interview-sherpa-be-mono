package com.sherpa.interviewsherpa.flow.adapter.in.websocket.dto;

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
public class FlowRequestResponse {
	private final UUID flowId;
	private final Flow flow;
}
