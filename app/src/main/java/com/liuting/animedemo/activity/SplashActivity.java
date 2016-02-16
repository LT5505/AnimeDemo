package com.liuting.animedemo.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.liuting.animedemo.R;
import com.liuting.animedemo.adaper.SplashPagerAdapter;
import com.liuting.animedemo.utils.ImageUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuting on 2016/2/16.
 */
public class SplashActivity extends Activity implements ViewPager.OnPageChangeListener{
    private ViewPager mViewPager;
    private ImageView[] listImages;
    private List<View> listViews;
    private View view1,view2,view3,view4;
    private ImageUtil mImageUtil;
    private SplashPagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initView();
    }

    public void initView(){
        mImageUtil=new ImageUtil();
        listViews = new ArrayList<View>();
        view1 = LayoutInflater.from(SplashActivity.this).inflate(R.layout.layout_common_pager, null);
        LinearLayout mLly1=(LinearLayout)view1.findViewById(R.id.pager_ll_bg);
        Bitmap pho1=mImageUtil.readBitMap(this, R.drawable.bg_splash_01);
        Drawable drawable = new BitmapDrawable(pho1);
        mLly1.setBackgroundDrawable(drawable);

        view2 = LayoutInflater.from(SplashActivity.this).inflate(R.layout.layout_common_pager, null);
        LinearLayout mLly2=(LinearLayout)view2.findViewById(R.id.pager_ll_bg);
        Bitmap pho2=mImageUtil.readBitMap(this, R.drawable.bg_splash_02);
        Drawable drawable2 = new BitmapDrawable(pho2);
        mLly2.setBackgroundDrawable(drawable2);

        view3 = LayoutInflater.from(SplashActivity.this).inflate(R.layout.layout_common_pager, null);
        LinearLayout mLly3=(LinearLayout)view3.findViewById(R.id.pager_ll_bg);
        Bitmap pho3=mImageUtil.readBitMap(this, R.drawable.bg_splash_03);
        Drawable drawable3 = new BitmapDrawable(pho3);
        mLly3.setBackgroundDrawable(drawable3);

        view4 = LayoutInflater.from(SplashActivity.this).inflate(R.layout.layout_last_pager, null);
        RelativeLayout mLly4=(RelativeLayout)view4.findViewById(R.id.pager_rly_open);
        Bitmap pho4=mImageUtil.readBitMap(this, R.drawable.bg_splash_04);
        Drawable drawable4 = new BitmapDrawable(pho4);
        mLly4.setBackgroundDrawable(drawable4);
        Button btn=(Button)view4.findViewById(R.id.pager_btn_open);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SplashActivity.this,MainActivity.class);
                startActivity(intent);
                SplashActivity.this.finish();
            }
        });

        listViews.add(view1);
        listViews.add(view2);
        listViews.add(view3);
        listViews.add(view4);

        mPagerAdapter = new SplashPagerAdapter(listViews);
        ViewGroup group = (ViewGroup) findViewById(R.id.splash_vg_main);
        mViewPager = (ViewPager) findViewById(R.id.splash_vp_main);
        mViewPager.setAdapter(mPagerAdapter);

        //将点点加入到ViewGroup中
        listImages = new ImageView[listViews.size()];
        for (int i = 0; i < listImages.length; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(10, 10));
            listImages[i] = imageView;
            if (i == 0) {
                listImages[i].setBackgroundResource(R.drawable.page_indicator_focused);
            } else {
                listImages[i].setBackgroundResource(R.drawable.page_indicator_unfocused);
            }

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(new ViewGroup.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            layoutParams.leftMargin = 5;
            layoutParams.rightMargin = 5;
            layoutParams.bottomMargin =10;
            group.addView(imageView, layoutParams);
        }
        //设置监听，主要是设置点点的背景
        mViewPager.setOnPageChangeListener(this);
        //设置ViewPager的默认项, 设置为长度的100倍，这样子开始就能往左滑动
        mViewPager.setCurrentItem(0);
    }
    @Override
    public void onPageScrollStateChanged(int arg0) {
    }

    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2) {

    }

    @Override
    public void onPageSelected(int arg0) {
        setImageBackground(arg0 % listViews.size());
    }

    /**
     * 设置选中的pager的背景
     *
     * @param selectItems
     */
    private void setImageBackground(int selectItems) {
        for (int i = 0; i < listImages.length; i++) {
            if (i == selectItems) {
                listImages[i].setBackgroundResource(R.drawable.page_indicator_focused);
            } else {
                listImages[i].setBackgroundResource(R.drawable.page_indicator_unfocused);
            }
        }
    }
}
