package com.github.callautoprefixer;

import android.provider.ContactsContract.CommonDataKinds.Phone;

public class PhoneEntryType {
	public static final PhoneEntryType HOME = new PhoneEntryType("Home");
	public static final PhoneEntryType MOBILE = new PhoneEntryType("Mobile");
	public static final PhoneEntryType OTHER = new PhoneEntryType("Other");
	
	private String _name;
	
	public PhoneEntryType(String name) {
		_name = name;
	}
	
	@Override
	public String toString() {
		return _name;
	}
	
	public static PhoneEntryType fromAndroidContactType(int type) {
		switch(type) {
		case Phone.TYPE_HOME:
			return HOME;
		case Phone.TYPE_MOBILE:
			return MOBILE;
		default:
			return OTHER;
		}
	}
}
