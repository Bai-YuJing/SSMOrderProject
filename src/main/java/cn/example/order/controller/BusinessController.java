package cn.example.order.controller;

import cn.example.order.PoJo.*;
import cn.example.order.service.Impl.BusinessServiceImpl;
import com.alibaba.fastjson.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

////////////////////////////////////////////////////////////////////
//                          _ooOoo_                               //
//                         o8888888o                              //
//                         88" . "88                              //
//                         (| ^_^ |)                              //
//                         O\  =  /O                              //
//                      ____/`---'\____                           //
//                    .'  \\|     |//  `.                         //
//                   /  \\|||  :  |||//  \                        //
//                  /  _||||| -:- |||||-  \                       //
//                  |   | \\\  -  /// |   |                       //
//                  | \_|  ''\---/''  |   |                       //
//                  \  .-\__  `-`  ___/-. /                       //
//                ___`. .'  /--.--\  `. . ___                     //
//              ."" '<  `.___\_<|>_/___.'  >'"".                  //
//            | | :  `- \`.;`\ _ /`;.`/ - ` : | |                 //
//            \  \ `-.   \_ __\ /__ _/   .-` /  /                 //
//      ========`-.____`-.___\_____/___.-`____.-'========         //
//                           `=---='                              //
//      ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^        //
//         佛祖保佑       永无BUG     永不修改                  //
////////////////////////////////////////////////////////////////////


@Controller
@RequestMapping("/business")
public class BusinessController {
    @Autowired
    private BusinessServiceImpl businessService;

    @RequestMapping("/index")
    public String index(HttpSession session) {
        if (session.getAttribute("business") == null) {
            return "redirect:/login/business";
        }
        return "BusinessManage";
    }

    //    将商家信息封装成json
    @RequestMapping("/findBusiness")
    @ResponseBody
    public Business findBusiness(HttpSession session) {
        return (Business) session.getAttribute("business");
    }

    @RequestMapping("/classify")
    @ResponseBody
    public Map<String, Object> classify(@RequestParam("id") Integer id,
                                        @RequestParam(value = "page", defaultValue = "1") Integer page,
                                        @RequestParam(value = "limit", defaultValue = "10") Integer rows) {
        List<Classify> list = businessService.findClassify(id, page, rows);
        int count = businessService.findAllClassifyCount(id);
        HashMap<String, Object> map = new HashMap<>();

        map.put("code", 0);
        map.put("msg", "");
        map.put("count", count);
        map.put("data", list);
        return map;
    }

    //    更改分类和菜品名称
    @RequestMapping("/updateName")
    @ResponseBody
    public Map<String, Object> update(@RequestParam("name") String name, @RequestParam Integer id, @RequestParam("table") String table) {
        HashMap<String, Object> map = new HashMap<>();
        if (businessService.updateClassify(name, id, table) != 0) {
            map.put("msg", "success");
            return map;
        }
        map.put("msg", "error");
        return map;
    }

    //    添加分类
    @RequestMapping("/addClassify")
    @ResponseBody
    public Map<String, Object> addClassify(String name, Integer id) {
        HashMap<String, Object> map = new HashMap<>();
        if (businessService.addClassify(name, id) != 0) {
            map.put("msg", "success");
            return map;
        }
        map.put("msg", "error");
        return map;
    }

    //    删除分类、菜品
    @RequestMapping("/delete")
    @ResponseBody
    public Map<String, Object> delete(String table, Integer id) {
        HashMap<String, Object> map = new HashMap<>();
//        如果分类下有菜品，不能删除
        if (businessService.findClassifyCount(id) != 0) {
            map.put("msg", "该分类下有菜品，不能删除");
            return map;
        }
        if (businessService.delete(table, id) != 0) {
            map.put("msg", "success");
            return map;
        }
        map.put("msg", "error");
        return map;
    }

