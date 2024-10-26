package com.sherpa.interviewsherpa.flow.adapter.in.http;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sherpa.interviewsherpa.flow.adapter.in.http.dto.createflow.CreateFlowRequest;
import com.sherpa.interviewsherpa.flow.adapter.in.http.dto.createflow.CreateFlowResponse;
import com.sherpa.interviewsherpa.flow.adapter.in.http.dto.createflow.PatchFlowRequest;
import com.sherpa.interviewsherpa.flow.adapter.in.http.dto.createflow.PatchFlowTitleRequest;
import com.sherpa.interviewsherpa.flow.adapter.in.http.dto.getflow.GetFlowResponse;
import com.sherpa.interviewsherpa.flow.application.port.in.CreateFlowUseCase;
import com.sherpa.interviewsherpa.flow.application.port.in.DeleteFlowUseCase;
import com.sherpa.interviewsherpa.flow.application.port.in.GetFlowUseCase;
import com.sherpa.interviewsherpa.flow.application.port.in.PatchFlowUseCase;
import com.sherpa.interviewsherpa.flow.application.port.in.dto.getflow.GetFlowCommand;
import com.sherpa.interviewsherpa.flow.application.port.in.dto.patchflow.PatchFlowCommand;
import com.sherpa.interviewsherpa.flow.application.port.in.dto.patchflow.PatchFlowTitleCommand;
import com.sherpa.interviewsherpa.http.ApiResponse;

@RestController
@RequestMapping("/flows")
public class FlowHttpController {

	private final CreateFlowUseCase createFlowUseCase;
	private final PatchFlowUseCase patchFlowUseCase;
	private final GetFlowUseCase getFlowUseCase;
	private final DeleteFlowUseCase deleteFlowUseCase;

	public FlowHttpController(CreateFlowUseCase createFlowUseCase, PatchFlowUseCase patchFlowUseCase,
		GetFlowUseCase getFlowUseCase, DeleteFlowUseCase deleteFlowUseCase) {
		this.createFlowUseCase = createFlowUseCase;
		this.patchFlowUseCase = patchFlowUseCase;
		this.getFlowUseCase = getFlowUseCase;
		this.deleteFlowUseCase = deleteFlowUseCase;
	}

	@PostMapping
	public ResponseEntity<ApiResponse<CreateFlowResponse>> createFlow(@RequestBody CreateFlowRequest request) {
		var createFlowCommand = request.toCommand();
		var createFlowResult = createFlowUseCase.createFlow(createFlowCommand);
		var response = new CreateFlowResponse(createFlowResult.getFlowId());
		return ResponseEntity.ok(ApiResponse.wrap(response));
	}

	@PatchMapping("/{flowId}")
	public ResponseEntity<?> patchFlow(@PathVariable UUID flowId,
		@RequestBody PatchFlowRequest request) {
		var command = new PatchFlowCommand(flowId, request.flow());
		patchFlowUseCase.patchFlow(command);
		return ResponseEntity.ok(ApiResponse.wrap(null));
	}

	@GetMapping("/{flowId}")
	public ResponseEntity<ApiResponse<GetFlowResponse>> getFlow(@PathVariable UUID flowId) {
		var command = new GetFlowCommand(flowId);
		var result = getFlowUseCase.getFlow(command);
		var response = new GetFlowResponse(result.flow());
		return ResponseEntity.ok(ApiResponse.wrap(response));
	}

	@PatchMapping("/{flowId}/title")
	public ResponseEntity<?> updateFlowTitle(@PathVariable UUID flowId, @RequestBody PatchFlowTitleRequest request) {
		var command = new PatchFlowTitleCommand(flowId, request.title());
		patchFlowUseCase.patchFlowTitle(command);
		return ResponseEntity.ok(ApiResponse.wrap(null));
	}

	@DeleteMapping("/{flowId}")
	public ResponseEntity<?> deleteFlow(@PathVariable UUID flowId) {
		deleteFlowUseCase.deleteFlow(flowId);
		return ResponseEntity.ok(ApiResponse.wrap(null));
	}

}
