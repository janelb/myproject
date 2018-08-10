package com.libang.erp.vo;

/**
 * @author libang
 * @date 2018/8/8 20:09
 */
public class CustomerVo {

    /**
     * 用户ID
     */
    private Integer id;

    /**
     * 客户名称
     */
    private String userName;

    /**
     * 身份证
     */
    private String idCard;

    /**
     * 手机号
     */
    private String tel;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @Override
    public String toString() {
        return "CustomerVo{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", idCard='" + idCard + '\'' +
                ", tel='" + tel + '\'' +
                '}';
    }
}
