package at.medunigraz.imi.abbres.model.mapper;

import java.util.Map;
import java.util.TreeMap;

import at.medunigraz.imi.abbres.model.Abbreviation;
import at.medunigraz.imi.abbres.model.NGramMapFactory;

public class RightBigramMapper extends AbstractMapper {

	public RightBigramMapper(Abbreviation abbreviation) {
		super(abbreviation);
	}

	@Override
	public Map<String, Integer> map() {
		String prefix = trimAbbreviation(abbreviation.getToken());
		String suffix = concatenate("", abbreviation.getRightContext().getUnigram());

		Map<String, Integer> subMap = NGramMapFactory.getBigram().prefixSuffixMap(prefix, suffix);
		Map<String, Integer> ret = new TreeMap<>();

		for (Map.Entry<String, Integer> entry : subMap.entrySet()) {
			String expansion = leftToken(entry.getKey());
			if (isValidExpansion(abbreviation.getToken(), expansion)) {
				ret.put(expansion, entry.getValue());
			}
		}

		return ret;
	}

}
