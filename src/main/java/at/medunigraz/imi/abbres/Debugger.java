package at.medunigraz.imi.abbres;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.NavigableSet;
import java.util.TreeSet;

import at.medunigraz.imi.abbres.model.Abbreviation;
import at.medunigraz.imi.abbres.model.mapper.DoubleMapper;
import at.medunigraz.imi.abbres.model.mapper.Mapper;
import at.medunigraz.imi.abbres.model.mapper.SingleMapper;
import at.medunigraz.imi.abbres.model.matcher.LeftBigramMatcher;
import at.medunigraz.imi.abbres.model.matcher.Matcher;
import at.medunigraz.imi.abbres.model.matcher.RightBigramMatcher;
import at.medunigraz.imi.abbres.model.matcher.UnigramMatcher;
import at.medunigraz.imi.abbres.model.policy.FuzzyPolicy;
import at.medunigraz.imi.abbres.model.policy.Policy;
import at.medunigraz.imi.abbres.model.policy.StrictPolicy;
import at.medunigraz.imi.abbres.resolver.DefaultResolver;
import at.medunigraz.imi.abbres.resolver.Resolver;

public class Debugger {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		Resolver r = new DefaultResolver();

		do {
			System.out.print("Solve for: ");
			String abbr = br.readLine();

			System.out.print("With left context: ");
			String left = br.readLine();

			System.out.print("And right context: ");
			String right = br.readLine();

			Abbreviation a = new Abbreviation(abbr).withLeftContext(left).withRightContext(right);
			String guess = r.resolve(a);
			System.out.println("Best guess is: " + guess);

			Matcher unigram = new UnigramMatcher(a);
			Matcher leftBigram = new LeftBigramMatcher(a);
			Matcher rightBigram = new RightBigramMatcher(a);

			Policy strict = new StrictPolicy();
			Mapper singleStrictUnigram = new SingleMapper(unigram, strict);
			Mapper singleStrictLeftBigram = new SingleMapper(leftBigram, strict);
			Mapper singleStrictRightBigram = new SingleMapper(rightBigram, strict);
			Mapper doubleStrictLeftBigram = new DoubleMapper(leftBigram, strict);
			Mapper doubleStrictRightBigram = new DoubleMapper(rightBigram, strict);

			Policy fuzzy = new FuzzyPolicy();
			Mapper singleFuzzyUnigram = new SingleMapper(unigram, fuzzy);
			Mapper singleFuzzyLeftBigram = new SingleMapper(leftBigram, fuzzy);
			Mapper singleFuzzyRightBigram = new SingleMapper(rightBigram, fuzzy);
			Mapper doubleFuzzyLeftBigram = new DoubleMapper(leftBigram, fuzzy);
			Mapper doubleFuzzyRightBigram = new DoubleMapper(rightBigram, fuzzy);

			NavigableSet<Mapper> set = new TreeSet<>();
			set.add(singleStrictUnigram);
			set.add(singleStrictLeftBigram);
			set.add(singleStrictRightBigram);
			set.add(doubleStrictLeftBigram);
			set.add(doubleStrictRightBigram);

			set.add(singleFuzzyUnigram);
			set.add(singleFuzzyLeftBigram);
			set.add(singleFuzzyRightBigram);
			// set.add(doubleFuzzyLeftBigram);
			// set.add(doubleFuzzyRightBigram);

			for (Iterator<Mapper> iter = set.descendingIterator(); iter.hasNext();) {
				Mapper mapper = iter.next();

				String mapperClass = mapper.getClass().getSimpleName();
				String matcherClass = mapper.getMatcher().getClass().getSimpleName();
				String policyClass = mapper.getPolicy().getClass().getSimpleName();
				String gram = mapper.getBestEntry().getKey();
				int frequency = mapper.getBestEntry().getValue();
				System.out.println(String.format("%s %s %s best guess: %s (%d)", mapperClass, policyClass, matcherClass,
						gram, frequency));

			}

		} while (br.readLine() != null);
	}
}
