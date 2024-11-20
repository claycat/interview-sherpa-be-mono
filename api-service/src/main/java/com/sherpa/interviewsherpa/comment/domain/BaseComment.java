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
public abstract class BaseComment {
	protected UUID id;
	protected UUID nodeId;
	protected UUID ownerId;
	protected Comment parent;
	protected LocalDateTime createdAt;
	protected List<Comment> replies;
}
