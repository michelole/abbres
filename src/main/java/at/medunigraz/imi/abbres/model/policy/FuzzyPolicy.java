package at.medunigraz.imi.abbres.model.policy;

import at.medunigraz.imi.abbres.TextUtils;

public class FuzzyPolicy extends AbstractPolicy {
	
	@Override
	public boolean isValidExpansion(String abbreviation, String expansion) {
		return containCharsSameOrder(abbreviation, expansion);
	}

	/**
	 * Checks if the expansion contains the abbreviation chars in the same order
	 * 
	 * @param abbreviation
	 * @param expansion
	 * @return
	 */
	private boolean containCharsSameOrder(String abbreviation, String expansion) {
		String trimmedAbbrev = TextUtils.trimAbbreviation(abbreviation);
		for (int i = 0, j = 0; i < trimmedAbbrev.length(); i++, j++) {
			char a = trimmedAbbrev.charAt(i);
			for (; j < expansion.length(); j++) {
				char e = expansion.charAt(j);
				if (a == e) {
					break;
				}
			}
			if (j == expansion.length() && i != trimmedAbbrev.length()) {
				return false;
			}
		}
		return true;
	}

	@Override
	public int getPriority() {
		return 10;
	}

	@Override
	public String strip(String s) {
		return s.substring(0, 1);
	}

}
