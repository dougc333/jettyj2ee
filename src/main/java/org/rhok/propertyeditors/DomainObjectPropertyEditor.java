package org.rhok.propertyeditors;

import org.rhok.domain.DomainObject;
import org.rhok.service.DomainObjectService;
import org.apache.commons.lang.math.NumberUtils;

import java.beans.PropertyEditorSupport;

public class DomainObjectPropertyEditor extends PropertyEditorSupport
{
    private DomainObjectService domainObjectService;
    private Class<? extends DomainObject> domainObjectClass;

    public DomainObjectPropertyEditor(DomainObjectService domainObjectService, Class<? extends DomainObject> domainObjectClass)
    {
        this.domainObjectService = domainObjectService;
        this.domainObjectClass = domainObjectClass;
    }

    @Override
    public String getAsText()
    {
        String asText = null;

        if(getValue() != null)
        {
            asText = String.valueOf(((DomainObject)getValue()).getId());
        }

        return asText; 
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException
    {
        if(NumberUtils.isNumber(text))
        {
            setValue(domainObjectService.get(domainObjectClass, Long.valueOf(text)));
        }
    }
}

