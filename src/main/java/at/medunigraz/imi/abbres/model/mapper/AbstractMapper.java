package at.medunigraz.imi.abbres.model.mapper;

import java.util.AbstractMap;
import java.util.Map;

import at.medunigraz.imi.abbres.Constants;
import at.medunigraz.imi.abbres.model.matcher.Matcher;

public abstract class AbstractMapper implements Mapper {
	protected Map<String, Integer> candidates = null;

	protected Map.Entry<String, Integer> bestEntry = null;
	
	protected Matcher matcher;

	public AbstractMapper(Matcher matcher) {
		this.matcher = matcher;
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
