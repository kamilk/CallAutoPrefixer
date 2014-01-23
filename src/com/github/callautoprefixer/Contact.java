package com.github.callautoprefixer;

public class Contact {
	private String _name;
	private PhoneNumber _phoneNumber;
	
	public Contact(String name, PhoneNumber phoneNumber) {
		_name = name;
		_phoneNumber = phoneNumber;
	}
	
	public String getName() {
		return _name;
	}
	public PhoneNumber getPhoneNumber() {
		return _phoneNumber;
	}
}
