package com.sherpa.interviewsherpa.common.exception.code;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CommentExceptionCode implements ExceptionCode {
	COMMENT_NOT_FOUND(HttpStatus.NOT_FOUND, "Comment does not exist");

	private final HttpStatus httpStatus;
	private final String message;

}
