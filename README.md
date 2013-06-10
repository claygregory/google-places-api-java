#Google Places API - Java

This is just a lightweight wrapper of the Google Places API for Java. Currently only query actions (search, detail, autocomplete) are supported.

##Example Usage

###Search
```
GooglePlaces places = new GooglePlaces( "API_KEY" );
PlacesResult result = places.search( 40.10744f, -88.22724f, 5000, PlacesQueryOptions.create( ).keyword( "siebel center" ), false );
		
System.out.println( result.getStatus( ) );
for ( Place place : result )
	System.out.println( place.getName( ) + " " + place.getGeometry( ).getLocation( ) );
```

###Detail
```
GooglePlaces places = new GooglePlaces( "API_KEY" );
PlaceDetailResult result = places.detail( "CpQBigAAANDSVGDvi95...",  false );
		
if ( result.isOkay( ) )
	System.out.println( result.getResult( ).getFormattedAddress( ) );
```

###Autocomplete
```
GooglePlaces places = new GooglePlaces( "API_KEY" );
AutocompleteResult result = places.autocomplete( "Siebel Ce",  false );
		
for ( Prediction p : result )
	System.out.println( p.getDescription( ) );
```

##Dependencies
 * [Apache HttpClient](http://hc.apache.org/)
 * [GSON](http://code.google.com/p/google-gson/)
 * [cg-jcommons](https://github.com/claygregory/cg-jcommons)

##Binary Download
 * [Latest JAR](http://www.claygregory.com/projects/google-places-api-java/releases/0.2-SNAPSHOT/google-places-api-java-0.2-SNAPSHOT.jar)
 * [Latest JAR with dependencies](http://www.claygregory.com/projects/google-places-api-java/releases/0.2-SNAPSHOT/google-places-api-java-0.2-SNAPSHOT-jar-with-dependencies.jar)
 
