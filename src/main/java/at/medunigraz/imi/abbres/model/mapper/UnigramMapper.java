package at.medunigraz.imi.abbres.model.mapper;

import java.util.Map;
import java.util.TreeMap;

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
		String prefix = trimAbbreviation(abbreviation);
		
		Map<String, Integer> subMap = ngram.prefixMap(prefix);
		Map<String, Integer> ret = new TreeMap<>();

		for (Map.Entry<String, Integer> entry : subMap.entrySet()) {
			String expansion = entry.getKey();
			if (isValidExpansion(abbreviation, expansion)) {
				ret.put(expansion, entry.getValue());
			}
		}

		return ret;
	}

}
