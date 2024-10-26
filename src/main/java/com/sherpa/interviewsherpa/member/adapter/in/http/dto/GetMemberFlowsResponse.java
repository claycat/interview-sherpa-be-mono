package com.sherpa.interviewsherpa.member.adapter.in.http.dto;

import java.util.List;

public record GetMemberFlowsResponse(
	List<GetMemberFlowDto> flows
) {
}
