package at.medunigraz.imi.abbres.model;

import java.util.Map;
import java.util.TreeMap;

import junit.framework.TestCase;

public class NGramListTest extends TestCase {
	public void testPrefixMap() {
		Map<String, Integer> map = new TreeMap<>();
		map.put("To Kill a Mockingbird", 10);
		map.put("Pride and Prejudice", 1000);
		map.put("To Kill A Warlock", 1);
		map.put("To Kill For", 100);
		map.put("To Kill For Warlock", 100);

		NGramList ngram = new NGramList(map);
		Map<String, Integer> prefixMap = ngram.prefixMap("To Kill");
		assertEquals(4, prefixMap.size());
	}

	public void testPrefixSuffixMap() {
		Map<String, Integer> map = new TreeMap<>();
		map.put("To Kill a Mockingbird", 10);
		map.put("Pride and Prejudice", 1000);
		map.put("To Kill A Warlock", 1);
		map.put("To Kill For", 100);
		map.put("To Kill For Warlock", 100);

		NGramList ngram = new NGramList(map);
		Map<String, Integer> prefixMap = ngram.prefixSuffixMap("To Kill", "Warlock");
		assertEquals(2, prefixMap.size());
	}
}
