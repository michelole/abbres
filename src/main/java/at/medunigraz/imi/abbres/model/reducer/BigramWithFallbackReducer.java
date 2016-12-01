package at.medunigraz.imi.abbres.model.reducer;

import java.util.List;
import java.util.Map;

import at.medunigraz.imi.abbres.model.mapper.LeftBigramMapper;
import at.medunigraz.imi.abbres.model.mapper.Mapper;
import at.medunigraz.imi.abbres.model.matcher.RightBigramMatcher;

public class BigramWithFallbackReducer implements Reducer {

	@Override
	public String reduce(List<Mapper> mappers) {
		String bestUnigram = "", bestBigram = "";
		int countBestUnigram = 0, countBestBigram = 0;

		for (Mapper mapper : mappers) {
			Map.Entry<String, Integer> entry = mapper.getBestEntry();
			String currentNGram = entry.getKey();
			int countCurrentNGram = entry.getValue();
			
			if (mapper instanceof RightBigramMatcher || mapper instanceof LeftBigramMapper) {
				if (countCurrentNGram > countBestBigram) {
					bestBigram = currentNGram;
					countBestBigram = countCurrentNGram;
				}
			} else {
				if (countCurrentNGram > countBestUnigram) {
					bestUnigram = currentNGram;
					countBestUnigram = countCurrentNGram;
				}
			}
		}

		if (countBestBigram != 0)
			return bestBigram;

		return bestUnigram;
	}
}
