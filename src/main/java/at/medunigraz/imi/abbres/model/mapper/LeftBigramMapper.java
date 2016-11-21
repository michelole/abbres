package at.medunigraz.imi.abbres.model.mapper;

import java.util.Map;
import java.util.TreeMap;

import at.medunigraz.imi.abbres.model.NGramMapFactory;

public class LeftBigramMapper extends AbstractMapper {

	public LeftBigramMapper(String abbreviation, String leftContext) {
		super(abbreviation, leftContext, "");
	}

	@Override
	public Map<String, Integer> map(String abbreviation, String leftContext, String rightContext) {
		String prefix = concatenate(leftContext, trimAbbreviation(abbreviation));

		Map<String, Integer> subMap = NGramMapFactory.getBigram().prefixMap(prefix);
		Map<String, Integer> ret = new TreeMap<>();

		for (Map.Entry<String, Integer> entry : subMap.entrySet()) {
			String expansion = rightToken(entry.getKey());
			if (isValidExpansion(abbreviation, expansion)) {
				ret.put(expansion, entry.getValue());
			}
		}

		return ret;
	}

}
