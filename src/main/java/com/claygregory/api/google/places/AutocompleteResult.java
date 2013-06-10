package com.claygregory.api.google.places;

import java.util.Iterator;
import java.util.List;


public class AutocompleteResult implements Iterable<Prediction> {

	private List<Prediction> predictions;
	
	private String status;
	
	public String getStatus( ) {
		return this.status;
	}
	
	@Override
	public Iterator<Prediction> iterator( ) {
		return this.predictions.iterator( );
	}

	public int size( ) {
		return this.predictions.size( );
	}

}
