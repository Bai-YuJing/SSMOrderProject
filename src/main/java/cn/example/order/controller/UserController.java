package cn.example.order.controller;

import cn.example.order.PoJo.*;
import cn.example.order.service.Impl.AddressServiceImpl;
import cn.example.order.service.Impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private AddressServiceImpl addressService;

    @RequestMapping("/index")
    public String index(HttpSession session) {
        if (session.getAttribute("user") != null) {

            return "userIndex";
        }
        return "redirect:/login/user";
    }

    //  显示所以商家
    @RequestMapping("/findBusiness")
    @ResponseBody
    public List<UserBusiness> findBusiness() {
        return userService.findBusiness();
    }

    //    显示该商家的分类
    @RequestMapping("/businessMenu")
    public String businessMenu(Integer id, HttpSession session) {
        session.setAttribute("id", id);
        return "businessMenu";
    }

    @RequestMapping("/findBusinessCls")
    @ResponseBody
    public List<UserClassify> findBusinessMenu(HttpSession session) {
        Integer id = (Integer) session.getAttribute("id");
        return userService.findBusinessCls(id);
    }

    @RequestMapping("/findMenu")
    @ResponseBody
    public List<UserMenu> findMenu(Integer id) {
        return userService.findMenu(id);
    }

    @RequestMapping("/order")
    public String order(String[] uMenu, Integer price, HttpSession session) {
        session.setAttribute("uMenu", uMenu);
        session.setAttribute("price", price);
        return "pay";
    }

    //    支付
    @RequestMapping("/pay")
    public String pay(HttpSession session) {
        String[] uMenu = (String[]) session.getAttribute("uMenu");
        Integer price = (Integer) session.getAttribute("price");
        User user = (User) session.getAttribute("user");
        Integer businessId = (Integer) session.getAttribute("id");
        int pay = userService.pay(uMenu, price, user.getId(), businessId);
        if (pay == 0) {
            session.setAttribute("pay", pay);
            return "redirect:/user/addAddress";
        }
        return "redirect:/user/index";
    }

    @RequestMapping("/userMs")
    public String userMs() {
        return "userMs";
    }

    @RequestMapping("/userOrder")
    public String userOrder() {
        return "UserOrder";
    }

    @RequestMapping("/userAddress")
    public String UserAddress() {
        return "UserAddress";
    }

    @RequestMapping("/addAddress")
    public String addAddress() {
        return "AddAddress";
    }

    //    获取省
    @RequestMapping("/findAddress")
    @ResponseBody
    public List<Address> findAddress() {
        return addressService.findProvince();
    }

    //    获取市列表
    @RequestMapping("/findCityList")
    @ResponseBody
    public List<Address> findCityList(HttpSession session) {
        Integer ProvinceId = (Integer) session.getAttribute("ProvinceId");
        return addressService.findCity(ProvinceId);
    }

    @RequestMapping("/findCity")
    public String findCity(Integer id, HttpSession session) {
        session.setAttribute("ProvinceId", id);
        return "FindCity";
    }

    //    获取区
    @RequestMapping("/findDistrict")
    public String findDistrict(Integer id, HttpSession session) {
        session.setAttribute("cityId", id);
        return "FindDistrict";
    }

    @RequestMapping("/findDistrictList")
    @ResponseBody
    public List<Address> findDistrictList(HttpSession session) {
        Integer id = (Integer) session.getAttribute("cityId");
        return addressService.findDistrict(id);
    }

    @RequestMapping("/addAddressMs")
    public String addAddressMs(Integer id, HttpSession session) {
        session.setAttribute("districtId", id);
        return "AddAddressMs";
    }

    @RequestMapping("/successAdd")
    public String successAdd(String username, String phonenumber, String fullAddress, HttpSession session) {
        Integer provinceId = (Integer) session.getAttribute("ProvinceId");
        Integer cityId = (Integer) session.getAttribute("cityId");
        Integer districtId = (Integer) session.getAttribute("districtId");
        User user = (User) session.getAttribute("user");
        userService.addAddress(user.getId(), username, phonenumber, fullAddress, provinceId, cityId, districtId);
        return "redirect:/user/userAddress";
    }

    @RequestMapping("/findUserAddress")
    @ResponseBody
    public List<UserAddress> findUserAddress(HttpSession session) {
        User user = (User) session.getAttribute("user");
        return userService.findUserAddress(user.getId());
    }

    @RequestMapping("/setAddress")
    public String setAddress(Integer id, HttpSession session) {
        User user = (User) session.getAttribute("user");
        userService.setAddress(id, user.getId());
        return "redirect:/user/userAddress";
    }

    @RequestMapping("/getPrice")
    @ResponseBody
    public Integer getPrice(HttpSession session) {
        return (Integer) session.getAttribute("price");
    }

    @RequestMapping("/findOrder")
    @ResponseBody
    public List<Orders> findOreder(HttpSession session) {
        User user = (User) session.getAttribute("user");
        return userService.findOrder(user.getId());
    }
}
