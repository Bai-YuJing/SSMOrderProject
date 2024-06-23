package cn.example.order.controller;

import cn.example.order.PoJo.Business;
import cn.example.order.PoJo.Rider;
import cn.example.order.PoJo.User;
import cn.example.order.service.Impl.BusinessServiceImpl;
import cn.example.order.service.Impl.RiderServiceImpl;
import cn.example.order.service.Impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/register")
public class RegisterController {
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private BusinessServiceImpl businessService;
    @Autowired
    private RiderServiceImpl riderService;

    @GetMapping("/user")
    public String registerUser() {
        return "UserRegister";
    }

    @PostMapping("/user")
    public String registerUser(@ModelAttribute User user, HttpSession session, String captcha, Model model) {
        String RealCaptcha = (String) session.getAttribute("captcha");
        if (captcha.equalsIgnoreCase(RealCaptcha)) {
            userService.register(user);
            return "redirect:/login/user";
        }
        model.addAttribute("Error", "验证码错误");
        return "UserRegister";

    }

    @GetMapping("/business")
    public String register() {
        return "BusinessRegister";
    }

    @PostMapping("/business")
    public String register(@ModelAttribute Business business) {
        businessService.register(business);
        return "redirect:/login/business";
    }

    @GetMapping("/rider")
    public String rider() {
        return "riderRegist";
    }

    @PostMapping("/rider")
    public String rider(@ModelAttribute Rider rider) {
        riderService.register(rider);
        return "redirect:/login/rider";
    }
}
