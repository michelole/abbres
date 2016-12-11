package at.medunigraz.imi.abbres.model.policy;

import at.medunigraz.imi.abbres.TextUtils;

public class StrictPolicy extends AbstractPolicy {

	@Override
	public boolean isValidExpansion(String abbreviation, String expansion) {
		return isLeftSubstring(abbreviation, expansion);
	}

	private boolean isLeftSubstring(String abbreviation, String expansion) {
		String trimmedAbbrev = TextUtils.trimAbbreviation(abbreviation);
		if (expansion.length() < trimmedAbbrev.length()) {
			return false;
		}

		for (int i = 0; i < trimmedAbbrev.length(); i++) {
			if (trimmedAbbrev.charAt(i) != expansion.charAt(i)) {
				return false;
			}
		}
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
