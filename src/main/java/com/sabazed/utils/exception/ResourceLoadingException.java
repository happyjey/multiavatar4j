package com.sabazed.utils.exception;

import java.io.IOException;

public class ResourceLoadingException extends IOException {

	private static final String EXCEPTION_MESSAGE = "Failed to read resource or its content: %s";

	public ResourceLoadingException(String path, Throwable cause) {
		super(EXCEPTION_MESSAGE.formatted(path), cause);
	}

}
