package org.rhok.validation;

import org.rhok.domain.DomainObject;
import java.util.Arrays;
import java.util.List;
import org.hibernate.validator.ClassValidator;
import org.hibernate.validator.InvalidValue;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class DomainObjectValidator implements Validator
{
    public boolean supports(Class aClass)
    {
        return DomainObject.class.isAssignableFrom(aClass);
    }

    public void validate(Object object, Errors errors)
    {
        DomainObject domainObject = (DomainObject) object;
        List<InvalidValue> invalids = validateObject(domainObject);

        for (int i = 0; i < invalids.size(); i++)
        {
            InvalidValue invalidValue = invalids.get(i);
            errors.rejectValue(invalidValue.getPropertyPath(), null, invalidValue.getMessage());
        }
    }

    private List<InvalidValue> validateObject(DomainObject object)
    {
        ClassValidator validator = new ClassValidator(object.getClass());
        return Arrays.asList(validator.getInvalidValues(object));
    }
}
