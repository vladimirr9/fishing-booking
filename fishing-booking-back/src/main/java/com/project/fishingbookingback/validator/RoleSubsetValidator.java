package com.project.fishingbookingback.validator;

import com.project.fishingbookingback.model.Role;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class RoleSubsetValidator implements ConstraintValidator<RoleSubset, Role> {
    private Role[] subset;

    @Override
    public void initialize(RoleSubset constraint) {
        this.subset = constraint.anyOf();
    }

    @Override
    public boolean isValid(Role value, ConstraintValidatorContext context) {
        return Arrays.asList(subset).contains(value);
    }
}

