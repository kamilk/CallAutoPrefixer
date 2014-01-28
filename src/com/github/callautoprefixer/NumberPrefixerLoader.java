package com.github.callautoprefixer;

public class NumberPrefixerLoader {
	public void loadInto(NumberPrefixerRepository provider) {
		// TODO actually load it from somewhere
		Country poland = new Country("PL", "48");
		NamedNumberPrefixer prefix420 = createPrefixer("420", "mobiles", poland);
		NamedNumberPrefixer prefix421 = createPrefixer("421", "landlines", poland);
		
		provider.addPrefixer(prefix420);
		provider.addPrefixer(prefix421);
		
		provider.setPrefixerAsDefault(poland, NumberKind.MOBILE, prefix420);
		provider.setPrefixerAsDefault(poland, NumberKind.LANDLINE, prefix421);
	}
	
	static NamedNumberPrefixer createPrefixer(String prefix,
			String description, Country country) {
		NumberPrefixer prefixer = new NumberPrefixer(prefix);
		String prefixerName = String.format("%s (%s %s)",
				prefix, country.getSymbol(), description);
		return new NamedNumberPrefixer(prefixerName, prefixer);
	}
}
