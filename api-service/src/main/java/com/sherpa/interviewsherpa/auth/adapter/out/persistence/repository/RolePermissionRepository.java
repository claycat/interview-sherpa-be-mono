package com.sherpa.interviewsherpa.auth.adapter.out.persistence.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sherpa.interviewsherpa.auth.adapter.out.persistence.entity.RoleJpaEntity;
import com.sherpa.interviewsherpa.auth.adapter.out.persistence.entity.RolePermissionJpaEntity;

@Repository
public interface RolePermissionRepository extends JpaRepository<RolePermissionJpaEntity, UUID> {
	List<RolePermissionJpaEntity> findByRole(RoleJpaEntity role);
}
