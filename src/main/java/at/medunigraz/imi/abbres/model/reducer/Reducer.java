package at.medunigraz.imi.abbres.model.reducer;

import java.util.NavigableSet;

import at.medunigraz.imi.abbres.model.policy.Mapper;

public interface Reducer {

	/**
	 * Compares a list of ngram mappers and returns the highest ranked entry
	 * according to some algorithm.
	 * 
	 * @param mappers
	 * @return
	 */
	public String reduce(NavigableSet<Mapper> set);
}
