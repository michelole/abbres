package at.medunigraz.imi.abbres.model.reducer;

import java.util.Iterator;
import java.util.Map;
import java.util.NavigableSet;

import at.medunigraz.imi.abbres.model.policy.Mapper;

public class FuzzyBigramWithFallbackReducer implements Reducer {

	@Override
	public String reduce(NavigableSet<Mapper> set) {
		for (Iterator<Mapper> iter = set.descendingIterator(); iter.hasNext();) {
			Mapper mapper = iter.next();
			Map.Entry<String, Integer> entry = mapper.getBestEntry();
			if (entry.getValue() != 0) {
				return entry.getKey();
			}
		}

		return "";
	}
}
