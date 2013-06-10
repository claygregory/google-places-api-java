package com.claygregory.api.google.places;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;


public class PlacesResult implements Iterable<Place> {
	
	private static final String OKAY_STATUS = "OK";
	
	private String nextPageToken;

	private List<Place> results;
	
	private String status;
	
	public List<Place> asList( ) {
		return Collections.unmodifiableList( this.results );
	}

	public String getNextPageToken( ) {
		return this.nextPageToken;
	}
	
	public String getStatus( ) {
		return this.status;
	}

	public boolean isOkay( ) {
		return OKAY_STATUS.equals( this.getStatus( ) );
	}
	
	@Override
	public Iterator<Place> iterator( ) {
		return this.results.iterator( );
	}

	public int size( ) {
		return this.results.size( );
	}

}
