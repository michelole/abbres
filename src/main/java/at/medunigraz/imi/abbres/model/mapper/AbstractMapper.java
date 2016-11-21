package at.medunigraz.imi.abbres.model.mapper;

import java.util.Map;

public abstract class AbstractMapper implements Mapper {

	private static final String ABBREVIATION_MARKER = ".";
	private static final String TOKEN_SEPARATOR = " ";

	protected Map<String, Integer> candidates = null;

	public AbstractMapper(String abbreviation, String leftContext, String rightContext) {
		candidates = map(abbreviation, leftContext, rightContext);
	}

	public Map<String, Integer> getCandidates() {
		return candidates;
	}

	/**
	 * Removes abbreviation out of one string.
	 * 
	 * @param abbreviation
	 * @return
	 */
	protected String trimAbbreviation(String abbreviation) {
		return abbreviation.substring(0, abbreviation.indexOf(ABBREVIATION_MARKER));
	}

	/**
	 * Concatenates two strings using the default token separator.
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	protected String concatenate(String a, String b) {
		return String.join(TOKEN_SEPARATOR, a, b);
	}

	/**
	 * Gets the left token of a bigram.
	 * 
	 * @param s
	 * @return
	 */
	protected String leftToken(String s) {
		return s.substring(0, s.indexOf(TOKEN_SEPARATOR));
	}

	/**
	 * Gets the right token of a bigram.
	 * 
	 * @param s
	 * @return
	 */
	protected String rightToken(String s) {
		return s.substring(s.indexOf(TOKEN_SEPARATOR) + 1, s.length());
	}

	/**
	 * Checks if an expansion is a valid expansion of a candidate abbreviation.
	 * 
	 * @param abbreviation
	 * @param expansion
	 * @return
	 */
	protected boolean isValidExpansion(String abbreviation, String expansion) {
		String trimmedAbbreviation = trimAbbreviation(abbreviation);

		// The expansion must be longer than the abbreviation
		if (trimmedAbbreviation.length() >= expansion.length())
			return false;

		// The expansion cannot be another abbreviation
		if (expansion.endsWith(ABBREVIATION_MARKER))
			return false;

		return true;
	}
}
