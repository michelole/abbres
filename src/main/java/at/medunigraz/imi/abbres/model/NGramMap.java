package at.medunigraz.imi.abbres.model;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import org.apache.commons.collections4.Trie;
import org.apache.commons.collections4.trie.PatriciaTrie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NGramMap implements Serializable {
	private static final Logger LOG = LoggerFactory.getLogger(NGramMap.class);

	private static final long serialVersionUID = -1896123411638697098L;

	private Trie<String, Integer> trie;

	public NGramMap(Map<String, Integer> inputMap) {
		LOG.trace("Loading map into a PatriciaTrie...");
		this.trie = new PatriciaTrie<>(inputMap);
	}

	public int get(String key) {
		return trie.getOrDefault(key, 0);
	}

	/**
	 * Gets a submap matched by prefix.
	 * 
	 * @param prefix
	 * @return
	 */
	public Map<String, Integer> prefixMap(String prefix) {
		return trie.prefixMap(prefix);
	}

	/**
	 * Gets a submap matched by prefix and suffix.
	 * 
	 * @param prefix
	 * @param suffix
	 * @return
	 */
	public Map<String, Integer> prefixSuffixMap(String prefix, String suffix) {
		if (suffix.isEmpty()) {
			return prefixMap(prefix);
		}
		
		Map<String, Integer> submap = new TreeMap<>();

		Set<Entry<String, Integer>> entrySet = trie.prefixMap(prefix).entrySet();

		Iterator<Entry<String, Integer>> iter = entrySet.iterator();
		while (iter.hasNext()) {
			Entry<String, Integer> entry = iter.next();

			String key = entry.getKey();
			Integer value = entry.getValue();

			if (key.endsWith(suffix)) {
				submap.put(key, value);
			}
		}

		return submap;
	}
}
