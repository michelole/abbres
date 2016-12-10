package at.medunigraz.imi.abbres.model.policy;

import at.medunigraz.imi.abbres.model.Abbreviation;

public class StrictPolicy extends AbstractPolicy {

	@Override
	public int compareTo(Policy o) {
		// (1) Strict match with bigrams
		// (2) Fuzzy match (abc -> a.*b.*c.*) with bigrams
		// (3) Strict match with unigrams
		// (4) Fuzzy match (abc -> a.*b.*c.*) with unigrams
		int matcherComparison = this.getMatcher().compareTo(o.getMatcher());
		if (o instanceof FuzzyPolicy) {
			// Strict Unigram < Fuzzy Bigram
			if (matcherComparison < 0) {
				return -1;
			}
			// Strict Unigram > Fuzzy Unigram
			// Strict Bigram > Fuzzy Unigram
			// Strict Bigram > Fuzzy Bigram
			return +1;
		}
		if (o instanceof StrictPolicy) {
			// Strict Bigram == Strict Bigram
			// Strict Unigram == Strict Unigram
//			if (matcherComparison == 0) {
//				return this.getBestEntry().getValue() > o.getBestEntry().getValue() ? +1 : -1;
//			}
			// Strict Unigram < Strict Bigram
			// Strict Bigram > Strict Unigram
			return matcherComparison;
		}
		return 0;
	}

	@Override
	public boolean containChars(String abbreviation, String expansion) {
		return true;
	}

	@Override
	public String prefix(Abbreviation abbreviation) {
		return abbreviation.getTrimmedToken();
	}

}
