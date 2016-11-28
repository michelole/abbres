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

	private static final float SIMILARITY_THRESHOLD = 0.85f;

	private int correct = 0, total = 0, partial = 0;

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

	public int getPartialTotal() {
		return correct + partial;
	}

	public int getCorrect() {
		return correct;
	}

	public int getTotal() {
		return total;
	}

	public void evaluate() {
		LOG.debug(String.format("%s\t%s\t%s", "Abbr.", "Gold", "Guess"));
		while (validation.hasNext()) {
			Abbreviation gold = validation.next();
			if (gold == null) {
				continue;
			}
			Abbreviation guess = gold.clone();
			guess.withExpansion("");
			guess.withExpansion(resolver.resolve(guess));
			float similarity = gold.tokenSimilarity(guess);
			if (gold.equals(guess)) {
				correct++;
			} else {
				if (similarity > SIMILARITY_THRESHOLD)
					partial++;
				LOG.debug(String.format("%s\t%s\t%s\t%4f", guess.getToken(), gold.getExpansion(), guess.getExpansion(),
						similarity));
			}
			total++;
		}

	}

	public static void main(String args[]) {
		Evaluator e = new Evaluator();
		e.evaluate();
		LOG.info(String.format("Total: %d", e.getTotal()));
		LOG.info(String.format("Strict matches: %d (%.4f)", e.getCorrect(), e.getAccuracy()));
		LOG.info(String.format("Partial matches: %d (%.4f) (sim > %.4f)", e.getPartialTotal(), e.getPartialAccuracy(),
				SIMILARITY_THRESHOLD));
	}

}
