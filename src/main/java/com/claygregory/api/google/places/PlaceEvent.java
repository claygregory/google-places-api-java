package com.claygregory.api.google.places;

import java.net.URL;
import java.util.Date;

public class PlaceEvent {

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

	public Date getStartTimeAsDate( ) {
		return new Date( this.getStartTime( ) );
	}

	public URL getUrl( ) {
		return this.url;
	}
}
