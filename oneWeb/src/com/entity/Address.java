package com.entity;

/**
 * @ClassName Address
 * @Author: ChenBJ
 * @Description: TODO
 * @Date: 2018/6/27 14:54
 * @Version:
 */
public class Address {
    private String province;
     private String city;

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Address{" +
                "province='" + province + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
