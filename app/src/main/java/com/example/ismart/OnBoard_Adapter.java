package com.example.ismart;

/**  DEVELOPED BY ismart TEAM
 *
 * @allrights reserved
 *
 *  */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;

import java.util.ArrayList;


class OnBoard_Adapter extends PagerAdapter {

    private Context mContext;
    ArrayList<com.example.ismart.OnBoardItem> onBoardItems=new ArrayList<>();


    public OnBoard_Adapter(Context mContext, ArrayList<com.example.ismart.OnBoardItem> items) {
        this.mContext = mContext;
        this.onBoardItems = items;
    }

    @Override
    public int getCount() {
        return onBoardItems.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.on_board_iteams, container, false);

        com.example.ismart.OnBoardItem item=onBoardItems.get(position);

        ImageView imageView = itemView.findViewById(R.id.onboard_id);
        imageView.setImageResource(item.getImageID());

        TextView title= itemView.findViewById(R.id.header);
        title.setText(item.getTitle());
        TextView desc= itemView.findViewById(R.id.description);
        desc.setText(item.getDescription());
        container.addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((ConstraintLayout) object);
    }

}
