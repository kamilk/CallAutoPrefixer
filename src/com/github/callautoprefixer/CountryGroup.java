package com.github.callautoprefixer;

import java.util.HashMap;

public class CountryGroup {
	private Country _country;
	private HashMap<NumberKind, NamedNumberPrefixer> _defaults =
			new HashMap<NumberKind, NamedNumberPrefixer>();
	
	public CountryGroup(Country country) {
		_country = country;
	}
	
	public Country getCountry() {
		return _country;
	}
	
	public void setDefault(NumberKind numberType, NamedNumberPrefixer prefixer) {
		_defaults.put(numberType, prefixer);
	}

	public NamedNumberPrefixer getDefaultFor(NumberKind type) {
		return _defaults.get(type);
	}
}
