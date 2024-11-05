package com.sherpa.interviewsherpa.flow.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.sherpa.interviewsherpa.auth.domain.MemberFlowRole;
import com.sherpa.interviewsherpa.auth.domain.Role;
import com.sherpa.interviewsherpa.flow.domain.flowcontent.FlowContent;
import com.sherpa.interviewsherpa.member.domain.Member;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Flow {
	private UUID id;
	private UUID ownerId;
	private FlowContent flowContent;
	private String title;
	private List<MemberFlowRole> memberFlowRoles = new ArrayList<>();

	public Flow(UUID id, UUID ownerId, FlowContent flowContent, String title) {
		this.id = id;
		this.ownerId = ownerId;
		this.flowContent = flowContent;
		this.title = title;
	}

	public Flow(UUID id, UUID ownerId, FlowContent flowContent, String title, List<MemberFlowRole> memberFlowRoles) {
		this.id = id;
		this.ownerId = ownerId;
		this.flowContent = flowContent;
		this.title = title;
		this.memberFlowRoles = memberFlowRoles;
	}

	public static Flow withoutId(UUID ownerId, FlowContent flowContent, String title) {
		return new Flow(null, ownerId, flowContent, title);
	}

	public Role getRole(UUID memberId) {
		return memberFlowRoles.stream()
			.filter(assignment -> assignment.getMemberId().equals(memberId))
			.findFirst()
			.map(MemberFlowRole::getRole)
			.orElse(null);
	}

	public void assignRole(Member member, Role role) {
		var existingAssignment = memberFlowRoles.stream()
			.filter(assignment -> assignment.getMemberId().equals(member.getId()))
			.findFirst();

		if (existingAssignment.isPresent()) {
			existingAssignment.get().changeRole(role);
		} else {
			memberFlowRoles.add(MemberFlowRole.withoutId(member.getId(), role, this.getId()));
		}
	}

}
