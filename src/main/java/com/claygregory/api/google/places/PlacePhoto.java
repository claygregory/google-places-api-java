package com.claygregory.api.google.places;

import java.util.List;

public class PlacePhoto {

	private int height;
	
	private List<String> htmlAttributions;
	
	private String photoReference;
	
	private int width;

	public int getHeight( ) {
		return this.height;
	}

	public List<String> getHtmlAttributions( ) {
		return this.htmlAttributions;
	}

	public String getPhotoReference( ) {
		return this.photoReference;
	}

	public int getWidth( ) {
		return this.width;
	}
}
