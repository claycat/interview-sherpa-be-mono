package com.sherpa.interviewsherpa.member.adapter.out.persistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sherpa.interviewsherpa.member.adapter.out.persistence.entity.MemberJpaEntity;

public interface MemberRepository extends JpaRepository<MemberJpaEntity, String> {

	Optional<MemberJpaEntity> findByEmail(String email);
}
