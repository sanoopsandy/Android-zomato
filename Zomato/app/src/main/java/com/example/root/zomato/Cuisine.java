package com.example.root.zomato;

/**
 * Created by root on 31/8/15.
 */
public class Cuisine {
    private String name;
    private String id;
    private String city_id;

    public Cuisine(String name, String id, String city_id) {
        this.name = name;
        this.id = id;
        this.city_id = city_id;
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

    public String getCity_id() {
        return city_id;
    }

    public void setCity_id(String city_id) {
        this.city_id = city_id;
    }
}
