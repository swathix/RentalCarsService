package com.demo.model;

import java.util.List;

public class CarCollection {

    private List<Car> collections;

    private Integer size;

    public CarCollection() {
    }

    public List<Car> getCollections() {
        return collections;
    }

    public void setCollections(List<Car> collections) {
        this.collections = collections;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
