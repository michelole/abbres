package at.medunigraz.imi.abbres.model.mapper;

import java.util.Map;

import at.medunigraz.imi.abbres.model.Abbreviation;

public interface Mapper {

	/**
	 * Maps an abbreviation into possible candidates.
	 * 
	 * @param abbreviation
	 * @return
	 */
	public Map<String, Integer> map(Abbreviation abbreviation);

	/**
	 * Returns a list of candidates expansions and their frequency.
	 * 
	 * @return
	 */
	public Map<String, Integer> getCandidates();

	/**
	 * Returns the top entry in the mapper.
	 * 
	 * @return
	 */
	public Map.Entry<String, Integer> getBestEntry();
}
