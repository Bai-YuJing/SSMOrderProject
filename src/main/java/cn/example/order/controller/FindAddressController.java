package cn.example.order.controller;

import cn.example.order.PoJo.Address;
import cn.example.order.service.Impl.AddressServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/address")
public class FindAddressController {
    @Autowired
    private AddressServiceImpl addressService;

    @RequestMapping("/province")
    @ResponseBody
    public Map<String, Object> findProvince() {
        HashMap<String, Object> map = new HashMap<>();
        List<Address> province = addressService.findProvince();
        map.put("data", province);
        return map;
    }

    @RequestMapping("/city")
    @ResponseBody
    public Map<String, Object> findCity(Integer pid) {
        HashMap<String, Object> map = new HashMap<>();
        List<Address> city = addressService.findCity(pid);
        map.put("data", city);
        return map;
    }

    @RequestMapping("/district")
    @ResponseBody
    public Map<String, Object> findDistrict(Integer pid) {
        HashMap<String, Object> map = new HashMap<>();
        List<Address> city = addressService.findDistrict(pid);
        map.put("data", city);
        return map;
    }


}
