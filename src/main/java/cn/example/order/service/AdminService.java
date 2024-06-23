package cn.example.order.service;

import cn.example.order.PoJo.Admin;
import cn.example.order.PoJo.Business;
import cn.example.order.PoJo.Rider;
import cn.example.order.PoJo.User;

import java.util.List;


public interface AdminService {
    public Admin login(String account, String password);

    public Integer findCountUser();

    public List<User> findUser(Integer page, Integer rows);

    public Integer findCountBusiness();

    public List<Business> findBusiness(Integer page, Integer rows);

    public int delete(Integer id, String table);

    public int state(Integer id, String table);

    int findCountRider();

    public List<Rider> findRider(Integer page, Integer rows);
}
