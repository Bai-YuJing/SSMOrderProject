

# OrderProject

#  **基于SSM的外卖系统**

## 1 技术介绍

### 1.1 LayUi

Layui 是一套免费的开源 Web UI 组件库，采用自身轻量级模块化规范，遵循原生态的 HTML/CSS/JavaScript 开发模式，极易上手，拿来即用。其风格简约轻盈，而内在雅致丰盈，甚至包括文档在内的每一处细节都经过精心雕琢，非常适合网页界面的快速构建。Layui 区别于一众主流的前端框架，却并非逆道而行，而是信奉返璞归真之道。确切地说，它更多是面向于追求简单的务实主义者，即无需涉足各类构建工具，只需面向浏览器本身，便可将页面所需呈现的元素与交互信手拈来。

### 1.2 Spring

Spring是Java EE编程领域的一个轻量级开源框架，该框架由一个叫Rod Johnson的程序员在 2002 年最早提出并随后创建，是为了解决企业级编程开发中的复杂性，实现敏捷开发的应用型框架 。 [2]Spring是一个开源容器框架，它集成各类型的工具，通过核心的Bean factory实现了底层的类的实例化和生命周期的管理。在整个框架中，各类型的功能被抽象成一个个的 Bean，这样就可以实现各种功能的管理，包括动态加载和切面编程。 [3]Spring是独特的，因为若干个原因：

### 1.3 Spring MVC

Spring MVC属于SpringFrameWork的后续产品，已经融合在Spring Web Flow里面。Spring 框架提供了构建 Web 应用程序的全功能 MVC 模块。使用 Spring 可插入的 MVC 架构，从而在使用Spring进行WEB开发时，可以选择使用Spring的Spring MVC框架或集成其他MVC开发框架。

### 1.4 MyBatis

MyBatis 是一款优秀的持久层框架，它支持定制化 SQL、存储过程以及高级映射。MyBatis 避免了几乎所有的 JDBC 代码和手动设置参数以及获取结果集。MyBatis 可以使用简单的 XML 或注解来配置和映射原生信息，将接口和 Java 的 POJOs(Plain Ordinary Java Object,普通的 Java对象)映射成数据库中的记录。

### 1.5 Vue

Vue是一款用于构建用户界面的 JavaScript 框架。它基于标准 HTML、CSS 和 JavaScript 构建，并提供了一套声明式的、组件化的编程模型，可以高效地开发用户界面。无论是简单还是复杂的界面，Vue 都可以胜任。

Vue是一个独立的社区驱动的项目，它是由尤雨溪在2014年作为其个人项目创建， [4]是一个成熟的、经历了无数实战考验的框架，它是目前生产环境中使用最广泛的JavaScript框架之一，可以轻松处理大多数web应用的场景，并且几乎不需要手动优化，并且Vue完全有能力处理大规模的应用。 

### 1.6 MVC

MVC模式中，M是指模型，V是视图，C则是控制器。模型（Model）：模型是应用程序的核心部分，负责管理数据和业务逻辑。它直接与数据库交互，检索数据并处理前端的命令。视图（View）：视图是用户界面的部分，负责将数据以图形界面的形式展示给用户。它仅仅展示数据，不包含业务逻辑处理。控制器（Controller）：控制器作为模型和视图之间的中介，处理用户的输入，将命令传递给模型，并选择视图来显示模型的数据。

## 2 界面设计

### 2.1 首页界面

![首页](I:\ZXW\SSM\code\实训\报告\截图\首页.png)

### 2.2用户界面

![点单界面](I:\ZXW\SSM\code\实训\报告\截图\点单界面.png)

### 2.3商家界面

![商家主页](I:\ZXW\SSM\code\实训\报告\截图\商家主页.png)

### 2.4 管理员界面

![骑手管理](I:\ZXW\SSM\code\实训\报告\截图\骑手管理.png)

### 2.5 订单查询（骑手）

![image-20240623182141753](C:\Users\ZXW\AppData\Roaming\Typora\typora-user-images\image-20240623182141753.png)

### 2.6 订单查询（商家）

![image-20240623182209912](C:\Users\ZXW\AppData\Roaming\Typora\typora-user-images\image-20240623182209912.png)

