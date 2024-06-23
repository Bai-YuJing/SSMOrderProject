package cn.example.order.service.Impl;

import cn.example.order.Dao.AddressMapper;
import cn.example.order.Dao.AdminMapper;
import cn.example.order.Dao.UserMapper;
import cn.example.order.PoJo.*;
import cn.example.order.service.UserService;
import com.alibaba.fastjson.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private AddressMapper addressMapper;
    @Autowired
    private AdminMapper adminMapper;

    @Override
    public void register(User user) {
        userMapper.register(user);
    }

    @Override
    public User login(String account, String password) {
        return userMapper.login(account, password);
    }

    @Override
    public List<UserBusiness> findBusiness() {
        List<UserBusiness> business = userMapper.findBusiness();
        for (UserBusiness userBusiness : business) {
            if (userBusiness.getPic().equals("N")) {
                userBusiness.setPic("../pages/img/business/" + "pic.jpg");
            } else {

                userBusiness.setPic("../pages/img/business/" + userBusiness.getStoreName() + "/" + userBusiness.getPic());
            }
            userBusiness.setUrl("../user/businessMenu?id=" + userBusiness.getId());
        }
        return business;
    }

    @Override
    public List<UserClassify> findBusinessCls(Integer id) {
        return userMapper.findBusinessCls(id);
    }

    @Override
    public List<UserMenu> findMenu(Integer id) {
        List<UserMenu> menu = userMapper.findMenu(id);
        for (UserMenu userMenu : menu) {
            if (userMenu.getPic().equals("N")) {
                userMenu.setPic("../pages/img/menu/" + "menu.jpg");
            }
            userMenu.setPic("../pages/img/menu/" + userMapper.findStoreName(userMenu.getBusinessId()) + "/" + userMenu.getPic());
        }
        return menu;
    }

    @Override
    public void addAddress(Integer userId, String name, String phonenumber, String fullAddress, Integer provinceId, Integer cityId, Integer districtId) {
        String province = addressMapper.findName(provinceId);
        String city = addressMapper.findName(cityId);
        String district = addressMapper.findName(districtId);
        UserAddress userAddress = new UserAddress();
        userAddress.setAddress(province + "-" + city + '-' + district);
        userAddress.setName(name);
        userAddress.setPhonenumber(phonenumber);
        userAddress.setUserId(userId);
        userAddress.setFull_address(fullAddress);
        System.out.println(JSONArray.toJSONString(userAddress));
        userMapper.addAddress(userAddress);
    }

    @Override
    public List<UserAddress> findUserAddress(Integer id) {
        return userMapper.findUserAddress(id);
    }

    @Override
    public void setAddress(Integer id, Integer userId) {
        userMapper.setAddressNo(userId);
        userMapper.setAddress(id);
    }

    @Override
    public int pay(String[] menu, Integer price, Integer userId, Integer businessId) {
        Orders orders = new Orders();
        Long id = new Date().getTime() + businessId + userId;
        orders.setId(id);
        orders.setUserId(userId);
        orders.setBusinessId(businessId);
        orders.setPrice(price);
        UserAddress orderAddress = userMapper.getOrderAddress(userId);
        if (orderAddress == null) {
            return 0;
        }
//        用户地址
        orders.setUserAddress(orderAddress.getAddress() + "-" + orderAddress.getFull_address());
//        商家地址
        Integer businessAddressID = userMapper.findBusinessAddressID(businessId);//查询商家地址ID
        Address district = adminMapper.getDistrict(businessAddressID);//区
//            System.out.println(JSONArray.toJSONString(district));
        Address city = adminMapper.getCity(district.getPid());//市
//           System.out.println(JSONArray.toJSONString(city));
        Address province = adminMapper.getProvince(city.getPid());//省
//           System.out.println(JSONArray.toJSONString(province));
        String businessFullAddress = userMapper.findBusinessFullAddress(businessId);//查询商家详细地址

        orders.setBusinessAddress(province.getName() + "-" + city.getName() + "-" + district.getName() + "-" + businessFullAddress);
//        System.out.println(JSONArray.toJSONString(orders));
        userMapper.pay(orders);
        FullOrder order = new FullOrder();
        for (int i = 0; i < menu.length; i++) {
            order.setOrderId(id);
            if (i % 2 == 0) {
                order.setMenuId(Integer.valueOf(menu[i].substring(4)));
            } else {
                order.setNum(Integer.valueOf(menu[i].substring(4, menu[i].length() - 1)));
                userMapper.payFull(order);

            }
        }
        return 1;
    }

    @Override
    public List<Orders> findOrder(Integer id) {
        List<Orders> list = userMapper.findOrder(id);
        for (Orders orders : list) {
            orders.setBusinessName(userMapper.findStoreName(orders.getBusinessId()));
            String pic = userMapper.findPic(orders.getBusinessId());
            if (pic.equals("N")) {
                orders.setPic("../pages/img/business/pic.jpg");
            } else {
                orders.setPic("../pages/img/business/" + orders.getBusinessName() + "/" + pic);
            }
        }
        return list;
    }
}
