package cn.example.order.Dao;

import cn.example.order.PoJo.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface BusinessMapper {
    void register(Business business);

    Business login(@Param("account") String account, @Param("password") String password);

    List<Classify> findClassify(@Param("id") Integer id, @Param("page") Integer page, @Param("rows") Integer rows);

    //    获取该分类的菜品数量
    int findClassifyCount(Integer id);

    //    获取分类数
    int findAllClassifyCount(Integer id);

    //    修改分类
    int updateClassify(@Param("name") String name, @Param("id") Integer id, @Param("table") String table);

    //    添加分类
    int addClassify(Map<String, Object> map);

    //    删除分类、菜品
    int delete(@Param("table") String table, @Param("id") Integer id);

    //    添加菜品
    int addMenu(Menu menu);

    //    查找菜品
    List<Menu> findMenu(@Param("id") Integer id, @Param("page") Integer page, @Param("rows") Integer rows);

    //    查找菜品数量
    int findMenuCount(Integer id);

    String findClassifyName(Integer id);

    //    修改菜品状态
    int updateMenuState(@Param("id") Integer id, @Param("state") String state);

    //  删除
    int deleteMenu(Integer id);

    //    修改图片
    int updatePic(@Param("id") Integer id, @Param("name") String pinName);

    //     修改菜品价格、名称
    int updateValue(@Param("id") Integer id, @Param("name") String name, @Param("price") Integer price);

    //    根据ID获取菜的价格、名称
    Menu findUpdateValue(Integer id);

    //    更新商家信息
    public int updateBusinessMassageStoreName(@Param("id") Integer id, @Param("msg") String msg);

    public int updateBusinessMassageAddressId(@Param("id") Integer id, @Param("msg") Integer msg);

    //    修改详细地址
    int upFullAddress(@Param("id") Integer id, @Param("fullAddress") String fullAddress);

    //    修改状态
    int upBState(@Param("id") Integer id, @Param("fullAddress") String s);

    int upBPic(@Param("id") Integer id, @Param("name") String pinName);

    //    查询商家订单
    List<Orders> findOrders(@Param("id") Integer id, @Param("page") Integer page, @Param("rows") Integer rows);

    int findOrdersCount(Integer id);

    //    出餐
    int upStateBusinesses(Long id);

    //    根据状态查询用户
    List<Orders> selectOrders(@Param("id") Integer id, @Param("page") Integer page, @Param("rows") Integer rows, @Param("state") Integer state);

    //    查询该状态记录数
    int selectOrdersCount(@Param("id") Integer id, @Param("state") Integer state);

    //      查看订单详情
    List<BusinessMenu> selectMenuList(Long id);

    String selectMenuName(Integer id);

    int selectMenuListCount(Long id);

    int selectClsid(Integer id);

    String selectClsName(Integer id);
}
