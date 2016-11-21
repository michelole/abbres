package at.medunigraz.imi.abbres.model.mapper;

import java.util.Map;
import java.util.TreeMap;

import at.medunigraz.imi.abbres.model.NGramMapFactory;

public class RightBigramMapper extends AbstractMapper {

	@Override
	public Map<String, Integer> map(String abbreviation, String leftContext, String rightContext) {
		String prefix = trimAbbreviation(abbreviation);
		String suffix = concatenate("", rightContext);

		Map<String, Integer> subMap = NGramMapFactory.getBigram().prefixSuffixMap(prefix, suffix);
		Map<String, Integer> ret = new TreeMap<>();

		for (Map.Entry<String, Integer> entry : subMap.entrySet()) {
			String expansion = leftToken(entry.getKey());
			if (isValidExpansion(abbreviation, expansion)) {
				ret.put(expansion, entry.getValue());
			}
		}

		return ret;
	}

}
