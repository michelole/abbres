package at.medunigraz.imi.abbres.model.matcher;

import java.util.Map;

import at.medunigraz.imi.abbres.TextUtils;
import at.medunigraz.imi.abbres.model.Abbreviation;
import at.medunigraz.imi.abbres.model.NGramMapFactory;

public class RightBigramMatcher extends AbstractMatcher {

	public RightBigramMatcher(Abbreviation abbreviation) {
		super(abbreviation);
	}

	@Override
	public String suffix() {
		return TextUtils.concatenate("", abbreviation.getRightContext().getUnigram());
	}

	@Override
	public Map<String, Integer> submap(String prefix, String suffix) {
		return NGramMapFactory.getBigram().prefixSuffixMap(prefix, suffix);
	}

	@Override
	public String expansion(String entryKey) {
		return TextUtils.left(entryKey);
	}

	@Override
	public String context(String entryKey) {
		return TextUtils.right(entryKey);
	}

	@Override
	public int compareTo(Matcher o) {
		if (o instanceof UnigramMatcher) {
			return +1;
		}
		return 0;
	}

}
