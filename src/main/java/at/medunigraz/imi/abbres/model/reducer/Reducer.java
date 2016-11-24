package at.medunigraz.imi.abbres.model.reducer;

import java.util.List;

import at.medunigraz.imi.abbres.model.mapper.Mapper;

public interface Reducer {

	/**
	 * Compares a list of ngram mappers and returns the highest ranked entry
	 * according to some algorithm.
	 * 
	 * @param mappers
	 * @return
	 */
	public String reduce(List<Mapper> mappers);
}
