package com.libang.erp.dto;

import com.libang.erp.entity.Order;
import com.libang.erp.entity.Parts;
import com.libang.erp.entity.ServiceType;
import com.libang.erp.entity.Type;

import java.util.List;

/**
 * @author libang
 * @date 2018/8/9 9:21
 */
public class OrderDto {

    private Order order;
    private ServiceType serviceType;
    private List<Parts> partsList;
    private List<Type> typeList;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public ServiceType getServiceType() {
        return serviceType;
    }

    public void setServiceType(ServiceType serviceType) {
        this.serviceType = serviceType;
    }

    public List<Parts> getPartsList() {
        return partsList;
    }

    public void setPartsList(List<Parts> partsList) {
        this.partsList = partsList;
    }

    public List<Type> getTypeList() {
        return typeList;
    }

    public void setTypeList(List<Type> typeList) {
        this.typeList = typeList;
    }

    @Override
    public String toString() {
        return "OrderDto{" +
                "order=" + order +
                ", serviceType=" + serviceType +
                ", partsList=" + partsList +
                ", typeList=" + typeList +
                '}';
    }
}
