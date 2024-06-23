package cn.example.order.service;

import cn.example.order.PoJo.*;

import java.util.List;
import java.util.Map;

public interface BusinessService {
    //    注册
    public void register(Business business);

    //   登录
    public Business login(String account, String password);

    //    查询分类
    public List<Classify> findClassify(Integer id, Integer page, Integer rows);

    //    查询分类数
    public int findAllClassifyCount(Integer id);

    //  修改分类名称
    public int updateClassify(String name, Integer id, String table);

    //    添加废分类
    public int addClassify(String name, Integer id);

    //    删除分类、菜品
    public int delete(String table, Integer id);

    //    获取分类菜品数
    public int findClassifyCount(Integer id);

    //    添加菜品
    public void addMenu(Menu menu);

    //    查看菜品
    public List<Menu> findMenu(Integer id, Integer page, Integer rows, String name);

    //    查找菜品数量
    public int findMenuCount(Integer id);

    //    修改菜品状态
    public Map<String, Object> updateState(Integer id, String sate);

    //    删除
    public Map<String, Object> deleteMenu(Integer id);

    //    更改图片
    Map<String, Object> updatePic(Integer id, String pinName);

    Map<String, Object> updateValue(Integer id, String name, Integer price);

    //    根据ID获取菜的价格和minc
    Map<String, Object> findUpdateValue(Integer id);

    void updateBusinessMassage(String name, Integer id, String msg);

    //    修改详细地址
    Map<String, Object> upFullAddress(Integer id, String fullAddress);

    Map<String, Object> upBState(Integer id, String s);

    //    更改商家图片
    Map<String, Object> upBPic(Integer id, String pinName);

    //    订单查询
    List<Orders> findOrders(Integer id, Integer page, Integer rows);

    //    查询订单条数
    int findOrdersCount(Integer id);

    //    出餐
    Map<String, Object> upStateBusinesses(Long id);

    List<Orders> selectOrders(Integer id, Integer page, Integer rows, Integer sate);

    //    查询订单条数
    int selectOrdersCount(Integer id, Integer state);

    public List<BusinessMenu> selectMenuList(Long id);

    int selectMenuListCount(Long id);
}
