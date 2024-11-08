package com.sherpa.interviewsherpa.auth.adapter.out.persistence.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sherpa.interviewsherpa.auth.adapter.out.persistence.entity.MemberFlowRoleJpaEntity;

@Repository
public interface MemberFlowRoleRepository extends JpaRepository<MemberFlowRoleJpaEntity, UUID> {
	Optional<MemberFlowRoleJpaEntity> findByFlow_IdAndMember_Id(UUID flowId, UUID memberId);
}
