package at.medunigraz.imi.abbres.model.policy;

import at.medunigraz.imi.abbres.model.Abbreviation;

public interface Policy extends Comparable<Policy> {
	/**
	 * Checks if the expansion contains the abbreviation chars in the same order
	 * 
	 * @param abbreviation
	 * @param expansion
	 * @return
	 */
	public boolean containChars(String abbreviation, String expansion);
	
	public String prefix(Abbreviation abbreviation);
	
	public String suffix(Abbreviation abbreviation);
	
	public int getPriority();
	
	public String strip(String s);
}
