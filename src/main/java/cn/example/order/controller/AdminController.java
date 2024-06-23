package cn.example.order.controller;

import cn.example.order.PoJo.Admin;
import cn.example.order.PoJo.Business;
import cn.example.order.PoJo.Rider;
import cn.example.order.PoJo.User;
import cn.example.order.service.Impl.AdminServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminServiceImpl adminService;

    @ResponseBody
//    同时传管理员信息参数
    @RequestMapping("/admin")
    public Admin getAdmin(HttpSession session) {
        Admin admin = (Admin) session.getAttribute("admin");
        return admin;
    }

    //    管理员首页
    @RequestMapping("/index")
    public String index(HttpSession session) {
        if (session.getAttribute("admin") != null) {

            return "AdminManage";
        }
        return "redirect:/login/admin";

    }

    @RequestMapping("/findUser")
    @ResponseBody
    public Map<String, Object> findUser(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                        @RequestParam(value = "limit", defaultValue = "10") Integer rows) {

        Integer count = adminService.findCountUser();
        List<User> table = adminService.findUser(page, rows);
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("msg", "");
        map.put("count", count);
        map.put("data", table);
        return map;
    }

    //    查询商家、用户、骑手
    @RequestMapping("/findBusiness")
    @ResponseBody
    public Map<String, Object> findBusiness(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                            @RequestParam(value = "limit", defaultValue = "10") Integer rows) {

        Integer count = adminService.findCountBusiness();
        List<Business> table = adminService.findBusiness(page, rows);
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("msg", "");
        map.put("count", count);
        map.put("data", table);
        return map;
    }

    @RequestMapping("/findRider")
    @ResponseBody
    public Map<String, Object> findRider(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                         @RequestParam(value = "limit", defaultValue = "10") Integer rows) {

        Integer count = adminService.findCountRider();
        List<Rider> table = adminService.findRider(page, rows);

        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("msg", "");
        map.put("count", count);
        map.put("data", table);
        return map;
    }

    //    修改用户、商家、骑手状态
    @RequestMapping("/state")
    @ResponseBody
    public Map<Object, String> state(Integer id, String table) {
        HashMap<Object, String> map = new HashMap<>();
        if (adminService.state(id, table) != 0) {
            map.put("msg", "success");
            return map;
        }
        map.put("msg", "error");
        return map;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Map<Object, String> delete(Integer id, String table) {
        HashMap<Object, String> map = new HashMap<>();
        if (adminService.delete(id, table) != 0) {
            map.put("msg", "success");
            return map;
        }
        map.put("msg", "error");
        return map;
    }
}
