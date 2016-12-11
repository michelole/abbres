package at.medunigraz.imi.abbres.resolver;

import java.util.NavigableSet;
import java.util.TreeSet;

import at.medunigraz.imi.abbres.model.Abbreviation;
import at.medunigraz.imi.abbres.model.NGramMapFactory;
import at.medunigraz.imi.abbres.model.mapper.DoubleMapper;
import at.medunigraz.imi.abbres.model.mapper.Mapper;
import at.medunigraz.imi.abbres.model.mapper.SingleMapper;
import at.medunigraz.imi.abbres.model.matcher.LeftBigramMatcher;
import at.medunigraz.imi.abbres.model.matcher.Matcher;
import at.medunigraz.imi.abbres.model.matcher.RightBigramMatcher;
import at.medunigraz.imi.abbres.model.matcher.UnigramMatcher;
import at.medunigraz.imi.abbres.model.policy.FuzzyPolicy;
import at.medunigraz.imi.abbres.model.policy.Policy;
import at.medunigraz.imi.abbres.model.policy.StrictPolicy;
import at.medunigraz.imi.abbres.model.reducer.FuzzyBigramWithFallbackReducer;

public class DefaultResolver implements Resolver {

	public DefaultResolver() {
		NGramMapFactory.warm();
	}

	@Override
	public String resolve(Abbreviation abbreviation) {
		Matcher unigram = new UnigramMatcher(abbreviation);
		Matcher leftBigram = new LeftBigramMatcher(abbreviation);
		Matcher rightBigram = new RightBigramMatcher(abbreviation);

		Policy strict = new StrictPolicy();
		Mapper singleStrictUnigram = new SingleMapper(unigram, strict);
		Mapper singleStrictLeftBigram = new SingleMapper(leftBigram, strict);
		Mapper singleStrictRightBigram = new SingleMapper(rightBigram, strict);
		Mapper doubleStrictLeftBigram = new DoubleMapper(leftBigram, strict);
		Mapper doubleStrictRightBigram = new DoubleMapper(rightBigram, strict);

		Policy fuzzy = new FuzzyPolicy();
		Mapper singleFuzzyUnigram = new SingleMapper(unigram, fuzzy);
		Mapper singleFuzzyLeftBigram = new SingleMapper(leftBigram, fuzzy);
		Mapper singleFuzzyRightBigram = new SingleMapper(rightBigram, fuzzy);
		Mapper doubleFuzzyLeftBigram = new DoubleMapper(leftBigram, fuzzy);
		Mapper doubleFuzzyRightBigram = new DoubleMapper(rightBigram, fuzzy);

		NavigableSet<Mapper> set = new TreeSet<>();
		set.add(singleStrictUnigram);
		set.add(singleStrictLeftBigram);
		set.add(singleStrictRightBigram);
		set.add(doubleStrictLeftBigram);
		set.add(doubleStrictRightBigram);
		
		set.add(singleFuzzyUnigram);
		set.add(singleFuzzyLeftBigram);
		set.add(singleFuzzyRightBigram);
//		set.add(doubleFuzzyLeftBigram);
//		set.add(doubleFuzzyRightBigram);

		return new FuzzyBigramWithFallbackReducer().reduce(set);
	}
}
