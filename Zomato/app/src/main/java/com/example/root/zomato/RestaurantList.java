package com.example.root.zomato;

/**
 * Created by root on 31/8/15.
 */
public class RestaurantList {
    private String name;
    private String id;

    public RestaurantList(String name, String id) {
        this.name = name;
        this.id = id;
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
