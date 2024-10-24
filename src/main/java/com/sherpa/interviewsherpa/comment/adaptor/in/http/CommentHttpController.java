package com.sherpa.interviewsherpa.comment.adaptor.in.http;

import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sherpa.interviewsherpa.comment.adaptor.in.http.dto.GetCommentResponse;
import com.sherpa.interviewsherpa.comment.adaptor.in.http.dto.PostCommentRequest;
import com.sherpa.interviewsherpa.comment.adaptor.in.http.dto.PostCommentResponse;
import com.sherpa.interviewsherpa.comment.application.port.in.GetCommentsUseCase;
import com.sherpa.interviewsherpa.comment.application.port.in.PostCommentUseCase;
import com.sherpa.interviewsherpa.comment.application.port.in.dto.GetCommentsCommand;
import com.sherpa.interviewsherpa.comment.application.port.in.dto.PostCommentCommand;
import com.sherpa.interviewsherpa.http.ApiResponse;

import jakarta.validation.Valid;

@RestController
public class CommentHttpController {

	private final PostCommentUseCase postCommentUseCase;
	private final GetCommentsUseCase getCommentsUseCase;

	public CommentHttpController(PostCommentUseCase postCommentUseCase, GetCommentsUseCase getCommentsUseCase) {
		this.postCommentUseCase = postCommentUseCase;
		this.getCommentsUseCase = getCommentsUseCase;
	}

	@GetMapping("/flow/{flowId}/node/{nodeId}/comments")
	public ApiResponse<GetCommentResponse> getComments(
		@PathVariable UUID flowId,
		@PathVariable UUID nodeId) {

		var command = new GetCommentsCommand(flowId, nodeId);
		var result = getCommentsUseCase.getComments(command);

		return ApiResponse.wrap(new GetCommentResponse(result.comments()));
	}

	@PostMapping("/flow/{flowId}/node/{nodeId}/comments")
	public ApiResponse<PostCommentResponse> postComment(
		@PathVariable UUID flowId,
		@PathVariable UUID nodeId,
		@Valid @RequestBody PostCommentRequest request) {
		var command = new PostCommentCommand(
			request.content(),
			flowId,
			request.memberId(),
			nodeId,
			request.parentId()
		);

		System.out.println("request.toString() = " + request.toString());
		System.out.println("command.toString() = " + command.toString());

		var result = postCommentUseCase.postComment(command);
		return ApiResponse.wrap(
			new PostCommentResponse(result.commentId(), result.memberId(), result.nodeId(), result.content(),
				result.createdAt()));
	}

}
