package cn.example.order.Dao;

import cn.example.order.PoJo.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AdminMapper {
    Admin login(@Param("account") String account, @Param("password") String password);

    //    获取用户数量
    Integer findCountUser();

    //    获取用户
    List<User> findUser(@Param("page") Integer page, @Param("rows") Integer rows);

    Integer findCountBusiness();

    List<Business> findBusiness(@Param("page") Integer page, @Param("rows") Integer rows);

    //    删除，table参数：要删除表格
    Integer delete(@Param("id") Integer id, @Param("table") String table);

    //    修改状态
    Integer state(@Param("id") Integer id, @Param("state") String state, @Param("table") String table);

    String getState(@Param("id") Integer id, @Param("table") String table);

    //  获取省
    Address getProvince(Integer id);

    //  获取市
    Address getCity(Integer id);

    //  获取区
    Address getDistrict(Integer id);

    int findCountRider();

    public List<Rider> findRider(@Param("page") Integer page, @Param("rows") Integer rows);

}
