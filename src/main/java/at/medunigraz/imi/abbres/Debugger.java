package at.medunigraz.imi.abbres;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import at.medunigraz.imi.abbres.model.Abbreviation;
import at.medunigraz.imi.abbres.model.mapper.LeftBigramMapper;
import at.medunigraz.imi.abbres.model.mapper.RightBigramMapper;
import at.medunigraz.imi.abbres.model.mapper.UnigramMapper;
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

			UnigramMapper unigram = new UnigramMapper(a);
			System.out.println(String.format("Unigram best guess: %s (%d)", unigram.getBestEntry().getKey(),
					unigram.getBestEntry().getValue()));

			LeftBigramMapper leftBigram = new LeftBigramMapper(a);
			System.out.println(String.format("Left bigram best guess: %s (%d)", leftBigram.getBestEntry().getKey(),
					leftBigram.getBestEntry().getValue()));

			RightBigramMapper rightBigram = new RightBigramMapper(a);
			System.out.println(String.format("Right bigram best guess: %s (%d)", rightBigram.getBestEntry().getKey(),
					rightBigram.getBestEntry().getValue()));

		} while (br.readLine() != null);
	}
}
