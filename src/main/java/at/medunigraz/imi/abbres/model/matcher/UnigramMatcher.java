package at.medunigraz.imi.abbres.model.matcher;

import java.util.Map;

import at.medunigraz.imi.abbres.model.Abbreviation;
import at.medunigraz.imi.abbres.model.NGramMapFactory;

public class UnigramMatcher extends AbstractMatcher {

	public UnigramMatcher(Abbreviation abbreviation) {
		super(abbreviation);
	}

	@Override
	public String prefix() {
		return trimAbbreviation(abbreviation.getToken());
	}

	@Override
	public Map<String, Integer> submap(String prefix, String suffix) {
		return NGramMapFactory.getUnigram().prefixMap(prefix);
	}

}
