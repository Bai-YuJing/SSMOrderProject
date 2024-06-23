package cn.example.order.service;

import cn.example.order.PoJo.Address;

import java.util.List;

public interface AddressService {
    public List<Address> findProvince();

    public List<Address> findCity(Integer pid);

    public List<Address> findDistrict(Integer pid);

}
