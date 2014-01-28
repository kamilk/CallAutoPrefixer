package com.github.callautoprefixer;

import android.provider.ContactsContract.CommonDataKinds.Phone;

public class PhoneEntryType {
	public static final PhoneEntryType HOME = new PhoneEntryType(
			"Home", NumberKind.LANDLINE);
	public static final PhoneEntryType MOBILE = new PhoneEntryType(
			"Mobile", NumberKind.MOBILE);
	public static final PhoneEntryType OTHER = new PhoneEntryType("Other");
	
	private String _name;
	private NumberKind _type;
	
	public PhoneEntryType(String name) {
		this(name, NumberKind.OTHER);
	}
	
	public PhoneEntryType(String name, NumberKind type) {
		_name = name;
		_type = type;
	}
	
	@Override
	public String toString() {
		return _name;
	}
	
	public NumberKind getKind() {
		return _type;
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
