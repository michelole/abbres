package at.medunigraz.imi.abbres.model.mapper;

import java.util.Map;
import java.util.TreeMap;

import at.medunigraz.imi.abbres.model.Abbreviation;
import at.medunigraz.imi.abbres.model.NGramMap;
import at.medunigraz.imi.abbres.model.NGramMapFactory;
import at.medunigraz.imi.abbres.model.matcher.RightBigramMatcher;
import at.medunigraz.imi.abbres.model.policy.StrictPolicy;
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
		
		Abbreviation a = new Abbreviation("Pr.").withRightContext("and");
		Mapper mapper = new SingleMapper(new RightBigramMatcher(a), new StrictPolicy());

		Map<String, Integer> ngrams = mapper.getCandidates();
		assertEquals(1, ngrams.size());
		
		assertTrue(ngrams.containsKey("Pride"));
		assertFalse(ngrams.containsKey("Prejudice"));
	}
}
