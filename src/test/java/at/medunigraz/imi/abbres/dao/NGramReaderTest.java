package at.medunigraz.imi.abbres.dao;

import java.io.File;
import java.util.Map;

import junit.framework.TestCase;

public class NGramReaderTest extends TestCase {
	public void testReadFile() {
		NGramReader reader;
		Map<String, Integer> nGramMap;
		String key;
		Integer value;

		reader = new NGramReader(new File("src/test/resources/unigram.tsv"), 1);
		nGramMap = reader.readAll();
		key = "unigram";
		value = 300000;
		assertTrue(nGramMap.containsKey(key));
		assertEquals(value, nGramMap.get(key));

		reader = new NGramReader(new File("src/test/resources/bigram.tsv"), 2);
		nGramMap = reader.readAll();
		key = "test bigram";
		value = 20000;
		assertTrue(nGramMap.containsKey(key));
		assertEquals(value, nGramMap.get(key));
	}
}
