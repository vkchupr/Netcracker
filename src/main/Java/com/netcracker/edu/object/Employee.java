package com.netcracker.edu.object;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class Employee {
    String firstName;
    String lastName;
    String position;
    int age;

    public String getFullName() {
        return lastName + " " + firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return String.format("name: %s %s; age: %d; position: %s", firstName, lastName, age, position);
    }

    public Map<String, String> getMapOfProperties() {
        Map<String, String> props = new HashMap<String, String>();
        Field[] fields = getClass().getDeclaredFields();

        for (int i = 0; i < fields.length; i++) {
            String key = null, value = null;
            key = fields[i].getName();
            try {
                value = fields[i].get(this).toString();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            props.put(key, value);
        }

        return props;
    }
}
