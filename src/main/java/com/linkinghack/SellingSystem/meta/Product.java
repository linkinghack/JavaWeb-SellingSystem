package com.linkinghack.SellingSystem.meta;

public class Product {
	private int id;
	private String title;
	private String icon;
	private long price;
	private boolean isBuy = false;
	private boolean isSell = false;
	private String comment = "";
	private String text;

	private long buyPrice;
	private int buyNum;
	private int saleNum;
	private long buyTime=0;

	public long getBuyTime() {
		return buyTime;
	}

	public void setBuyTime(long buyTime) {
		this.buyTime = buyTime;
	}

	public int getSaleNum() {
		return saleNum;
	}

	public void setSaleNum(int saleNum) {
		this.saleNum = saleNum;
	}

	public int getBuyNum() {
		return buyNum;
	}

	public void setBuyNum(int buyNum) {
		this.buyNum = buyNum;
	}

	public long getBuyPrice() {
		return buyPrice;
	}

	public void setBuyPrice(long buyPrice) {
		this.buyPrice = buyPrice;
	}

	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getIcon(){
		return icon;
	}
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
	
	public void setImage(String icon) {
		this.icon = icon;
	}
	public long getPrice() {
		return price;
	}
	public void setPrice(long price) {
		this.price = price;
	}

	public void setIsBuy(boolean isBuy) {
		this.isBuy = isBuy;
	}
	public boolean getIsBuy(){return isBuy;}
	public void setIsSell(boolean isSell) {
		this.isSell = isSell;
	}
	public boolean getIsSell(){return isSell;}
	
}
