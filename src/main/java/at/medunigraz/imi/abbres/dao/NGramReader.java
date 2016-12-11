package at.medunigraz.imi.abbres.dao;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opencsv.CSVReader;

public class NGramReader {
	private static final Logger LOG = LoggerFactory.getLogger(NGramReader.class);
	
	private static final Pattern TOKEN_SEPARATOR = Pattern.compile(" ");

	private File file;
	
	/**
	 * Indicates "n" in n-gram. Used for validation.
	 */
	private int n;

	public NGramReader(File file, int n) {
		this.file = file;
		this.n = n;
	}

	/**
	 * Reads a tab-separated ngram file into a TreeMap.
	 * 
	 * @return
	 */
	public Map<String, Integer> readAll() {
		LOG.info("Loading file " + file.getAbsolutePath());
		Map<String, Integer> nGramMap = new TreeMap<>();

		try (CSVReader reader = new CSVReader(new FileReader(file), '\t', '\0');) {
			String[] record;
			while ((record = reader.readNext()) != null) {
				if (record.length != 2) {
					LOG.trace(String.format("Ignored unexpected record length (%s) at line %s: %s", record.length,
							reader.getLinesRead(), Arrays.toString(record)));
					continue;
				}

				int count = Integer.parseInt(record[0].trim());
				String ngram = record[1].trim();
				int ngramLength = TOKEN_SEPARATOR.split(ngram).length;
				if (ngramLength != n) {
					LOG.trace(String.format("Ignored unexpected ngram length (%s) at line %s: %s", ngramLength,
							reader.getLinesRead(), Arrays.toString(record)));
					continue;
				}
				nGramMap.put(ngram, count);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return nGramMap;
	}
}
