package com.sherpa.interviewsherpa.member.adapter.in.http;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sherpa.interviewsherpa.http.ApiResponse;
import com.sherpa.interviewsherpa.member.adapter.in.http.dto.GetMemberFlowDto;
import com.sherpa.interviewsherpa.member.adapter.in.http.dto.GetMemberFlowsResponse;
import com.sherpa.interviewsherpa.member.application.port.in.GetMemberFlowsUseCase;

@RestController
@RequestMapping("/members")
public class MemberHttpController {

	private final GetMemberFlowsUseCase getMemberFlowsUseCase;

	public MemberHttpController(GetMemberFlowsUseCase getMemberFlowsUseCase) {
		this.getMemberFlowsUseCase = getMemberFlowsUseCase;
	}

	@GetMapping("/{memberId}/flows")
	public ResponseEntity<ApiResponse<GetMemberFlowsResponse>> getMemberFlows(@PathVariable UUID memberId) {
		var memberFlows = getMemberFlowsUseCase.getMemberFlows(memberId);

		var response = new GetMemberFlowsResponse(memberFlows.stream()
			.map(e -> new GetMemberFlowDto(e.id(), e.title(), e.updatedAt(), e.createdAt()))
			.toList());
		return ResponseEntity.ok(ApiResponse.wrap(response));
	}

}
