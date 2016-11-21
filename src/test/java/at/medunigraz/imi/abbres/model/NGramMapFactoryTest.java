package at.medunigraz.imi.abbres.model;

import junit.framework.TestCase;

public class NGramMapFactoryTest extends TestCase {
	public void testGetUnigram() {
		NGramMap a = NGramMapFactory.getUnigram();
		NGramMap b = NGramMapFactory.getUnigram();
		assertSame(b, a);
	}
}
