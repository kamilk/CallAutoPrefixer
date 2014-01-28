package com.github.callautoprefixer;

import java.util.Collections;
import java.util.List;
import java.util.Vector;

public class NumberPrefixerProvider {
	private final Vector<NamedNumberPrefixer> _namedPrefixers = new Vector<NamedNumberPrefixer>();

	public NumberPrefixerProvider() {
		// TODO do it properly
		addPrefixer("420", "mobiles");
		addPrefixer("421", "landlines");
	}
	
	public List<NamedNumberPrefixer> getNamedPrefixers() {
		return Collections.unmodifiableList(_namedPrefixers);
	}
	
	public void addPrefixer(String prefix, String description) {
		NumberPrefixer prefixer = new NumberPrefixer(prefix);
		String prefixerName = String.format("%s (%s)", prefix, description);
		_namedPrefixers.add(new NamedNumberPrefixer(prefixerName, prefixer));
	}
	
	public int getDefaultPrefixerPositionFor(PhoneEntry entry) {
		return 0; //TODO
	}
}
