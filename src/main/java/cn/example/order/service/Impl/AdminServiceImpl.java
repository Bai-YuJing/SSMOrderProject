package cn.example.order.service.Impl;

import cn.example.order.Dao.AdminMapper;
import cn.example.order.PoJo.*;
import cn.example.order.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;

    @Override
    public Admin login(String account, String password) {
        return adminMapper.login(account, password);
    }

    @Override
    public Integer findCountUser() {
        return adminMapper.findCountUser();
    }

    @Override
    public List<User> findUser(Integer page, Integer rows) {
        List<User> table = adminMapper.findUser(page, rows);
        for (User user : table) {
            if (user.getstate().equalsIgnoreCase("Y")) {
                user.setstate("正常");
            } else {
                user.setstate("封禁");
            }

        }
        return table;
    }

    @Override
    public Integer findCountBusiness() {
        return adminMapper.findCountBusiness();
    }

    @Override
    public List<Business> findBusiness(Integer page, Integer rows) {
        List<Business> table = adminMapper.findBusiness(page, rows);
        for (Business business : table) {
//            获取省市区
            Address district = adminMapper.getDistrict(business.getAddressId());//区
//            System.out.println(JSONArray.toJSONString(district));
            Address city = adminMapper.getCity(district.getPid());//市
//            System.out.println(JSONArray.toJSONString(city));
            Address province = adminMapper.getProvince(city.getPid());//省
//            System.out.println(JSONArray.toJSONString(province));

            business.setAddress(province.getName() + "-" + city.getName() + "-" + district.getName());
//           修改状态显示为中文
            if (business.getState().equalsIgnoreCase("Y")) {
                business.setState("正常");
            } else if (business.getState().equalsIgnoreCase("N")) {
                business.setState("封禁");
            } else {
                business.setState("下线");
            }
        }
//        System.out.println(JSONArray.toJSONString(adminMapper.findBusiness(page,rows)));
        return table;
    }

    @Override
    public int delete(Integer id, String table) {


        return adminMapper.delete(id, table);
    }

    @Override
    public int state(Integer id, String table) {
        String state = adminMapper.getState(id, table);
        if (state.equals("Y")) {
            state = "N";
        } else if (state.equals("N")) {
            state = "Y";
        } else {
            return 0;
        }
        return adminMapper.state(id, state, table);
    }

    @Override
    public int findCountRider() {
        return adminMapper.findCountRider();
    }

    @Override
    public List<Rider> findRider(Integer page, Integer rows) {
        List<Rider> list = adminMapper.findRider(page, rows);
        for (Rider rider : list) {
            if (rider.getState().equalsIgnoreCase("Y")) {
                rider.setState("正常");
            } else {
                rider.setState("封禁");
            }
        }
        return list;
    }
}
