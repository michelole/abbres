package at.medunigraz.imi.abbres.model.mapper;

import java.util.Map;
import java.util.TreeMap;

import at.medunigraz.imi.abbres.model.Abbreviation;

public abstract class StrictMapper extends AbstractMapper {

	public StrictMapper(Abbreviation abbreviation) {
		super(abbreviation);
	}
	
	@Override
	public Map<String, Integer> map() {
		String prefix = prefix();
		String suffix = suffix();

		Map<String, Integer> subMap = submap(prefix, suffix);
		Map<String, Integer> ret = new TreeMap<>();

		for (Map.Entry<String, Integer> entry : subMap.entrySet()) {
			String expansion = expansion(entry.getKey());
			if (isValidExpansion(abbreviation.getToken(), expansion)) {
				ret.put(expansion, entry.getValue());
			}
		}

		return ret;
	}

}
