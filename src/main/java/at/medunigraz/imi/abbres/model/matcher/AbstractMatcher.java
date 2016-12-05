package at.medunigraz.imi.abbres.model.matcher;

import at.medunigraz.imi.abbres.model.Abbreviation;

public abstract class AbstractMatcher implements Matcher {
	protected Abbreviation abbreviation;

	public AbstractMatcher(Abbreviation abbreviation) {
		this.abbreviation = abbreviation;
	}

	public Abbreviation getAbbreviation() {
		return abbreviation;
	}
}
