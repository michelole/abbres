package at.medunigraz.imi.abbres.model.mapper;

import at.medunigraz.imi.abbres.model.Abbreviation;
import at.medunigraz.imi.abbres.model.matcher.Matcher;
import at.medunigraz.imi.abbres.model.policy.Policy;

public class SingleMapper extends AbstractMapper {

	public SingleMapper(Matcher matcher, Policy policy) {
		super(matcher, policy);
	}

	public String prefix(Abbreviation abbreviation) {
		return matcher.prefix().concat(policy.prefix(abbreviation));
	}

	public int getPriority() {
		return 10;
	}

	@Override
	public String suffix(Abbreviation abbreviation) {
		return matcher.suffix();
	}

}
