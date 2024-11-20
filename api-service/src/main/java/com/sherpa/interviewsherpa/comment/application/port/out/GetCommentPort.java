package com.sherpa.interviewsherpa.comment.application.port.out;

import java.util.UUID;

import com.sherpa.interviewsherpa.comment.domain.Comment;

public interface GetCommentPort {
	Comment getComment(UUID commentId);
}
