package cn.example.order.Dao;

import cn.example.order.PoJo.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
    void register(User user);

    User login(@Param("account") String account, @Param("password") String password);

    List<UserBusiness> findBusiness();

    //    查询店家分类
    List<UserClassify> findBusinessCls(Integer id);

    //    查询店家分类下的菜品
    List<UserMenu> findMenu(Integer id);

    //    查询商家名称
    String findStoreName(Integer id);

    //    添加地址
    void addAddress(UserAddress userAddress);

    //    查询用户地址
    List<UserAddress> findUserAddress(Integer id);

    //    修改默认地址
    int setAddress(Integer id);

    int setAddressNo(Integer id);

    UserAddress getOrderAddress(Integer id);

    int pay(Orders orders);

    int payFull(FullOrder order);

    String findBusinessFullAddress(Integer id);

    Integer findBusinessAddressID(Integer id);

    List<Orders> findOrder(Integer id);

    String findPic(Integer id);

}
