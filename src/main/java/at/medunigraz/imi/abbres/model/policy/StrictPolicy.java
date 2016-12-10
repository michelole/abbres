package at.medunigraz.imi.abbres.model.policy;

public class StrictPolicy extends AbstractPolicy {

	@Override
	public int compareTo(Policy o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean containChars(String abbreviation, String expansion) {
		return true;
	}

	@Override
	public String prefix() {
		String trimmedAbbrev = matcher.getAbbreviation().getTrimmedToken();
		return matcher.prefix().concat(trimmedAbbrev);
	}

}
