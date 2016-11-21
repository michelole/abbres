package at.medunigraz.imi.abbres.model.mapper;

import java.util.Map;
import java.util.TreeMap;

import at.medunigraz.imi.abbres.model.NGramMap;
import at.medunigraz.imi.abbres.model.NGramMapFactory;
import junit.framework.TestCase;

public class RightBigramMapperTest extends TestCase {
	public void testMap() {
		Map<String, Integer> map = new TreeMap<>();
		map.put("Pride and", 10);
		map.put("and Prejudice", 1000);
		map.put("Prejudice is", 1);
		map.put("is a", 100);
		map.put("a novel", 100);
		map.put("Pr and", 10);
		map.put("Prid. and", 10);

		NGramMapFactory.setBigramMap(new NGramMap(map));
		Mapper mapper = new RightBigramMapper();

		Map<String, Integer> ngrams = mapper.map("Pr.", "", "and");
		assertEquals(1, ngrams.size());
		
		assertTrue(ngrams.containsKey("Pride"));
		assertFalse(ngrams.containsKey("Prejudice"));
	}
}
