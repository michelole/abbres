package at.medunigraz.imi.abbres.model.mapper;

import java.util.AbstractMap;
import java.util.Map;

import at.medunigraz.imi.abbres.TextUtils;
import at.medunigraz.imi.abbres.model.Abbreviation;
import at.medunigraz.imi.abbres.model.matcher.Matcher;
import at.medunigraz.imi.abbres.model.policy.Policy;

public class DoubleMapper extends AbstractMapper {

	public DoubleMapper(Matcher matcher, Policy policy) {
		super(matcher, policy);
	}
	
	@Override
	protected Map.Entry<String, Integer> calculateBestEntry() {		
		String context = matcher.context(matcher.getAbbreviation().getTokenWithContext());
		if (!TextUtils.isAbbreviation(context)) {
			return new AbstractMap.SimpleEntry<>("", 0);
		}
		return super.getBestEntry();
	}

	@Override
	public String prefix(Abbreviation abbreviation) {
		String matcherPrefix = matcher.prefix();
		if (!matcherPrefix.isEmpty()) {
			return policy.strip(matcherPrefix);
		}
		
		return policy.prefix(abbreviation);
	}

	@Override
	public String suffix(Abbreviation abbreviation) {
		return "";
	}

	@Override
	public int getPriority() {
		return 20;
	}

}
