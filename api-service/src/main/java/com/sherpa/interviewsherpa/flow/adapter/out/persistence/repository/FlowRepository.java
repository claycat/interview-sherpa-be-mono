package com.sherpa.interviewsherpa.flow.adapter.out.persistence.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sherpa.interviewsherpa.flow.adapter.out.persistence.entity.FlowJpaEntity;

@Repository
public interface FlowRepository extends JpaRepository<FlowJpaEntity, UUID> {
}
