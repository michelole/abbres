package at.medunigraz.imi.abbres.model.mapper;

import java.util.Map;
import java.util.TreeMap;

import at.medunigraz.imi.abbres.model.NGramMap;
import junit.framework.TestCase;

public class RightBigramMapperTest extends TestCase {
	public void testMap() {
		Map<String, Integer> map = new TreeMap<>();
		map.put("Pride and", 10);
		map.put("and Prejudice", 1000);
		map.put("Prejudice is", 1);
		map.put("is a", 100);
		map.put("a novel", 100);

		Mapper mapper = new RightBigramMapper(new NGramMap(map));

		Map<String, Integer> ngrams = mapper.map("Pr.", "", "and");
		assertEquals(1, ngrams.size());
		
		assertTrue(ngrams.containsKey("Pride and"));
		assertFalse(ngrams.containsKey("Prejudice is"));
	}
}
