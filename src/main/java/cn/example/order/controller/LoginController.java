package cn.example.order.controller;

import cn.example.order.PoJo.Admin;
import cn.example.order.PoJo.Business;
import cn.example.order.PoJo.Rider;
import cn.example.order.PoJo.User;
import cn.example.order.service.Impl.AdminServiceImpl;
import cn.example.order.service.Impl.BusinessServiceImpl;
import cn.example.order.service.Impl.RiderServiceImpl;
import cn.example.order.service.Impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private BusinessServiceImpl businessService;
    @Autowired
    private AdminServiceImpl adminService;
    @Autowired
    private RiderServiceImpl riderService;

    @GetMapping("/user")
    public String login(HttpSession session) {
        session.removeAttribute("user");
        return "UserLogin";
    }

    @PostMapping("/user")
    public String login(String account, String password, String captcha, Model model, HttpSession session) {
//        获取验证码
        String RealCaptcha = (String) session.getAttribute("captcha");
        if (captcha.equalsIgnoreCase(RealCaptcha)) {
            User user = userService.login(account, password);
            if (user != null) {
//            登陆成功
                session.setAttribute("user", user);
                return "redirect:/user/index";
            }
            model.addAttribute("Error", "用户名或密码错误");
            return "UserLogin";
        }
        // 验证码的值从session删掉
        session.removeAttribute("vcode");
        model.addAttribute("Error", "验证码错误");
        return "UserLogin";
    }

    @GetMapping("/business")
    public String business(HttpSession session) {
        session.removeAttribute("business");
        return "BusinessLogin";
    }

    @PostMapping("/business")
    public String business(String account, String password, String captcha, Model model, HttpSession session) {
//        System.out.println(account);
        //        获取验证码
        String RealCaptcha = (String) session.getAttribute("captcha");
        if (captcha.equalsIgnoreCase(RealCaptcha)) {
            Business business = businessService.login(account, password);
            if (business != null) {
//            登陆成功
                session.setAttribute("business", business);
                return "redirect:/business/index";
            }
            model.addAttribute("Error", "用户名或密码错误");
            return "BusinessLogin";
        }
        // 验证码的值从session删掉
        session.removeAttribute("vcode");
        model.addAttribute("Error", "验证码错误");
        return "BusinessLogin";
    }

    @GetMapping("/admin")
    public String admin(HttpSession session) {
        session.removeAttribute("admin");
        return "AdminLogin";
    }

    @PostMapping(value = "/admin")
    public String admin(String account, String password, String captcha, Model model, HttpSession session) {
        System.out.println(account);
        //        获取验证码
        String RealCaptcha = (String) session.getAttribute("captcha");
        if (captcha.equalsIgnoreCase(RealCaptcha)) {
            Admin admin = adminService.login(account, password);
            if (admin != null) {
//            登陆成功
                session.setAttribute("admin", admin);
                return "redirect:/admin/index";
            }
            model.addAttribute("Error", "用户名或密码错误");
            return "AdminLogin";
        }
        // 验证码的值从session删掉
        session.removeAttribute("vcode");
        model.addAttribute("Error", "验证码错误");
        return "AdminLogin";
    }

    @GetMapping("/rider")
    public String rider(HttpSession session) {
        session.removeAttribute("rider");
        return "riderLogin";
    }

    @PostMapping(value = "/rider")
    public String rider(String account, String password, String captcha, Model model, HttpSession session) {
        //        获取验证码
        String RealCaptcha = (String) session.getAttribute("captcha");
        if (captcha.equalsIgnoreCase(RealCaptcha)) {
            Rider rider = riderService.login(account, password);
            if (rider != null) {
//            登陆成功
                session.setAttribute("rider", rider);

                return "redirect:/rider/index";
            }
            model.addAttribute("Error", "用户名或密码错误");
            return "riderLogin";
        }
        // 验证码的值从session删掉
        session.removeAttribute("vcode");
        model.addAttribute("Error", "验证码错误");
        return "riderLogin";
    }
}
