package at.medunigraz.imi.abbres.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import at.medunigraz.imi.abbres.dao.NGramReader;

public class NGramMapFactory {
	private static transient final Logger LOG = LoggerFactory.getLogger(NGramMap.class);
	
	private static NGramMap unigramMap = null;
	private static NGramMap bigramMap = null;

	private static final String DEFAULT_UNIGRAM_FILE = "src/main/resources/unigram.csv";
	private static final String DEFAULT_BIGRAM_FILE = "src/main/resources/bigram.csv";

	private static final String DEFAULT_UNIGRAM_CACHE = "src/main/resources/unigram.ser";
	private static final String DEFAULT_BIGRAM_CACHE = "src/main/resources/bigram.ser";

	public static void warm() {
		getUnigram();
		getBigram();
	}

	private static void deserializeUnigram() {
		LOG.trace("Deserializing unigram map from " + DEFAULT_UNIGRAM_CACHE);
		try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(DEFAULT_UNIGRAM_CACHE))) {
			unigramMap = (NGramMap) in.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void deserializeBigram() {
		LOG.trace("Deserializing bigram map from " + DEFAULT_BIGRAM_CACHE);
		try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(DEFAULT_BIGRAM_CACHE))) {
			bigramMap = (NGramMap) in.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void serializeUnigram() {
		try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(DEFAULT_UNIGRAM_CACHE))) {
			out.writeObject(unigramMap);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void serializeBigram() {
		try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(DEFAULT_BIGRAM_CACHE))) {
			out.writeObject(bigramMap);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static NGramMap getUnigram() {
		if (unigramMap == null) {
			if (!Files.isReadable(Paths.get(DEFAULT_UNIGRAM_CACHE))) {
				NGramReader reader = new NGramReader(new File(DEFAULT_UNIGRAM_FILE));
				unigramMap = new NGramMap(reader.readAll());
				serializeUnigram();
			}
			deserializeUnigram();
		}
		return unigramMap;
	}

	public static NGramMap getBigram() {
		if (bigramMap == null) {
			if (!Files.isReadable(Paths.get(DEFAULT_BIGRAM_CACHE))) {
				NGramReader reader = new NGramReader(new File(DEFAULT_BIGRAM_FILE));
				bigramMap = new NGramMap(reader.readAll());
				serializeBigram();
			}
			deserializeBigram();
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
