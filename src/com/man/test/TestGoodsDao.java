package com.man.test;

import java.util.List;

import android.test.AndroidTestCase;

import com.example.eat.R;
import com.man.dao.GoodsDao;
import com.man.entity.Goods;

public class TestGoodsDao extends AndroidTestCase{
	
	public void testAdd(){
		GoodsDao goodsDao = new GoodsDao(getContext());
		Goods goods = new Goods(null, "Æ½µ×¹ø", goodsDao.bitmapToBytes(getContext(), R.drawable.pan), 20.5, 300);
		goodsDao.insert(goods);

	}
	
	public void testUpdate(){
		GoodsDao goodsDao = new GoodsDao(getContext());
		goodsDao.update(50, "1");
	}
	
	public void testDelete(){
		GoodsDao goodsDao = new GoodsDao(getContext());
		goodsDao.delete("1");
	}
	
	public void testFind(){
		GoodsDao goodsDao = new GoodsDao(getContext());
		Goods goods = goodsDao.find("3");
		
		if (goods != null) {
			System.out.println(goods.toString());	
		}
		
	}
	
	public void testGetAll(){
		GoodsDao goodsDao = new GoodsDao(getContext());
		List<Goods> dbList = goodsDao.getAll();
		System.out.println("´óÐ¡: "+ dbList.size());
	}

}
