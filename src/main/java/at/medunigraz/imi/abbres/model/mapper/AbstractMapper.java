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

}
