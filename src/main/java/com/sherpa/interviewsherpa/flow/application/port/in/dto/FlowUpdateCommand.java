package com.sherpa.interviewsherpa.flow.application.port.in.dto;

import com.sherpa.interviewsherpa.flow.domain.flow.Flow;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor(force = true)
public class FlowUpdateCommand {
	private final Flow flow;

	@Builder
	public FlowUpdateCommand(Flow flow) {
		this.flow = flow;
	}
}
