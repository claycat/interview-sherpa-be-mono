package com.sherpa.interviewsherpa.member.exception;

import com.sherpa.interviewsherpa.common.exception.DefinedException;
import com.sherpa.interviewsherpa.common.exception.code.MemberExceptionCode;

public class MemberNotFoundException extends DefinedException {
	public MemberNotFoundException(String email) {
		super(MemberExceptionCode.MEMBER_NOT_FOUND, "Member with email " + email + " not found");
	}
}
