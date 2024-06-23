package cn.example.order.Dao;

import cn.example.order.PoJo.Orders;
import cn.example.order.PoJo.Rider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RiderMapper {
    void register(Rider rider);

    Rider login(@Param("account") String account, @Param("password") String password);

    List<Orders> getOrders(@Param("page") Integer page, @Param("rows") Integer rows);

    int getOrdersCount();

    int riderOrder(@Param("id") Long id, @Param("riderId") Integer riderId);

    int selectOrders(Long id);

    List<Orders> getMyOrders(@Param("page") Integer page, @Param("rows") Integer rows, @Param("riderId") Integer riderId);

    int getMyOrdersCount(Integer riderId);

    int riderMyOrder(@Param("id") Long id, @Param("state") Integer state);

}
