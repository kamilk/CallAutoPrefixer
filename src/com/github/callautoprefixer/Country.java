package com.github.callautoprefixer;

public class Country {
	private String _symbol;
	private String _dialingCode;
	
	public Country(String symbol, String code) {
		_symbol = symbol;
		_dialingCode = code;
	}
	
	public String getSymbol() {
		return _symbol;
	}

	public String getDialingCode() {
		return _dialingCode;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		
		if (o instanceof Country) {
			Country other = (Country) o;
			return other._dialingCode.equals(_dialingCode);
		}
		
		return super.equals(o);
	}
}
