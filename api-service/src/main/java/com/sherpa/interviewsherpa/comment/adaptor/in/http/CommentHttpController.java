package com.sherpa.interviewsherpa.comment.adaptor.in.http;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sherpa.interviewsherpa.comment.adaptor.in.http.dto.GetCommentResponse;
import com.sherpa.interviewsherpa.comment.adaptor.in.http.dto.GetCommentResponseDto;
import com.sherpa.interviewsherpa.comment.adaptor.in.http.dto.PostCommentRequest;
import com.sherpa.interviewsherpa.comment.adaptor.in.http.dto.PostCommentResponse;
import com.sherpa.interviewsherpa.comment.application.port.in.GetCommentsUseCase;
import com.sherpa.interviewsherpa.comment.application.port.in.PostCommentUseCase;
import com.sherpa.interviewsherpa.comment.application.port.in.dto.GetCommentsCommand;
import com.sherpa.interviewsherpa.comment.application.port.in.dto.PostCommentCommand;
import com.sherpa.interviewsherpa.comment.constant.CommentType;
import com.sherpa.interviewsherpa.http.ApiResponse;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class CommentHttpController {

	private final PostCommentUseCase postCommentUseCase;
	private final GetCommentsUseCase getCommentsUseCase;

	public CommentHttpController(PostCommentUseCase postCommentUseCase, GetCommentsUseCase getCommentsUseCase) {
		this.postCommentUseCase = postCommentUseCase;
		this.getCommentsUseCase = getCommentsUseCase;
	}

	@GetMapping("/flows/{flowId}/nodes/{nodeId}/comments")
	@PreAuthorize("hasPermission(#flowId, 'VIEW_FLOW') || @flowAccessTokenService.isValidToken(#token, 'VIEW_FLOW', #flowId)")
	public ResponseEntity<ApiResponse<GetCommentResponse>> getComments(
		@PathVariable UUID flowId,
		@PathVariable UUID nodeId,
		@RequestParam(required = false) UUID token
	) {

		var command = new GetCommentsCommand(flowId, nodeId);
		var result = getCommentsUseCase.getComments(command);

		List<GetCommentResponseDto> responseComments = result.comments().stream()
			.map(comment -> new GetCommentResponseDto(
				comment.id(),
				comment.type(),
				comment.provider(),
				comment.parentId(),
				comment.content(),
				comment.author(),
				comment.profileURL(),
				comment.createdAt()
			))
			.toList();

		return ResponseEntity.ok(ApiResponse.wrap(new GetCommentResponse(responseComments)));
	}

	@PostMapping("/flows/{flowId}/nodes/{nodeId}/comments")
	@PreAuthorize("hasPermission(#flowId, 'COMMENT_FLOW') || @flowAccessTokenService.isValidToken(#token, 'COMMENT_FLOW', #flowId)")
	public ResponseEntity<ApiResponse<PostCommentResponse>> postComment(
		@PathVariable UUID flowId,
		@PathVariable UUID nodeId,
		@Valid @RequestBody PostCommentRequest request,
		@RequestHeader(value = "flow-token", required = false) UUID token
	) {

		log.info("Member {} posting comment on node {} on flow {}", request.memberId(), nodeId, flowId);
		var command = new PostCommentCommand(
			request.question(),
			request.content(),
			flowId,
			request.memberId(),
			nodeId,
			request.parentId(),
			request.requestAIEvaluation(),
			CommentType.USER
		);

		var result = postCommentUseCase.postComment(command);
		return ResponseEntity.ok(ApiResponse.wrap(
			new PostCommentResponse(result.commentId(), result.memberId(), result.nodeId(), result.content(),
				result.createdAt())));
	}

}
