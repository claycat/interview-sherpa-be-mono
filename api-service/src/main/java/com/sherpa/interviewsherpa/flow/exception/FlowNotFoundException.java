package com.sherpa.interviewsherpa.flow.exception;

import java.util.UUID;

import com.sherpa.interviewsherpa.common.exception.DefinedException;
import com.sherpa.interviewsherpa.common.exception.code.FlowExceptionCode;

public class FlowNotFoundException extends DefinedException {
	public FlowNotFoundException(UUID flowId) {
		super(FlowExceptionCode.FLOW_NOT_FOUND, "Flow with id " + flowId + " not found");
	}
}
