package at.medunigraz.imi.abbres.model.policy;

import junit.framework.TestCase;

public class FuzzyPolicyTest extends TestCase {

	public void testContainsChars() {
		Policy policy = new FuzzyPolicy();

		assertTrue(policy.containChars("Tbl", "Tablette"));
		assertTrue(policy.containChars("Tbl", "Tblzahl"));
		assertTrue(policy.containChars("Tbl", "Taubal"));
		assertTrue(policy.containChars("Tbl", "Tbl"));

		assertTrue(policy.containChars("T", "Table"));
		assertTrue(policy.containChars("T", "T"));

		assertFalse(policy.containChars("Tbl", "Tomato"));
		assertFalse(policy.containChars("Tbl", "Tb"));
	}
}
