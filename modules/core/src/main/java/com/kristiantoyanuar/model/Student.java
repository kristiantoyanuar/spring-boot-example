package com.kristiantoyanuar.model;

import javax.persistence.*;

/**
 * Created by Kristianto Yanuar on 5/23/16.
 */
@Entity
public class Student extends Person<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String code;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
