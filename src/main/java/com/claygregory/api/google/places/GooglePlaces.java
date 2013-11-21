package com.claygregory.api.google.places;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import com.claygregory.common.data.geo.GeoLocation;
import com.claygregory.common.net.URLBuilder;
import com.claygregory.common.util.StringUtil;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class GooglePlaces {
	
	private static final int DEFAULT_RADIUS = 500;
	
	private static final String AUTOCOMPLETE_URL = "https://maps.googleapis.com/maps/api/place/autocomplete/json";
	
	private static final String DETAIL_URL = "https://maps.googleapis.com/maps/api/place/details/json";
	
	private static final String PHOTO_URL = "https://maps.googleapis.com/maps/api/place/photo";
	
	private static final String SEARCH_URL = "https://maps.googleapis.com/maps/api/place/nearbysearch/json";

    private static final String TEXT_SEARCH_URL = "https://maps.googleapis.com/maps/api/place/textsearch/json";

	private String apikey;
	
	private HttpClient client;
	
	private Gson gson;
	
	public GooglePlaces( String apikey ) {
		this( HttpClientBuilder.create().useSystemProperties().build(), apikey );
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
	
	public AutocompleteResult autocomplete( String input, boolean sensor ) {
		return autocomplete( input, null, sensor );
	}
	
	public AutocompleteResult autocomplete( String input, AutocompleteQueryOptions options, boolean sensor ) {
		try {
			URLBuilder builder = URLBuilder.create( AUTOCOMPLETE_URL )
				.queryParam( "key", this.apikey )
				.queryParam( "input", input )
				.queryParam( "sensor", String.valueOf( sensor ) );
			
			if ( options != null )
				for ( String param : options.params( ).keySet( ) )
					builder.queryParam( param, options.param( param ) );
						
			HttpGet get = new HttpGet( builder.buildURL( ).toString( ) );
			return this.parseAutocompleteResponse( this.client.execute( get ) );
			
		} catch( Exception e ) {
			throw new PlacesException( e );
		}
	}
	
	public PlaceDetailResult detail( String reference, boolean sensor ) {
		try {
			URLBuilder builder = URLBuilder.create( DETAIL_URL )
				.queryParam( "key", this.apikey )
				.queryParam( "reference", reference )
				.queryParam( "sensor", String.valueOf( sensor ) );
								
			HttpGet get = new HttpGet( builder.buildURL( ).toString( ) );
			return this.parseDetailResponse( this.client.execute( get ) );
			
		} catch( Exception e ) {
			throw new PlacesException( e );
		}
	}

	public URL photoUrl( String photoReference, Integer maxHeight, Integer maxWidth, boolean sensor ) {
		
		try {
			
			return URLBuilder.create( PHOTO_URL )
				.queryParam( "key", this.apikey )
				.queryParam( "photoreference", photoReference )
				.queryParam( "sensor", String.valueOf( sensor ) )
				.queryParam( "maxheight", maxHeight != null ? String.valueOf( maxHeight ) : null )
				.queryParam( "maxwidth", maxWidth != null ? String.valueOf( maxWidth ) : null )
				.buildURL( );
			
		} catch( MalformedURLException e ) {
			throw new PlacesException( e );
		}
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
			return this.parseSearchResponse( this.client.execute( get ) );
			
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
			return this.parseSearchResponse( this.client.execute( get ) );
			
		} catch( Exception e ) {
			throw new PlacesException( e );
		}	
	}

    public PlacesResult searchText( String query, String types, boolean sensor) {

        try {
            URLBuilder urlbuilder = URLBuilder.create(TEXT_SEARCH_URL)
                    .queryParam( "key", this.apikey )
                    .queryParam("query", query)
                    .queryParam("sensor", String.valueOf(sensor));

            if (StringUtil.empty( types ) ) {
                urlbuilder.queryParam( "types", types);
            }

             URL url = urlbuilder.buildURL( );

            HttpGet get = new HttpGet( url.toString( ) );
            return this.parseSearchResponse( this.client.execute( get ) );

        } catch( Exception e ) {
            throw new PlacesException( e );
        }

    }

    public PlacesResult searchText( String query, boolean sensor) {

        return searchText(query, "", sensor);

    }
}
