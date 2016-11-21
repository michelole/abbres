package at.medunigraz.imi.abbres.model.mapper;

public abstract class AbstractMapper implements Mapper {

	private static final String ABBREVIATION_MARKER = ".";
	private static final String TOKEN_SEPARATOR = " ";

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

}
