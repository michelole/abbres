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
	public String prefix() {
		return concatenate(abbreviation.getLeftContext().getUnigram(), trimAbbreviation(abbreviation.getToken()));
	}

	@Override
	public Map<String, Integer> submap(String prefix, String suffix) {
		return NGramMapFactory.getBigram().prefixMap(prefix);
	}

	@Override
	public String expansion(String entryKey) {
		return rightToken(entryKey);
	}

}
