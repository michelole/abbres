package at.medunigraz.imi.abbres.resolver;

import java.util.NavigableSet;
import java.util.TreeSet;

import at.medunigraz.imi.abbres.model.Abbreviation;
import at.medunigraz.imi.abbres.model.NGramMapFactory;
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

		Mapper strictUnigram = new SingleMapper(unigram, strict);
		Mapper strictLeftBigram = new SingleMapper(leftBigram, strict);
		Mapper strictRightBigram = new SingleMapper(rightBigram, strict);
		
		Policy fuzzy = new FuzzyPolicy();

		Mapper fuzzyUnigram = new SingleMapper(unigram, fuzzy);
		Mapper fuzzyLeftBigram = new SingleMapper(leftBigram, fuzzy);
		Mapper fuzzyRightBigram = new SingleMapper(rightBigram, fuzzy);

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
