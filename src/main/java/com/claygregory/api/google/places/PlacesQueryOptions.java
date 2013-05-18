package com.claygregory.api.google.places;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.claygregory.common.util.StringUtil;

public class PlacesQueryOptions {
	
	private static final String KEYWORD = "keyword";
	
	private static final String NAME = "name";
	
	private static final String TYPES = "types";

	private Map<String,String> params = new HashMap<String,String>( );
	
	public static PlacesQueryOptions create( ) {
		return new PlacesQueryOptions( );
	}
	
	public PlacesQueryOptions keyword( String keyword ) {
		return this.param( KEYWORD, keyword );
	}
	
	public PlacesQueryOptions name( String name ) {
		return this.param( NAME, name );
	}
	
	public String param( String key ) {
		return this.params.get( key );
	}
	
	public PlacesQueryOptions param( String key, String value ) {
		this.params.put( key, value );
		return this;
	}
	
	public Map<String,String> params( ) {
		return Collections.unmodifiableMap( this.params );
	}
	
	public PlacesQueryOptions types( String... types ) {
		return this.param( TYPES, StringUtil.join( "|", types ) );
	}
}
