package cn.example.order.Dao;

import cn.example.order.PoJo.Address;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AddressMapper {
    public List<Address> findProvince();

    public List<Address> findCity(Integer pid);

    public List<Address> findDistrict(Integer pid);

    public String findName(Integer id);

}
