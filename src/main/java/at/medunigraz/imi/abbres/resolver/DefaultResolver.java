package at.medunigraz.imi.abbres.resolver;

import java.util.Arrays;

import at.medunigraz.imi.abbres.model.Abbreviation;
import at.medunigraz.imi.abbres.model.NGramMapFactory;
import at.medunigraz.imi.abbres.model.mapper.LeftBigramMapper;
import at.medunigraz.imi.abbres.model.mapper.Mapper;
import at.medunigraz.imi.abbres.model.mapper.RightBigramMapper;
import at.medunigraz.imi.abbres.model.mapper.UnigramMapper;
import at.medunigraz.imi.abbres.model.reducer.BigramWithFallbackReducer;

public class DefaultResolver implements Resolver {

	public DefaultResolver() {
		NGramMapFactory.warm();
	}

	@Override
	public String resolve(Abbreviation abbreviation) {
		Mapper unigram = new UnigramMapper(abbreviation);
		Mapper leftBigram = new LeftBigramMapper(abbreviation);
		Mapper rightBigram = new RightBigramMapper(abbreviation);
		return new BigramWithFallbackReducer().reduce(Arrays.asList(unigram, leftBigram, rightBigram));
	}
}
