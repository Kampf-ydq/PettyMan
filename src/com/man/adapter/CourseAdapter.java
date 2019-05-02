package com.man.adapter;

import java.util.List;

import com.example.eat.R;
import com.man.dao.CourseDao;
import com.man.entity.Course;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CourseAdapter extends ArrayAdapter<Course> {
	private int resourceId;

	public CourseAdapter(Context context, int textViewResourceId,
			List<Course> objects) {
		super(context, textViewResourceId, objects);
		resourceId = textViewResourceId;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Course course = getItem(position);//获取Course实例
		View view;
		ViewHolder viewHolder;
		if (convertView == null) {
			view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
			
			viewHolder = new ViewHolder();
			viewHolder.cimage = (ImageView) view.findViewById(R.id.course_imag);
			viewHolder.cname = (TextView) view.findViewById(R.id.cour_name);
			
			view.setTag(viewHolder);
		}else {
			view = convertView;
			viewHolder = (ViewHolder) view.getTag();
		}
		
		//显示
		CourseDao courseDao = new CourseDao(getContext());
		viewHolder.cimage.setImageBitmap(courseDao.chageToBitmap(course.getCourImg()));
		viewHolder.cname.setText(course.getCourName());
		return view;
	}
	
	class ViewHolder{
		TextView cname;
		ImageView cimage;
	}

}
