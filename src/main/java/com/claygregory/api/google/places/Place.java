package com.claygregory.api.google.places;

import java.util.Collections;
import java.util.Set;

public class Place {
	
	public static class Geometry {
		
		private Location location;

		public Location getLocation( ) {
			return this.location;
		}

		@Override
		public String toString( ) {
			return this.getLocation( ).toString( );
		}
		
	}
	
	public static class Location {
		
		private float lat;

		private float lng;

		public float getLat( ) {
			return this.lat;
		}

		public float getLng( ) {
			return this.lng;
		}
		
		@Override
		public String toString( ) {
			return this.getLat( ) + ", " + this.getLng( );
		}
		
	}
	
	private String formattedAddress;
	
	private Geometry geometry;
	
	private String icon;
	
	private String id;
	
	private String name;
	
	private String placeId;
	
	private Float rating;
	
	private String reference;

	private Set<String> types = Collections.emptySet( );

	private String url;

	private String vicinity;

	public String getFormattedAddress( ) {
		return this.formattedAddress;
	}

	public Geometry getGeometry( ) {
		return this.geometry;
	}

	public String getIcon( ) {
		return this.icon;
	}

	@Deprecated
	public String getId( ) {
		return this.id;
	}

	public String getName( ) {
		return this.name;
	}

	public String getPlaceId( ) {
		return this.placeId;
	}

	public Float getRating( ) {
		return this.rating;
	}

	@Deprecated
	public String getReference( ) {
		return this.reference;
	}

	public Set<String> getTypes( ) {
		return this.types;
	}

	public String getUrl( ) {
		return this.url;
	}

	public String getVicinity( ) {
		return this.vicinity;
	}
	
}
