package cn.example.order.service.Impl;

import cn.example.order.Dao.AddressMapper;
import cn.example.order.PoJo.Address;
import cn.example.order.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    private AddressMapper addressMapper;

    @Override
    public List<Address> findProvince() {
        return addressMapper.findProvince();
    }

    @Override
    public List<Address> findCity(Integer pid) {
        return addressMapper.findCity(pid);
    }

    @Override
    public List<Address> findDistrict(Integer pid) {
        return addressMapper.findDistrict(pid);
    }


}
