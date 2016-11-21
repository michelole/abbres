package at.medunigraz.imi.abbres.model.mapper;

import java.util.Map;

import at.medunigraz.imi.abbres.model.NGramMap;
import at.medunigraz.imi.abbres.model.NGramMapFactory;

public class RightBigramMapper extends AbstractMapper {

	protected NGramMap ngram;

	public RightBigramMapper(NGramMap ngram) {
		this.ngram = ngram;
	}

	public RightBigramMapper() {
		this.ngram = NGramMapFactory.getBigram();
	}

	@Override
	public Map<String, Integer> map(String abbreviation, String leftContext, String rightContext) {
		return ngram.prefixSuffixMap(trimAbbreviation(abbreviation), concatenate("", rightContext));
	}

}
