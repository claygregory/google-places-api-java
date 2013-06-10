package com.claygregory.api.google.places;

import java.net.URL;
import java.util.Date;
import java.util.List;

import com.claygregory.common.data.TimestampedEvent;

public class Review implements TimestampedEvent {

	private List<AspectRating> aspects;
	
	private String authorName;
	
	private URL authorUrl;
	
	private String text;
	
	private long time;

	public List<AspectRating> getAspects( ) {
		return this.aspects;
	}

	public String getAuthorName( ) {
		return this.authorName;
	}

	public URL getAuthorUrl( ) {
		return this.authorUrl;
	}

	public String getText( ) {
		return this.text;
	}

	public long getTime( ) {
		return this.time;
	}

	@Override
	public long getTimestamp( ) {
		return this.getTime( );
	}

	@Override
	public Date getTimestampAsDate( ) {
		return new Date( this.getTime( ) );
	}
}
