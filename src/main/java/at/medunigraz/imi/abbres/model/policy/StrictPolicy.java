package at.medunigraz.imi.abbres.model.policy;

import at.medunigraz.imi.abbres.model.Abbreviation;

public class StrictPolicy extends AbstractPolicy {

	@Override
	public boolean containChars(String abbreviation, String expansion) {
		return true;
	}

	@Override
	public String prefix(Abbreviation abbreviation) {
		return abbreviation.getTrimmedToken();
	}

	@Override
	public int getPriority() {
		return 20;
	}

}
