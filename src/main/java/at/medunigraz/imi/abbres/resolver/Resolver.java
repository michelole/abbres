package at.medunigraz.imi.abbres.resolver;

import at.medunigraz.imi.abbres.model.Abbreviation;

public interface Resolver {
	/**
	 * Resolves a given abbreviation into its expansion.
	 * 
	 * @param abbreviation
	 * @return
	 */
	public String resolve(Abbreviation abbreviation);
}
