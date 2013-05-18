package com.claygregory.api.google.places;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import com.claygregory.common.data.geo.GeoLocation;
import com.claygregory.common.net.URLBuilder;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class GooglePlaces {
	
	private static final int DEFAULT_RADIUS = 500;
	
	private static final String SEARCH_URL = "https://maps.googleapis.com/maps/api/place/nearbysearch/json";

	private String apikey;
	
	private HttpClient client;
	
	private Gson gson;
	
	public GooglePlaces( String apikey ) {
		this.apikey = apikey;
		
		GsonBuilder gb = new GsonBuilder( );
		gb.setFieldNamingPolicy( FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES );
		this.gson = gb.create( );
		
		this.client = new DefaultHttpClient( );
	}
	
	private PlacesResult parseResponse( HttpResponse response ) throws IOException {
		return this.gson.fromJson( new InputStreamReader( response.getEntity( ).getContent( ) ), PlacesResult.class );
	}
	
	public PlacesResult search( float lat, float lon, int radius, boolean sensor ) {
		return search( lat, lon, radius, null, sensor );
	}
	
	public PlacesResult search( float lat, float lon, int radius, PlacesQueryOptions options, boolean sensor ) {
		try {
			URLBuilder builder = URLBuilder.create( SEARCH_URL )
				.queryParam( "key", this.apikey )
				.queryParam( "location", lat + "," + lon )
				.queryParam( "radius", String.valueOf( radius ) )
				.queryParam( "sensor", String.valueOf( sensor ) );
			
			if ( options != null )
				for ( String param : options.params( ).keySet( ) )
					builder.queryParam( param, options.param( param ) );
						
			HttpGet get = new HttpGet( builder.buildURL( ).toString( ) );
			return this.parseResponse( this.client.execute( get ) );
			
		} catch( Exception e ) {
			throw new PlacesException( e );
		}
	}
	
	public PlacesResult search( GeoLocation location, boolean sensor ) {
		return this.search( location, null, sensor );
	}
	
	public PlacesResult search( GeoLocation location, PlacesQueryOptions options, boolean sensor ) {
		return this.search( location.getLatitude( ), location.getLongitude( ), location.getAccuracy( ) != null ? location.getAccuracy( ) : DEFAULT_RADIUS, options, sensor );
	}
	
	public PlacesResult search( String pageToken, boolean sensor ) {
		
		try {
			URL url = URLBuilder.create( SEARCH_URL )
					.queryParam( "key", this.apikey )
					.queryParam( "pagetoken", pageToken )
					.queryParam( "sensor", String.valueOf( sensor ) )
					.buildURL( );
			
			HttpGet get = new HttpGet( url.toString( ) );
			return this.parseResponse( this.client.execute( get ) );
			
		} catch( Exception e ) {
			throw new PlacesException( e );
		}	
	}
}
