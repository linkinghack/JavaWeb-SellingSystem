package com.linkinghack.SellingSystem.meta;

/**
 * Created by linking on 16-7-14.
 */
public class Buy {
    private int id;
    private String title;
    private String icon;
    private long buyPrice;
    private long buyNum;
    private long buyTime;
    private int total;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public long getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(long buyPrice) {
        this.buyPrice = buyPrice;
    }

    public long getBuyNum() {
        return buyNum;
    }

    public void setBuyNum(long buyNum) {
        this.buyNum = buyNum;
    }

    public long getBuyTime() {
        return buyTime;
    }

    public void setBuyTime(long buyTime) {
        this.buyTime = buyTime;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
