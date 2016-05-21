package com.kristiantoyanuar.model;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

/**
 * Hold commons attributes among all entities
 *
 * Created by Kristianto Yanuar on 12/16/2015.
 */
@MappedSuperclass
public abstract class BaseEntity<ID extends Serializable> implements Serializable {

    @CreatedDate
    protected Date createdDate;

    @CreatedBy
    protected User createdBy;

    @LastModifiedDate
    protected Date lastModified;

    @LastModifiedBy
    protected User modifiedBy;

    public BaseEntity() {
        createdDate = new Date();
    }

    @Id
    public abstract ID getId();

    public abstract void setId(ID id);

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    public User getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(User modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null
                && this.getId() != null
                && this.getClass().equals(obj.getClass())
                && obj instanceof BaseEntity
                && this.getId().equals(((BaseEntity) obj).getId())) return true;

        return false;
    }

    @Override
    public int hashCode() {
        if (getId() != null) return getId().hashCode();
        return super.hashCode();
    }

    @Override
    public String toString() {
        return getClass().getName() +"[ID="+getId()+"]";
    }
}
