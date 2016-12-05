package at.medunigraz.imi.abbres.model.matcher;

import java.util.Map;

import at.medunigraz.imi.abbres.model.Abbreviation;
import at.medunigraz.imi.abbres.model.NGramMapFactory;

public class UnigramMatcher extends AbstractMatcher {

	public UnigramMatcher(Abbreviation abbreviation) {
		super(abbreviation);
	}

	@Override
	public Map<String, Integer> submap(String prefix, String suffix) {
		return NGramMapFactory.getUnigram().prefixMap(prefix);
	}

	@Override
	public int compareTo(Matcher o) {
		if (o instanceof UnigramMatcher) {
			return 0;
		}
		return -1;
	}

}
