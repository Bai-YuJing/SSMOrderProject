package cn.example.order.service.Impl;

import cn.example.order.Dao.RiderMapper;
import cn.example.order.PoJo.Orders;
import cn.example.order.PoJo.Rider;
import cn.example.order.service.RiderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RiderServiceImpl implements RiderService {
    @Autowired
    private RiderMapper riderMapper;

    @Override
    public void register(Rider rider) {
        riderMapper.register(rider);
    }

    @Override
    public Rider login(String account, String password) {
        return riderMapper.login(account, password);
    }

    @Override
    public List<Orders> getOrders(Integer pages, Integer rows) {
        return riderMapper.getOrders(pages, rows);
    }

    @Override
    public int getOrdersCount() {
        return riderMapper.getOrdersCount();
    }

    @Override
    public Map<String, Object> riderOrder(Long id, Integer riderId) {
        HashMap<String, Object> map = new HashMap<>();
        if (riderMapper.selectOrders(id) == 3) {
            map.put("msg", "error");
            return map;
        }
        if (riderMapper.riderOrder(id, riderId) != 0) {
            map.put("msg", "success");
            return map;
        }
        map.put("msg", "error");
        return map;
    }

    @Override
    public List<Orders> getMyOrders(Integer pages, Integer rows, Integer riderId) {
        return riderMapper.getMyOrders(pages, rows, riderId);
    }

    @Override
    public int getMyOrdersCount(Integer riderId) {
        return riderMapper.getMyOrdersCount(riderId);
    }

    @Override
    public Map<String, Object> riderMyOrder(Long id, Integer state) {
        HashMap<String, Object> map = new HashMap<>();
        if (riderMapper.riderMyOrder(id, state) != 0) {
            map.put("msg", "success");
            return map;
        }
        map.put("msg", "error");
        return map;
    }
}
