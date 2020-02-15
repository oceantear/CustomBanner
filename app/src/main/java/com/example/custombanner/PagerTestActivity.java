package com.example.custombanner;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.viewpager.widget.ViewPager;

import com.rd.PageIndicatorView;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PagerTestActivity extends AppCompatActivity {

    private List<Integer> colorsRes = Arrays.asList(R.color.colorPrimary, R.color.orange, R.color.orange2, R.color.darkorange, R.color.red, R.color.blue, R.color.cyan);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pager_test);
        init();
    }

    private void init() {


        final ImageView img = findViewById(R.id.img);
        /**  Horizontal ViewPager*/
        ViewPager vpHorizontalStack = findViewById(R.id.vp_horizontal_stack);
        vpHorizontalStack.setOffscreenPageLimit(3);
        vpHorizontalStack.setPageMargin(20);
        CommonPagerAdapter<Integer> horizontalStackAdapter = new CommonPagerAdapter<Integer>(colorsRes) {
            @Override
            public void renderItemView(@NonNull View itemView, final int position) {
                TextView page = itemView.findViewById(R.id.tv_page_index);
                page.setText("Page :"+ (position + 1));
                CardView innerCard = itemView.findViewById(R.id.inner_cardVw);
                ImageView upperImg = itemView.findViewById(R.id.upImg);
                innerCard.setCardBackgroundColor(getResources().getColor(colorsRes.get(position)));
                if(position % 2 == 0)
                    upperImg.setImageResource(R.drawable.cat);
                else
                    upperImg.setImageResource(R.drawable.dog);

            }

            @NonNull
            @Override
            public View getPageItemView(@NonNull ViewGroup container, int position) {
                View view = LayoutInflater.from(PagerTestActivity.this).inflate(R.layout.item_vp_test, container, false);
                view.setTag(String.valueOf(position));
                return view;
            }
        };
        horizontalStackAdapter.setOnItemClickListener(new CommonPagerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(PagerTestActivity.this, "click page :" + (position + 1) ,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onImageClick(View view, int position) {
                Toast.makeText(PagerTestActivity.this, "click Button :" + (position + 1) ,Toast.LENGTH_LONG).show();
            }


        });



        vpHorizontalStack.setPageTransformer(false, new ScalePageTransformer(vpHorizontalStack));
        vpHorizontalStack.setAdapter(horizontalStackAdapter);

        final PageIndicatorView pageIndicatorView = findViewById(R.id.pageIndicatorView);
        pageIndicatorView.setCount(5); // specify total count of indicators
        pageIndicatorView.setSelection(2);
        pageIndicatorView.setSelectedColor(getResources().getColor(R.color.color_red));
        pageIndicatorView.setUnselectedColor(getResources().getColor(R.color.bg_lunch_selected));

        vpHorizontalStack.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                pageIndicatorView.setSelection(position);
                if(position % 2 == 0)
                    img.setImageResource(R.drawable.cat);
                else
                    img.setImageResource(R.drawable.dog);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    @SuppressWarnings("unchecked")
    private <T> List<T> deepCopyList(List<T> src) {
        List<T> dest = new ArrayList<>();
        try {
            ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(byteOut);
            out.writeObject(src);
            ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
            ObjectInputStream in = new ObjectInputStream(byteIn);
            dest = (List<T>) in.readObject();
        } catch (Exception e) {
            Log.e(e.getMessage(), e.toString());
        }
        return dest;
    }
}
