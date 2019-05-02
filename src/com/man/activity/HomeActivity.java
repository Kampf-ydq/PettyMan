package com.man.activity;

import java.util.ArrayList;
import java.util.List;

import com.example.eat.R;
import com.man.dao.GoodsDao;
import com.man.entity.Goods;
import com.man.adapter.GoodsAdapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

/*
 * 小当家应用主页
 */
public class HomeActivity extends Activity {
	
	private List<Goods> goodsList = new ArrayList<Goods>();
	private ListView listView;
	private String userAcc;
	
	private Button learn;
	private Button eat;
	private Button personal;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		setContentView(R.layout.home_layout);
		
		//拿到激活HomeActivity的意图对象
		Intent intent = getIntent();
		userAcc = intent.getStringExtra("stu_id");
		
		//System.out.println("学号： ====== "+userAcc);
		
		learn = (Button) findViewById(R.id.study_btn);
		eat = (Button) findViewById(R.id.eat_btn);
		personal = (Button) findViewById(R.id.personal_btn);
		
		//从数据库取出数据
		GoodsDao goodsDao = new GoodsDao(HomeActivity.this);
		goodsList = goodsDao.getAll();
		
		if (goodsList == null) {
			Log.e("SQLite", "数据库t_goods表无数据");
			return;
		}
		//为ListView设置控制器（适配器）
		GoodsAdapter adapter = new GoodsAdapter(HomeActivity.this, R.layout.goods_item_layout, goodsList);
		listView = (ListView) findViewById(R.id.show_goods);
		listView.setAdapter(adapter);
		
		personal.setOnClickListener(hListener);
		learn.setOnClickListener(hListener);
		
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Goods goods = goodsList.get(position);
				
				//传输数据Bundle数据包
				Bundle bundle = new Bundle();
				bundle.putString("goodsId", goods.getGoodsId());
				bundle.putString("goods_name", goods.getGoodsName());
				bundle.putDouble("goods_price", goods.getPrice());
				bundle.putInt("goods_store", goods.getStoreNum());
				bundle.putString("stuId", userAcc);
				Intent toDetailPage = new Intent(HomeActivity.this, gDetailActivity.class);
				toDetailPage.putExtras(bundle);
				
				startActivity(toDetailPage);
				
				
			}
			
		});
		
	}
	
	OnClickListener hListener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.personal_btn:
				Intent toPersonal = new Intent(HomeActivity.this, PersonalActivity.class);
				toPersonal.putExtra("stu_id", userAcc);
				startActivity(toPersonal);
				break;
				
			case R.id.study_btn:
				Intent toLearn = new Intent(HomeActivity.this, LearnAcitity.class);
				toLearn.putExtra("stu_id", userAcc);
				startActivity(toLearn);
				break;

			default:
				break;
			}
			
		}
	};
		
	
	//重新启动HomeActivity活动，利用onRestart()刷新页面数据
	protected void onRestart() {
		super.onRestart();
		GoodsDao goodsDao = new GoodsDao(HomeActivity.this);
		goodsList = goodsDao.getAll();
		
		if (goodsList == null) {
			Log.e("SQLite", "数据库t_goods表无数据");
			return;
		}
		//为ListView设置控制器（适配器）
		GoodsAdapter adapter = new GoodsAdapter(HomeActivity.this, R.layout.goods_item_layout, goodsList);
		listView = (ListView) findViewById(R.id.show_goods);
		listView.setAdapter(adapter);
	};
}
