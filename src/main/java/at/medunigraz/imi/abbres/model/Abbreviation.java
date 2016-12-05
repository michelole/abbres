package at.medunigraz.imi.abbres.model;

import java.util.Objects;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

import at.medunigraz.imi.abbres.TextUtils;
import at.medunigraz.imi.abbres.model.context.LeftContext;
import at.medunigraz.imi.abbres.model.context.RightContext;

public class Abbreviation implements Cloneable {
	private static final Pattern ALLOWED_EXPANSION = Pattern.compile("[\\p{javaLowerCase}\\p{javaUpperCase}]+");

	private static final float MIN_GAIN = 0.001f;
	private static final float MAX_GAIN = 7.00f;

	private String token;

	private LeftContext leftContext;

	private RightContext rightContext;

	private String expansion;

	public Abbreviation(String token) {
		this.token = token;
	}

	public Abbreviation withLeftContext(LeftContext leftContext) {
		this.leftContext = leftContext;
		return this;
	}

	public Abbreviation withLeftContext(String leftContext) {
		this.leftContext = new LeftContext(leftContext);
		return this;
	}

	public Abbreviation withRightContext(RightContext rightContext) {
		this.rightContext = rightContext;
		return this;
	}

	public Abbreviation withRightContext(String rightContext) {
		this.rightContext = new RightContext(rightContext);
		return this;
	}

	public Abbreviation withExpansion(String expansion) {
		this.expansion = expansion;
		return this;
	}

	public String getToken() {
		return token;
	}

	public String getTrimmedToken() {
		if (!token.endsWith(String.valueOf(TextUtils.ABBREVIATION_MARK))) {
			return token;
		}
		return token.substring(0, token.indexOf(TextUtils.ABBREVIATION_MARK));
	}

	public LeftContext getLeftContext() {
		return leftContext;
	}

	public RightContext getRightContext() {
		return rightContext;
	}

	public String getExpansion() {
		return expansion;
	}

	/**
	 * Checks if an expansion is a valid expansion of a candidate abbreviation.
	 * 
	 * @param expansion
	 * @return
	 */
	public boolean isValidExpansion(String expansion) {
		// The expansion must be longer than the abbreviation
		if (this.getTrimmedToken().length() >= expansion.length()) {
			return false;
		}

		// The expansion cannot be another abbreviation
		if (expansion.indexOf(TextUtils.ABBREVIATION_MARK) >= 0) {
			return false;
		}

		// The expansion must match a defined regex (characters only)
		if (!ALLOWED_EXPANSION.matcher(expansion).matches()) {
			return false;
		}
		
		// There is no gain on replacing a char with an abbreviation mark...
		if (absoluteGain(expansion) <= 1) {
			return false;
		}
		
		// The expansion must provide a minimum information gain
		if (relativeGain(expansion) < MIN_GAIN) {
			return false;
		}
		
		// ...but the gain should not be enormous
		if (relativeGain(expansion) > MAX_GAIN) {
			return false;
		}

		return true;
	}
	
	private int absoluteGain(String expansion) {
		return expansion.length() - this.getTrimmedToken().length();
	}
	
	private float relativeGain(String expansion) {
		int tokenLength = this.getTrimmedToken().length();
		int expansionLength = expansion.length();
		
		return (expansionLength - tokenLength) / (float) tokenLength;
	}

	/**
	 * Calculates the similarity of two tokens via levenshteinDistance /
	 * longestStringLength
	 * 
	 * @param other
	 * @return
	 */
	public float tokenSimilarity(Abbreviation other) {
		int distance = StringUtils.getLevenshteinDistance(this.expansion, other.expansion);
		int longest = Math.max(this.expansion.length(), other.expansion.length());

		return 1 - distance / (float) longest;
	}

	@Override
	public int hashCode() {
		return Objects.hash(token, expansion);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Abbreviation other = (Abbreviation) obj;
		return Objects.equals(token, other.token) && Objects.equals(expansion, other.expansion);
	}

	@Override
	public Abbreviation clone() {
		try {
			return (Abbreviation) super.clone();
		} catch (CloneNotSupportedException e) {
			throw new AssertionError();
		}
	}
}
