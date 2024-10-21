package com.sherpa.interviewsherpa.comment.domain;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor(force = true)
@AllArgsConstructor
@ToString
public class Comment {
	private final UUID id;
	private final UUID nodeId;
	private final UUID ownerId;
	private final Comment parent;
	private String content;
	private final LocalDateTime createdAt;
	private List<Comment> replies;

	public static Comment newRootWithId(UUID id, UUID nodeId, UUID ownerId, String content, LocalDateTime createdAt) {
		return new Comment(id, nodeId, ownerId, null, content, createdAt, null);
	}

}
