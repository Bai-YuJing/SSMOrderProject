package cn.example.order.PoJo;

public class BusinessMenu {
    private Integer menuId;
    private String menu;
    private Integer num;
    private String Classify;

    public String getClassify() {
        return Classify;
    }

    public void setClassify(String Classify) {
        this.Classify = Classify;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}
