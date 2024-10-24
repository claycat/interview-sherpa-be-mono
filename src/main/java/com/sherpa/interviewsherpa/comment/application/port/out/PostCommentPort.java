package com.sherpa.interviewsherpa.comment.application.port.out;

import java.util.UUID;

import com.sherpa.interviewsherpa.comment.domain.Comment;

public interface PostCommentPort {
	Comment saveComment(String content, UUID memberId, UUID parentId, UUID nodeId);
}
