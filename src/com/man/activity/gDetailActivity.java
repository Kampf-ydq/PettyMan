package com.man.activity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.Utils.CarouselShow;
import com.example.eat.R;
import com.man.dao.GoodsDetailDao;
import com.man.entity.GoodsDetail;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/*
 * 商品详情
 */
public class gDetailActivity extends Activity {

	  private ViewPager ViewPage_Detail;
	  private LinearLayout point_detail;
	  private Context context;
	  private View view;
	  //轮播图图片资源
	  private ArrayList<ImageView> viewpage_imageList;
	  //判断是否自动滚动ViewPage
	  private boolean isRunning = true;
	  
	  
	  private TextView score;
	  private TextView goodsIntr;
	  private TextView subOrder;
	  
	  private String goodsId;
	  private String goodsName;
	  private double goodsPrice;
	  private int goodsStore;
	  private String stuId;
	 
	  @Override
	  protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    
	    requestWindowFeature(Window.FEATURE_NO_TITLE);
	    
	    setContentView(R.layout.carousel);
	    
	    //初始化控件
	    ViewPage_Detail = (ViewPager)findViewById(R.id.ViewPage_Detail);
	    point_detail = (LinearLayout)findViewById(R.id.point_detail);
	    score = (TextView) findViewById(R.id.score);
	    goodsIntr = (TextView) findViewById(R.id.goods_intro);
	    subOrder = (TextView) findViewById(R.id.submit_order);
	    
	    //获取商品编号
	    Bundle bundle = getIntent().getExtras();
	    
	    goodsId = bundle.getString("goodsId");
	    goodsName = bundle.getString("goods_name");
	    goodsPrice = bundle.getDouble("goods_price");
	    goodsStore = bundle.getInt("goods_store");
	    
	    System.out.println("库存======"+goodsStore);
	    
	    stuId = bundle.getString("stuId");
	    Instantiation();
	    
	    
	    //提交监听事件
	    subOrder.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				//获取下单时间
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		        System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
				
				
				
				//传递订单信息
				Bundle toOrdBundle = new Bundle();
				toOrdBundle.putString("goodsId", goodsId);
				toOrdBundle.putString("goods_name", goodsName);
				toOrdBundle.putDouble("goods_price", goodsPrice);
				toOrdBundle.putInt("goods_store", goodsStore);
				toOrdBundle.putString("stuId", stuId);
				toOrdBundle.putString("order_time", df.format(new Date()));
				Intent toOrderPage = new Intent(gDetailActivity.this, OrderActivity.class);
				toOrderPage.putExtras(toOrdBundle);
				
				startActivity(toOrderPage);
				
				finish();
				
			}
		});
	 
	  }
	 
	  public void Instantiation() {
	 
	    //初始化图片资源
	    viewpage_imageList = new ArrayList<ImageView>();
	    
	    //从数据库取出数据
	    GoodsDetailDao goodsDetailDao = new GoodsDetailDao(this);
	    GoodsDetail goodsDetail = goodsDetailDao.find(goodsId);
	    if (goodsDetail == null) {
			System.out.println("goodsDetail为空值");
		}
	    
	    int[] viewpage_images = {goodsDetail.getFirstImg(),goodsDetail.getSecondImg(),goodsDetail.getThirdImg(),goodsDetail.getFourthImg()};
	    //int[] viewpage_images = {R.drawable.codefirst, R.drawable.codetwo, R.drawable.codethree};
	    
	    for (int i : viewpage_images) {
	      // 初始化图片资源
	      ImageView imageView = new ImageView(this);
	      imageView.setBackgroundResource(i);
	      viewpage_imageList.add(imageView);
	 
	      // 添加指示小点
	      ImageView point = new ImageView(this);
	      LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(15,
	          15);
	      params.rightMargin = 10;
	      params.bottomMargin = 15;
	      point.setLayoutParams(params);
	      point.setBackgroundResource(R.drawable.light_green_point);
	      if (i == viewpage_images[1]) {
	        //默认聚焦在第一张
	        point.setBackgroundResource(R.drawable.green_point);
	        point.setEnabled(true);
	      } else {
	        point.setEnabled(false);
	      }
	 
	      point_detail.addView(point);
	 
	    }
	 
	    //首页轮播
	    CarouselShow carouselShow = new CarouselShow(context, viewpage_imageList);
	    carouselShow.CarouselShow_Info_Detail(this);
	    handler.sendEmptyMessageDelayed(0, 3000);
	    
	    //文字介绍
	    score.setText(goodsDetail.getScore());
	    goodsIntr.setText(goodsDetail.getGoodsIntro());
	 
	  }
	 
	  private Handler handler = new Handler() {
	    public void handleMessage(Message msg) {
	      // 执行滑动到下一个页面
	      ViewPage_Detail.setCurrentItem(ViewPage_Detail.getCurrentItem() + 1);
	      if (isRunning) {
	        // 在发一个handler延时
	        handler.sendEmptyMessageDelayed(0, 3000);
	      }
	    }
	  };
	 
	 
	  @Override
	  public void onDestroy() {
	    // 停止滚动
	    isRunning = false;
	    super.onDestroy();
	  }
	
}
