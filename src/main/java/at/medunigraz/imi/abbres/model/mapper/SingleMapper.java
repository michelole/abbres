package at.medunigraz.imi.abbres.model.mapper;

import at.medunigraz.imi.abbres.model.Abbreviation;
import at.medunigraz.imi.abbres.model.matcher.Matcher;
import at.medunigraz.imi.abbres.model.policy.Policy;

public class SingleMapper extends AbstractMapper {

	public SingleMapper(Matcher matcher, Policy policy) {
		super(matcher, policy);
	}

	@Override
	public String prefix() {
		return prefix(matcher.getAbbreviation());
	}

	public String prefix(Abbreviation abbreviation) {
		return matcher.prefix().concat(policy.prefix(abbreviation));
	}

	public int getPriority() {
		return 10;
	}

}
