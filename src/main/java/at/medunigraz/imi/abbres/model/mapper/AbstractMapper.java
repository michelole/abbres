package at.medunigraz.imi.abbres.model.mapper;

public abstract class AbstractMapper implements Mapper {

	private static final String ABBREVIATION_MARKER = ".";

	/**
	 * Removes abbreviation out of one string.
	 * 
	 * @param abbreviation
	 * @return
	 */
	protected String trimAbbreviation(String abbreviation) {
		return abbreviation.substring(0, abbreviation.indexOf(ABBREVIATION_MARKER));
	}

}
