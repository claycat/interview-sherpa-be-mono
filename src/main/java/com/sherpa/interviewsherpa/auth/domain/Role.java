package com.sherpa.interviewsherpa.auth.domain;

import java.util.List;
import java.util.Objects;

import com.sherpa.interviewsherpa.auth.domain.constant.RoleEnum;

public record Role(RoleEnum name, List<Permission> permissions) {

	public boolean hasPermission(Permission permission) {
		return permissions.contains(permission);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Role role = (Role)o;
		return name == role.name;
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}
}
