package at.medunigraz.imi.abbres.resolver;

import java.util.Arrays;

import at.medunigraz.imi.abbres.model.Abbreviation;
import at.medunigraz.imi.abbres.model.NGramMapFactory;
import at.medunigraz.imi.abbres.model.mapper.Mapper;
import at.medunigraz.imi.abbres.model.mapper.StrictMapper;
import at.medunigraz.imi.abbres.model.matcher.LeftBigramMatcher;
import at.medunigraz.imi.abbres.model.matcher.RightBigramMatcher;
import at.medunigraz.imi.abbres.model.matcher.UnigramMatcher;
import at.medunigraz.imi.abbres.model.reducer.BigramWithFallbackReducer;

public class DefaultResolver implements Resolver {

	public DefaultResolver() {
		NGramMapFactory.warm();
	}

	@Override
	public String resolve(Abbreviation abbreviation) {
		Mapper unigram = new StrictMapper(new UnigramMatcher(abbreviation));
		Mapper leftBigram = new StrictMapper(new LeftBigramMatcher(abbreviation));
		Mapper rightBigram = new StrictMapper(new RightBigramMatcher(abbreviation));
		return new BigramWithFallbackReducer().reduce(Arrays.asList(unigram, leftBigram, rightBigram));
	}
}
