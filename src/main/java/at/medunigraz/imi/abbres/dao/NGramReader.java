package at.medunigraz.imi.abbres.dao;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opencsv.CSVReader;

public class NGramReader {
	private static final Logger LOG = LoggerFactory.getLogger(NGramReader.class);

	private File file;

	public NGramReader(File file) {
		this.file = file;
	}

	/**
	 * Reads a tab-separated ngram file into a TreeMap.
	 * 
	 * @return
	 */
	public Map<String, Integer> readAll() {
		LOG.info("Loading file " + file.getAbsolutePath());
		Map<String, Integer> nGramMap = new TreeMap<>();

		try (CSVReader reader = new CSVReader(new FileReader(file), '\t');) {
			String[] record;
			while ((record = reader.readNext()) != null) {
				if (record.length != 2) {
					LOG.debug(String.format("Unexpected record length (%s) at line %s.", record.length,
							reader.getLinesRead()));
					continue;
				}

				int count = Integer.parseInt(record[0].trim());
				String ngram = record[1].trim();
				nGramMap.put(ngram, count);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return nGramMap;
	}
}
