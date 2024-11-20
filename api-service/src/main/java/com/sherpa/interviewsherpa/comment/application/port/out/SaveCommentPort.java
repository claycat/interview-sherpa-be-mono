package com.sherpa.interviewsherpa.comment.application.port.out;

import java.util.UUID;

import com.sherpa.interviewsherpa.comment.constant.CommentType;
import com.sherpa.interviewsherpa.comment.domain.Comment;

import ai.AIModelProvider;

public interface SaveCommentPort {
	Comment saveComment(String content, UUID memberId, UUID parentId, UUID nodeId, UUID flowId,
		CommentType commentType, AIModelProvider provider);

}
