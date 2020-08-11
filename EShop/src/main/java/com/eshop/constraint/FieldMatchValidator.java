package com.eshop.constraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 * Validator class for checking whether two fields are equal or not.
 * </p>
 * 
 * @author Sreejith
 * @version 1.0
 * @since July 2020
 *
 */
public class FieldMatchValidator implements ConstraintValidator<FieldMatch, Object> {

	private static final Logger LOGGER = LoggerFactory.getLogger(FieldMatchValidator.class);

	private String firstFieldName;
	private String secondFieldName;
	private String message;

	@Override
	public void initialize(final FieldMatch constraintAnnotation) {
		firstFieldName = constraintAnnotation.first();
		secondFieldName = constraintAnnotation.second();
		message = constraintAnnotation.message();
	}

	/**
	 * <p>
	 * Method for for checking whether two fields are equal or not.
	 * </p>
	 */
	@Override
	public boolean isValid(final Object value, final ConstraintValidatorContext context) {
		boolean valid = true;
		try {
			final Object firstObj = BeanUtils.getProperty(value, firstFieldName);
			final Object secondObj = BeanUtils.getProperty(value, secondFieldName);

			valid = firstObj == null && secondObj == null || firstObj != null && firstObj.equals(secondObj);
		} catch (final Exception ex) {
			LOGGER.error("Exception occured in isValid() @ FieldMatchValidator.class");
		}

		if (!valid) {
			context.buildConstraintViolationWithTemplate(message).addPropertyNode(firstFieldName)
					.addConstraintViolation().disableDefaultConstraintViolation();
		}

		return valid;
	}
}