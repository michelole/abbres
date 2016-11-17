package at.medunigraz.imi.abbres.dao;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

import com.opencsv.CSVReader;

public class NGramReader {
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
		Map<String, Integer> nGramMap = new TreeMap<>();

		try (CSVReader reader = new CSVReader(new FileReader(file), '\t');) {
			String[] record;
			while ((record = reader.readNext()) != null) {
				if (record.length != 2)
					continue;

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
