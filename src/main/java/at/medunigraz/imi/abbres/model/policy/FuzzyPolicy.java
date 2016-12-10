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
	public int getPriority() {
		return 10;
	}

	@Override
	public String strip(String s) {
		return s.substring(0, 1);
	}

}
