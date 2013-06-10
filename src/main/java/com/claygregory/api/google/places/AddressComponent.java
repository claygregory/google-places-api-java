package com.claygregory.api.google.places;

import java.util.Collections;
import java.util.Set;

public class AddressComponent {

	private String longName;
	
	private String shortName;
	
	private Set<String> types = Collections.emptySet( );

	public String getLongName( ) {
		return this.longName;
	}

	public String getShortName( ) {
		return this.shortName;
	}

	public Set<String> getTypes( ) {
		return this.types;
	}
}
