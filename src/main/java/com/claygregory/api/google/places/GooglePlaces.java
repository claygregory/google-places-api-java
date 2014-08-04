package com.claygregory.api.google.places;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;

import com.claygregory.api.google.places.Place.Location;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class GooglePlaces {
	
	private static final String AUTOCOMPLETE_URL = "https://maps.googleapis.com/maps/api/place/autocomplete/json";
	
	private static final String DETAIL_URL = "https://maps.googleapis.com/maps/api/place/details/json";
	
	private static final String PHOTO_URL = "https://maps.googleapis.com/maps/api/place/photo";
	
	private static final String NEARBY_SEARCH_URL = "https://maps.googleapis.com/maps/api/place/nearbysearch/json";

    private static final String TEXT_SEARCH_URL = "https://maps.googleapis.com/maps/api/place/textsearch/json";

	private String apikey;
	
	private HttpClient client;
	
	private Gson gson;
	
	public GooglePlaces( String apikey ) {
		this( HttpClientBuilder.create( ).useSystemProperties( ).build( ), apikey );
	}
	
	public GooglePlaces( HttpClient client, String apikey ) {
		this.apikey = apikey;
		
		GsonBuilder gb = new GsonBuilder( );
		gb.setFieldNamingPolicy( FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES );
		this.gson = gb.create( );
		
		this.client = client;
	}

	private AutocompleteResult parseAutocompleteResponse( HttpResponse response ) throws IOException {
		return this.gson.fromJson( new InputStreamReader( response.getEntity( ).getContent( ) ), AutocompleteResult.class );
	}
	
	private PlaceDetailResult parseDetailResponse( HttpResponse response ) throws IOException {
		return this.gson.fromJson( new InputStreamReader( response.getEntity( ).getContent( ) ), PlaceDetailResult.class );
	}
	
	private PlacesResult parseSearchResponse( HttpResponse response ) throws IOException {
		return this.gson.fromJson( new InputStreamReader( response.getEntity( ).getContent( ) ), PlacesResult.class );
	}
	
	public AutocompleteResult autocomplete( String input ) {
		return autocomplete( input, null );
	}
	
	public AutocompleteResult autocomplete( String input, PlacesQueryOptions options ) {
		try {
			URIBuilder url = new URIBuilder( AUTOCOMPLETE_URL );
			url.addParameter( "key", this.apikey );
			url.addParameter( "input", input );
			
			if ( options != null )
				for ( String param : options.params( ).keySet( ) )
					url.addParameter( param, options.param( param ) );
						
			HttpGet get = new HttpGet( url.build( ) );
			return this.parseAutocompleteResponse( this.client.execute( get ) );
			
		} catch( Exception e ) {
			throw new PlacesException( e );
		}
	}
	
	public PlaceDetailResult detail( String placeId ) {
		try {
			URIBuilder url = new URIBuilder( DETAIL_URL );
			url.addParameter( "key", this.apikey );
			url.addParameter( "placeid", placeId );
								
			HttpGet get = new HttpGet( url.build( ) );
			return this.parseDetailResponse( this.client.execute( get ) );
			
		} catch( Exception e ) {
			throw new PlacesException( e );
		}
	}
	
	@Deprecated
	public PlaceDetailResult detail( String reference, boolean sensor ) {
		try {
			URIBuilder url = new URIBuilder( DETAIL_URL );
			url.addParameter( "key", this.apikey );
			url.addParameter( "reference", reference );
			url.addParameter( "sensor", String.valueOf( sensor ) );
								
			HttpGet get = new HttpGet( url.build( ) );
			return this.parseDetailResponse( this.client.execute( get ) );
			
		} catch( Exception e ) {
			throw new PlacesException( e );
		}
	}

	public URL photoUrl( String photoReference, Integer maxHeight, Integer maxWidth ) {
		
		try {
			
			URIBuilder url = new URIBuilder( PHOTO_URL );
			url.addParameter( "key", this.apikey );
			url.addParameter( "photoreference", photoReference );
			url.addParameter( "maxheight", maxHeight != null ? String.valueOf( maxHeight ) : null );
			url.addParameter( "maxwidth", maxWidth != null ? String.valueOf( maxWidth ) : null );
			
			return url.build( ).toURL( );
			
		} catch( MalformedURLException e ) {
			throw new PlacesException( e );
		} catch( URISyntaxException e ) {
			throw new PlacesException( e );
		}
	}
	
	public PlacesResult searchNearby( float lat, float lon, int radius ) {
		return searchNearby( lat, lon, radius, null );
	}
	
	public PlacesResult searchNearby( float lat, float lon, int radius, PlacesQueryOptions options ) {
		try {
			URIBuilder url = new URIBuilder( NEARBY_SEARCH_URL );
			url.addParameter( "key", this.apikey );
			url.addParameter( PlacesQueryOptions.LOCATION, lat + "," + lon );
			url.addParameter( PlacesQueryOptions.RADIUS, String.valueOf( radius ) );
						
			if ( options != null )
				for ( String param : options.params( ).keySet( ) )
					url.addParameter( param, options.param( param ) );
						
			HttpGet get = new HttpGet( url.build( ) );
			return this.parseSearchResponse( this.client.execute( get ) );
			
		} catch( Exception e ) {
			throw new PlacesException( e );
		}
	}
	
	public PlacesResult searchNearby( Location location, int radius ) {
		return this.searchNearby( location, radius, null );
	}
	
	public PlacesResult searchNearby( Location location, int radius, PlacesQueryOptions options ) {
		return this.searchNearby( location.getLat( ), location.getLng( ), radius, options );
	}
	
	public PlacesResult searchNearby( String pageToken ) {
		
		try {
			URIBuilder url = new URIBuilder( NEARBY_SEARCH_URL );
			url.addParameter( "key", this.apikey );
			url.addParameter( "pagetoken", pageToken );

			HttpGet get = new HttpGet( url.build( ) );
			return this.parseSearchResponse( this.client.execute( get ) );
			
		} catch( Exception e ) {
			throw new PlacesException( e );
		}	
	}

    public PlacesResult searchText( String query ) {
        return searchText( query, null );
    }

    public PlacesResult searchText( String query, PlacesQueryOptions options ) {

        try {
        	URIBuilder url = new URIBuilder( TEXT_SEARCH_URL );
        	url.addParameter( "key", this.apikey );
        	url.addParameter( "query", query );

        	if ( options != null )
				for ( String param : options.params( ).keySet( ) )
					url.addParameter( param, options.param( param ) );

            HttpGet get = new HttpGet( url.build( ) );
            return this.parseSearchResponse( this.client.execute( get ) );

        } catch( Exception e ) {
            throw new PlacesException( e );
        }

    }
}
