package com.sherpa.interviewsherpa.auth.domain;

import java.io.Serializable;
import java.util.UUID;

import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.sherpa.interviewsherpa.auth.application.service.FlowAccessControlService;
import com.sherpa.interviewsherpa.auth.domain.constant.PermissionEnum;

public class FlowPermissionEvaluator implements PermissionEvaluator {

	private final FlowAccessControlService flowAccessControlService;

	public FlowPermissionEvaluator(FlowAccessControlService flowAccessControlService) {
		this.flowAccessControlService = flowAccessControlService;
	}

	@Override
	public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
		if (authentication.getPrincipal() instanceof OAuth2User oAuth2User) {
			UUID memberId = (UUID)oAuth2User.getAttributes().get("id");

			if (targetDomainObject instanceof UUID flowId && permission instanceof String permissionString) {
				PermissionEnum permissionEnum = PermissionEnum.valueOf(permissionString);
				Permission perm = new Permission(permissionEnum);

				return flowAccessControlService.memberHasPermission(flowId, memberId, perm);
			}
		}
		return false;
	}

	@Override
	public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType,
		Object permission) {
		return false;
	}
}
