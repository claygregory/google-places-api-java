package com.claygregory.api.google.places;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.claygregory.common.data.geo.GeoLocation;
import com.claygregory.common.util.StringUtil;

public class AutocompleteQueryOptions {
	
	private static final String LOCATION = "location";
	
	private static final String RADIUS = "radius";
	
	private static final String TYPES = "types";

	private Map<String,String> params = new HashMap<String,String>( );
	
	public static AutocompleteQueryOptions create( ) {
		return new AutocompleteQueryOptions( );
	}
	
	public AutocompleteQueryOptions location( GeoLocation location ) {
		this.location( location.getLatitude( ), location.getLongitude( ) );
		this.radius( location.getAccuracy( ) );
		return this;
	}
	
	public AutocompleteQueryOptions location( float lat, float lon ) {
		return this.param( LOCATION, lat + "," + lon );
	}
	
	public AutocompleteQueryOptions radius( Integer radius ) {
		return this.param( RADIUS, radius != null ? String.valueOf( radius ) : null );
	}
	
	public String param( String key ) {
		return this.params.get( key );
	}
	
	public AutocompleteQueryOptions param( String key, String value ) {
		this.params.put( key, value );
		return this;
	}
	
	public Map<String,String> params( ) {
		return Collections.unmodifiableMap( this.params );
	}
	
	public AutocompleteQueryOptions types( String... types ) {
		return this.param( TYPES, StringUtil.join( "|", types ) );
	}
}
