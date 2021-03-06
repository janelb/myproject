package com.libang.erp.vo;

import com.libang.erp.entity.FixOrderParts;

import java.util.List;

/**
 * @author libang
 * @date 2018/8/9 19:49
 */
public class FixOrderPartsVo {

    private Integer employeeId;
    private List<FixOrderParts> fixOrderPartsList;

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public List<FixOrderParts> getFixOrderPartsList() {
        return fixOrderPartsList;
    }

    public void setFixOrderPartsList(List<FixOrderParts> fixOrderPartsList) {
        this.fixOrderPartsList = fixOrderPartsList;
    }

    @Override
    public String toString() {
        return "FixOrderPartsVo{" +
                "employeeId=" + employeeId +
                ", fixOrderPartsList=" + fixOrderPartsList +
                '}';
    }
}
