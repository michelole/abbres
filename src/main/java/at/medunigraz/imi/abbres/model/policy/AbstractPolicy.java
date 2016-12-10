package at.medunigraz.imi.abbres.model.policy;

import at.medunigraz.imi.abbres.model.matcher.Matcher;

public abstract class AbstractPolicy implements Policy {
	protected Matcher matcher;

	@Override
	public String suffix() {
		return matcher.suffix();
	}

	public Matcher getMatcher() {
		return matcher;
	}
}