## 3 概要设计

系统主要涉及到游客、用户、骑手、商家和管理员3种用户。

### 3.1 E-R图

![image-20240623181921000](C:\Users\ZXW\AppData\Roaming\Typora\typora-user-images\image-20240623181921000.png)

### 3.2 数据字典

表**3.1** 管理员信息表

| **字段名** | **数据类型** | **默认值** | **允许非空** | **自动递增** | **备注**                 |
| ---------- | ------------ | ---------- | ------------ | ------------ | ------------------------ |
| id         | int(10)      |            | NO           | 是           | 编号                     |
| name       | varchar(255) |            | NO           |              | 用户名                   |
| account    | varchar(255) |            | NO           |              | 账号                     |
| password   | varchar(255) |            | NO           |              | 密码（password函数加密） |

表**3.2** 用户信息表

| **字段名** | **数据类型** | **默认值** | **允许非空** | **自动递增** | **备注** |
| ---------- | ------------ | ---------- | ------------ | ------------ | -------- |
| id         | int(10)      |            | NO           | 是           | 用户Id   |
| username   | varchar(255) |            | NO           |              | 用户名   |
| account    | varchar(255) |            | NO           |              | 账号     |
| password   | varchar(255) |            | NO           |              | 密码     |
| birthday   | Date         |            | NO           |              | 生日     |
| state      | varchar(255) | Y          | NO           |              | 状态     |

  

表**3.3** 商家信息表

| 字段名       | 数据类型     | 默认值 | 允许非空 | 自动递增 | 备注     |
| ------------ | ------------ | ------ | -------- | -------- | -------- |
| id           | int(10)      |        | NO       | 是       | 商家ID   |
| store-name   | varchar(100) |        | NO       |          | 店名     |
| account      | varchar(100) |        | NO       |          | 账号     |
| pic          | varchar(255) | N      | NO       |          | 图片名   |
| password     | varchar(200) |        | NO       |          | 密码     |
| address      | int(10)      |        | NO       |          | 地址     |
| full_address | varchar(255) |        | NO       |          | 详细地址 |
| state        | varchar(10)  | Y      | NO       |          | 状态     |

表**3.4** 骑手信息表

| **字段名** | **数据类型** | **默认值** | **允许非空** | **自动递增** | **备注** |
| ---------- | ------------ | ---------- | ------------ | ------------ | -------- |
| id         | int(11)      |            | NO           | 是           | 骑手ID   |
| account    | varchar(100) |            | NO           |              | 账号     |
| password   | varchar(200) |            | NO           |              | 密码     |
| phone      | bigint (255) |            | NO           |              | 手机号   |
| name       | varchar(100) |            | NO           |              | 姓名     |
| state      | varchar(10)  | Y          | NO           |              | 状态     |

 

 

表**3.5** 分类信息表

| **字段名**  | **数据类型** | **默认值**        | **允许非空** | **自动递增** | **备注** |
| ----------- | ------------ | ----------------- | ------------ | ------------ | -------- |
| id          | int(11)      |                   | NO           | 是           | 分类ID   |
| name        | varchar(255) |                   | NO           |              | 分类名   |
| business_id | int(11)      |                   | NO           |              | 商家ID   |
| addtime     | datetime     | CURRENT_TIMESTAMP | NO           |              | 添加时间 |

 

表**3.6** 菜品信息表

| **字段名**  | **数据类型** | **默认值**        | **允许非空** | **自动递增** | **备注** |
| ----------- | ------------ | ----------------- | ------------ | ------------ | -------- |
| id          | int(11)      |                   | NO           | 是           | 菜品ID   |
| name        | varchar(100) |                   | NO           |              | 菜品名   |
| price       | int(11)      |                   | NO           |              | 价格     |
| pic         | varchar(100) | N                 | NO           |              | 图片名   |
| classify_id | int(11)      |                   | NO           |              | 分类ID   |
| business_id | int(11)      |                   | NO           |              | 商家ID   |
| state       | varchar(10)  | Y                 | NO           |              | 状态     |
| addtime     | datetime     | CURRENT_TIMESTAMP | NO           |              | 添加时间 |

 

