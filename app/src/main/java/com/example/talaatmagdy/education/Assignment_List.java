package com.example.talaatmagdy.education;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by talaatmagdy on 5/23/17.
 */


public class Assignment_List extends ArrayAdapter<Assignment> {
    private Activity context;
    private List<Assignment> Assignmentlist;
/*
    public Assignment_List(  Activity context, List<Course> Assignmentlist) {
        super(context,R.layout.assignmentlist_layout,Assignmentlist);
        this.context = context;
        this.Assignmentlist = Assignmentlist;
    }
    */
    public Assignment_List( Activity context, List<Assignment> assignmentlist) {
        super(context, R.layout.assignmentlist_layout,assignmentlist);
        this.context = context;
        this.Assignmentlist = assignmentlist;
    }


    @Override
    public View getView(int position,  View convertView,  ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.assignmentlist_layout,null,true);

        TextView e_id=(TextView) listViewItem.findViewById(R.id.textview_id_assignment);
        TextView e_name=(TextView) listViewItem.findViewById(R.id.textview_name_assignment);
        TextView e_dec=(TextView) listViewItem.findViewById(R.id.textview_decription_assignment);

        Assignment assignment = Assignmentlist.get(position);

        e_id.setText(assignment.getId_assignment());
        e_name.setText(assignment.getNameassignment());
        e_dec.setText(assignment.getDeadline());

        return listViewItem;

    }
}
