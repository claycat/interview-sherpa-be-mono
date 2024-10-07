package com.sherpa.interviewsherpa.auth.adapter.out.persistence.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sherpa.interviewsherpa.auth.adapter.out.persistence.entity.ActionJpaEntity;

@Repository
public interface ActionRepository extends JpaRepository<ActionJpaEntity, UUID> {
}
