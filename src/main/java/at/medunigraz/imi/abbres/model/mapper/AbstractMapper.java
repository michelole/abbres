package at.medunigraz.imi.abbres.model.mapper;

public abstract class AbstractMapper implements Mapper {

	/**
	 * Removes abbreviation out of one string.
	 * 
	 * @param abbreviation
	 * @return
	 */
	protected String trimAbbreviation(String abbreviation) {
		return abbreviation.substring(0, abbreviation.indexOf("."));
	}

}
