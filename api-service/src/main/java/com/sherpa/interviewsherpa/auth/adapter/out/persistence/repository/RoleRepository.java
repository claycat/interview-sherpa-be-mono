package com.sherpa.interviewsherpa.auth.adapter.out.persistence.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sherpa.interviewsherpa.auth.adapter.out.persistence.entity.RoleJpaEntity;
import com.sherpa.interviewsherpa.auth.domain.constant.RoleEnum;

@Repository
public interface RoleRepository extends JpaRepository<RoleJpaEntity, UUID> {
	Optional<RoleJpaEntity> findByName(RoleEnum name);
}
