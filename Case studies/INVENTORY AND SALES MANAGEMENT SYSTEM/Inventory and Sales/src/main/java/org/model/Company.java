package org.model;

public class Company {
    private int id;
    private Location location;
    private String contact;
    private String name;
    enum Location{
        Hyderabad,Pune,Vizag,Banglore,Amaravati
    }

    public Company(int id, String name, Location location, String contact) {
        this.id = id;
        this.location = location;
        this.contact = contact;
        this.name=name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Location getLocation() {
        return location;
    }

    public String getContact() {
        return contact;
    }

}
