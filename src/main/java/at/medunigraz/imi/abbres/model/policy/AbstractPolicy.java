package at.medunigraz.imi.abbres.model.policy;

import at.medunigraz.imi.abbres.model.Abbreviation;

public abstract class AbstractPolicy implements Policy {

	@Override
	public String suffix(Abbreviation abbreviation) {
		return "";
	}

	@Override
	public int compareTo(Policy o) {
		return this.getPriority() - o.getPriority();
	}
}
