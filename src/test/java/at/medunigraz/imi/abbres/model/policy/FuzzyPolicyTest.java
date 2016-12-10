package at.medunigraz.imi.abbres.model.policy;

import junit.framework.TestCase;

public class FuzzyPolicyTest extends TestCase {

	public void testContainsChars() {
		Policy policy = new FuzzyPolicy();

		assertTrue(policy.containCharsSameOrder("Tbl", "Tablette"));
		assertTrue(policy.containCharsSameOrder("Tbl", "Tblzahl"));
		assertTrue(policy.containCharsSameOrder("Tbl", "Taubal"));
		assertTrue(policy.containCharsSameOrder("Tbl", "Tbl"));

		assertTrue(policy.containCharsSameOrder("T", "Table"));
		assertTrue(policy.containCharsSameOrder("T", "T"));

		assertFalse(policy.containCharsSameOrder("Tbl", "Tomato"));
		assertFalse(policy.containCharsSameOrder("Tbl", "Tb"));
	}
}
