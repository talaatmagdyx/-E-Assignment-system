package com.example.talaatmagdy.education;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

/**
 * Created by talaatmagdy on 5/22/17.
 */

public class Course_List extends ArrayAdapter<Course> {
    private Activity context;
    private List<Course> courselist;

    public Course_List(  Activity context, List<Course> courselist) {
        super(context,R.layout.courselist_layout,courselist);
        this.context = context;
        this.courselist = courselist;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.courselist_layout,null,true);

        TextView e_id=(TextView) listViewItem.findViewById(R.id.textview_id_course);
        TextView e_name=(TextView) listViewItem.findViewById(R.id.textview_name_course);
        TextView e_number=(TextView) listViewItem.findViewById(R.id.textview_number_course);

        Course course = courselist.get(position);

        e_id.setText(course.getCourse_id());
        e_name.setText(course.getCourse_name());
        e_number.setText(course.getCourse_number());

        return listViewItem;

    }
}
