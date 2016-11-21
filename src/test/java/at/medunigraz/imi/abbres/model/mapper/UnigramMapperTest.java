package at.medunigraz.imi.abbres.model.mapper;

import java.util.Map;
import java.util.TreeMap;

import at.medunigraz.imi.abbres.model.NGramMap;
import junit.framework.TestCase;

public class UnigramMapperTest extends TestCase {

	public void testMap() {
		Map<String, Integer> map = new TreeMap<>();
		map.put("To", 10);
		map.put("A", 1000);
		map.put("Kill", 1);
		map.put("Pride", 100);
		map.put("Prejudice", 100);

		UnigramMapper unigramMapper = new UnigramMapper(new NGramMap(map));

		Map<String, Integer> unigrams = unigramMapper.map("Pr.", "", "");
		assertEquals(2, unigrams.size());
	}
}
