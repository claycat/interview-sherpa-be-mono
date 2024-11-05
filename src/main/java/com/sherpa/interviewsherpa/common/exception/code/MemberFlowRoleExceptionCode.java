package com.sherpa.interviewsherpa.common.exception.code;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MemberFlowRoleExceptionCode implements ExceptionCode {

	MEMBER_FLOW_ROLE_NOT_FOUND(HttpStatus.NOT_FOUND, "Member flow role not found");

	private final HttpStatus httpStatus;
	private final String message;

}
