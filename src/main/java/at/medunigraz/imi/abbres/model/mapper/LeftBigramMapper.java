package at.medunigraz.imi.abbres.model.mapper;

import java.util.Map;

import at.medunigraz.imi.abbres.model.NGramMap;
import at.medunigraz.imi.abbres.model.NGramMapFactory;

public class LeftBigramMapper extends AbstractMapper {

	protected NGramMap ngram;

	public LeftBigramMapper(NGramMap ngram) {
		this.ngram = ngram;
	}

	public LeftBigramMapper() {
		this.ngram = NGramMapFactory.getBigram();
	}

	@Override
	public Map<String, Integer> map(String abbreviation, String leftContext, String rightContext) {
		return ngram.prefixMap(concatenate(leftContext, trimAbbreviation(abbreviation)));
	}

}
