package at.medunigraz.imi.abbres.model.matcher;

import at.medunigraz.imi.abbres.TextUtils;
import at.medunigraz.imi.abbres.model.Abbreviation;

public abstract class AbstractMatcher implements Matcher {
	protected Abbreviation abbreviation;
	
	/**
	 * Removes abbreviation out of one string.
	 * 
	 * @param abbreviation
	 * @return
	 */
	protected String trimAbbreviation(String abbreviation) {
		if (!abbreviation.endsWith(String.valueOf(TextUtils.ABBREVIATION_MARK))) {
			return abbreviation;
		}
		return abbreviation.substring(0, abbreviation.indexOf(TextUtils.ABBREVIATION_MARK));
	}

	/**
	 * Concatenates two strings using the default token separator.
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	protected String concatenate(String a, String b) {
		return String.join(String.valueOf(TextUtils.DEFAULT_TOKEN_SEPARATOR), a, b);
	}
	
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
		String trimmedAbbreviation = trimAbbreviation(abbreviation.getToken());

		// The expansion must be longer than the abbreviation
		if (trimmedAbbreviation.length() >= expansion.length())
			return false;

		// The expansion cannot be another abbreviation
		if (expansion.indexOf(TextUtils.ABBREVIATION_MARK) >= 0)
			return false;

		return true;
	}
}
