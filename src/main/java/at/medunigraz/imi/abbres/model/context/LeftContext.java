package at.medunigraz.imi.abbres.model.context;

public class LeftContext extends Context {
	public LeftContext(String text) {
		super(text);
	}

	@Override
	protected String getNGram(int n) {
		if (n < 1) {
			return "";
		}

		String[] tokenizedText = text.split(TOKEN_SEPARATOR);

		StringBuilder sb = new StringBuilder();
		for (int i = Integer.max(0, tokenizedText.length - n); i < tokenizedText.length; i++) {
			sb.append(tokenizedText[i]);
			sb.append(TOKEN_SEPARATOR);
		}
		return sb.toString().trim();
	}
}
