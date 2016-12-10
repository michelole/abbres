package at.medunigraz.imi.abbres.model.mapper;

import at.medunigraz.imi.abbres.model.matcher.Matcher;
import at.medunigraz.imi.abbres.model.policy.FuzzyPolicy;

public class FuzzyMapper extends AbstractMapper {

	public FuzzyMapper(Matcher matcher) {
		super(matcher, new FuzzyPolicy());
	}

	@Override
	public int getPriority() {
		// TODO Auto-generated method stub
		return 0;
	}

}
