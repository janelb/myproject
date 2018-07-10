package com.libang.entity;

/**
 * @author libang
 * @date 2018/7/10 19:20
 */
public class TypeStudent {
    private Integer student_id;
    private Integer ype_id;

    @Override
    public String toString() {
        return "TypeStudent{" +
                "student_id=" + student_id +
                ", ype_id=" + ype_id +
                '}';
    }

    public Integer getStudent_id() {
        return student_id;
    }

    public void setStudent_id(Integer student_id) {
        this.student_id = student_id;
    }

    public Integer getYpe_id() {
        return ype_id;
    }

    public void setYpe_id(Integer ype_id) {
        this.ype_id = ype_id;
    }
}
