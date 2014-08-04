package com.claygregory.api.google.places;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.claygregory.api.google.places.Place.Location;

public class PlacesQueryOptions {
	
	protected static final String KEYWORD = "keyword";
	
	protected static final String LANGUAGE = "language";
	
	protected static final String LOCATION = "location";
	
	protected static final String NAME = "name";
	
	protected static final String RADIUS = "radius";
	
	protected static final String SENSOR = "sensor";
	
	protected static final String TYPES = "types";

	private Map<String,String> params = new HashMap<String,String>( );
	
	public static PlacesQueryOptions create( ) {
		return new PlacesQueryOptions( );
	}
	
	public PlacesQueryOptions keyword( String keyword ) {
		return this.param( KEYWORD, keyword );
	}
	
	public PlacesQueryOptions language( String language ) {
		return this.param( LANGUAGE, language );
	}
	
	public PlacesQueryOptions location( Location location ) {
		return this.location( location.getLat( ), location.getLng( ) );
	}
	
	public PlacesQueryOptions location( float lat, float lon ) {
		return this.param( LOCATION, lat + "," + lon );
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
	
	public PlacesQueryOptions radius( int radius ) {
		return this.param( RADIUS, String.valueOf( radius ) );
	}
	
	public PlacesQueryOptions sensor( boolean sensor ) {
		return this.param( SENSOR, String.valueOf( sensor ) );
	}
	
	public PlacesQueryOptions types( String... types ) {
		StringBuilder typesString = new StringBuilder( );
		for ( String type : types ) {
			if ( typesString.length( ) != 0 ) typesString.append( '|' );
			typesString.append(type );
		}
			
		return this.param( TYPES, typesString.toString( ) );
	}
}
