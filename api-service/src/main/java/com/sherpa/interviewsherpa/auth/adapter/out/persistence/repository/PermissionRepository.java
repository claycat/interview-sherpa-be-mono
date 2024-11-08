package com.sherpa.interviewsherpa.auth.adapter.out.persistence.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sherpa.interviewsherpa.auth.adapter.out.persistence.entity.PermissionJpaEntity;

@Repository
public interface PermissionRepository extends JpaRepository<PermissionJpaEntity, UUID> {
}
