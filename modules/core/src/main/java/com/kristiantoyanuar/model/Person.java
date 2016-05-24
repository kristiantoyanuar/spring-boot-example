package com.kristiantoyanuar.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Kristianto Yanuar on 5/23/16.
 */
@MappedSuperclass
public abstract class Person<ID extends Serializable> extends AbstractEntity<ID> {

    public enum Gender {
        MALE, FEMALE, OTHERS
    }

    @Column
    private String firstName;

    @Column
    private String middleName;

    @Column
    private String lastName;

    @Temporal(TemporalType.DATE)
    private Date birthDate;

    @Enumerated(EnumType.ORDINAL)
    private Gender gender;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}
