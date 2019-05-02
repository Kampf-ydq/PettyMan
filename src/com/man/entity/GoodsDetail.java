package com.man.entity;

public class GoodsDetail {
	
	private String goodsDetailId;
	private int firstImg;
	private int secondImg;
	private int thirdImg;
	private int fourthImg;
	private String score;
	private String goodsIntro;
	public String getGoodsDetailId() {
		return goodsDetailId;
	}
	public void setGoodsDetailId(String goodsDetailId) {
		this.goodsDetailId = goodsDetailId;
	}
	public int getFirstImg() {
		return firstImg;
	}
	public void setFirstImg(int firstImg) {
		this.firstImg = firstImg;
	}
	public int getSecondImg() {
		return secondImg;
	}
	public void setSecondImg(int secondImg) {
		this.secondImg = secondImg;
	}
	public int getThirdImg() {
		return thirdImg;
	}
	public void setThirdImg(int thirdImg) {
		this.thirdImg = thirdImg;
	}
	public int getFourthImg() {
		return fourthImg;
	}
	public void setFourthImg(int fourthImg) {
		this.fourthImg = fourthImg;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	public String getGoodsIntro() {
		return goodsIntro;
	}
	public void setGoodsIntro(String goodsIntro) {
		this.goodsIntro = goodsIntro;
	}
	public GoodsDetail(String goodsDetailId, int firstImg, int secondImg,
			int thirdImg, int fourthImg, String score, String goodsIntro) {
		super();
		this.goodsDetailId = goodsDetailId;
		this.firstImg = firstImg;
		this.secondImg = secondImg;
		this.thirdImg = thirdImg;
		this.fourthImg = fourthImg;
		this.score = score;
		this.goodsIntro = goodsIntro;
	}
	@Override
	public String toString() {
		return "GoodsDetail [goodsDetailId=" + goodsDetailId + ", firstImg="
				+ firstImg + ", secondImg=" + secondImg + ", thirdImg="
				+ thirdImg + ", fourthImg=" + fourthImg + ", score=" + score
				+ ", goodsIntro=" + goodsIntro + "]";
	}
	
	

}
