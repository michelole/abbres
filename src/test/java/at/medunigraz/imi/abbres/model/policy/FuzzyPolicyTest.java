package at.medunigraz.imi.abbres.model.policy;

import junit.framework.TestCase;

public class FuzzyPolicyTest extends TestCase {

	public void testIsValidExpansion() {
		Policy policy = new FuzzyPolicy();

		assertTrue(policy.isValidExpansion("Tbl", "Tablette"));
		assertTrue(policy.isValidExpansion("Tbl", "Tblzahl"));
		assertTrue(policy.isValidExpansion("Tbl", "Taubal"));
		assertTrue(policy.isValidExpansion("Tbl", "Tbl"));

		assertTrue(policy.isValidExpansion("T", "Table"));
		assertTrue(policy.isValidExpansion("T", "T"));

		assertFalse(policy.isValidExpansion("Tbl", "Tomato"));
		assertFalse(policy.isValidExpansion("Tbl", "Tb"));
	}
}
