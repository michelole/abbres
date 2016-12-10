package at.medunigraz.imi.abbres.model.matcher;

import java.util.Map;

import at.medunigraz.imi.abbres.model.Abbreviation;

public interface Matcher extends Comparable<Matcher> {
	default public String prefix() {
		return "";
	}

	default public String suffix() {
		return "";
	}

	public Map<String, Integer> submap(String prefix, String suffix);

	public String expansion(String entryKey);
	
	public String context(String entryKey);

	public Abbreviation getAbbreviation();

}
