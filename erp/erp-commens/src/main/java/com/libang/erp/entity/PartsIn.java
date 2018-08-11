package com.libang.erp.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 */
public class PartsIn implements Serializable {
    private Integer id;

    private Integer partsId;

    private Integer num;

    private Date createTime;

    private Integer employeeId;

    private Parts parts;
    private Employee employee;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPartsId() {
        return partsId;
    }

    public void setPartsId(Integer partsId) {
        this.partsId = partsId;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public Parts getParts() {
        return parts;
    }

    public void setParts(Parts parts) {
        this.parts = parts;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public String toString() {
        return "PartsIn{" +
                "id=" + id +
                ", partsId=" + partsId +
                ", num=" + num +
                ", createTime=" + createTime +
                ", employeeId=" + employeeId +
                ", parts=" + parts +
                ", employee=" + employee +
                '}';
    }
}