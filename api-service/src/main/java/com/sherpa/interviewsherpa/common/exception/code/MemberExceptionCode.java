package com.sherpa.interviewsherpa.common.exception.code;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MemberExceptionCode implements ExceptionCode {
	DUPLICATED_EMAIL(HttpStatus.BAD_REQUEST, "Duplicated email"),
	MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "Member does not exist");

	private final HttpStatus httpStatus;
	private final String message;
}
