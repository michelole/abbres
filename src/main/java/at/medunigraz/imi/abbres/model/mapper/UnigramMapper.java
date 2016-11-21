package at.medunigraz.imi.abbres.model.mapper;

import java.util.Map;

import at.medunigraz.imi.abbres.model.NGramMap;
import at.medunigraz.imi.abbres.model.NGramMapFactory;

public class UnigramMapper extends AbstractMapper {

	protected NGramMap ngram;

	public UnigramMapper(NGramMap ngram) {
		this.ngram = ngram;
	}

	public UnigramMapper() {
		this.ngram = NGramMapFactory.getUnigram();
	}

	@Override
	public Map<String, Integer> map(String abbreviation, String leftContext, String rightContext) {
		return ngram.prefixMap(trimAbbreviation(abbreviation));
	}

}
