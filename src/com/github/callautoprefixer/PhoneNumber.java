package com.github.callautoprefixer;

import android.net.Uri;

public class PhoneNumber {
	private String _value;
	
	public PhoneNumber(String value) {
		_value = value;
	}
	
	@Override
	public String toString() {
		return _value;
	}
	
	public PhoneNumber expandPlusSign() {
		return new PhoneNumber(_value.replace("+", "00"));
	}
	
	public Uri getCallUri() {
		return Uri.parse("tel:" + _value);
	}
	
	public PhoneNumber prependWith(String prefix) {
		PhoneNumber expanded = expandPlusSign();
		return new PhoneNumber(prefix + expanded.toString());
	}
}
