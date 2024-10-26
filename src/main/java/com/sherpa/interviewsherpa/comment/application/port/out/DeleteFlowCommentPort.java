package com.sherpa.interviewsherpa.comment.application.port.out;

import java.util.UUID;

public interface DeleteFlowCommentPort {
	void deleteFlowComments(UUID flowId);
}
