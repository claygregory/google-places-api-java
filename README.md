#Google Places API - Java

This is a lightweight Java wrapper of the Google Places API supporting common query actions including search, detail, and autocomplete.

##Example Usage

###Search Nearby
```java
GooglePlaces places = new GooglePlaces( "API_KEY" );
PlacesResult result = places.searchNearby( 40.10744f, -88.22724f, 5000, PlacesQueryOptions.create( ).keyword( "siebel center" ) );
		
System.out.println( result.getStatus( ) );
for ( Place place : result )
	System.out.println( place.getName( ) + " " + place.getGeometry( ).getLocation( ) );
```

###Search Text
```java
GooglePlaces places = new GooglePlaces( "API_KEY" );
PlacesResult result = places.searchText( "Pizza in Champaign, IL" );

System.out.println( result.getStatus( ) );
for ( Place place : result )
	System.out.println( place.getName( ) + ", " + place.getFormattedAddress( ) );
```

###Detail
```java
GooglePlaces places = new GooglePlaces( "API_KEY" );
PlaceDetailResult result = places.detail( place.getPlaceId( ) );
```

###Autocomplete
```java
GooglePlaces places = new GooglePlaces( "API_KEY" );
AutocompleteResult result = places.autocomplete( "Siebel Ce" );
		
for ( Prediction p : result )
	System.out.println( p.getDescription( ) );
```

##Dependencies
 * [Apache HttpClient](http://hc.apache.org/)
 * [GSON](http://code.google.com/p/google-gson/)

##Downloads

Source is hosted on [GitHub](https://github.com/claygregory/google-places-api-java).
