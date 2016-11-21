package at.medunigraz.imi.abbres.model.mapper;

import java.util.Map;
import java.util.TreeMap;

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
		String prefix = trimAbbreviation(abbreviation);
		String suffix = concatenate("", rightContext);

		Map<String, Integer> subMap = ngram.prefixSuffixMap(prefix, suffix);
		Map<String, Integer> ret = new TreeMap<>();
		
		for (Map.Entry<String, Integer> entry : subMap.entrySet()) {
			ret.put(leftToken(entry.getKey()), entry.getValue());
		}

		return ret;
	}

}
