package at.medunigraz.imi.abbres.resolver;

import java.util.Arrays;

import at.medunigraz.imi.abbres.model.mapper.LeftBigramMapper;
import at.medunigraz.imi.abbres.model.mapper.Mapper;
import at.medunigraz.imi.abbres.model.mapper.RightBigramMapper;
import at.medunigraz.imi.abbres.model.mapper.UnigramMapper;
import at.medunigraz.imi.abbres.model.reducer.BigramWithFallbackReducer;

public class DefaultResolver implements Resolver {

	@Override
	public String resolve(String abbreviation, String leftContext, String rightContext) {
		Mapper unigram = new UnigramMapper(abbreviation);
		Mapper leftBigram = new LeftBigramMapper(abbreviation, leftContext);
		Mapper rightBigram = new RightBigramMapper(abbreviation, rightContext);
		return new BigramWithFallbackReducer().reduce(Arrays.asList(unigram, leftBigram, rightBigram));
	}

}
