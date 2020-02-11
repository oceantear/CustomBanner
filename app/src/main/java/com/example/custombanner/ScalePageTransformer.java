package com.example.custombanner;

import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;
import androidx.viewpager.widget.ViewPager;


/**
 * 层叠式ViewPager PageTransformer
 * Created by cy on 2018/4/11.
 */

public class ScalePageTransformer implements ViewPager.PageTransformer {
    private static final float CENTER_PAGE_SCALE =1f;
    private static final float OTHER_PAGE_SCALE = 0.8f;
    private static final float OTHER_PAGE_ALPHA = 0.4f;
    private int offscreenPageLimit;
    private ViewPager boundViewPager;
    private boolean isAlpha = true;

    public ScalePageTransformer(@NonNull ViewPager boundViewPager) {
        this.boundViewPager = boundViewPager;
        this.offscreenPageLimit = boundViewPager.getOffscreenPageLimit();
    }

    @Override
    public void transformPage(@NonNull View view, float position) {
        int pagerWidth = boundViewPager.getWidth();
        float horizontalOffsetBase = (pagerWidth - pagerWidth * CENTER_PAGE_SCALE) / 2 / offscreenPageLimit;// + DisplayUtil.dp2px(15);
        float my_horizontalOffsetBase = (pagerWidth - pagerWidth * CENTER_PAGE_SCALE) /2 ;
        Log.e("jimmy","== position :"+position+ " ==");
        //Log.i("jimmy","offscreenPageLimit : "+offscreenPageLimit);//3
        Log.i("jimmy","pagerWidth : "+pagerWidth);//1080
        Log.i("jimmy","horizontalOffsetBase : "+horizontalOffsetBase);
        //Log.i("jimmy","view.getWidth() : "+view.getWidth());//1080
        float scaleFactor = (position < 0)
                ? ((1 - OTHER_PAGE_SCALE) * position + 1)
                : ((OTHER_PAGE_SCALE - 1) * position + 1);

        float alpha  = (position < 0)
                ? ((1 - OTHER_PAGE_ALPHA) * position + 1)
                : ((OTHER_PAGE_ALPHA - 1) * position + 1);


        //view.setScaleX();
        view.setScaleY(scaleFactor);
        if(isAlpha) view.setAlpha(alpha);

        // test code: view初始化时，设置了tag
        String tag = (String) view.getTag();
        //Log.e("viewTag" + tag, "viewTag: " + (String) view.getTag() + " --- transformerPosition: " + position + " --- floor: " + Math.floor(position) + " --- childCount: "+ boundViewPager.getChildCount());
        ViewCompat.setElevation(view, (offscreenPageLimit - position) * 5);
    }

}
