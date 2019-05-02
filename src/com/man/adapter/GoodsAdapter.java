package com.man.adapter;

import java.util.List;

import com.example.eat.R;
import com.man.dao.GoodsDao;
import com.man.entity.Goods;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class GoodsAdapter extends ArrayAdapter<Goods> {
	private int resourceId;
	
	public GoodsAdapter(Context context, int textViewResourceId,
			List<Goods> objects) {
		super(context, textViewResourceId, objects);
		resourceId = textViewResourceId;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Goods goods = getItem(position);//获取当前项的Goods实例
		View view;
		ViewHolder viewHolder;
		
		//利用convertView缓存布局，提高运行效率
		if (convertView == null) {
			view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
			
			//初始化控件
			viewHolder = new ViewHolder();
			viewHolder.goodsImage = (ImageView) view.findViewById(R.id.goods_image);
			viewHolder.goodsName = (TextView) view.findViewById(R.id.goods_name);
			viewHolder.price = (TextView)view.findViewById(R.id.goods_price);
			viewHolder.storeNum = (TextView)view.findViewById(R.id.store_number);
			
			//将viewHolder存储在view中
			view.setTag(viewHolder);
		}else {
			view = convertView;//重用
			viewHolder = (ViewHolder)view.getTag();//重新获取ViewHolder
		}
		
		//设置相关信息
		GoodsDao goodsDao = new GoodsDao(getContext());
		viewHolder.goodsImage.setImageBitmap(goodsDao.chageToBitmap(goods.getGoodsImage()));//将位图显示为图片
		viewHolder.goodsName.setText(goods.getGoodsName());
		
		//需利用String.valueOf（）将double转为String
		viewHolder.price.setText(String.valueOf(goods.getPrice()));
		viewHolder.storeNum.setText(String.valueOf(goods.getStoreNum()));
		return view;
	}
	
	//对实例控件进行缓存
		class ViewHolder{
			
			ImageView goodsImage;
			
			TextView goodsName;
			
			TextView price;
			
			TextView storeNum;
		}



}
