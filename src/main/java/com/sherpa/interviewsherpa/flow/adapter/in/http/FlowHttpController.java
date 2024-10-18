package com.sherpa.interviewsherpa.flow.adapter.in.http;

import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sherpa.interviewsherpa.flow.adapter.in.http.dto.createflow.CreateFlowRequest;
import com.sherpa.interviewsherpa.flow.adapter.in.http.dto.createflow.CreateFlowResponse;
import com.sherpa.interviewsherpa.flow.adapter.in.http.dto.getflow.GetFlowResponse;
import com.sherpa.interviewsherpa.flow.application.port.in.GetFlowUseCase;
import com.sherpa.interviewsherpa.flow.application.port.in.dto.getflow.GetFlowCommand;
import com.sherpa.interviewsherpa.flow.application.service.CreateFlowService;
import com.sherpa.interviewsherpa.http.ApiResponse;

@RestController
@RequestMapping("/flow")
public class FlowHttpController {

	private final CreateFlowService createFlowService;
	private final GetFlowUseCase getFlowUseCase;

	public FlowHttpController(CreateFlowService createFlowService, GetFlowUseCase getFlowUseCase) {
		this.createFlowService = createFlowService;
		this.getFlowUseCase = getFlowUseCase;
	}

	@PostMapping
	public ApiResponse<CreateFlowResponse> createFlow(@RequestBody CreateFlowRequest request) {
		var createFlowCommand = request.toCommand();
		var createFlowResult = createFlowService.createFlow(createFlowCommand);
		var response = new CreateFlowResponse(createFlowResult.getFlowId());
		return ApiResponse.wrap(response);
	}

	@GetMapping("/{flowId}")
	public ApiResponse<GetFlowResponse> getFlow(@PathVariable UUID flowId) {
		var command = new GetFlowCommand(flowId);
		var result = getFlowUseCase.getFlow(command);
		var response = new GetFlowResponse(result.flow());
		return ApiResponse.wrap(response);
	}

}
