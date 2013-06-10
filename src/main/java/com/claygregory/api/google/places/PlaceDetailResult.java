package com.claygregory.api.google.places;

import java.util.List;

public class PlaceDetailResult {
	
	private static final String OKAY_STATUS = "OK";
	
	private List<String> htmlAttributions;

	private PlaceDetail result;
	
	private String status;

	public List<String> getHtmlAttributions( ) {
		return this.htmlAttributions;
	}

	public PlaceDetail getResult( ) {
		return this.result;
	}

	public String getStatus( ) {
		return this.status;
	}
	
	public boolean isOkay( ) {
		return OKAY_STATUS.equals( this.getStatus( ) );
	}
}
