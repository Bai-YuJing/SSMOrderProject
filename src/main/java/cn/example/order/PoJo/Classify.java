package cn.example.order.PoJo;

public class Classify {
    private Integer id;
    private String name;
    private Integer businessId;
    private Integer menuCount;
    private String addtime;

    public String getAddtime() {
        return addtime;
    }

    public void setAddtime(String addtime) {
        this.addtime = addtime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Integer businessId) {
        this.businessId = businessId;
    }

    public Integer getMenuCount() {
        return menuCount;
    }

    public void setMenuCount(Integer menuCount) {
        this.menuCount = menuCount;
    }
}
