package com.github.callautoprefixer;

public class PrefixerSearchResult {
	private int _position;
	private NamedNumberPrefixer _prefixer;
	
	public PrefixerSearchResult(int position, NamedNumberPrefixer prefixer) {
		_position = position;
		_prefixer = prefixer;
	}
	
	public int getPosition() {
		return _position;
	}
	
	public void setPosition(int position) {
		_position = position;
	}
	
	public NamedNumberPrefixer getNamedPrefixer() {
		return _prefixer;
	}
	
	public void setPrefixer(NamedNumberPrefixer prefixer) {
		_prefixer = prefixer;
	}
}
