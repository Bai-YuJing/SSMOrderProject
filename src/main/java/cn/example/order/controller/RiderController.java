package cn.example.order.controller;

import cn.example.order.PoJo.Orders;
import cn.example.order.PoJo.Rider;
import cn.example.order.service.Impl.RiderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/rider")
public class RiderController {
    @Autowired
    private RiderServiceImpl riderService;

    @RequestMapping("/index")
    public String index(HttpSession session) {
        if (session.getAttribute("rider") != null) {
            return "RiderMassage";
        }
        return "redirect:/login/rider";
    }

    //    获取骑手信息
    @RequestMapping("/rider")
    @ResponseBody
    public Rider getAdmin(HttpSession session) {
        return (Rider) session.getAttribute("admin");
    }

    //    查询所有未取餐订单
    @RequestMapping("/getOrders")
    @ResponseBody
    public List<Orders> getOrders(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                  @RequestParam(value = "limit", defaultValue = "8") Integer rows) {
        System.out.println(page);
        return riderService.getOrders(page, rows);
    }

    @RequestMapping("/getOrdersCount")
    @ResponseBody
    public int getOrdersCount() {
        return riderService.getOrdersCount();
    }

    @RequestMapping("/riderOrder")
    @ResponseBody
    public Map<String, Object> riderOrder(Long id, HttpSession session) {
        Rider rider = (Rider) session.getAttribute("rider");
        return riderService.riderOrder(id, rider.getId());
    }

    @RequestMapping("/getMyOrdersCount")
    @ResponseBody
    public int getMyOrdersCount(HttpSession session) {
        Rider rider = (Rider) session.getAttribute("rider");
        return riderService.getMyOrdersCount(rider.getId());
    }

    @RequestMapping("/getMyOrders")
    @ResponseBody
    public List<Orders> getMyOrders(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                    @RequestParam(value = "limit", defaultValue = "8") Integer rows, HttpSession session) {
        Rider rider = (Rider) session.getAttribute("rider");
        return riderService.getMyOrders(page, rows, rider.getId());
    }

    @RequestMapping("/riderMyOrder")
    @ResponseBody
    public Map<String, Object> riderMyOrder(Long id, Integer state) {
        return riderService.riderMyOrder(id, state);
    }
}
