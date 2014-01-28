package com.github.callautoprefixer;

public class NumberPrefixer {
	private String _prefix;
	
	public NumberPrefixer(String prefix) {
		_prefix = prefix;
	}
	
	public PhoneNumber processPhoneNumber(PhoneNumber number) {
		return number.prependWith(_prefix);
	}

}
