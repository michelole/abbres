package at.medunigraz.imi.abbres.model.mapper;

import java.util.Map;
import java.util.TreeMap;

import at.medunigraz.imi.abbres.model.Abbreviation;
import at.medunigraz.imi.abbres.model.NGramMapFactory;

public class UnigramMapper extends AbstractMapper {

	public UnigramMapper(Abbreviation abbreviation) {
		super(abbreviation);
	}

	@Override
	public Map<String, Integer> map() {
		String prefix = trimAbbreviation(abbreviation.getToken());

		Map<String, Integer> subMap = NGramMapFactory.getUnigram().prefixMap(prefix);
		Map<String, Integer> ret = new TreeMap<>();

		for (Map.Entry<String, Integer> entry : subMap.entrySet()) {
			String expansion = entry.getKey();
			if (isValidExpansion(abbreviation.getToken(), expansion)) {
				ret.put(expansion, entry.getValue());
			}
		}

		return ret;
	}

}
