package at.medunigraz.imi.abbres.model.mapper;

import java.util.AbstractMap;
import java.util.Map;

import at.medunigraz.imi.abbres.Constants;
import at.medunigraz.imi.abbres.model.Abbreviation;

public abstract class AbstractMapper implements Mapper {
	protected Map<String, Integer> candidates = null;

	protected Map.Entry<String, Integer> bestEntry = null;
	
	protected Abbreviation abbreviation;

	public AbstractMapper(Abbreviation abbreviation) {
		this.abbreviation = abbreviation;
	}

	public Map<String, Integer> getCandidates() {
		if (candidates == null) {
			candidates = map();
		}
		return candidates;
	}

	public Map.Entry<String, Integer> getBestEntry() {
		if (bestEntry == null) {
			bestEntry = calculateBestEntry();
		}
		return bestEntry;
	}

	private Map.Entry<String, Integer> calculateBestEntry() {
		Map.Entry<String, Integer> bestEntry = new AbstractMap.SimpleEntry<>("", 0);
		int bestCount = 0;
		for (Map.Entry<String, Integer> entry : getCandidates().entrySet()) {
			int count = entry.getValue();
			if (count > bestCount) {
				bestEntry = entry;
				bestCount = count;
			}
		}

		return bestEntry;
	}

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
		if (expansion.indexOf(Constants.ABBREVIATION_MARK) >= 0)
			return false;

		return true;
	}
}
