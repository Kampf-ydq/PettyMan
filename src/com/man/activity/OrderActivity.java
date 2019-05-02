package com.man.activity;

import java.util.List;

import com.Utils.GetOrderId;
import com.Utils.PayWayDialog;
import com.example.eat.R;
import com.man.dao.GoodsDao;
import com.man.dao.UserDao;
import com.man.entity.User;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/*
 * 订单管理
 */
public class OrderActivity extends Activity {
	
	private TextView goodsName;
	private TextView name;
	private TextView tel;
	private TextView add;
	private TextView orderNum;
	private TextView orderTime;
	
	private String stuId;
	private String goodsId;
	
	private TextView pay;
	
	private double smMoney;//用户余额
	private double goodsMoney;//商品价格（/每件）
	private int goodsStroe;//商品库存
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.order);
		
		//接收数据
		Bundle bundle = getIntent().getExtras();
		
		String ordTime = bundle.getString("order_time");
		String gname = bundle.getString("goods_name");
		stuId = bundle.getString("stuId");
		goodsId = bundle.getString("goodsId");
		goodsMoney = bundle.getDouble("goods_price");
		goodsStroe = bundle.getInt("goods_store");
		
		 System.out.println("库存======"+goodsStroe);
		
		//初始化控件
		goodsName = (TextView) findViewById(R.id.goods_name);
		name = (TextView) findViewById(R.id.user_name);
		tel = (TextView) findViewById(R.id.telephone);
		add = (TextView) findViewById(R.id.address);
		orderNum = (TextView) findViewById(R.id.order_number);
		orderTime = (TextView) findViewById(R.id.order_time);
		pay = (TextView) findViewById(R.id.pay);
		
		UserDao userDao = new UserDao(this);
		User user = userDao.find(stuId);
		smMoney = user.getMoney();
		
		//显示订单信息
		goodsName.setText(gname);
		name.setText(user.getUserName());
		tel.setText(user.getTelPhone());
		add.setText(user.getAddress());
		orderNum.setText(GetOrderId.produceOrderId());
		orderTime.setText(ordTime);
		
		pay.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//判断余额是否足够
				if (smMoney < goodsMoney) {
					Toast.makeText(OrderActivity.this, "余额不足，请去赚取学习币", 0).show();
					return;
				}
				initDialog(OrderActivity.this);
				
				//更新数据库的t_user余额值,t_goods的storeNum值
				updateDb(OrderActivity.this);
				
			}
		});
	
	}
	
	//初始化自定义Dialog
	private void initDialog(Context context){
		PayWayDialog payWayDialog = new PayWayDialog(context, R.style.recharge_pay_dialog,
		              String.valueOf(smMoney), String.valueOf(goodsMoney), new OnClickListener() {
														
						@Override
						public void onClick(View v) {
							Toast.makeText(OrderActivity.this, "本次支付"+goodsMoney+"学习币", 0).show();
							Handler handler = new Handler();
							handler.sendEmptyMessageDelayed(0, 2000);
							finish();
						}
					});
		payWayDialog.show();
	}
	
	//更新数据库
	private void updateDb(Context context){
		UserDao userDao = new UserDao(context);
		userDao.updateMoney(smMoney-goodsMoney, stuId);
		
		GoodsDao goodsDao = new GoodsDao(context);
		int gs = goodsStroe - 1;//一次规定买一件
		goodsDao.update(gs, goodsId);
	}
	

}















