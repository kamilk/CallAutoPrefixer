package com.github.callautoprefixer;

import android.net.Uri;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;

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
	
	public String getCountryCode() throws InvalidPhoneNumberException {
		try {
			// TODO don't hardcode the default country
			Phonenumber.PhoneNumber phoneNumber = PhoneNumberUtil
					.getInstance().parse(_value,  "PL");
			int countryCode = phoneNumber.getCountryCode();
			return String.valueOf(countryCode);
		} catch (NumberParseException e) {
			throw new InvalidPhoneNumberException(e);
		}
	}
}
