package com.sherpa.interviewsherpa.comment.exception;

import java.util.UUID;

import com.sherpa.interviewsherpa.common.exception.DefinedException;
import com.sherpa.interviewsherpa.common.exception.code.CommentExceptionCode;

public class CommentNotFoundException extends DefinedException {

	public CommentNotFoundException(UUID commentId) {
		super(CommentExceptionCode.COMMENT_NOT_FOUND, "Comment with id " + commentId + " not found");
	}
}
