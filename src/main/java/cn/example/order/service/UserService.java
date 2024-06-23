package cn.example.order.service;

import cn.example.order.PoJo.*;

import java.util.List;


public interface UserService {
    public void register(User user);

    public User login(String account, String password);

    //    查询商家
    List<UserBusiness> findBusiness();

    //    查询分类
    List<UserClassify> findBusinessCls(Integer id);

    //    查找菜品
    List<UserMenu> findMenu(Integer id);

    void addAddress(Integer userId, String name, String phonenumber, String fullAddress, Integer provinceId, Integer cityId, Integer districtId);

    List<UserAddress> findUserAddress(Integer id);

    //    修改默认地址
    void setAddress(Integer id, Integer userId);

    int pay(String[] menu, Integer price, Integer userId, Integer businessId);

    //    查询用户订单
    List<Orders> findOrder(Integer id);


}
