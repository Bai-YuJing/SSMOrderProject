package cn.example.order.service.Impl;

import cn.example.order.Dao.AdminMapper;
import cn.example.order.Dao.BusinessMapper;
import cn.example.order.PoJo.*;
import cn.example.order.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BusinessServiceImpl implements BusinessService {
    @Autowired
    private BusinessMapper businessMapper;
    @Autowired
    private AdminMapper adminMapper;

    @Override
    public void register(Business business) {
        businessMapper.register(business);
    }

    @Override
    public Business login(String account, String password) {
//        将图片路径更换为完整地址
        Business business = businessMapper.login(account, password);
        if (business == null) {
            return null;
        }
        if (business.getPic().equals("N")) {
            business.setPic("../pages/img/business/" + "pic.jpg");
        } else {

            business.setPic("../pages/img/business/" + business.getStoreName() + "/" + business.getPic());
        }

        //            获取省市区
        Address district = adminMapper.getDistrict(business.getAddressId());//区
        Address city = adminMapper.getCity(district.getPid());//市
        Address province = adminMapper.getProvince(city.getPid());//省
//      将地址id变为省市区
        business.setAddress(province.getName() + "-" + city.getName() + "-" + district.getName());
//        System.out.println(JSONArray.toJSONString(business));
//        设置状态为中文
        if (business.getState().equals("Y")) {
            business.setState("正常");
        }
        if (business.getState().equals("N")) {
            business.setState("封禁");
        } else {
            business.setState("下架");
        }
        return business;
    }

    @Override
    public List<Classify> findClassify(Integer id, Integer page, Integer rows) {
        List<Classify> list = businessMapper.findClassify(id, page, rows);
        for (Classify classify : list) {
            int classifyCount = businessMapper.findClassifyCount(classify.getId());
            classify.setMenuCount(classifyCount);
        }
        return list;
    }

    @Override
    public int findAllClassifyCount(Integer id) {
        return businessMapper.findAllClassifyCount(id);
    }

    @Override
    public int updateClassify(String name, Integer id, String table) {
        return businessMapper.updateClassify(name, id, table);
    }

    @Override
    public int addClassify(String name, Integer id) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("name", name);
        map.put("id", id);
        return businessMapper.addClassify(map);
    }

    @Override
    public int delete(String table, Integer id) {
        return businessMapper.delete(table, id);
    }

    @Override
    public int findClassifyCount(Integer id) {
        return businessMapper.findClassifyCount(id);
    }

    @Override
    public void addMenu(Menu menu) {
//        判断图片是否为空
        if (menu.getPic() == null) {
//            为空设置默认图片
            menu.setPic("N");
        }
        businessMapper.addMenu(menu);
    }

    @Override
    public List<Menu> findMenu(Integer id, Integer page, Integer rows, String name) {
        List<Menu> menu = businessMapper.findMenu(id, page, rows);
        for (Menu list : menu) {
//            将分类显示为中文
            list.setClassifyName(businessMapper.findClassifyName(list.getClassifyId()));
//                将路径变为完整路径
            if (list.getPic().equals("N")) {
                list.setPic("../pages/img/menu/menu.jpg");
            } else {

                list.setPic("../pages/img/menu/" + name + "/" + list.getPic());
            }
//            将状态显示为中文
            if (list.getState().equalsIgnoreCase("Y")) {
                list.setState("正常");
            } else {
                list.setState("下架");
            }
        }
        return menu;
    }

    @Override
    public int findMenuCount(Integer id) {
        return businessMapper.findMenuCount(id);
    }

    @Override
    public Map<String, Object> updateState(Integer id, String sate) {
        System.out.println(id + sate + "a");
        int i = businessMapper.updateMenuState(id, sate);
        System.out.println(i);
        HashMap<String, Object> map = new HashMap<>();
        if (businessMapper.updateMenuState(id, sate) != 0) {
            map.put("msg", "success");
            return map;
        }
        map.put("msg", "error");
        return map;
    }

    @Override
    public Map<String, Object> deleteMenu(Integer id) {
        HashMap<String, Object> map = new HashMap<>();
        if (businessMapper.deleteMenu(id) != 0) {
            map.put("msg", "success");
            return map;
        }
        map.put("msg", "error");
        return map;
    }

    @Override
    public Map<String, Object> updatePic(Integer id, String pinName) {
        int i = businessMapper.updatePic(id, pinName);
        Map<String, Object> map = new HashMap<>();
        if (i != 0) {
            map.put("msg", "success");
            return map;
        }
        map.put("msg", "error");
        return map;
    }

    @Override
    public Map<String, Object> updateValue(Integer id, String name, Integer price) {
        int i = businessMapper.updateValue(id, name, price);
        Map<String, Object> map = new HashMap<>();
        if (i != 0) {
            map.put("msg", "success");
            return map;
        }
        map.put("msg", "error");
        return map;
    }

    @Override
    public Map<String, Object> findUpdateValue(Integer id) {
        Menu updateValue = businessMapper.findUpdateValue(id);
        HashMap<String, Object> map = new HashMap<>();
        map.put("name", updateValue.getName());
        map.put("price", updateValue.getPrice());
        return map;
    }

    @Override
    public void updateBusinessMassage(String name, Integer id, String msg) {
        int i;
        if (name.equals("storeName")) {
            businessMapper.updateBusinessMassageStoreName(id, msg);
        } else {
            i = Integer.parseInt(msg);
            businessMapper.updateBusinessMassageAddressId(id, i);
        }
    }

    @Override
    public Map<String, Object> upFullAddress(Integer id, String fullAddress) {
        int i = businessMapper.upFullAddress(id, fullAddress);
        HashMap<String, Object> map = new HashMap<>();
        if (i != 0) {
            map.put("msg", "success");
            return map;
        }
        map.put("msg", "error");
        return map;
    }

    @Override
    public Map<String, Object> upBState(Integer id, String s) {
        int i = businessMapper.upBState(id, s);
        HashMap<String, Object> map = new HashMap<>();
        if (i != 0) {
            map.put("msg", "success");
            return map;
        }
        map.put("msg", "error");
        return map;
    }

    @Override
    public Map<String, Object> upBPic(Integer id, String pinName) {
        int i = businessMapper.upBPic(id, pinName);
        Map<String, Object> map = new HashMap<>();
        if (i != 0) {
            map.put("msg", "success");
            return map;
        }
        map.put("msg", "error");
        return map;
    }

    @Override
    public List<Orders> findOrders(Integer id, Integer page, Integer rows) {
        return businessMapper.findOrders(id, page, rows);
    }

    @Override
    public int findOrdersCount(Integer id) {
        return businessMapper.findOrdersCount(id);
    }

    @Override
    public Map<String, Object> upStateBusinesses(Long id) {
        HashMap<String, Object> map = new HashMap<>();
        int i = businessMapper.upStateBusinesses(id);
        if (i != 0) {
            map.put("msg", "success");
            return map;
        }
        map.put("msg", "error");
        return map;
    }

    @Override
    public List<Orders> selectOrders(Integer id, Integer page, Integer rows, Integer sate) {
        if (sate == 0) {
            return businessMapper.findOrders(id, page, rows);
        }
        return businessMapper.selectOrders(id, page, rows, sate);
    }

    @Override
    public int selectOrdersCount(Integer id, Integer state) {
        if (state == 0) {
            return businessMapper.findOrdersCount(id);
        }
        return businessMapper.selectOrdersCount(id, state);
    }

    @Override
    public List<BusinessMenu> selectMenuList(Long id) {
        List<BusinessMenu> list = businessMapper.selectMenuList(id);
        for (BusinessMenu businessMenu : list) {
            businessMenu.setMenu(businessMapper.selectMenuName(businessMenu.getMenuId()));
            businessMenu.setClassify(businessMapper.selectClsName(businessMapper.selectClsid(businessMenu.getMenuId())));
        }
        return list;
    }

    @Override
    public int selectMenuListCount(Long id) {

        return businessMapper.selectMenuListCount(id);
    }

}
