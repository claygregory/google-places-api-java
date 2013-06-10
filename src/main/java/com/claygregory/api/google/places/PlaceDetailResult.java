package com.claygregory.api.google.places;

import java.util.List;

public class PlaceDetailResult {
	
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
	
	
}