    //    添加菜品 将菜品的分类信息保存到session
    @GetMapping("/addMenu")
    public String addMenu(@RequestParam("classifyId") Integer classifyId, HttpSession session) {
        session.setAttribute("classifyId", classifyId);
//        System.out.println(classifyId);
        return "addMenu";
    }

    @PostMapping("/addMenu")
    public String addMenu(String name, Integer price, HttpSession session) {
        Integer classifyId = (Integer) session.getAttribute("classifyId");
        String pic = (String) session.getAttribute("pic");
        Business business = (Business) session.getAttribute("business");
        Menu menu = new Menu();
        menu.setName(name);
        menu.setPrice(price);
        menu.setPic(pic);
        menu.setClassifyId(classifyId);
        menu.setBusinessId(business.getId());
        System.out.println(JSONArray.toJSONString(menu));
        businessService.addMenu(menu);
//        删除session
        session.removeAttribute("pic");
        return "/addMenu";
    }

    //     上传菜品图片
    @ResponseBody
    @RequestMapping("/addPic")
    public Map<String, Object> addPic(@RequestParam("file") MultipartFile file, HttpServletRequest request, HttpSession session) throws IOException {
        Business business = (Business) session.getAttribute("business");

//       创建名为商家名称的文件夹
        File path = new File(request.getSession().getServletContext().getRealPath("/pages/img/menu/") + business.getStoreName());
        if (!path.exists()) { //如果不存在
            boolean dr = path.mkdirs(); //创建目录
        }
//        上传
        file.transferTo(new File(path, file.getOriginalFilename()));
/*        System.out.println(path);
        System.out.println(file.getOriginalFilename());*/
        HashMap<String, Object> map = new HashMap<>();
        map.put("msg", "上传成功");
        session.setAttribute("pic", Objects.requireNonNull(file.getOriginalFilename()));
        return map;
    }

    @RequestMapping("/findMenu")
    @ResponseBody
    public List<Menu> findMenu(@RequestParam("id") Integer id,
                               @RequestParam(value = "page", defaultValue = "1") Integer page,
                               @RequestParam(value = "limit", defaultValue = "5") Integer rows, HttpSession session) {
        Business business = (Business) session.getAttribute("business");
        return businessService.findMenu(id, page, rows, business.getStoreName());
    }

    @RequestMapping("/findMenuCount")
    @ResponseBody
    public int findMenuCount(HttpSession session) {
        Business business = (Business) session.getAttribute("business");
        return businessService.findMenuCount(business.getId());
    }

    @RequestMapping("/updateState")
    @ResponseBody
    public Map<String, Object> updateState(Integer id, String state) {
        System.out.println(id + state);
        return businessService.updateState(id, state);
    }

    @
            RequestMapping("/deleteMenu")
    @ResponseBody
    public Map<String, Object> deleteMenu(Integer id) {
        return businessService.deleteMenu(id);
    }

    @RequestMapping("/updatePicP")
    @ResponseBody
    public Map<String, Object> updatePicP(@RequestParam("file") MultipartFile file, HttpServletRequest request, HttpSession session) throws IOException {
//       查找商家存放图片的文件夹
        Business business = (Business) session.getAttribute("business");
//        获取Id
        int id = (int) session.getAttribute("id");
        File path = new File(request.getSession().getServletContext().getRealPath("/pages/img/menu/") + business.getStoreName());
        System.out.println("111" + path);
        if (!path.exists()) { //如果不存在
            boolean dr = path.mkdirs(); //创建目录
        }
//        上传
        file.transferTo(new File(path, file.getOriginalFilename()));
        return businessService.updatePic(id, file.getOriginalFilename());
    }

    //    将菜品信息保存到session
    @RequestMapping("/updatePic")
    public String updatePic(@RequestParam("id") Integer id, HttpSession session) {
        session.setAttribute("id", id);
//        System.out.println(classifyId);
        return "updatePic";
    }

    //获取菜品ID
    @GetMapping("/updateValue")
    public String updateValue(Integer id, HttpSession session) {
        session.setAttribute("id", id);
        return "updateValue";
    }

