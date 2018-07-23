package com.libang.entity;

/**
 * @author libang
 * @date 2018/7/22 15:42
 */
public class User {
    private Integer id ;
    private String userName;
    private String address;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    public User(Integer id, String userName, String address) {
        this.id = id;
        this.userName = userName;
        this.address = address;
    }

    public User() {

    }

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
