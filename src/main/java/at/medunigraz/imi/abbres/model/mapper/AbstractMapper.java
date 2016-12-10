package at.medunigraz.imi.abbres.model.mapper;

import java.util.AbstractMap;
import java.util.Map;

import at.medunigraz.imi.abbres.model.matcher.Matcher;

public abstract class AbstractMapper implements Mapper {
	protected Map<String, Integer> candidates = null;

	protected Map.Entry<String, Integer> bestEntry = null;

	@Deprecated
	protected Matcher matcher;
	
	private static final int MIN_COUNT = 1;

	public AbstractMapper(Matcher matcher) {
		this.matcher = matcher;
	}

	public Map<String, Integer> getCandidates() {
		if (candidates == null) {
			candidates = map();
		}
		return candidates;
	}

	public Matcher getMatcher() {
		return matcher;
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
			if (count > bestCount && count >= MIN_COUNT) {
				bestEntry = entry;
				bestCount = count;
			}
		}

		return bestEntry;
	}

}
