package com.claygregory.api.google.places;

import java.text.DateFormatSymbols;

public class OpenTime {

	private int day;
	
	private String time;

	public int getDay( ) {
		return this.day;
	}
	
	public String getLocalizedDay( ) {
		DateFormatSymbols symbols = new DateFormatSymbols( );
		return symbols.getWeekdays( )[ this.getDay( ) + 1 ];
	}
	
	public String getShortLocalizedDay( ) {
		DateFormatSymbols symbols = new DateFormatSymbols( );
		return symbols.getShortWeekdays( )[ this.getDay( ) + 1 ];
	}

	public String getTime( ) {
		return this.time;
	}
	
	public String toString( ) {
		return this.getLocalizedDay( ) + " at " + this.getTime( );
	}
}
