package com.libang.erp.vo;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author libang
 * @date 2018/8/4 8:32
 */
public class OrderVo {
    private Integer id;
    private Integer carId;
    private Integer serviceTypeId;
    private BigDecimal fee;
    private List<PartsVo> partsList;


    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public Integer getServiceTypeId() {
        return serviceTypeId;
    }

    public void setServiceTypeId(Integer serviceTypeId) {
        this.serviceTypeId = serviceTypeId;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public List<PartsVo> getPartsList() {
        return partsList;
    }

    public void setPartsList(List<PartsVo> partsList) {
        this.partsList = partsList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "OrderVo{" +
                "id=" + id +
                ", carId=" + carId +
                ", serviceTypeId=" + serviceTypeId +
                ", fee=" + fee +
                ", partsList=" + partsList +
                '}';
    }
}
