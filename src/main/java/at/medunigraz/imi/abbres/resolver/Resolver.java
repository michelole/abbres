package at.medunigraz.imi.abbres.resolver;

public interface Resolver {
	/**
	 * Resolves a given abbreviation into its expansion.
	 * 
	 * @param abbreviation
	 * @param leftContext
	 * @param rightContext
	 * @return
	 */
	public String resolve(String abbreviation, String leftContext, String rightContext);
}
