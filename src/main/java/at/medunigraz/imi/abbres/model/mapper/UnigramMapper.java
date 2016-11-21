package at.medunigraz.imi.abbres.model.mapper;

import java.io.File;
import java.util.Map;

import at.medunigraz.imi.abbres.dao.NGramReader;
import at.medunigraz.imi.abbres.model.NGramMap;

public class UnigramMapper extends AbstractMapper {

	private static final String DEFAULT_UNIGRAM_FILE = "unigram.csv";

	protected NGramMap ngram;

	public UnigramMapper(NGramMap ngram) {
		this.ngram = ngram;
	}

	public UnigramMapper() {
		NGramReader reader = new NGramReader(new File(DEFAULT_UNIGRAM_FILE));
		this.ngram = new NGramMap(reader.readAll());
	}

	@Override
	public Map<String, Integer> map(String abbreviation, String leftContext, String rightContext) {
		return ngram.prefixMap(trimAbbreviation(abbreviation));
	}

}
