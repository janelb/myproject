package com.libang.entity;

import java.util.List;

/**
 * @author libang
 * @date 2018/7/11 11:05
 */
public class Student{

    private Integer id;
    private String stuName;
    private Integer schoolId;
    private School school;
    private List<Type> typeList;


    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", stuName='" + stuName + '\'' +
                ", schoolId=" + schoolId +
                ", school=" + school +
                ", typeList=" + typeList +
                '}';
    }

    public List<Type> getTypeList() {
        return typeList;
    }

    public void setTypeList(List<Type> typeList) {
        this.typeList = typeList;
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
