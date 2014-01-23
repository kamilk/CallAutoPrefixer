package com.github.callautoprefixer;

import java.util.List;
import java.util.Vector;

public class Contact {
	private String _name;
	private Vector<PhoneEntry> _phoneEntries = new Vector<PhoneEntry>();
	
	public Contact(String name, List<PhoneEntry> phoneEntries) {
		_name = name;
		_phoneEntries = new Vector<PhoneEntry>(phoneEntries);
	}
	
	public String getName() {
		return _name;
	}
	
	public void addPhoneEntry(PhoneEntry entry) {
		_phoneEntries.add(entry);
	}
	
	public List<PhoneEntry> getPhoneEntries() {
		return _phoneEntries;
	}
}
