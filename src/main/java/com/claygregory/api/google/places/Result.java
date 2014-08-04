package com.claygregory.api.google.places;

public class Result {
	
	private static final String OKAY_STATUS = "OK";
	
	private String status;

	public String getStatus( ) {
		return this.status;
	}
	
	public boolean isOkay( ) {
		return OKAY_STATUS.equals( this.getStatus( ) );
	}

}
