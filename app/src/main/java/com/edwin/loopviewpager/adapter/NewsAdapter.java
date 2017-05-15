package com.edwin.loopviewpager.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhang . DATA: 2017/5/9 . Description :
 */

public class NewsAdapter extends PagerAdapter {

    private Context mContext;

    private List<String> mList;

    private List<ImageView> viewList;

    public NewsAdapter(Context mContext, List<String> mList) {
        this.mContext = mContext;
        this.mList = mList;
        viewList = new ArrayList<>();
        for (int i = 0; i < mList.size(); i++) {
            ImageView image = new ImageView(mContext);
            RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            image.setLayoutParams(lp);
            image.setScaleType(ImageView.ScaleType.CENTER_CROP);
            viewList.add(image);
        }
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(viewList.get(position));
        Glide.with(mContext).load(mList.get(position)).into(viewList.get(position));
        return viewList.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(viewList.get(position));
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
