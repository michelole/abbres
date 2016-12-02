package at.medunigraz.imi.abbres.model.matcher;

import java.util.Map;

import at.medunigraz.imi.abbres.TextUtils;
import at.medunigraz.imi.abbres.model.Abbreviation;
import at.medunigraz.imi.abbres.model.NGramMapFactory;

public class LeftBigramMatcher extends AbstractMatcher {

	public LeftBigramMatcher(Abbreviation abbreviation) {
		super(abbreviation);
	}

	@Override
	public String prefix() {
		return TextUtils.concatenate(abbreviation.getLeftContext().getUnigram(), trimAbbreviation(abbreviation.getToken()));
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
