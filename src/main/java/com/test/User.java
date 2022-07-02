package com.test;

import java.util.LinkedHashMap;

public class User {
    public Long id;
    public String name;
    public String lastName;
    public Byte age;

    public User(long id, String name, String lastName, byte age) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.age = age;
    }

    public User(LinkedHashMap<String, Object> map) {
        this.id = Long.parseLong(map.get("id").toString());
        this.name = map.get("name").toString();
        this.lastName = map.get("lastName").toString();
        this.age = Byte.parseByte(map.get("age").toString());
    }
}