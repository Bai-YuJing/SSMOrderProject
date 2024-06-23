package cn.example.order.service;

import cn.example.order.PoJo.Orders;
import cn.example.order.PoJo.Rider;

import java.util.List;
import java.util.Map;

public interface RiderService {
    //    注册
    public void register(Rider rider);

    //   登录
    public Rider login(String account, String password);

    //    查询所有为取餐订单
    List<Orders> getOrders(Integer pages, Integer rows);

    int getOrdersCount();

    Map<String, Object> riderOrder(Long id, Integer riderId);

    List<Orders> getMyOrders(Integer pages, Integer rows, Integer riderId);

    int getMyOrdersCount(Integer riderId);

    Map<String, Object> riderMyOrder(Long id, Integer state);

}
