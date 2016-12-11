package at.medunigraz.imi.abbres.model.mapper;

import at.medunigraz.imi.abbres.TextUtils;
import at.medunigraz.imi.abbres.model.Abbreviation;
import at.medunigraz.imi.abbres.model.matcher.Matcher;
import at.medunigraz.imi.abbres.model.policy.Policy;

public class SingleMapper extends AbstractMapper {

	public SingleMapper(Matcher matcher, Policy policy) {
		super(matcher, policy);
	}

	public String prefix(Abbreviation abbreviation) {
		String matcherPrefix = matcher.prefix();
		if (!matcherPrefix.isEmpty()) {
			return TextUtils.concatenate(matcherPrefix, policy.prefix(abbreviation));
		}
		
		return policy.prefix(abbreviation);
	}
	
	@Override
	public boolean isValidContext(String context, String contextExpansion) {
		return true;
	}

	public int getPriority() {
		return 10;
	}

	@Override
	public String suffix(Abbreviation abbreviation) {
		return matcher.suffix();
	}

}
