package com.github.callautoprefixer;

import java.util.Collections;
import java.util.List;
import java.util.Vector;

public class NumberPrefixerRepository {
	private final Vector<NamedNumberPrefixer> _namedPrefixers = new Vector<NamedNumberPrefixer>();
	private final Vector<CountryGroup> _countryGroups = new Vector<CountryGroup>();

	public List<NamedNumberPrefixer> getNamedPrefixers() {
		return Collections.unmodifiableList(_namedPrefixers);
	}
	
	public void addPrefixer(NamedNumberPrefixer prefixer) {
		_namedPrefixers.add(prefixer);
	}

	public PrefixerSearchResult findDefaultPrefixerFor(PhoneEntry entry)
			throws InvalidPhoneNumberException {
		String countryCode = entry.getPhoneNumber().getCountryCode();
		CountryGroup group = getGroupByDialingCode(countryCode);
		if (group == null) {
			return null;
		}
		
		NamedNumberPrefixer prefixer = group.getDefaultFor(
				entry.getType().getKind());
		if (prefixer == null) {
			return null;
		}
		
		int position = _namedPrefixers.indexOf(prefixer);
		return new PrefixerSearchResult(position, prefixer);
	}

	public void setPrefixerAsDefault(Country country, NumberKind numberType,
			NamedNumberPrefixer prefixer) {
		CountryGroup group = getOrCreateCountryGroup(country);
		group.setDefault(numberType, prefixer);
	}
	
	private CountryGroup getOrCreateCountryGroup(Country country) {
		for (CountryGroup group : _countryGroups) {
			if (group.getCountry().equals(country)) {
				return group;
			}
		}
		
		CountryGroup group = new CountryGroup(country);
		_countryGroups.add(group);
		return group;
	}
	
	private CountryGroup getGroupByDialingCode(String dialingCode) {
		for (CountryGroup group : _countryGroups) {
			if (group.getCountry().getDialingCode().equals(dialingCode)) {
				return group;
			}
		}
		
		return null;
	}
}
