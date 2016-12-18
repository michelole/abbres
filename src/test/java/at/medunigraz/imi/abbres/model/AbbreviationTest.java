package at.medunigraz.imi.abbres.model;

import junit.framework.TestCase;

public class AbbreviationTest extends TestCase {

	public void testIsValidExpansion() {
		assertTrue((new Abbreviation("max.")).isValidExpansion("maximalen"));
		assertTrue((new Abbreviation("Tbl.")).isValidExpansion("Tablette"));

		assertFalse((new Abbreviation("systol.")).isValidExpansion("systol.Funktion"));
		assertFalse((new Abbreviation("bds.")).isValidExpansion("bds,"));

		// No absolute gain
		assertFalse((new Abbreviation("bds.")).isValidExpansion("bdsl"));

		// Reasonable relative gains, valid expansions
		assertTrue((new Abbreviation("intensiv.")).isValidExpansion("intensives")); // 0.25
		assertTrue((new Abbreviation("ret.")).isValidExpansion("retard")); // 1.00
		assertTrue((new Abbreviation("u.")).isValidExpansion("und")); // 2.00
		assertTrue((new Abbreviation("p.")).isValidExpansion("post"));	// 3.00
		assertTrue((new Abbreviation("rez.")).isValidExpansion("rezidivierende")); // 3.67
		assertTrue((new Abbreviation("ms.")).isValidExpansion("Millisekunden")); // 5.50
		
		//assertFalse((new Abbreviation("ret.")).isValidExpansion("retrosternales")); // 3.67
		//assertTrue((new Abbreviation("Z.")).isValidExpansion("Zustand")); // 6.00

		// Extreme relative gains, not valid
		assertFalse((new Abbreviation("p.")).isValidExpansion("propter")); // 6.00
		assertFalse((new Abbreviation("li.")).isValidExpansion("lipidsenkenden")); // 6.00
		assertFalse((new Abbreviation("re.")).isValidExpansion("rechtsventrikulär")); // 7.50
	}

}
