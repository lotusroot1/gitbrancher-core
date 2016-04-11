package com.alex.gitbrancher.validator;

public interface BrancherValidator {

	ValidatorStep getValidatorStep();

	ValidatorResult validate(String repository, String sha, String name);
}
