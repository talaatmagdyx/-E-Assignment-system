package com.example.talaatmagdy.education;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Main1 extends AppCompatActivity {

    private ViewPager viewPager;
    private Intromanger intromanger;
    private int [] layouts;

    private ViewPagerAdapter viewPagerAdapter;

    private TextView [] dots;
    private LinearLayout dotsLayout;

    Button next,skip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);

        intromanger = new Intromanger(this);
        if(!intromanger.check())
        {
            intromanger.setfirst(false);
            Intent intent = new Intent(Main1.this,Main2.class);
            startActivity(intent);
            finish();
        }

        if(Build.VERSION.SDK_INT>=21)
        {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE|View.SYSTEM_UI_FLAG_FULLSCREEN);
        }

        viewPager = (ViewPager)findViewById(R.id.view_paper);
        dotsLayout=(LinearLayout)findViewById(R.id.layoutDots);
        skip=(Button)findViewById(R.id.btn_skip);
        next=(Button)findViewById(R.id.btn_next);

        layouts = new int[] {R.layout.activity_screen1,R.layout.activity_screen2,
                R.layout.activity_screen3,R.layout.activity_screen4,R.layout.activity_screen5};


        addBottomDots(0);
        changeStatusBarColor();
        viewPagerAdapter = new ViewPagerAdapter();
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.addOnPageChangeListener(viewListener);

        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main1.this,Main2.class);
                startActivity(intent);
                finish();

            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int curent = getItem(+1);
                if(curent<layouts.length)
                {
                    viewPager.setCurrentItem(curent);
                }
                else
                {
                    Intent intent = new Intent(Main1.this,Main2.class);
                    startActivity(intent);
                    finish();
                }

            }
        });


    }






    private void addBottomDots(int postion)
    {

        dots = new TextView[layouts.length];
        int [] colorActive=getResources().getIntArray(R.array.dot_active);
        int [] colorInactive=getResources().getIntArray(R.array.dot_inactive);
        dotsLayout.removeAllViews();
        for(int i=0; i<dots.length;i++)
        {

            dots[i]=new TextView(this);
          //  dots[i].setText(Html.fromHtml("&m8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(colorInactive[postion]);
            dotsLayout.addView(dots[i]);
        }
        if(dots.length>0)
        {
            dots[postion].setTextColor(colorActive[postion]);
        }

    }


    private int getItem(int i)
    {
        return viewPager.getCurrentItem()+i;
    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener()
    {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addBottomDots(position);
            if(position==layouts.length-1)
            {
                next.setText("PROCEED");
                skip.setVisibility(View.GONE);
            }
            else
            {
                next.setText("NEXT");
                skip.setVisibility(View.VISIBLE);
            }

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };



    public void changeStatusBarColor()
    {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);


        }
    }




   public class ViewPagerAdapter extends PagerAdapter {
        private LayoutInflater layoutInflater;

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            layoutInflater=(LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = layoutInflater.inflate(layouts[position],container,false);
            container.addView(view);

            return view;
        }

        @Override
        public int getCount() {
            return layouts.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

       @Override
       public void destroyItem(ViewGroup container, int position, Object object) {
           View view = (View)object;
           container.removeView(view);
       }
   }

}
