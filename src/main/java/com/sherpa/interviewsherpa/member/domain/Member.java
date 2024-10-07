package com.sherpa.interviewsherpa.member.domain;

import java.util.UUID;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Member {

	private final UUID id;

	private final String email;

	private final String name;

	private final String profileURL;

	public static Member withoutId(String email, String name, String profileURL) {
		return new Member(null, email, name, profileURL);
	}

	public static Member withId(UUID id, String email, String name, String profileURL) {
		return new Member(id, email, name, profileURL);
	}

}
