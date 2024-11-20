package com.sherpa.interviewsherpa.comment.domain;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor(force = true)
@AllArgsConstructor
@ToString
public class Comment extends BaseComment {
	private String content;

	public Comment(UUID id, UUID nodeId, UUID ownerId, String content, LocalDateTime createdAt) {
		this.id = id;
		this.nodeId = nodeId;
		this.ownerId = ownerId;
		this.content = content;
		this.createdAt = createdAt;
	}

	public static Comment newRootWithId(UUID id, UUID nodeId, UUID ownerId, String content, LocalDateTime createdAt) {
		return new Comment(id, nodeId, ownerId, content, createdAt);
	}

}
