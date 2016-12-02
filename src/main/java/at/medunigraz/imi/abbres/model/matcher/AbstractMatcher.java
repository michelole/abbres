package at.medunigraz.imi.abbres.model.matcher;

import at.medunigraz.imi.abbres.TextUtils;
import at.medunigraz.imi.abbres.model.Abbreviation;

public abstract class AbstractMatcher implements Matcher {
	protected Abbreviation abbreviation;

	/**
	 * Gets the left token of a bigram.
	 * 
	 * @param s
	 * @return
	 */
	protected String leftToken(String s) {
		return s.substring(0, s.indexOf(TextUtils.DEFAULT_TOKEN_SEPARATOR));
	}

	/**
	 * Gets the right token of a bigram.
	 * 
	 * @param s
	 * @return
	 */
	protected String rightToken(String s) {
		return s.substring(s.indexOf(TextUtils.DEFAULT_TOKEN_SEPARATOR) + 1, s.length());
	}

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
