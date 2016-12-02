package at.medunigraz.imi.abbres.model.matcher;

import java.util.Map;

import at.medunigraz.imi.abbres.model.Abbreviation;

public interface Matcher {
	default public String prefix() {
		return "";
	}

	default public String suffix() {
		return "";
	}

	public Map<String, Integer> submap(String prefix, String suffix);

	default public String expansion(String entryKey) {
		return entryKey;
	}

	public boolean isValidExpansion(String expansion);

	public Abbreviation getAbbreviation();

}
