package com.sherpa.interviewsherpa.http;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor(force = true)
public class ApiResponse<T> {
	private final T data;
	private final boolean success;

	private ApiResponse(T data, boolean success) {
		this.data = data;
		this.success = success;
	}

	public static <T> ApiResponse<T> wrap(T data) {
		return new ApiResponse<>(data, true);
	}

}
