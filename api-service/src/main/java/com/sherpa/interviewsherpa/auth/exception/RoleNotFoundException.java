package com.sherpa.interviewsherpa.auth.exception;

import com.sherpa.interviewsherpa.auth.domain.constant.RoleEnum;
import com.sherpa.interviewsherpa.common.exception.DefinedException;
import com.sherpa.interviewsherpa.common.exception.code.RoleExceptionCode;

public class RoleNotFoundException extends DefinedException {
	public RoleNotFoundException(String roleName) {
		super(RoleExceptionCode.ROLE_NOT_FOUND, "Role with name " + roleName + " not found");
	}

	public RoleNotFoundException(RoleEnum role) {
		super(RoleExceptionCode.ROLE_NOT_FOUND, "Role with name " + role.name() + " not found");
	}
}
