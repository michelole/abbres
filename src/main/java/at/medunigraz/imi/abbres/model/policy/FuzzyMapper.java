package at.medunigraz.imi.abbres.model.policy;

import java.util.Map;
import java.util.TreeMap;

import at.medunigraz.imi.abbres.model.matcher.Matcher;

public class FuzzyMapper extends AbstractMapper {

	public FuzzyMapper(Matcher matcher) {
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
			String trimmedAbbrev = matcher.getAbbreviation().getTrimmedToken();

			if (containChars(trimmedAbbrev, expansion) && matcher.getAbbreviation().isValidExpansion(expansion)) {
				ret.put(expansion, entry.getValue());
			}
		}

		return ret;
	}

	/**
	 * Checks if the expansion contains the abbreviation chars in the same order
	 * 
	 * @param abbreviation
	 * @param expansion
	 * @return
	 */
	/* private -> testing */ boolean containChars(String abbreviation, String expansion) {
		for (int i = 0, j = 0; i < abbreviation.length(); i++, j++) {
			char a = abbreviation.charAt(i);
			for (; j < expansion.length(); j++) {
				char e = expansion.charAt(j);
				if (a == e) {
					break;
				}
			}
			if (j == expansion.length() && i != abbreviation.length()) {
				return false;
			}
		}
		return true;
	}

	public String prefix() {
		String token = matcher.getAbbreviation().getToken();
		if (token.isEmpty()) {
			return "";
		}
		
		String firstCharAbbrev = token.substring(0, 1);
		return matcher.prefix().concat(firstCharAbbrev);
	}

	@Override
	public int compareTo(Mapper o) {
		// (1) Strict match with bigrams
		// (2) Fuzzy match (abc -> a.*b.*c.*) with bigrams
		// (3) Strict match with unigrams
		// (4) Fuzzy match (abc -> a.*b.*c.*) with unigrams
		int matcherComparison = this.getMatcher().compareTo(o.getMatcher());
		if (o instanceof StrictMapper) {
			// Fuzzy Bigram > Strict Unigram
			if (matcherComparison > 0) {
				return +1;
			}
			// Fuzzy Bigram < Strict Bigram
			// Fuzzy Unigram < Strict Unigram
			// Fuzzy Unigram < Strict Bigram
			return -1;
		}
		if (o instanceof FuzzyMapper) {
			// Fuzzy Bigram == Fuzzy Bigram
			// Fuzzy Unigram == Fuzzy Unigram
			if (matcherComparison == 0) {
				return this.getBestEntry().getValue() > o.getBestEntry().getValue() ? +1 : -1;
			}
			// Fuzzy Unigram < Fuzzy Bigram
			// Fuzzy Bigram > Fuzzy Unigram
			return matcherComparison;
		}
		return 0;
	}

}
