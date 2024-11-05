package com.sherpa.interviewsherpa.auth.domain;

import com.sherpa.interviewsherpa.auth.domain.constant.PermissionEnum;

public record Permission(PermissionEnum name) {

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Permission that = (Permission)o;
		return name == that.name;
	}

}
