package at.medunigraz.imi.abbres.stats;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import at.medunigraz.imi.abbres.dao.ValidationReader;
import at.medunigraz.imi.abbres.model.Abbreviation;
import at.medunigraz.imi.abbres.resolver.DefaultResolver;
import at.medunigraz.imi.abbres.resolver.Resolver;

public class Evaluator {
	private static final Logger LOG = LoggerFactory.getLogger(Evaluator.class);

	private ValidationReader validation;

	private Resolver resolver;

	private static final String DEFAULT_VALIDATION = "src/main/resources/validation.xlsx";

	private static final float SIMILARITY_THRESHOLD = 0.7f;

	private int correct = 0, total = 0, partial = 0, empty = 0;

	public Evaluator() {
		validation = new ValidationReader(new File(DEFAULT_VALIDATION));
		resolver = new DefaultResolver();
	}

	public double getAccuracy() {
		return correct / (double) total;
	}

	public double getPartialAccuracy() {
		return (correct + partial) / (double) total;
	}

	public double getPartialPrecision() {
		return (correct + partial) / (double) (total - empty);
	}

	public double getPartialRecall() {
		return getPartialAccuracy();
	}

	public double getPartialF1Score() {
		return getPartialFScore(1f);
	}

	private double getPartialFScore(float beta) {
		double precision = getPartialPrecision();
		double recall = getPartialRecall();
		return (1 + Math.pow(beta, 2)) * precision * recall / (precision + recall);
	}

	public int getPartialTotal() {
		return correct + partial;
	}

	public int getCorrect() {
		return correct;
	}

	public int getNotEmpty() {
		return total - empty;
	}

	public int getTotal() {
		return total;
	}

	public void evaluate() {
		while (validation.hasNext()) {
			Abbreviation gold = validation.next();
			validation.writeGuess("");
			if (gold == null) {
				continue;
			}

			Abbreviation guess = gold.clone().withExpansion("");

			LOG.trace("Resolving " + guess.getTokenWithContext());

			String expansion = resolver.resolve(guess);
			guess.withExpansion(expansion);
			if (expansion.isEmpty()) {
				empty++;
			}
			if (gold.equals(guess)) {
				correct++;
			} else {
				if (isSimilar(gold, guess)) {
					partial++;
				} else {
					float similarity = gold.tokenSimilarity(guess);
					LOG.trace(String.format("%s and %s are only %.2f%% similar", gold.getExpansion(),
							guess.getExpansion(), similarity * 100));
					validation.writeGuess(guess);
				}
			}
			total++;
		}

		validation.close();
	}

	public static boolean isSimilar(Abbreviation gold, Abbreviation guess) {
		float similarity = gold.tokenSimilarity(guess);
		if (similarity >= SIMILARITY_THRESHOLD) {
			return true;
		}
		return false;
	}

	public static void main(String args[]) {
		Evaluator e = new Evaluator();
		e.evaluate();
		LOG.info(String.format("Total: %d", e.getTotal()));
		LOG.info(String.format("Not empty guesses: %d", e.getNotEmpty()));
		LOG.info(String.format("Strict matches: %d (A = %.4f)", e.getCorrect(), e.getAccuracy()));
		LOG.info(String.format("Partial matches: %d (P = %.4f, R = %.4f, F1 = %.4f) (sim > %.4f)", e.getPartialTotal(),
				e.getPartialPrecision(), e.getPartialRecall(), e.getPartialF1Score(), SIMILARITY_THRESHOLD));
	}

}
