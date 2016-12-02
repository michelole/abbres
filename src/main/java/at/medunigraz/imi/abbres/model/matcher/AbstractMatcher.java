package at.medunigraz.imi.abbres.model.matcher;

import at.medunigraz.imi.abbres.TextUtils;
import at.medunigraz.imi.abbres.model.Abbreviation;

public abstract class AbstractMatcher implements Matcher {
	protected Abbreviation abbreviation;

	public AbstractMatcher(Abbreviation abbreviation) {
		this.abbreviation = abbreviation;
	}

	/**
	 * Checks if an expansion is a valid expansion of a candidate abbreviation.
	 * 
	 * @param expansion
	 * @return
	 */
	public boolean isValidExpansion(String expansion) {
		// The expansion must be longer than the abbreviation
		if (abbreviation.getTrimmedToken().length() >= expansion.length())
			return false;

		// The expansion cannot be another abbreviation
		if (expansion.indexOf(TextUtils.ABBREVIATION_MARK) >= 0)
			return false;

		return true;
	}

	public Abbreviation getAbbreviation() {
		return abbreviation;
	}
}
