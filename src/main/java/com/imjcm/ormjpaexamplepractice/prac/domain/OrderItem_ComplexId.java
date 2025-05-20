package com.imjcm.ormjpaexamplepractice.prac.domain;

import java.io.Serializable;

public class OrderItem_ComplexId implements Serializable {
    private long order;
    private long product;

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
