package com.sherpa.interviewsherpa.auth.application.port.out;

import com.sherpa.interviewsherpa.auth.domain.Role;
import com.sherpa.interviewsherpa.auth.domain.constant.RoleEnum;

public interface GetRolePort {
	Role getRole(RoleEnum role);
}
