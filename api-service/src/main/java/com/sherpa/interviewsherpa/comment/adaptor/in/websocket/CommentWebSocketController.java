package com.sherpa.interviewsherpa.comment.adaptor.in.websocket;

import org.springframework.stereotype.Controller;

import com.sherpa.interviewsherpa.comment.application.port.in.GetCommentsUseCase;
import com.sherpa.interviewsherpa.comment.application.port.in.PostCommentUseCase;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class CommentWebSocketController {

	private final GetCommentsUseCase getCommentsUseCase;
	private final PostCommentUseCase postCommentUseCase;

	public CommentWebSocketController(GetCommentsUseCase getCommentsUseCase, PostCommentUseCase postCommentUseCase
	) {
		this.getCommentsUseCase = getCommentsUseCase;
		this.postCommentUseCase = postCommentUseCase;
	}

	// @MessageMapping("/comment/{nodeId}/get")
	// @SendTo("/topic/comment/{nodeId}")
	// public GetCommentsResponse getComments(@DestinationVariable UUID nodeId) {
	// 	var command = new GetCommentsCommand(nodeId);
	// 	var result = getCommentsUseCase.getComments(command);
	// 	return new GetCommentsResponse(result.comments());
	// }
	//
	// @MessageMapping("/flow/{flowId}/node/{nodeId}/comments/post")
	// @SendTo("/topic/flow/{flowId}/comments")
	// public ApiResponse<PostCommentResponse> postComment(
	// 	@DestinationVariable UUID flowId,
	// 	@DestinationVariable UUID nodeId,
	// 	PostCommentRequest request) {
	// 	var command = new PostCommentCommand(request.content(), flowId, request.memberId(), nodeId, request.parentId(),
	// 		CommentType.USER);
	// 	var result = postCommentUseCase.postComment(command);
	// 	return ApiResponse.wrap(
	// 		new PostCommentResponse(result.commentId(), result.memberId(), result.nodeId(), result.content(),
	// 			result.createdAt()));
	// }

	// @MessageMapping("/comment/{commentId}/patch")
	// @SendTo("/topic/comment/{commentId}")
	// public void patchComment(@DestinationVariable UUID commentId, PatchCommentRequest request) {
	// 	var command = new PatchCommentCommand(commentId, request.content());
	// 	patchCommentUseCase.patchComment(command);
	// }
}
