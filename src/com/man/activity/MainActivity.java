package com.man.activity;

import java.util.Timer;
import java.util.TimerTask;

import com.example.eat.R;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;

/*
 * 定时广告页
 */
public class MainActivity extends Activity {
	
	private Button skipBtn;
	Timer timer = new Timer();
	
	private int recLen = 5;//倒计时5s
	private Handler handler;
	private Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        //去掉标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //设置全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        
        setContentView(R.layout.activity_main);
        
        initView();
        
        timer.schedule(task, 1000, 1000);//停顿1秒
        
        //不点击跳过
        handler = new Handler();
        handler.postDelayed(runnable = new Runnable() {
			
			@Override
			public void run() {
				//从闪屏到主页面
				Intent intent = new Intent(MainActivity.this, LoginActivity.class);
				startActivity(intent);
				finish();
			}
		},5000);//延迟3秒后发生handler信息
    }
    
    private void initView(){
    	skipBtn = (Button)findViewById(R.id.skip);
    	skipBtn.setOnClickListener(shipListener);
    }
    
    OnClickListener shipListener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			//从闪屏界面跳转到首界面
			Intent intent = new Intent(MainActivity.this, LoginActivity.class);
			startActivity(intent);
			finish();
            if (runnable != null) {
                handler.removeCallbacks(runnable);
            }
		}
	};
    
    TimerTask task = new TimerTask() {
        @Override
        public void run() {
            runOnUiThread(new Runnable() { // UI thread
                @Override
                public void run() {
                    recLen--;
                    skipBtn.setText("跳过 " + recLen);
                    if (recLen < 0) {
                        timer.cancel();
                        skipBtn.setVisibility(View.GONE);//倒计时到0隐藏字体
                    }
                }
            });
        }
    };

    //点击跳过
    
    
}
