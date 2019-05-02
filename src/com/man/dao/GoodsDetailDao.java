package com.man.dao;

import java.util.ArrayList;
import java.util.List;

import com.man.entity.GoodsDetail;

import db.helper.MySQLiteHelper;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class GoodsDetailDao {
	
	MySQLiteHelper helper;
	
	public GoodsDetailDao(Context context){
		helper = new MySQLiteHelper(context);
	}
	
	public void insert(GoodsDetail goodsDetail){
		SQLiteDatabase db = helper.getWritableDatabase();
		db.execSQL("insert into t_goods_detail values (null,?,?,?,?,?,?)", new Object[]{
				goodsDetail.getFirstImg(),goodsDetail.getSecondImg(),
				goodsDetail.getThirdImg(),goodsDetail.getFourthImg(),goodsDetail.getScore(),goodsDetail.getGoodsIntro()
		});
	}
	
	public List<GoodsDetail> getAll(){
		SQLiteDatabase db = helper.getReadableDatabase();
		Cursor cursor = db.rawQuery("select * from t_goods_detail", null);
		
		List<GoodsDetail> gDetailList = new ArrayList<GoodsDetail>();
		GoodsDetail goodsDetail;
		while (cursor.moveToNext()) {
			String gDeId = cursor.getString(cursor.getColumnIndex("goodsDetailId"));
			int firImg = cursor.getInt(cursor.getColumnIndex("firstImg"));
			int secImg = cursor.getInt(cursor.getColumnIndex("secondImg"));
			int thirImg = cursor.getInt(cursor.getColumnIndex("thirdImg"));
			int fourImg = cursor.getInt(cursor.getColumnIndex("fourthImg"));
			String score = cursor.getString(cursor.getColumnIndex("score"));
			String gIntr = cursor.getString(cursor.getColumnIndex("goodsIntro"));
			
			goodsDetail = new GoodsDetail(gDeId, firImg, secImg, thirImg, fourImg, score, gIntr);
			gDetailList.add(goodsDetail);
		}
		return gDetailList;
	}
	
	public GoodsDetail find(String id){
		SQLiteDatabase db = helper.getReadableDatabase();
		Cursor cursor = db.rawQuery("select * from t_goods_detail where goodsDetailId=?", new String[]{id});
	
		GoodsDetail goodsDetail = null;
		if (cursor.moveToNext()) {
			String gDeId = cursor.getString(cursor.getColumnIndex("goodsDetailId"));
			int firImg = cursor.getInt(cursor.getColumnIndex("firstImg"));
			int secImg = cursor.getInt(cursor.getColumnIndex("secondImg"));
			int thirImg = cursor.getInt(cursor.getColumnIndex("thirdImg"));
			int fourImg = cursor.getInt(cursor.getColumnIndex("fourthImg"));
			String score = cursor.getString(cursor.getColumnIndex("score"));
			String gIntr = cursor.getString(cursor.getColumnIndex("goodsIntro"));
			
			goodsDetail = new GoodsDetail(gDeId, firImg, secImg, thirImg, fourImg, score, gIntr);
		}
		return goodsDetail;
	}
	
	


}
