package com.sherpa.interviewsherpa.member.adapter.out.persistence.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sherpa.interviewsherpa.member.adapter.out.persistence.entity.MemberJpaEntity;

public interface MemberRepository extends JpaRepository<MemberJpaEntity, UUID> {

	Optional<MemberJpaEntity> findByEmail(String email);
}
