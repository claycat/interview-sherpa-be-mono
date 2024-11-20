package com.sherpa.interviewsherpa.init;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.sherpa.interviewsherpa.auth.adapter.out.persistence.entity.PermissionJpaEntity;
import com.sherpa.interviewsherpa.auth.adapter.out.persistence.entity.RoleJpaEntity;
import com.sherpa.interviewsherpa.auth.adapter.out.persistence.entity.RolePermissionJpaEntity;
import com.sherpa.interviewsherpa.auth.adapter.out.persistence.repository.PermissionRepository;
import com.sherpa.interviewsherpa.auth.adapter.out.persistence.repository.RolePermissionRepository;
import com.sherpa.interviewsherpa.auth.adapter.out.persistence.repository.RoleRepository;
import com.sherpa.interviewsherpa.auth.domain.constant.PermissionEnum;
import com.sherpa.interviewsherpa.auth.domain.constant.RoleEnum;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ActionRoleInitializer implements CommandLineRunner {

	private final RoleRepository roleRepository;
	private final PermissionRepository permissionRepository;
	private final RolePermissionRepository rolePermissionRepository;

	public ActionRoleInitializer(RoleRepository roleRepository,
		PermissionRepository permissionRepository,
		RolePermissionRepository rolePermissionRepository) {
		this.roleRepository = roleRepository;
		this.permissionRepository = permissionRepository;
		this.rolePermissionRepository = rolePermissionRepository;
	}

	@Override
	public void run(String... args) throws Exception {

		// Initialize RoleJpaEntities
		RoleJpaEntity ownerRole = new RoleJpaEntity(RoleEnum.OWNER);
		RoleJpaEntity editorRole = new RoleJpaEntity(RoleEnum.EDITOR);
		RoleJpaEntity viewerRole = new RoleJpaEntity(RoleEnum.VIEWER);
		RoleJpaEntity commentatorRole = new RoleJpaEntity(RoleEnum.COMMENTER);

		roleRepository.save(ownerRole);
		roleRepository.save(editorRole);
		roleRepository.save(viewerRole);
		roleRepository.save(commentatorRole);

		log.info("Roles saved");

		// Initialize actions (permissions)
		PermissionJpaEntity viewItemAction = new PermissionJpaEntity(PermissionEnum.VIEW_FLOW);
		PermissionJpaEntity editItemAction = new PermissionJpaEntity(PermissionEnum.EDIT_FLOW);
		PermissionJpaEntity commentItemAction = new PermissionJpaEntity(PermissionEnum.COMMENT_FLOW);
		PermissionJpaEntity deleteItemAction = new PermissionJpaEntity(PermissionEnum.DELETE_FLOW);
		PermissionJpaEntity shareItemAction = new PermissionJpaEntity(PermissionEnum.SHARE_FLOW);
		PermissionJpaEntity changeVisibilityAction = new PermissionJpaEntity(PermissionEnum.CHANGE_VISIBILITY);
		PermissionJpaEntity configureCommentsAction = new PermissionJpaEntity(PermissionEnum.CONFIGURE_COMMENTS);

		permissionRepository.save(viewItemAction);
		permissionRepository.save(editItemAction);
		permissionRepository.save(commentItemAction);
		permissionRepository.save(deleteItemAction);
		permissionRepository.save(shareItemAction);
		permissionRepository.save(changeVisibilityAction);
		permissionRepository.save(configureCommentsAction);

		log.info("Permissions saved");

		// Initialize Role-Action mappings

		// VIEWER: Can only view items
		rolePermissionRepository.save(new RolePermissionJpaEntity(viewerRole, viewItemAction));

		// COMMENTATOR: Can view and comment on items
		rolePermissionRepository.save(new RolePermissionJpaEntity(commentatorRole, viewItemAction));
		rolePermissionRepository.save(new RolePermissionJpaEntity(commentatorRole, commentItemAction));

		// EDITOR: Can view, edit, and comment on items
		rolePermissionRepository.save(new RolePermissionJpaEntity(editorRole, viewItemAction));
		rolePermissionRepository.save(new RolePermissionJpaEntity(editorRole, editItemAction));
		rolePermissionRepository.save(new RolePermissionJpaEntity(editorRole, commentItemAction));

		// OWNER: Has all permissions
		rolePermissionRepository.save(new RolePermissionJpaEntity(ownerRole, viewItemAction));
		rolePermissionRepository.save(new RolePermissionJpaEntity(ownerRole, editItemAction));
		rolePermissionRepository.save(new RolePermissionJpaEntity(ownerRole, commentItemAction));
		rolePermissionRepository.save(new RolePermissionJpaEntity(ownerRole, deleteItemAction));
		rolePermissionRepository.save(new RolePermissionJpaEntity(ownerRole, shareItemAction));
		rolePermissionRepository.save(new RolePermissionJpaEntity(ownerRole, changeVisibilityAction));
		rolePermissionRepository.save(new RolePermissionJpaEntity(ownerRole, configureCommentsAction));

		log.info("Role-Action mappings saved");
	}
}