    //    修改价格菜名
    @PostMapping("/updateValue")
    @ResponseBody
    public Map<String, Object> updateValue(String name, Integer price, HttpSession session) {
        Integer id = (Integer) session.getAttribute("id");
        Map<String, Object> map = businessService.updateValue(id, name, price);
        session.removeAttribute("id");
        return map;
    }

    @RequestMapping("/findUpdateValue")
    @ResponseBody
    public Map<String, Object> findUpdateValue(HttpSession session) {
        Integer id = (Integer) session.getAttribute("id");
        return businessService.findUpdateValue(id);
    }

    @RequestMapping("/businessMassage")
    @ResponseBody
    public String businessMassage(HttpSession session) {
        return JSONArray.toJSONString(session.getAttribute("business"));
    }

    @RequestMapping("/updateBusinessMassage")
    public String updateBusinessMassage(String name, Integer id, String msg) {
        businessService.updateBusinessMassage(name, id, msg);
        return "BusinessMassage";
    }

    @RequestMapping("/upFullAddress")
    @ResponseBody
    public Map<String, Object> upFullAddress(Integer id, String fullAddress) {
        return businessService.upFullAddress(id, fullAddress);
    }

    @RequestMapping("/upBState")
    @ResponseBody
    public Map<String, Object> upBState(String s, Integer id) {
        return businessService.upBState(id, s);
    }

    @RequestMapping("/upBPic")
    @ResponseBody
    public Map<String, Object> upBPic(@RequestParam("file") MultipartFile file, HttpServletRequest request, HttpSession session) throws IOException {
        System.out.println(file);
        //       查找商家存放图片的文件夹
        Business business = (Business) session.getAttribute("business");
        File path = new File(request.getSession().getServletContext().getRealPath("/pages/img/business/") + business.getStoreName());

        if (!path.exists()) { //如果不存在
            boolean dr = path.mkdirs(); //创建目录
        }
//        上传
        file.transferTo(new File(path, file.getOriginalFilename()));
        return businessService.upBPic(business.getId(), file.getOriginalFilename());
    }

    @RequestMapping("/findOrders")
    @ResponseBody
    public List<Orders> findOrders(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                   @RequestParam(value = "limit", defaultValue = "6") Integer rows, HttpSession session) {
        Business business = (Business) session.getAttribute("business");
        return businessService.findOrders(business.getId(), page, rows);
    }

    @RequestMapping("/findOrderCount")
    @ResponseBody
    public int findOrderCount(HttpSession session) {
        Business business = (Business) session.getAttribute("business");
        return businessService.findOrdersCount(business.getId());
    }

    @RequestMapping("/upStateBusinesses")
    @ResponseBody
    public Map<String, Object> upStateBusinesses(Long id) {
        return businessService.upStateBusinesses(id);
    }

    @RequestMapping("/selectOrders")
    @ResponseBody
    public List<Orders> selectOrders(Integer state, @RequestParam(value = "page", defaultValue = "1") Integer page,
                                     @RequestParam(value = "limit", defaultValue = "6") Integer rows, HttpSession session) {
        Business business = (Business) session.getAttribute("business");
        return businessService.selectOrders(business.getId(), page, rows, state);
    }

    @RequestMapping("/selectOrdersCount")
    @ResponseBody
    public int selectOrdersCount(Integer state, HttpSession session) {
        Business business = (Business) session.getAttribute("business");
        return businessService.selectOrdersCount(business.getId(), state);
    }

    @RequestMapping("/selectMenu")
    private String selectMenu(Long id, HttpSession session) {
        session.setAttribute("orderId", id);
        return "selectMenu";
    }

    @RequestMapping("/selectMenuList")
    @ResponseBody
    private List<BusinessMenu> selectMenulist(HttpSession session) {
        Long id = (Long) session.getAttribute("orderId");
        return businessService.selectMenuList(id);
    }

}
