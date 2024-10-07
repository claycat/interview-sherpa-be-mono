package com.sherpa.interviewsherpa.auth.configuration;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.sherpa.interviewsherpa.auth.adapter.out.persistence.constant.ActionEnum;
import com.sherpa.interviewsherpa.auth.adapter.out.persistence.constant.RoleEnum;
import com.sherpa.interviewsherpa.auth.adapter.out.persistence.entity.ActionJpaEntity;
import com.sherpa.interviewsherpa.auth.adapter.out.persistence.entity.RoleActionJpaEntity;
import com.sherpa.interviewsherpa.auth.adapter.out.persistence.entity.RoleJpaEntity;
import com.sherpa.interviewsherpa.auth.adapter.out.persistence.repository.ActionRepository;
import com.sherpa.interviewsherpa.auth.adapter.out.persistence.repository.RoleActionRepository;
import com.sherpa.interviewsherpa.auth.adapter.out.persistence.repository.RoleRepository;

@Component
public class ActionRoleInitializer implements CommandLineRunner {

	private final RoleRepository roleRepository;
	private final ActionRepository actionRepository;
	private final RoleActionRepository roleActionRepository;

	public ActionRoleInitializer(RoleRepository roleRepository,
		ActionRepository actionRepository,
		RoleActionRepository roleActionRepository) {
		this.roleRepository = roleRepository;
		this.actionRepository = actionRepository;
		this.roleActionRepository = roleActionRepository;
	}

	@Override
	public void run(String... args) throws Exception {

		// Initialize RoleJpaEntities
		RoleJpaEntity viewerRoleJpaEntity = new RoleJpaEntity(RoleEnum.VIEWER);
		RoleJpaEntity editorRoleJpaEntity = new RoleJpaEntity(RoleEnum.EDITOR);
		RoleJpaEntity commenterRoleJpaEntity = new RoleJpaEntity(RoleEnum.COMMENTER);
		RoleJpaEntity adminRoleJpaEntity = new RoleJpaEntity(RoleEnum.ADMIN);
		RoleJpaEntity ownerRoleJpaEntity = new RoleJpaEntity(RoleEnum.OWNER);

		roleRepository.save(viewerRoleJpaEntity);
		roleRepository.save(editorRoleJpaEntity);
		roleRepository.save(commenterRoleJpaEntity);
		roleRepository.save(adminRoleJpaEntity);
		roleRepository.save(ownerRoleJpaEntity);

		// Initialize actions
		ActionJpaEntity editFlowAction = new ActionJpaEntity(ActionEnum.EDIT_FLOW);
		ActionJpaEntity viewFlowAction = new ActionJpaEntity(ActionEnum.VIEW_FLOW);
		ActionJpaEntity deleteFlowAction = new ActionJpaEntity(ActionEnum.DELETE_FLOW);
		ActionJpaEntity commentFlowAction = new ActionJpaEntity(ActionEnum.CREATE_FLOW_COMMENT);

		actionRepository.save(editFlowAction);
		actionRepository.save(viewFlowAction);
		actionRepository.save(deleteFlowAction);
		actionRepository.save(commentFlowAction);

		// Initialize RoleJpaEntity-action mappings

		//VIEWER
		roleActionRepository.save(new RoleActionJpaEntity(viewerRoleJpaEntity, viewFlowAction));

		//EDITOR
		roleActionRepository.save(new RoleActionJpaEntity(editorRoleJpaEntity, editFlowAction));
		roleActionRepository.save(new RoleActionJpaEntity(editorRoleJpaEntity, viewFlowAction));

		//COMMENTER
		//TODO : might have to add comment edit actions too
		roleActionRepository.save(new RoleActionJpaEntity(commenterRoleJpaEntity, commentFlowAction));
		roleActionRepository.save(new RoleActionJpaEntity(commenterRoleJpaEntity, viewFlowAction));

		//ADMIN
		roleActionRepository.save(new RoleActionJpaEntity(adminRoleJpaEntity, editFlowAction));
		roleActionRepository.save(new RoleActionJpaEntity(adminRoleJpaEntity, viewFlowAction));
		roleActionRepository.save(new RoleActionJpaEntity(adminRoleJpaEntity, deleteFlowAction));

		//OWNER
		roleActionRepository.save(new RoleActionJpaEntity(ownerRoleJpaEntity, editFlowAction));
		roleActionRepository.save(new RoleActionJpaEntity(ownerRoleJpaEntity, viewFlowAction));
		roleActionRepository.save(new RoleActionJpaEntity(ownerRoleJpaEntity, deleteFlowAction));
		roleActionRepository.save(new RoleActionJpaEntity(ownerRoleJpaEntity, commentFlowAction));
	}
}