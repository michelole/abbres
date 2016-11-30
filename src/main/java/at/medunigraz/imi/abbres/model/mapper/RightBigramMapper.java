package at.medunigraz.imi.abbres.model.mapper;

import java.util.Map;

import at.medunigraz.imi.abbres.model.Abbreviation;
import at.medunigraz.imi.abbres.model.NGramMapFactory;

public class RightBigramMapper extends StrictMapper {

	public RightBigramMapper(Abbreviation abbreviation) {
		super(abbreviation);
	}

	@Override
	public String prefix() {
		return trimAbbreviation(abbreviation.getToken());
	}

	@Override
	public String suffix() {
		return concatenate("", abbreviation.getRightContext().getUnigram());
	}

	@Override
	public Map<String, Integer> submap(String prefix, String suffix) {
		return NGramMapFactory.getBigram().prefixSuffixMap(prefix, suffix);
	}

	@Override
	public String expansion(String entryKey) {
		return leftToken(entryKey);
	}

}
