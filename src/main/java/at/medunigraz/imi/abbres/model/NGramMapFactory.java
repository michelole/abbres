package at.medunigraz.imi.abbres.model;

import java.io.File;

import at.medunigraz.imi.abbres.dao.NGramReader;

public class NGramMapFactory {
	private static NGramMap unigramMap = null;
	private static NGramMap bigramMap = null;

	private static final String DEFAULT_UNIGRAM_FILE = "src/main/resources/unigram.csv";
	private static final String DEFAULT_BIGRAM_FILE = "src/main/resources/bigram.csv";
	
	public static void warm() {
		getUnigram();
		getBigram();
	}

	public static NGramMap getUnigram() {
		if (unigramMap == null) {
			NGramReader reader = new NGramReader(new File(DEFAULT_UNIGRAM_FILE));
			unigramMap = new NGramMap(reader.readAll());
		}
		return unigramMap;
	}

	public static NGramMap getBigram() {
		if (bigramMap == null) {
			NGramReader reader = new NGramReader(new File(DEFAULT_BIGRAM_FILE));
			bigramMap = new NGramMap(reader.readAll());
		}
		return bigramMap;
	}

	public static void setUnigramMap(NGramMap unigramMap) {
		NGramMapFactory.unigramMap = unigramMap;
	}

	public static void setBigramMap(NGramMap bigramMap) {
		NGramMapFactory.bigramMap = bigramMap;
	}
}
