package at.medunigraz.imi.abbres;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import at.medunigraz.imi.abbres.model.Abbreviation;
import at.medunigraz.imi.abbres.model.mapper.Mapper;
import at.medunigraz.imi.abbres.model.mapper.StrictMapper;
import at.medunigraz.imi.abbres.model.matcher.LeftBigramMatcher;
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

			Mapper strictUnigram = new StrictMapper(new UnigramMatcher(a));
			System.out.println(String.format("Unigram best guess: %s (%d)", strictUnigram.getBestEntry().getKey(),
					strictUnigram.getBestEntry().getValue()));

			Mapper strictLeftBigram = new StrictMapper(new LeftBigramMatcher(a));
			System.out.println(String.format("Left bigram best guess: %s (%d)", strictLeftBigram.getBestEntry().getKey(),
					strictLeftBigram.getBestEntry().getValue()));

			Mapper strictRightBigram = new StrictMapper(new RightBigramMatcher(a));
			System.out.println(String.format("Right bigram best guess: %s (%d)", strictRightBigram.getBestEntry().getKey(),
					strictRightBigram.getBestEntry().getValue()));

		} while (br.readLine() != null);
	}
}
