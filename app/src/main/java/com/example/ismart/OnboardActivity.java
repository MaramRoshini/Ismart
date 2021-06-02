package com.example.ismart;

/**  DEVELOPED BY STARDATE TEAM
 *
 * @allrights reserved
 *
 *  */

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.TimerTask;

public class OnboardActivity extends AppCompatActivity {



    private LinearLayout pager_indicator;
    private int dotsCount;
    private ImageView[] dots;



    private ViewPager onboard_pager;

    private OnBoard_Adapter mAdapter;

    private Button continue1;

    TimerTask timer;

    Handler handler;

    int previous_pos=0, count=1;

    ImageView im;

    ArrayList<OnBoardItem> onBoardItems=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboard);

        continue1 = findViewById(R.id.btn_get_started);
        continue1.setVisibility(View.VISIBLE);
        onboard_pager = findViewById(R.id.pager_introduction);
        pager_indicator = findViewById(R.id.viewPagerCountDots);
        loadData();
        mAdapter = new OnBoard_Adapter(this,onBoardItems);
        onboard_pager.setAdapter(mAdapter);
        previous_pos = 0;
        onboard_pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                // Change the current position intimation

                for (int i = 0; i < dotsCount; i++) {
                    dots[i].setImageDrawable(ContextCompat.getDrawable(com.example.ismart.OnboardActivity.this, R.drawable.non_selected_item_dot));
                }
                count++;
                dots[position].setImageDrawable(ContextCompat.getDrawable(com.example.ismart.OnboardActivity.this, R.drawable.selected_item_dot));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }

        });

        continue1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(count>=3) {
                    startActivity(new Intent(com.example.ismart.OnboardActivity.this, sign_up.class));
                    count=1;
                }else
                       onboard_pager.setCurrentItem(count);
            }
        });

        setUiPageViewController();

    }

    // Load data into the viewpager

    public void loadData()
    {

        int[] header = {R.string.ob_header1, R.string.ob_header2, R.string.ob_header3};
        int[] desc = {R.string.ob_desc1, R.string.ob_desc2, R.string.ob_desc3};
        int[] imageId = {R.drawable.ic_fuel_indication, R.drawable.ic_live_tracking, R.drawable.ic_voice_assist};


        for(int i=0;i<imageId.length;i++)
        {
            OnBoardItem item=new OnBoardItem();
            item.setImageID(imageId[i]);
//            if(i==0) {
//                im.getLayoutParams().height = 210;
//                im.getLayoutParams().width = 200;
//            }
//            if(i==1){
//                im.getLayoutParams().width = 210;
//                im.getLayoutParams().height =200;
//            }
//            if(i==3){
//                im.getLayoutParams().width = 200;
//                im.getLayoutParams().height =210;
//            }
            item.setTitle(getResources().getString(header[i]));
            item.setDescription(getResources().getString(desc[i]));
            onBoardItems.add(item);
        }
    }


    // setup the ui page view
    private void setUiPageViewController() {

        dotsCount = mAdapter.getCount();
        dots = new ImageView[dotsCount];

        for (int i = 0; i < dotsCount; i++) {
            dots[i] = new ImageView(this);
            dots[i].setImageDrawable(ContextCompat.getDrawable(com.example.ismart.OnboardActivity.this, R.drawable.non_selected_item_dot));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );

            params.setMargins(6, 0, 6, 0);

            pager_indicator.addView(dots[i], params);
        }

        dots[0].setImageDrawable(ContextCompat.getDrawable(com.example.ismart.OnboardActivity.this, R.drawable.selected_item_dot));
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();

    }
}

