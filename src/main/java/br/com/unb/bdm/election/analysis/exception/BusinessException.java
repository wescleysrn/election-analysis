package br.com.unb.bdm.election.analysis.exception;

import lombok.Getter;
import lombok.Setter;

public class BusinessException extends Exception {

	private static final long serialVersionUID = -2389167573579041519L;

	@Getter
	@Setter
	private String errorDeveloperMessage;

	@Getter
	@Setter
	private String errorMessage;

	public BusinessException() {
		super();
	}

	public BusinessException(final String message) {
		super(message);
	}

	public BusinessException(final String errorMessage, final String errorDeveloperMessage) {
		super();
		this.errorMessage = errorMessage;
		this.errorDeveloperMessage = errorDeveloperMessage;
	}

	public BusinessException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public BusinessException(final Throwable cause) {
		super(cause);
	}

}
