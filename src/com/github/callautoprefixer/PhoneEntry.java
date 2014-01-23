package com.github.callautoprefixer;

public class PhoneEntry {
	private PhoneNumber _number;
	private PhoneEntryType _type;
	
	public PhoneEntry(PhoneNumber number, PhoneEntryType type) {
		_number = number;
		_type = type;
	}
	
	public PhoneNumber getPhoneNumber() {
		return _number;
	}
	
	public PhoneEntryType _getEntryType() {
		return _type;
	}
	
	@Override
	public String toString() {
		return _type.toString();
	}
}
