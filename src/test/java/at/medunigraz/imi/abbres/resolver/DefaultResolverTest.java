package at.medunigraz.imi.abbres.resolver;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import at.medunigraz.imi.abbres.model.NGramMap;
import at.medunigraz.imi.abbres.model.NGramMapFactory;
import at.medunigraz.imi.abbres.model.mapper.Mapper;
import at.medunigraz.imi.abbres.model.mapper.UnigramMapper;
import at.medunigraz.imi.abbres.model.reducer.BigramWithFallbackReducer;
import junit.framework.TestCase;

public class DefaultResolverTest extends TestCase {
	public void testResolve() {
		Map<String, Integer> unigramMap = new TreeMap<>();
		unigramMap.put("To", 10);
		unigramMap.put("A", 1000);
		unigramMap.put("Kill", 1);
		unigramMap.put("Pride", 1000);
		unigramMap.put("Prejudice", 100);
		unigramMap.put("Pr", 10);
		unigramMap.put("Prid.", 10);
		NGramMapFactory.setUnigramMap(new NGramMap(unigramMap));
		
		Map<String, Integer> bigramMap = new TreeMap<>();
		bigramMap.put("Pride and", 10);
		bigramMap.put("and Prejudice", 1000);
		bigramMap.put("Prejudice is", 1);
		bigramMap.put("is a", 100);
		bigramMap.put("a novel", 100);
		bigramMap.put("and Pr", 10);
		bigramMap.put("and Prej.", 10);
		NGramMapFactory.setBigramMap(new NGramMap(bigramMap));
		
		String actual = new DefaultResolver().resolve("Pr.", "and", "is");
		assertEquals("Prejudice", actual);
	}
}
