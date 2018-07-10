package com.libang.entity;

/**
 * @author libang
 * @date 2018/7/10 17:14
 */
public class School {

    private Integer id;
    private String schoolName;

    @Override
    public String toString() {
        return "School{" +
                "id=" + id +
                ", schoolName='" + schoolName + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }
}
