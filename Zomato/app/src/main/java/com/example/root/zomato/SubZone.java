package com.example.root.zomato;

public class SubZone {
    private String name, zone_id, subzone_id, city_id;

    public SubZone(String zone_id, String name, String subzone_id, String city_id) {
        this.zone_id = zone_id;
        this.name = name;
        this.subzone_id = subzone_id;
        this.city_id = city_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getZone_id() {
        return zone_id;
    }

    public void setZone_id(String zone_id) {
        this.zone_id = zone_id;
    }

    public String getSubzone_id() {
        return subzone_id;
    }

    public void setSubzone_id(String subzone_id) {
        this.subzone_id = subzone_id;
    }

    public String getCity_id() {
        return city_id;
    }

    public void setCity_id(String city_id) {
        this.city_id = city_id;
    }
}
