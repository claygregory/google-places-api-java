package com.claygregory.api.google.places;

public class Term {

	private int offset;
	
	private String value;

	public int getOffset( ) {
		return this.offset;
	}

	public String getValue( ) {
		return this.value;
	}
	
	@Override
	public String toString( ) {
		return this.value;
	}
}
