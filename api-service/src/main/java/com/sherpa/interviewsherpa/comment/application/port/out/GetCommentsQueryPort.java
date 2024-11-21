package com.sherpa.interviewsherpa.comment.application.port.out;

import java.util.List;
import java.util.UUID;

import com.sherpa.interviewsherpa.comment.application.port.in.dto.CommentQueryModel;

public interface GetCommentsQueryPort {
	List<CommentQueryModel> getComments(UUID flowId, UUID nodeId);
}
