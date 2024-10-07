package com.sherpa.interviewsherpa.common.exception.code;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum FlowExceptionCode implements ExceptionCode {
	FLOW_NOT_FOUND(HttpStatus.NOT_FOUND, "Flow not found");

	private final HttpStatus httpStatus;
	private final String message;
}
