package at.medunigraz.imi.abbres.model;

import junit.framework.TestCase;

public class AbbreviationTest extends TestCase {

	public void testIsValidExpansion() {
		assertTrue((new Abbreviation("max.")).isValidExpansion("maximalen"));
		assertTrue((new Abbreviation("Tbl.")).isValidExpansion("Tablette"));
		
		assertFalse((new Abbreviation("systol.")).isValidExpansion("systol.Funktion"));
		//assertFalse((new Abbreviation("bds.")).isValidExpansion("bds,"));
	}

}
