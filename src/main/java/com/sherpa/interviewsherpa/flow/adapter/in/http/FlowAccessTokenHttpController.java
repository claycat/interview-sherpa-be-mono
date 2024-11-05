package com.sherpa.interviewsherpa.flow.adapter.in.http;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sherpa.interviewsherpa.auth.application.port.in.FlowAccessTokenUseCase;
import com.sherpa.interviewsherpa.auth.application.port.in.dto.CreateFlowAccessTokenCommand;
import com.sherpa.interviewsherpa.auth.domain.constant.RoleEnum;

@RestController
@RequestMapping("/flows")
public class FlowAccessTokenHttpController {

	private final FlowAccessTokenUseCase flowAccessTokenUseCase;

	public FlowAccessTokenHttpController(FlowAccessTokenUseCase flowAccessTokenUseCase) {
		this.flowAccessTokenUseCase = flowAccessTokenUseCase;
	}

	@GetMapping("/{flowId}/token")
	@PreAuthorize("hasPermission(#flowId, 'SHARE_FLOW')")
	public ResponseEntity<UUID> createToken(@PathVariable UUID flowId, @RequestParam RoleEnum role) {

		var command = new CreateFlowAccessTokenCommand(flowId, role);
		var result = flowAccessTokenUseCase.createFlowAccessToken(command);

		return ResponseEntity.ok(result.token());
	}

	@GetMapping("/{flowId}/tokens/{tokenId}")
	public ResponseEntity<RoleEnum> getTokenRole(@PathVariable UUID flowId, @PathVariable UUID tokenId) {
		return ResponseEntity.ok(flowAccessTokenUseCase.getTokenRole(tokenId));
	}

}
