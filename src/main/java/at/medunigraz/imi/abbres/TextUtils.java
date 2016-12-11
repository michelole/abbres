package at.medunigraz.imi.abbres;

public final class TextUtils {
	@Deprecated
	public static final char ABBREVIATION_MARK = '.';

	@Deprecated
	public static final char DEFAULT_TOKEN_SEPARATOR = ' ';

	/**
	 * Concatenates two strings using the default token separator.
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static String concatenate(String a, String b) {
		return String.join(String.valueOf(TextUtils.DEFAULT_TOKEN_SEPARATOR), a, b);
	}

	/**
	 * Checks whether a string is an abbreviation.
	 * 
	 * @param s
	 * @return
	 */
	public static boolean isAbbreviation(String s) {
		return s.endsWith(String.valueOf(ABBREVIATION_MARK));
	}

	/**
	 * Removes the abbreviation mark, if present.
	 * 
	 * @param s
	 * @return
	 */
	public static String trimAbbreviation(String s) {
		if (!s.endsWith(String.valueOf(TextUtils.ABBREVIATION_MARK))) {
			return s;
		}
		return s.substring(0, s.indexOf(TextUtils.ABBREVIATION_MARK));
	}

	/**
	 * Gets the left token of a bigram.
	 * 
	 * @param bigram
	 * @return
	 */
	public static String left(String bigram) {
		return bigram.substring(0, bigram.indexOf(DEFAULT_TOKEN_SEPARATOR));
	}

	/**
	 * Gets the right token of a bigram.
	 * 
	 * @param bigram
	 * @return
	 */
	public static String right(String bigram) {
		return bigram.substring(bigram.lastIndexOf(DEFAULT_TOKEN_SEPARATOR) + 1, bigram.length());
	}
}
