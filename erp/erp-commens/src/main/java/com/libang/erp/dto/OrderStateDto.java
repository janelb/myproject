package com.libang.erp.dto;

/**
 * @author libang
 * @date 2018/8/9 17:34
 */
public class OrderStateDto {
    private Integer orderId;
    private String state;
    private Integer employeeId;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    @Override
    public String toString() {
        return "OrderStateDto{" +
                "orderId=" + orderId +
                ", state='" + state + '\'' +
                ", employeeId=" + employeeId +
                '}';
    }
}

