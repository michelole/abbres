package at.medunigraz.imi.abbres.model.matcher;

import at.medunigraz.imi.abbres.Constants;
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
		if (!abbreviation.endsWith(String.valueOf(Constants.ABBREVIATION_MARK))) {
			return abbreviation;
		}
		return abbreviation.substring(0, abbreviation.indexOf(Constants.ABBREVIATION_MARK));
	}

	/**
	 * Concatenates two strings using the default token separator.
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	protected String concatenate(String a, String b) {
		return String.join(String.valueOf(Constants.DEFAULT_TOKEN_SEPARATOR), a, b);
	}
	
	/**
	 * Gets the left token of a bigram.
	 * 
	 * @param s
	 * @return
	 */
	protected String leftToken(String s) {
		return s.substring(0, s.indexOf(Constants.DEFAULT_TOKEN_SEPARATOR));
	}
	
	/**
	 * Gets the right token of a bigram.
	 * 
	 * @param s
	 * @return
	 */
	protected String rightToken(String s) {
		return s.substring(s.indexOf(Constants.DEFAULT_TOKEN_SEPARATOR) + 1, s.length());
	}
	
	public AbstractMatcher(Abbreviation abbreviation) {
		this.abbreviation = abbreviation;
	}
}
