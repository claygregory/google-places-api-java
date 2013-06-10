package com.claygregory.api.google.places;

import java.util.List;
import java.util.Set;

public class Prediction {

	private String description;
	
	private String id;
	
	private List<MatchedSubstring> matchedSubstrings;
	
	private String reference;
	
	private List<Term> terms;
	
	private Set<String> types;

	public String getDescription( ) {
		return this.description;
	}

	public String getId( ) {
		return this.id;
	}

	public List<MatchedSubstring> getMatchedSubstrings( ) {
		return this.matchedSubstrings;
	}

	public String getReference( ) {
		return this.reference;
	}

	public List<Term> getTerms( ) {
		return this.terms;
	}

	public Set<String> getTypes( ) {
		return this.types;
	}
	
}
