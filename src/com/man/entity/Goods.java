package com.man.entity;

import java.sql.Blob;
import java.util.Arrays;

public class Goods {
	
	private String goodsId;
	private String goodsName;
	private byte[] goodsImage;
	private double price;
	private Integer storeNum;
	public String getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public byte[] getGoodsImage() {
		return goodsImage;
	}
	public void setGoodsImage(byte[] goodsImage) {
		this.goodsImage = goodsImage;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Integer getStoreNum() {
		return storeNum;
	}
	public void setStoreNum(Integer storeNum) {
		this.storeNum = storeNum;
	}
	
	
	
	public Goods() {
		super();
	}
	public Goods(String goodsId, String goodsName, byte[] goodsImage,
			double price, Integer storeNum) {
		super();
		this.goodsId = goodsId;
		this.goodsName = goodsName;
		this.goodsImage = goodsImage;
		this.price = price;
		this.storeNum = storeNum;
	}
	@Override
	public String toString() {
		return "Goods [goodsId=" + goodsId + ", goodsName=" + goodsName
				+ ", goodsImage=" + Arrays.toString(goodsImage) + ", price="
				+ price + ", storeNum=" + storeNum + "]";
	}
	
	

}
