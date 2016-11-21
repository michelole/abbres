package at.medunigraz.imi.abbres.model.mapper;

import java.util.Map;
import java.util.TreeMap;

import at.medunigraz.imi.abbres.model.NGramMap;
import at.medunigraz.imi.abbres.model.NGramMapFactory;
import junit.framework.TestCase;

public class UnigramMapperTest extends TestCase {

	public void testMap() {
		Map<String, Integer> map = new TreeMap<>();
		map.put("To", 10);
		map.put("A", 1000);
		map.put("Kill", 1);
		map.put("Pride", 100);
		map.put("Prejudice", 100);
		map.put("Pr", 10);
		map.put("Prid.", 10);

		NGramMapFactory.setUnigramMap(new NGramMap(map));
		Mapper mapper = new UnigramMapper();

		Map<String, Integer> ngrams = mapper.map("Pr.", "", "");
		assertEquals(2, ngrams.size());
	}
}
