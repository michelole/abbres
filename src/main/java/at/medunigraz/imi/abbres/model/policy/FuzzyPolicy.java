package at.medunigraz.imi.abbres.model.policy;

import at.medunigraz.imi.abbres.model.Abbreviation;

public class FuzzyPolicy extends AbstractPolicy {

	@Override
	public boolean containChars(String abbreviation, String expansion) {
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

	@Override
	public String prefix(Abbreviation abbreviation) {
		String token = abbreviation.getToken();
		if (token.isEmpty()) {
			return "";
		}

		return token.substring(0, 1);
	}

	@Override
	public int compareTo(Policy o) {
		// (1) Strict match with bigrams
		// (2) Fuzzy match (abc -> a.*b.*c.*) with bigrams
		// (3) Strict match with unigrams
		// (4) Fuzzy match (abc -> a.*b.*c.*) with unigrams
		int matcherComparison = this.getMatcher().compareTo(o.getMatcher());
		if (o instanceof StrictPolicy) {
			// Fuzzy Bigram > Strict Unigram
			if (matcherComparison > 0) {
				return +1;
			}
			// Fuzzy Bigram < Strict Bigram
			// Fuzzy Unigram < Strict Unigram
			// Fuzzy Unigram < Strict Bigram
			return -1;
		}
		if (o instanceof FuzzyPolicy) {
			// Fuzzy Bigram == Fuzzy Bigram
			// Fuzzy Unigram == Fuzzy Unigram
//			if (matcherComparison == 0) {
//				return this.getBestEntry().getValue() > o.getBestEntry().getValue() ? +1 : -1;
//			}
			// Fuzzy Unigram < Fuzzy Bigram
			// Fuzzy Bigram > Fuzzy Unigram
			return matcherComparison;
		}
		return 0;
	}

}
