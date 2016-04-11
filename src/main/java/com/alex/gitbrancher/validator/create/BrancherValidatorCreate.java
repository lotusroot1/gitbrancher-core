package com.alex.gitbrancher.validator.create;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import com.alex.gitbrancher.validator.BrancherValidator;
import com.alex.gitbrancher.validator.ValidatorMessage;
import com.alex.gitbrancher.validator.ValidatorResult;
import com.alex.gitbrancher.validator.ValidatorStep;

public class BrancherValidatorCreate implements BrancherValidator {

	private Set<String> reservedBranchNames;

	public BrancherValidatorCreate() {
		reservedBranchNames = new HashSet<String>();
		reservedBranchNames.add("master");
	}

	protected void addReservedNames(List<String> names) {
		if (!CollectionUtils.isEmpty(names)) {
			for (String name : names) {
				addReservedName(name);
			}
		}
	}

	protected void addReservedName(String name) {
		addToReservedBranchSet(name);
	}

	private void addToReservedBranchSet(String name) {
		if (reservedBranchNames.contains(name)) {
			// log here
			System.out.println("Skipping already reserved name: " + name);
		} else {
			reservedBranchNames.add(name);
		}
	}

	@Override
	public ValidatorResult validate(String repository, String sha, String name) {
		ValidatorResult result = new ValidatorResult();

		// validate
		repository = StringUtils.trimToEmpty(repository);
		name = StringUtils.trimToEmpty(name);
		sha = StringUtils.trimToEmpty(sha);

		if (StringUtils.isEmpty(repository)) {
			result.addError(new ValidatorMessage("Repository is empty"));
		}

		if (StringUtils.isEmpty(sha)) {
			result.addError(new ValidatorMessage("SHA is empty"));
		}

		if (StringUtils.isEmpty(name)) {
			result.addError(new ValidatorMessage("Branch name is empty"));
		}

		if (reservedBranchNames.contains(name)) {
			result.addError(new ValidatorMessage("Cannot use reserved branch name: [" + name + "]"));
		}

		return result;
	}

	@Override
	public ValidatorStep getValidatorStep() {
		return ValidatorStep.PRE_CREATE;
	}

}
