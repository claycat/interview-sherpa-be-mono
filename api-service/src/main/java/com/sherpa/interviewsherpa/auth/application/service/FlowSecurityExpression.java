package com.sherpa.interviewsherpa.auth.application.service;

import org.springframework.stereotype.Component;

@Component
public class FlowSecurityExpression {

	private final FlowAccessControlService flowAccessControlService;

	public FlowSecurityExpression(FlowAccessControlService flowAccessControlService) {
		this.flowAccessControlService = flowAccessControlService;
	}
	
}
