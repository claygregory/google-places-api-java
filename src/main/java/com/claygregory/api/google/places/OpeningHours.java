package com.claygregory.api.google.places;

import java.util.List;

public class OpeningHours {

	private boolean openNow;
	
	private List<OpenPeriod> periods;

	public List<OpenPeriod> getPeriods( ) {
		return this.periods;
	}

	public boolean isOpenNow( ) {
		return this.openNow;
	}
	
}
