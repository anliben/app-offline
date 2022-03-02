package com.example.myapplication;

public class Costumers {

    private int id;
    private String name;
    private int age;
    private boolean isActive;

    public Costumers(int id, String name, int age, boolean isActive){

        this.id = id;
        this.name = name;
        this.age = age;
        this.isActive = isActive;

    }

    @Override
    public String toString() {
        return "Costumers{" +
                "Id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", isActive=" + isActive +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) { id = id; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
