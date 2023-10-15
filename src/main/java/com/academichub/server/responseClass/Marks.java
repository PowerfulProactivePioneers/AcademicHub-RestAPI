package com.academichub.server.responseClass;

public class Marks {

    String id;
    int marks;

    public int GetMarks() {
        return marks;
    }

    public String GetId() {
        return id;
    }

    public Marks(String id,int marks) {
        this.id=id;
        this.marks = marks;
    }

    @Override
    public String toString() {
        return "Marks{" +
                "id='" + id + '\'' +
                ", marks='" + marks + '\'' +
                '}';
    }
}
