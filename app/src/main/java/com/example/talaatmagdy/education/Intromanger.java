package com.example.talaatmagdy.education;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by talaatmagdy on 5/5/17.
 */

public class Intromanger {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context context;

    public Intromanger(Context context)
    {
        this.context=context;
        pref=context.getSharedPreferences("first",0);
        editor = pref.edit();

    }

    public void setfirst(Boolean isfirst)
    {
        editor.putBoolean("check",isfirst);
        editor.commit();
    }

    public Boolean check()
    {
        return pref.getBoolean("check",true);
    }
}