表**3.7** 用户地址表

| **字段名**  | **数据类型** | **默认值**        | **允许非空** | **自动递增** | **备注** |
| ----------- | ------------ | ----------------- | ------------ | ------------ | -------- |
| id          | int(11)      |                   | NO           | 是           | 菜品ID   |
| name        | varchar(100) |                   | NO           |              | 菜品名   |
| price       | int(11)      |                   | NO           |              | 价格     |
| pic         | varchar(100) |                   | NO           |              | 图片名   |
| classify_id | int(11)      |                   | NO           |              | 分类ID   |
| business_id | int(11)      |                   | NO           |              | 商家ID   |
| state       | varchar(10)  |                   | NO           |              | 状态     |
| addtime     | datetime     | CURRENT_TIMESTAMP | NO           |              | 添加时间 |

 

表**3.8** 订单表

| **字段名**       | **数据类型** | **默认值**        | **允许非空** | **自动递增** | **备注** |
| ---------------- | ------------ | ----------------- | ------------ | ------------ | -------- |
| id               | bigint (100) |                   | NO           | 是           | 订单ID   |
| price            | int(10)      |                   | NO           |              | 价格     |
| order_time       | datetime     | CURRENT_TIMESTAMP | NO           |              | 下单时间 |
| user_id          | int(11)      |                   | NO           |              | 用户ID   |
| business_id      | int(11)      |                   | NO           |              | 商家ID   |
| user_address     | varchar(255) |                   | NO           |              | 用户地址 |
| business_address | varchar(255) |                   | NO           |              | 商家地址 |
| state            | int(11)      | 1                 | NO           |              | 状态     |
| rider_id         | int(11)      |                   | YES          |              | 骑手ID   |

表**3.9** 订单详情表

| **字段名** | **数据类型** | **默认值** | **允许非空** | **自动递增** | **备注** |
| ---------- | ------------ | ---------- | ------------ | ------------ | -------- |
| id         | int(11)      |            | NO           | 是           | ID       |
| order_id   | Bigint(100)  |            | NO           |              | 订单ID   |
| menu_id    | int(11)      |            | NO           |              | 菜品ID   |
| num        | int(11)      |            | NO           |              | 数量     |



表**3.10** 省市区表

| **字段名** | **数据类型** | **默认值** | **允许非空** | **自动递增** | **备注**   |
| ---------- | ------------ | ---------- | ------------ | ------------ | ---------- |
| id         | int(11)      |            | NO           | 是           | 区划信息   |
| pid        | int(11)      |            | YES          |              | 父级挂接id |
| code       | varchar(255) |            | YES          |              | 区划编码   |
| name       | varchar(255) |            | YES          |              | 区划名称   |
| father_id  | int(11)      |            | YES          |              | 父级ID     |
| level      | Tinyint(1)   |            | YES          |              | 级次ID     |

## 5 文档结构图 

系统开发框架主要以SSM为主，SSM框架是标准的MVC，将整个系统划分为表示层、控制层、服务层、数据库访问层4层，使用Spring实现业务对象管理， SpringMVC负责请求的转发和视图管理，Mybatis作为数据对象持久化引擎。

![电脑萤幕画面  描述已自动生成](file:///C:/Users/ZXW/AppData/Local/Temp/msohtmlclip1/01/clip_image002.gif)

文档结构图中其中的POJO包为表示层，里面定义了一个个类，每个类有对应数据库的必要属性，在该包中也定义了GET和SET方法。DAO包为数据库访问层，其包里面主要分为两类，一类是xxMapper.java类，定义了对数据库进行操作的方法名；另一类是xxMapper.xml类，在该xml中，主要完成对Mapper.java的映射以及对数据库操作的具体sql语句。Service层为服务层，在该层中，主要是返回调用DAO层方法后的一个结果。

Controller层为控制层，在该层中用于获取在前台界面传来的数据，并通过该数据在数据库里面进行查询，最后将查询到的结果通过Ajax返回给前台页面，在前台页面进行展示。

## 6 部分代码

```java
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
```



## 7 联系方式

1522993472@qq.com

zhouxiongwei346@outlook.com
