package com.sherpa.interviewsherpa.auth.exception;

import java.util.UUID;

import com.sherpa.interviewsherpa.common.exception.DefinedException;
import com.sherpa.interviewsherpa.common.exception.code.MemberFlowRoleExceptionCode;

public class MemberFlowRoleNotFoundException extends DefinedException {

	public MemberFlowRoleNotFoundException(UUID flowId, UUID memberId) {
		super(MemberFlowRoleExceptionCode.MEMBER_FLOW_ROLE_NOT_FOUND,
			"MemberFlowRole with " + flowId + " and memberId: " + memberId + " not found");
	}
}
