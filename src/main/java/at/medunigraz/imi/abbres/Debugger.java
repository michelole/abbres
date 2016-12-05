package at.medunigraz.imi.abbres;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.NavigableSet;
import java.util.TreeSet;

import at.medunigraz.imi.abbres.model.Abbreviation;
import at.medunigraz.imi.abbres.model.mapper.FuzzyMapper;
import at.medunigraz.imi.abbres.model.mapper.Mapper;
import at.medunigraz.imi.abbres.model.mapper.StrictMapper;
import at.medunigraz.imi.abbres.model.matcher.LeftBigramMatcher;
import at.medunigraz.imi.abbres.model.matcher.Matcher;
import at.medunigraz.imi.abbres.model.matcher.RightBigramMatcher;
import at.medunigraz.imi.abbres.model.matcher.UnigramMatcher;
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

			Mapper strictUnigram = new StrictMapper(unigram);
			Mapper strictLeftBigram = new StrictMapper(leftBigram);
			Mapper strictRightBigram = new StrictMapper(rightBigram);

			Mapper fuzzyUnigram = new FuzzyMapper(unigram);
			Mapper fuzzyLeftBigram = new FuzzyMapper(leftBigram);
			Mapper fuzzyRightBigram = new FuzzyMapper(rightBigram);

			NavigableSet<Mapper> set = new TreeSet<>();
			set.add(strictUnigram);
			set.add(strictLeftBigram);
			set.add(strictRightBigram);
			set.add(fuzzyUnigram);
			set.add(fuzzyLeftBigram);
			set.add(fuzzyRightBigram);

			for (Iterator<Mapper> iter = set.descendingIterator(); iter.hasNext();) {
				Mapper mapper = iter.next();

				String mapperClass = mapper.getClass().getSimpleName();
				String matcherClass = mapper.getMatcher().getClass().getSimpleName();
				String gram = mapper.getBestEntry().getKey();
				int frequency = mapper.getBestEntry().getValue();
				System.out.println(
						String.format("%s %s best guess: %s (%d)", mapperClass, matcherClass, gram, frequency));

			}

		} while (br.readLine() != null);
	}
}
