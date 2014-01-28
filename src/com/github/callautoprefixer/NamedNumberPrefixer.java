package com.github.callautoprefixer;

public class NamedNumberPrefixer {
	private String _name;
	private NumberPrefixer _prefixer;
	
	public NamedNumberPrefixer(String name, NumberPrefixer prefixer) {
		_name = name;
		_prefixer = prefixer;
	}
	
	public String getName() {
		return _name;
	}
	
	public NumberPrefixer getPrefixer() {
		return _prefixer;
	}
	
	@Override
	public String toString() {
		return _name;
	}
}
