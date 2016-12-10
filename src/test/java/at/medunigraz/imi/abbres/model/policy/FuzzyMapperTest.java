package at.medunigraz.imi.abbres.model.policy;

import at.medunigraz.imi.abbres.model.Abbreviation;
import at.medunigraz.imi.abbres.model.matcher.UnigramMatcher;
import at.medunigraz.imi.abbres.model.policy.FuzzyMapper;
import junit.framework.TestCase;

public class FuzzyMapperTest extends TestCase {

	public void testContainsChars() {
		FuzzyMapper mapper = new FuzzyMapper(new UnigramMatcher(new Abbreviation("Tbl.")));
		
		assertTrue(mapper.containChars("Tbl", "Tablette"));
		assertTrue(mapper.containChars("Tbl", "Tblzahl"));
		assertTrue(mapper.containChars("Tbl", "Taubal"));
		assertTrue(mapper.containChars("Tbl", "Tbl"));
		
		assertTrue(mapper.containChars("T", "Table"));
		assertTrue(mapper.containChars("T", "T"));
		
		assertFalse(mapper.containChars("Tbl", "Tomato"));
		assertFalse(mapper.containChars("Tbl", "Tb"));
	}
}
