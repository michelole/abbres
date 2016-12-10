package at.medunigraz.imi.abbres.model.policy;

import at.medunigraz.imi.abbres.model.Abbreviation;
import at.medunigraz.imi.abbres.model.matcher.Matcher;

public abstract class AbstractPolicy implements Policy {
	protected Matcher matcher;

	@Override
	public String suffix(Abbreviation abbreviation) {
		return "";
	}

	public Matcher getMatcher() {
		return matcher;
	}

	@Override
	public int compareTo(Policy o) {
		return this.getPriority() - o.getPriority();
	}
}
