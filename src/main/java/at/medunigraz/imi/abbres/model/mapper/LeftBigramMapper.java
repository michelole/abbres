package at.medunigraz.imi.abbres.model.mapper;

import java.util.Map;
import java.util.TreeMap;

import at.medunigraz.imi.abbres.model.Abbreviation;
import at.medunigraz.imi.abbres.model.NGramMapFactory;

public class LeftBigramMapper extends AbstractMapper {

	public LeftBigramMapper(Abbreviation abbreviation) {
		super(abbreviation);
	}

	@Override
	public Map<String, Integer> map(Abbreviation abbreviation) {
		String prefix = concatenate(abbreviation.getLeftContext().getUnigram(), trimAbbreviation(abbreviation.getToken()));

		Map<String, Integer> subMap = NGramMapFactory.getBigram().prefixMap(prefix);
		Map<String, Integer> ret = new TreeMap<>();

		for (Map.Entry<String, Integer> entry : subMap.entrySet()) {
			String expansion = rightToken(entry.getKey());
			if (isValidExpansion(abbreviation.getToken(), expansion)) {
				ret.put(expansion, entry.getValue());
			}
		}

		return ret;
	}

}
