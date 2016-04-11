package com.alex.gitbrancher.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.CollectionUtils;

public class ValidatorResult {
	private List<ValidatorMessage> warnings;
	private List<ValidatorMessage> errors;

	public ValidatorResult() {
		this.warnings = new ArrayList<ValidatorMessage>();
		this.errors = new ArrayList<ValidatorMessage>();
	}

	public ValidatorResult(List<ValidatorMessage> errors) {
		this();
		addErrors(errors);
	}

	public Boolean getIsSuccess() {
		return CollectionUtils.isEmpty(errors);
	}

	public Boolean getHasWarnings() {
		return !CollectionUtils.isEmpty(warnings);
	}

	public Boolean getHasError() {
		return !CollectionUtils.isEmpty(errors);
	}

	public List<ValidatorMessage> getWarnings() {
		return warnings;
	}

	public void addWarnings(ValidatorMessage warning) {
		this.warnings.add(warning);
	}

	public void addWarnings(List<ValidatorMessage> warnings) {
		this.warnings.addAll(warnings);
	}

	public List<ValidatorMessage> getErrors() {
		return errors;
	}

	public void addError(ValidatorMessage error) {
		this.errors.add(error);
	}

	public void addErrors(List<ValidatorMessage> errors) {
		this.errors.addAll(errors);
	}

}
