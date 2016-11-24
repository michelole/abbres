package at.medunigraz.imi.abbres.model;

import java.util.Objects;

import at.medunigraz.imi.abbres.model.context.LeftContext;
import at.medunigraz.imi.abbres.model.context.RightContext;

public class Abbreviation {
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

	public Abbreviation withRightContext(RightContext rightContext) {
		this.rightContext = rightContext;
		return this;
	}

	public Abbreviation withExpansion(String expansion) {
		this.expansion = expansion;
		return this;
	}

	public String getToken() {
		return token;
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
}
