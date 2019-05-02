package com.Utils;

import com.example.eat.R;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class PayWayDialog extends Dialog {
	
	private Context context;
	
	private LinearLayout chooseMyWallet;
	private LinearLayout chooseOtherWay;
	
	private TextView balance;
	private ImageView myWalletCb;
	private ImageView otherWayCb;
	private TextView needPayMoney;
	private TextView ConfirmPay;
	
	private String inBalance;//外界给出的余额
	private String inPayMoney;//外界给出的需付金额
	
	private View.OnClickListener listener;
    
  
    public PayWayDialog(Context context, int theme,String inBalance,String inPayMoney, 
    		            View.OnClickListener listener) {
		super(context, theme);
		this.context = context;
		this.inBalance = inBalance;
		this.inPayMoney = inPayMoney;
		this.listener = listener;
	}
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	// TODO Auto-generated method stub
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.pay_layout);

    	//空白处不能取消动画
    	setCanceledOnTouchOutside(false);
    	
    	//初始化控件
    	chooseMyWallet = (LinearLayout) findViewById(R.id.dialog_my_wallet);
    	chooseOtherWay = (LinearLayout) findViewById(R.id.other_pay);
    	
		balance = (TextView) findViewById(R.id.balance);
		ConfirmPay = (TextView) findViewById(R.id.dialog_confirm_pay);
		myWalletCb = (ImageView) findViewById(R.id.recharge_wallet_cb);
		needPayMoney = (TextView) findViewById(R.id.recharge_num);
		otherWayCb = (ImageView) findViewById(R.id.other_way_cb);
		
		//显示初始数据
		balance.setText(inBalance);
		needPayMoney.setText(inPayMoney);
		
		Window dialogWindow = getWindow();
        dialogWindow.setGravity(Gravity.BOTTOM);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = AbsListView.LayoutParams.MATCH_PARENT;
        lp.y = 0;//设置Dialog距离底部的距离
        dialogWindow.setAttributes(lp);
		
        chooseMyWallet.setOnClickListener(chooseListener);
        chooseOtherWay.setOnClickListener(chooseListener);
        
		ConfirmPay.setOnClickListener(listener);
    }
    

	//改变选中
    android.view.View.OnClickListener chooseListener = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			//我的钱包打钩
			if (v.getId() == R.id.dialog_my_wallet) {
				myWalletCb.setVisibility(View.VISIBLE);
			}
			
			//其它打钩
			if (v.getId() == R.id.other_pay) {
				otherWayCb.setVisibility(View.VISIBLE);
			}
		}
	};
	
}
