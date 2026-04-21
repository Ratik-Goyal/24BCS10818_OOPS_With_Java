package com.student.model;

public class Student {
    private int id;
    private String name;
    private String email;
    private String course;
    private String phone;
    private String grade;

    public Student() {}

    public Student(int id, String name, String email, String course, String phone, String grade) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.course = course;
        this.phone = phone;
        this.grade = grade;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getCourse() { return course; }
    public void setCourse(String course) { this.course = course; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getGrade() { return grade; }
    public void setGrade(String grade) { this.grade = grade; }
}
