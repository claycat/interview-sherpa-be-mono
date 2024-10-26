package com.sherpa.interviewsherpa.flow.application.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.sherpa.interviewsherpa.comment.application.port.out.DeleteFlowCommentPort;
import com.sherpa.interviewsherpa.flow.application.port.in.DeleteFlowUseCase;
import com.sherpa.interviewsherpa.flow.application.port.out.DeleteFlowPort;

import jakarta.transaction.Transactional;

@Service
public class DeleteFlowService implements DeleteFlowUseCase {
	private final DeleteFlowPort deleteFlowPort;
	private final DeleteFlowCommentPort deleteFlowCommentPort;

	public DeleteFlowService(DeleteFlowPort deleteFlowPort, DeleteFlowCommentPort deleteFlowCommentPort) {
		this.deleteFlowPort = deleteFlowPort;
		this.deleteFlowCommentPort = deleteFlowCommentPort;
	}

	@Override
	@Transactional
	public void deleteFlow(UUID flowId) {
		deleteFlowPort.deleteFlow(flowId);
		deleteFlowCommentPort.deleteFlowComments(flowId);
	}
}
