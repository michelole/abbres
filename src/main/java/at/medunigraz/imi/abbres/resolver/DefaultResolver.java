package at.medunigraz.imi.abbres.resolver;

import java.util.NavigableSet;
import java.util.TreeSet;

import at.medunigraz.imi.abbres.model.Abbreviation;
import at.medunigraz.imi.abbres.model.NGramMapFactory;
import at.medunigraz.imi.abbres.model.matcher.LeftBigramMatcher;
import at.medunigraz.imi.abbres.model.matcher.Matcher;
import at.medunigraz.imi.abbres.model.matcher.RightBigramMatcher;
import at.medunigraz.imi.abbres.model.matcher.UnigramMatcher;
import at.medunigraz.imi.abbres.model.policy.FuzzyMapper;
import at.medunigraz.imi.abbres.model.policy.Mapper;
import at.medunigraz.imi.abbres.model.policy.StrictMapper;
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

		Mapper strictUnigram = new StrictMapper(unigram);
		Mapper strictLeftBigram = new StrictMapper(leftBigram);
		Mapper strictRightBigram = new StrictMapper(rightBigram);

		Mapper fuzzyUnigram = new FuzzyMapper(unigram);
		Mapper fuzzyLeftBigram = new FuzzyMapper(leftBigram);
		Mapper fuzzyRightBigram = new FuzzyMapper(rightBigram);

		NavigableSet<Mapper> set = new TreeSet<>();
		set.add(strictUnigram);
		set.add(strictLeftBigram);
		set.add(strictRightBigram);
		set.add(fuzzyUnigram);
		set.add(fuzzyLeftBigram);
		set.add(fuzzyRightBigram);

		return new FuzzyBigramWithFallbackReducer().reduce(set);
	}
}
