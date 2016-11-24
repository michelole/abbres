package at.medunigraz.imi.abbres.model.mapper;

import java.util.Map;

public interface Mapper {

	/**
	 * Maps an abbreviation into possible candidates.
	 * 
	 * @param abbreviation
	 * @param leftContext
	 * @param rightContext
	 * @return
	 */
	public Map<String, Integer> map(String abbreviation, String leftContext, String rightContext);

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
