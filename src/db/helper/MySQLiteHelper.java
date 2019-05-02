package db.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class MySQLiteHelper extends SQLiteOpenHelper {
	
	private static final String DB_NAME = "little_man.db";
	private static final int version = 2;
	
	private Context mContext = null;//传入上下文
	
	
	//User表
	public static final String CREATE_USER = "create table t_user ("
			+ "userId integer primary key autoincrement, "
			+ "account text not null, "
			+ "password text not null, "
			+ "school text, "
			+ "nickName text, "
			+ "userName text, "
			+ "telPhone text, "
			+ "address text, "
			+ "money real)";
	
	//Goods表
	public static final String CREATE_GOODS = "create table t_goods ("
			+ "goodsId integer primary key autoincrement, "
			+ "goodsName text, "
			+ "goodsImage blob, "
			+ "price real, "
			+ "storeNum integer)";
	
	//Course表
	public static final String CREATE_COURSE = "create table t_course ("
			+ "courId integer primary key autoincrement, "
			+ "courName text, "
			+ "courImg blob, "
			+ "classRoom text, "
			+ "classNum text, "
			+ "teacher text)";
	
	
	//GoodsDetail表
	public static final String CREATE_GOODS_DETAIL = "create table t_goods_detail ("
			+ "goodsDetailId integer primary key autoincrement, "
			+ "firstImg integer, "
			+ "secondImg integer, "
			+ "thirdImg integer, "
			+ "fourthImg integer, "
			+ "score text, "
			+ "goodsIntro text)";
	
	public MySQLiteHelper(Context context) {
		super(context, DB_NAME, null, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_USER);
		db.execSQL(CREATE_GOODS);
		db.execSQL(CREATE_COURSE);
		db.execSQL(CREATE_GOODS_DETAIL);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("drop table if exists t_user");
		db.execSQL("drop table if exists t_goods");
		db.execSQL("drop table if exists t_course");
		db.execSQL("drop table if exists t_goods_detail");
		onCreate(db);
	}
}
