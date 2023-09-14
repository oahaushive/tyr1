package com.haushive.tyr1.exception;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

public class UserNotFoundException extends RuntimeException implements GraphQLError {

	private static final long serialVersionUID = 1L; 

	private String invalidField;

	   public UserNotFoundException(String message, String invalidField) {
	       super(message);
	       this.invalidField = invalidField;
	   }

	   @Override
	   public String getMessage() {
	       return super.getMessage();
	   }

	   @Override
	   public List<SourceLocation> getLocations() {
	       return null;
	   }

	   @Override
	   public ErrorType getErrorType() {
	       return null;
	   }

	   @Override
	   public Map<String, Object> getExtensions() {
	       return Collections.singletonMap("invalidField", invalidField);
	   }

}
