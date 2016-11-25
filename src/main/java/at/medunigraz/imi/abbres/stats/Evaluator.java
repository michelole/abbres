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

	private int correct = 0;
	private int total = 0;

	public Evaluator() {
		validation = new ValidationReader(new File(DEFAULT_VALIDATION));
		resolver = new DefaultResolver();
	}

	public double getAccuracy() {
		return correct / (double) total;
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
			if (gold == null)
				continue;
			Abbreviation guess = gold.clone();
			guess.withExpansion("");
			guess.withExpansion(resolver.resolve(guess));
			if (gold.equals(guess)) {
				correct++;
			} else {
				LOG.debug(String.format("%s\t%s\t%s", guess.getToken(), gold.getExpansion(), guess.getExpansion()));
			}
			total++;
		}

	}

	public static void main(String args[]) {
		Evaluator e = new Evaluator();
		e.evaluate();
		String message = String.format("%d corrects out of %d.%nAccuracy: %.4f", e.getCorrect(), e.getTotal(),
				e.getAccuracy());
		System.out.println(message);
	}

}
