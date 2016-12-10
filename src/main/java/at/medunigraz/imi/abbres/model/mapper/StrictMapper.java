package at.medunigraz.imi.abbres.model.mapper;

import at.medunigraz.imi.abbres.model.matcher.Matcher;
import at.medunigraz.imi.abbres.model.policy.StrictPolicy;

public class StrictMapper extends AbstractMapper {

	public StrictMapper(Matcher matcher) {
		super(matcher, new StrictPolicy());
	}

	@Override
	public int getPriority() {
		// TODO Auto-generated method stub
		return 0;
	}

}
