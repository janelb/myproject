package com.libang.entity;

/**
 * @author libang
 * @date 2018/7/9 20:35
 */
public class Student {

    private Integer id;
    private String stuName;
    private Integer schoolId;
    private School school;

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", stuName='" + stuName + '\'' +
                ", schoolId=" + schoolId +
                ", school=" + school +
                '}';
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }
}
