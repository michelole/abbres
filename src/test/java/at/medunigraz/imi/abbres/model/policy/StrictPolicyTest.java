package at.medunigraz.imi.abbres.model.policy;

import junit.framework.TestCase;

public class StrictPolicyTest extends TestCase {
	public void testIsValidExpansion() {
		Policy policy = new StrictPolicy();

		assertFalse(policy.isValidExpansion("Tbl", "Tablette"));
		assertTrue(policy.isValidExpansion("Tbl", "Tblzahl"));
		assertFalse(policy.isValidExpansion("Tbl", "Taubal"));
		assertTrue(policy.isValidExpansion("Tbl", "Tbl"));

		assertTrue(policy.isValidExpansion("T", "Table"));
		assertTrue(policy.isValidExpansion("T", "T"));

		assertFalse(policy.isValidExpansion("Tbl", "Tomato"));
		assertFalse(policy.isValidExpansion("Tbl", "Tb"));
		
		assertFalse(policy.isValidExpansion("Tbl.", "Tablette"));
		assertTrue(policy.isValidExpansion("Tbl.", "Tblzahl"));
	}

}
