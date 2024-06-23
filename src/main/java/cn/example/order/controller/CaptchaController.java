package cn.example.order.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.ShearCaptcha;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class CaptchaController {

    @RequestMapping("/captcha.jpg")
    public ResponseEntity<byte[]> captcha(HttpSession session) {
        ShearCaptcha captcha = CaptchaUtil.createShearCaptcha(120, 40, 4, 4);
        byte[] bytes = captcha.getImageBytes();
        session.setAttribute("captcha", captcha.getCode());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.IMAGE_JPEG);
        return new ResponseEntity<>(bytes, httpHeaders, HttpStatus.OK);
    }
}
