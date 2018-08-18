package com.example.xml_parser.bean;

public class App {
    private int id;
    private String name;
    private float version;

    /*public App(int id, String name, float version) {
        this.id = id;
        this.name = name;
        this.version = version;
    }*/

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getVersion() {
        return version;
    }

    public void setVersion(float version) {
        this.version = version;
    }
}
