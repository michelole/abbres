package at.medunigraz.imi.abbres.model.context;

import at.medunigraz.imi.abbres.TextUtils;

public abstract class Context {

	protected static final String TOKEN_SEPARATOR = " ";

	protected String text;

	public Context(String text) {
		this.text = TextUtils.standardize(text);
	}

	public String getUnigram() {
		return getNGram(1);
	}

	public String getBigram() {
		return getNGram(2);
	}
	
	protected abstract String getNGram(int n);

}
