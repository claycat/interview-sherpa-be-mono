package com.sherpa.interviewsherpa.auth.adapter.out.persistence.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sherpa.interviewsherpa.auth.adapter.out.persistence.entity.FlowAccessTokenJpaEntity;

@Repository
public interface FlowAccessTokenRepository extends JpaRepository<FlowAccessTokenJpaEntity, UUID> {
	List<FlowAccessTokenJpaEntity> findByRole_IdAndFlow_Id(UUID roleId, UUID flowId);

	Optional<FlowAccessTokenJpaEntity> findByToken(UUID token);
}
