package com.sherpa.interviewsherpa.auth.domain;

import java.util.UUID;

import lombok.Getter;

@Getter
public class MemberFlowRole {
	private final UUID id;
	private final UUID memberId;
	private Role role;
	private final UUID flowId;

	public MemberFlowRole(UUID id, UUID memberId, Role role, UUID flowId) {
		this.id = id;
		this.memberId = memberId;
		this.role = role;
		this.flowId = flowId;
	}

	public static MemberFlowRole withId(UUID id, UUID memberId, Role role, UUID flowId) {
		return new MemberFlowRole(id, memberId, role, flowId);
	}

	public static MemberFlowRole withoutId(UUID memberId, Role role, UUID flowId) {
		return new MemberFlowRole(null, memberId, role, flowId);
	}

	public void changeRole(Role role) {
		this.role = role;
	}

}
