package com.example.root.test2;

/**
 * Created by root on 28/8/15.
 */
public class City {
    private String name;
    private String id;

    public City(String name, String id){
        super();
        this.setName(name);
        this.setId(id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
