package com.example.sira.attendance;

public class ContactDao {

    private long id;
    private String name;
    private String phone;
    //private String category;
    private String studentNum;
    private String major;
    private String subject1;
    private String subject2;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    /*public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }*/

    public String getStudentNum() {
        return studentNum;
    }

    public void setStudentNum(String studentNum) {
        this.studentNum = studentNum;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getSubject1() {
        return subject1;
    }

    public void setSubject1(String subject1) {
        this.subject1 = subject1;
    }

    public String getSubject2() {
        return subject2;
    }

    public void setSubject2(String subject2) {
        this.subject2 = subject2;
    }

    @Override
    public String toString() {
        return "ContactDao{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", studentNum='" + studentNum + '\'' +
                ", major='" + major + '\'' +
                ", subject1='" + subject1 + '\'' +
                ", subject2='" + subject2 + '\'' +
                '}';
    }
}
