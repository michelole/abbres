package at.medunigraz.imi.abbres.resolver;

import java.util.Arrays;

import at.medunigraz.imi.abbres.model.Abbreviation;
import at.medunigraz.imi.abbres.model.context.LeftContext;
import at.medunigraz.imi.abbres.model.context.RightContext;
import at.medunigraz.imi.abbres.model.mapper.LeftBigramMapper;
import at.medunigraz.imi.abbres.model.mapper.Mapper;
import at.medunigraz.imi.abbres.model.mapper.RightBigramMapper;
import at.medunigraz.imi.abbres.model.mapper.UnigramMapper;
import at.medunigraz.imi.abbres.model.reducer.BigramWithFallbackReducer;

public class DefaultResolver implements Resolver {

	@Override
	public String resolve(Abbreviation abbreviation) {
		Mapper unigram = new UnigramMapper(abbreviation.getToken());
		Mapper leftBigram = new LeftBigramMapper(abbreviation.getToken(), abbreviation.getLeftContext().getUnigram());
		Mapper rightBigram = new RightBigramMapper(abbreviation.getToken(),
				abbreviation.getRightContext().getUnigram());
		return new BigramWithFallbackReducer().reduce(Arrays.asList(unigram, leftBigram, rightBigram));
	}

	@Deprecated
	public String resolve(String abbreviation, String leftContext, String rightContext) {
		return resolve(new Abbreviation(abbreviation).withLeftContext(new LeftContext(leftContext))
				.withRightContext(new RightContext(rightContext)));
	}

}
