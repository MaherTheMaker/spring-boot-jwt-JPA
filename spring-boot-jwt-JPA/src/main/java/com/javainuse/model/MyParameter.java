package com.javainuse.model;

import javax.persistence.*;

@Entity
@Table(name = "llllllllllll")
public class MyParameter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    String myKey;

    String myValue;
   public MyParameter() {
    }

    public MyParameter(String mykey, String value) {
        this.myKey = mykey;
        this.myValue = value;
    }

    public String getMyvalue() {
        return myValue;
    }

    public String getMyKey() {
        return myKey;
    }

    public void setMyvalue(String value) {
        this.myValue = value;
    }
}

