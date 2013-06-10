package com.claygregory.api.google.places;

import java.net.URL;
import java.util.Date;

import com.claygregory.common.data.TimestampedEvent;

public class PlaceEvent implements TimestampedEvent {

	private String eventId;
	
	private long startTime;
	
	private String summary;
	
	private URL url;

	public String getEventId( ) {
		return this.eventId;
	}

	public long getStartTime( ) {
		return this.startTime;
	}

	public String getSummary( ) {
		return this.summary;
	}

	@Override
	public long getTimestamp( ) {
		return this.getStartTime( );
	}

	@Override
	public Date getTimestampAsDate( ) {
		return new Date( this.getTimestamp( ) );
	}

	public URL getUrl( ) {
		return this.url;
	}
}
