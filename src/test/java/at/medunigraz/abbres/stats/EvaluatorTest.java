package at.medunigraz.abbres.stats;

import at.medunigraz.imi.abbres.model.Abbreviation;
import at.medunigraz.imi.abbres.stats.Evaluator;
import junit.framework.TestCase;

public class EvaluatorTest extends TestCase {
	public void testIsSimilar() {
		Abbreviation gold = new Abbreviation("");
		Abbreviation guess = new Abbreviation("");

		// Similar
		assertTrue(Evaluator.isSimilar(gold.withExpansion("elektive"), guess.withExpansion("elektiv"))); // 87.50%
		assertTrue(Evaluator.isSimilar(gold.withExpansion("rechte"), guess.withExpansion("rechten"))); // 85.71%
		assertTrue(Evaluator.isSimilar(gold.withExpansion("Regelmäßige"), guess.withExpansion("Regelmässige"))); // 83.33%
		assertTrue(Evaluator.isSimilar(gold.withExpansion("Klinischer"), guess.withExpansion("Klinisch"))); // 80.00%

		assertTrue(Evaluator.isSimilar(gold.withExpansion("Patientin"), guess.withExpansion("Patient"))); // 77.78%
		assertTrue(Evaluator.isSimilar(gold.withExpansion("maximalen"), guess.withExpansion("maximal"))); // 77.78%
		assertTrue(Evaluator.isSimilar(gold.withExpansion("Ambulanz"), guess.withExpansion("Ambulante"))); // 77.78%
		assertTrue(Evaluator.isSimilar(gold.withExpansion("rechts"), guess.withExpansion("rechten"))); // 71.43%
		assertTrue(Evaluator.isSimilar(gold.withExpansion("intensives"), guess.withExpansion("intensiviertes"))); // 71.43%

		// Maybe similar?
		assertFalse(Evaluator.isSimilar(gold.withExpansion("komplizierter"), guess.withExpansion("kompletter"))); // 69.23%
		assertFalse(Evaluator.isSimilar(gold.withExpansion("links"), guess.withExpansion("linker"))); // 66.67%
		assertFalse(Evaluator.isSimilar(gold.withExpansion("täglich"), guess.withExpansion("tglicher"))); // 62.50%

		assertFalse(Evaluator.isSimilar(gold.withExpansion("Transplantations"), guess.withExpansion("Transplantchirurgie"))); // 57.89%
		assertFalse(Evaluator.isSimilar(gold.withExpansion("Batterie"), guess.withExpansion("Batteriestatus"))); // 57.14%
		
		// Definitely not similar
		assertFalse(Evaluator.isSimilar(gold.withExpansion("retard"), guess.withExpansion("retrosternales"))); // 28.57%
		assertFalse(Evaluator.isSimilar(gold.withExpansion("Zustand"), guess.withExpansion("Zeit"))); // 25.00%
		assertFalse(Evaluator.isSimilar(gold.withExpansion("Beispiel"), guess.withExpansion("Bypass"))); // 25.00%
		assertFalse(Evaluator.isSimilar(gold.withExpansion("Ramus"), guess.withExpansion("RCA"))); // 20.00%
		
		assertFalse(Evaluator.isSimilar(gold.withExpansion("Millisekunden"), guess.withExpansion("msec"))); // 15.38%
		assertFalse(Evaluator.isSimilar(gold.withExpansion("Sekunden"), guess.withExpansion("sechs"))); // 12.50%
	}
}
