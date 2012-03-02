package org.rhok.domain;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
public abstract class DomainObject implements Cloneable
{
    private Long id;
    private int version;
    private Date createDate;
    private Date modifiedDate;

    @Id
    @GeneratedValue
    public Long getId()
    {
        return this.id;
    }

    private void setId(Long id)
    {
        this.id = id;
    }

    @Version
    public int getVersion()
    {
        return version;
    }

    private void setVersion(int version)
    {
        this.version = version;
    }

    @Temporal(TemporalType.TIMESTAMP)
    public Date getCreateDate()
    {
        return createDate;
    }

    private void setCreateDate(Date createDate)
    {
        this.createDate = createDate;
    }

    @Temporal(TemporalType.TIMESTAMP)
    public Date getModifiedDate()
    {
        return modifiedDate;
    }

    private void setModifiedDate(Date modifiedDate)
    {
        this.modifiedDate = modifiedDate;
    }

    @PrePersist
    private void handleCreateDate()
    {
        setCreateDate(new Date());
        handleModifiedDate();
    }

    @PreUpdate
    private void handleModifiedDate()
    {
        setModifiedDate(new Date());
    }

    public Object clone() throws CloneNotSupportedException
    {
        DomainObject domainObject = (DomainObject) super.clone();
        domainObject.setId(null);
        domainObject.setVersion(0);

        return domainObject;
    }
}