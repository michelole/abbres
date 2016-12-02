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
}
