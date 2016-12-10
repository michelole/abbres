package at.medunigraz.imi.abbres.model.policy;

import at.medunigraz.imi.abbres.TextUtils;

public class StrictPolicy extends AbstractPolicy {

	@Override
	public boolean containCharsSameOrder(String abbreviation, String expansion) {
		return true;
	}

	@Override
	public int getPriority() {
		return 20;
	}

	@Override
	public String strip(String s) {
		return TextUtils.trimAbbreviation(s);
	}

}
