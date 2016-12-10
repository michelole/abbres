package at.medunigraz.imi.abbres.model.policy;

public class FuzzyPolicy extends AbstractPolicy {

	@Override
	public boolean containChars(String abbreviation, String expansion) {
		for (int i = 0, j = 0; i < abbreviation.length(); i++, j++) {
			char a = abbreviation.charAt(i);
			for (; j < expansion.length(); j++) {
				char e = expansion.charAt(j);
				if (a == e) {
					break;
				}
			}
			if (j == expansion.length() && i != abbreviation.length()) {
				return false;
			}
		}
		return true;
	}

	@Override
	public String prefix() {
		String token = matcher.getAbbreviation().getToken();
		if (token.isEmpty()) {
			return "";
		}

		String firstCharAbbrev = token.substring(0, 1);
		return matcher.prefix().concat(firstCharAbbrev);
	}

	@Override
	public int compareTo(Policy o) {
		// TODO Auto-generated method stub
		return 0;
	}

}
