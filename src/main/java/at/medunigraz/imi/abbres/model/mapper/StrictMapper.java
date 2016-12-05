package at.medunigraz.imi.abbres.model.mapper;

import java.util.Map;
import java.util.TreeMap;

import at.medunigraz.imi.abbres.model.matcher.Matcher;

public class StrictMapper extends AbstractMapper {

	public StrictMapper(Matcher matcher) {
		super(matcher);
	}

	@Override
	public Map<String, Integer> map() {
		String prefix = prefix();
		String suffix = matcher.suffix();

		Map<String, Integer> subMap = matcher.submap(prefix, suffix);
		Map<String, Integer> ret = new TreeMap<>();

		for (Map.Entry<String, Integer> entry : subMap.entrySet()) {
			String expansion = matcher.expansion(entry.getKey());
			if (matcher.getAbbreviation().isValidExpansion(expansion)) {
				ret.put(expansion, entry.getValue());
			}
		}

		return ret;
	}

	public String prefix() {
		String trimmedAbbrev = matcher.getAbbreviation().getTrimmedToken();
		return matcher.prefix().concat(trimmedAbbrev);
	}

	@Override
	public int compareTo(Mapper o) {
		// (1) Strict match with bigrams
		// (2) Fuzzy match (abc -> a.*b.*c.*) with bigrams
		// (3) Strict match with unigrams
		// (4) Fuzzy match (abc -> a.*b.*c.*) with unigrams
		int matcherComparison = this.getMatcher().compareTo(o.getMatcher());
		if (o instanceof FuzzyMapper) {
			// Strict Unigram < Fuzzy Bigram
			if (matcherComparison < 0) {
				return -1;
			}
			// Strict Unigram > Fuzzy Unigram
			// Strict Bigram > Fuzzy Unigram
			// Strict Bigram > Fuzzy Bigram
			return +1;
		}
		if (o instanceof StrictMapper) {
			// Strict Bigram == Strict Bigram
			// Strict Unigram == Strict Unigram
			if (matcherComparison == 0) {
				return this.getBestEntry().getValue() > o.getBestEntry().getValue() ? +1 : -1;
			}
			// Strict Unigram < Strict Bigram
			// Strict Bigram > Strict Unigram
			return matcherComparison;
		}
		return 0;
	}

}
