package at.medunigraz.imi.abbres.model.mapper;

import at.medunigraz.imi.abbres.model.matcher.Matcher;
import at.medunigraz.imi.abbres.model.policy.FuzzyPolicy;

public class FuzzyMapper extends AbstractMapper {

	public FuzzyMapper(Matcher matcher) {
		super(matcher, new FuzzyPolicy());
	}

	@Deprecated
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

	@Override
	public int getPriority() {
		// TODO Auto-generated method stub
		return 0;
	}

}
