package at.medunigraz.imi.abbres.model.matcher;

import java.util.Map;

public interface Matcher {
	public String prefix();

	default public String suffix() {
		return "";
	}

	public Map<String, Integer> submap(String prefix, String suffix);

	default public String expansion(String entryKey) {
		return entryKey;
	}

}
