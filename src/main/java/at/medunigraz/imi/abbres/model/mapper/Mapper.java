package at.medunigraz.imi.abbres.model.mapper;

import java.util.Map;

import at.medunigraz.imi.abbres.model.Abbreviation;
import at.medunigraz.imi.abbres.model.matcher.Matcher;
import at.medunigraz.imi.abbres.model.policy.Policy;

public interface Mapper extends Comparable<Mapper> {

	/**
	 * Maps an abbreviation into possible candidates.
	 * 
	 * @param abbreviation
	 * @return
	 */
	public Map<String, Integer> map();

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

	public Matcher getMatcher();

	public Policy getPolicy();

	public String prefix(Abbreviation abbreviation);

	public int getPriority();
}
