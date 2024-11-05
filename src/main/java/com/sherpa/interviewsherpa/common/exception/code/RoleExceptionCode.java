package com.sherpa.interviewsherpa.common.exception.code;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum RoleExceptionCode implements ExceptionCode {

	ROLE_NOT_FOUND(HttpStatus.NOT_FOUND, "Role not found");

	private final HttpStatus httpStatus;
	private final String message;

}
