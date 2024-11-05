package com.sherpa.interviewsherpa.flow.adapter.in.http;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sherpa.interviewsherpa.flow.adapter.in.http.dto.flowrole.GetFlowMemberRoleResponse;
import com.sherpa.interviewsherpa.flow.application.port.in.GetFlowMemberRoleUseCase;
import com.sherpa.interviewsherpa.flow.application.port.in.dto.flowrole.GetFlowMemberRoleCommand;
import com.sherpa.interviewsherpa.http.ApiResponse;

@RestController
@RequestMapping("/flows")
public class FlowMemberRoleController {

	private final GetFlowMemberRoleUseCase getFlowMemberRoleUseCase;

	public FlowMemberRoleController(GetFlowMemberRoleUseCase getFlowMemberRoleUseCase) {
		this.getFlowMemberRoleUseCase = getFlowMemberRoleUseCase;
	}

	@GetMapping("/{flowId}/members/{memberId}/role")
	public ResponseEntity<ApiResponse<GetFlowMemberRoleResponse>> getFlowMemberRole(
		@PathVariable UUID flowId,
		@PathVariable UUID memberId
	) {
		var command = new GetFlowMemberRoleCommand(flowId, memberId);
		var result = getFlowMemberRoleUseCase.getFlowMemberRole(command);
		var response = new GetFlowMemberRoleResponse(result.role());
		return ResponseEntity.ok(ApiResponse.wrap(response));
	}

}
