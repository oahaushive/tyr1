package com.haushive.tyr1.exception;

import java.util.List;

import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

public class UserAlreadyExistsException extends RuntimeException implements GraphQLError {

	private static final long serialVersionUID = 1L;

	public UserAlreadyExistsException(String message) {
	       super(message);

	}

	@Override
	public List<SourceLocation> getLocations() {
	    return null;
	}

    @Override
	public ErrorType getErrorType() {
	    return null;
	}
	
}